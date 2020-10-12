package com.android.catfactappmvvm.data.remote.models;

import com.google.gson.annotations.SerializedName;

public class Fact {
    private boolean used;

    private String api;

    private String type;

    private boolean deleted;

    @SerializedName("_id")
    private String id;

    @SerializedName("__v")
    private int v;

    private String text;

    private String updatedAt;

    private String createdAt;

    private Status status = null;

    private String user;

    public boolean isUsed() {
        return used;
    }

    public String getApi() {
        return api;
    }

    public String getType() {
        return type;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public String getId() {
        return id;
    }

    public int getV() {
        return v;
    }

    public String getText() {
        return text;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public String getUser() {
        return user;
    }

    public class Status {
        private boolean verified;

        private int sentCount;
    }
}
