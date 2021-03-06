package com.w3engineers.unicef.telemesh.data.analytics.model;

/*
 * ============================================================================
 * Copyright (C) 2019 W3 Engineers Ltd - All Rights Reserved.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Purpose:  This class is the container of Message count analytics data
 * ============================================================================
 */


public class MessageCountModel {
    private String userId;
    private int msgCount;
    private long msgTime;

    public String getUserId() {
        return userId;
    }

    public MessageCountModel setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public MessageCountModel setMsgCount(int msgCount) {
        this.msgCount = msgCount;
        return this;
    }

    public long getMsgTime() {
        return msgTime;
    }

    public MessageCountModel setMsgTime(long msgTime) {
        this.msgTime = msgTime;
        return this;
    }
}
