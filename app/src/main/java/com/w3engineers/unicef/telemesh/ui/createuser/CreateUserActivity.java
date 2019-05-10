package com.w3engineers.unicef.telemesh.ui.createuser;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.w3engineers.eth.data.remote.EthereumService;
import com.w3engineers.ext.strom.application.ui.base.BaseActivity;
import com.w3engineers.ext.strom.util.helper.Toaster;
import com.w3engineers.unicef.telemesh.R;
import com.w3engineers.unicef.telemesh.data.helper.constants.Constants;
import com.w3engineers.unicef.telemesh.data.provider.ServiceLocator;
import com.w3engineers.unicef.telemesh.databinding.ActivityCreateUserBinding;
import com.w3engineers.unicef.telemesh.ui.chooseprofileimage.ProfileImageActivity;
import com.w3engineers.unicef.telemesh.ui.main.MainActivity;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class CreateUserActivity extends BaseActivity implements View.OnClickListener {

    private ActivityCreateUserBinding mBinding;

//    private ServiceLocator serviceLocator;
    private int PROFILE_IMAGE_REQUEST = 1;
    public static int INITIAL_IMAGE_INDEX = -1;
    private CreateUserViewModel mViewModel;
    @NonNull
    public static String IMAGE_POSITION = "image_position";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_user;
    }

    @Override
    protected int statusBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected void startUI() {

        mBinding = (ActivityCreateUserBinding) getViewDataBinding();
        setTitle(getString(R.string.create_user));
        getWindow().setBackgroundDrawableResource(R.mipmap.splash_screen_bg);
        mViewModel = getViewModel();

        mBinding.imageViewCamera.setOnClickListener(this);
        mBinding.buttonSignup.setOnClickListener(this);
        mBinding.imageProfile.setOnClickListener(this);

        mBinding.editTextFirstName.setMaxCharacters(Constants.DefaultValue.MAXIMUM_TEXT_LIMIT);
        mBinding.editTextLastName.setMaxCharacters(Constants.DefaultValue.MAXIMUM_TEXT_LIMIT);
        mBinding.editTextFirstName.setMinCharacters(Constants.DefaultValue.MINIMUM_TEXT_LIMIT);
        mBinding.editTextLastName.setMinCharacters(Constants.DefaultValue.MINIMUM_TEXT_LIMIT);


        mBinding.editTextLastName.setOnEditorActionListener((textView, i, keyEvent) -> {
            saveData();
            return true;
        });


        final Flowable<Boolean> firstNameObservable = RxTextView.afterTextChangeEvents(mBinding.editTextFirstName)
                .map(textViewAfterTextChangeEvent -> mViewModel.isNameValid(textViewAfterTextChangeEvent.view().getText().toString())).toFlowable(BackpressureStrategy.LATEST);

        final Flowable<Boolean> lastNameObservable = RxTextView.afterTextChangeEvents(mBinding.editTextLastName)
                .map(textViewAfterTextChangeEvent -> mViewModel.isNameValid(textViewAfterTextChangeEvent.view().getText().toString())).toFlowable(BackpressureStrategy.LATEST);


        final Flowable<Boolean> combineResult = Flowable.combineLatest(
                firstNameObservable,
                lastNameObservable,
                (aBoolean, aBoolean2) -> aBoolean && aBoolean2
        );

        getCompositeDisposable().add(combineResult.subscribe(aBoolean -> {
                    mBinding.buttonSignup.setEnabled(aBoolean);
                    if (aBoolean) {
                        mBinding.buttonSignup.setAlpha(Constants.ButtonOpacity.ENABLE_EFFECT);
                    } else {
                        mBinding.buttonSignup.setAlpha(Constants.ButtonOpacity.DISABLE_EFFECT);
                    }
                }, Throwable::printStackTrace
        ));
    }

    private CreateUserViewModel getViewModel() {
        return ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) ServiceLocator.getInstance().getCreateUserViewModel(getApplication());
            }
        }).get(CreateUserViewModel.class);
    }

    protected void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        if (report.areAllPermissionsGranted()) {
                            ethereumWalletCreation();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            requestMultiplePermissions();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(
                            List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(error -> requestMultiplePermissions()).onSameThread().check();
    }


    @Override
    public void onClick(@NonNull View view) {
        super.onClick(view);

        int id = view.getId();

        switch (id) {
            case R.id.button_signup:
                saveData();
                break;
            case R.id.image_profile:
            case R.id.image_view_camera:
                Intent intent = new Intent(this, ProfileImageActivity.class);
                intent.putExtra(CreateUserActivity.IMAGE_POSITION, mViewModel.getImageIndex());
                startActivityForResult(intent, PROFILE_IMAGE_REQUEST);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == PROFILE_IMAGE_REQUEST && resultCode == RESULT_OK) {
            mViewModel.setImageIndex(data.getIntExtra(IMAGE_POSITION, INITIAL_IMAGE_INDEX));

            int id = getResources().getIdentifier(Constants.drawables.AVATAR_IMAGE + mViewModel.getImageIndex(), Constants.drawables.AVATAR_DRAWABLE_DIRECTORY, getPackageName());
            mBinding.imageProfile.setImageResource(id);
        }
    }

    private void saveData() {
        if (mViewModel.getImageIndex() != INITIAL_IMAGE_INDEX) {
            requestMultiplePermissions();
        } else {
            Toaster.showLong(getString(R.string.select_avatar));
        }
    }

    private void ethereumWalletCreation() {

        ProgressDialog dialog = ProgressDialog.show(this, "",
                "Creating account. Please wait...", true);
        dialog.setCancelable(false);

        EthereumService.getInstance(CreateUserActivity.this).createWallet("123456789",
                new EthereumService.RunnableListener() {
                    @Override
                    public void onWalletCreated(String walletName, String walletAddress) {
                        dialog.dismiss();
                        goNext(walletAddress);
                    }

                    @Override
                    public void onWalletLoaded(String walletAddress) {
                        dialog.dismiss();
                        goNext(walletAddress);
                    }
                });
    }

    private void goNext(String myId) {
        if (mViewModel.storeData(mBinding.editTextFirstName.getText() + "",
                mBinding.editTextLastName.getText() + "", myId)) {
            Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
