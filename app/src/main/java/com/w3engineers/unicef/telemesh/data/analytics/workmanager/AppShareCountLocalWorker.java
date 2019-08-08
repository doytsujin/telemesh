package com.w3engineers.unicef.telemesh.data.analytics.workmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.w3engineers.unicef.telemesh.data.helper.RmDataHelper;
/*
 * ============================================================================
 * Copyright (C) 2019 W3 Engineers Ltd - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * ============================================================================
 */


public class AppShareCountLocalWorker extends Worker {


    public AppShareCountLocalWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d("AppShareTest", "Local send worker call");
        RmDataHelper.getInstance().sendAppShareCount();

        return Result.success();
    }
}