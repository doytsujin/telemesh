package com.w3engineers.unicef.util.helper.uiutil;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.w3engineers.unicef.telemesh.data.helper.constants.Constants;
import com.w3engineers.unicef.telemesh.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class AppBlockerUtilTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);
    public UiDevice mDevice = UiDevice.getInstance(getInstrumentation());

    @Test
    @UiThreadTest
    public void appUpdateAndBlockerTest() {
        addDelay(1000);

        rule.getActivity().stopAnimation();

        addDelay(500);

        rule.getActivity().checkPlayStoreAppUpdate(1, buildAppUpdateJson());

        addDelay(20 * 1000);

        UiObject button1 = mDevice.findObject(new UiSelector().text("Cancel"));
        try {
            if (button1.exists() && button1.isEnabled()) {
                button1.click();
            }
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        addDelay(1000);
    }

    private void addDelay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String buildAppUpdateJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.InAppUpdate.LATEST_VERSION_CODE_KEY, 100);
            jsonObject.put(Constants.InAppUpdate.LATEST_VERSION_KEY, "100.0.0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}