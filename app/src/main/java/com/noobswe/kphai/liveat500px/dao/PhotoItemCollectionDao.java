package com.noobswe.kphai.liveat500px.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by K'Phai on 3/4/2016.
 */
public class PhotoItemCollectionDao implements Parcelable {

    @SerializedName("success") private boolean success;
    @SerializedName("data")    private List<PhotoItemDAO> data;

    public PhotoItemCollectionDao() {

    }

    protected PhotoItemCollectionDao(Parcel in) {
        success = in.readByte() != 0;
        data = in.createTypedArrayList(PhotoItemDAO.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeTypedList(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoItemCollectionDao> CREATOR = new Creator<PhotoItemCollectionDao>() {
        @Override
        public PhotoItemCollectionDao createFromParcel(Parcel in) {
            return new PhotoItemCollectionDao(in);
        }

        @Override
        public PhotoItemCollectionDao[] newArray(int size) {
            return new PhotoItemCollectionDao[size];
        }
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PhotoItemDAO> getData() {
        return data;
    }

    public void setData(List<PhotoItemDAO> data) {
        this.data = data;
    }
}
