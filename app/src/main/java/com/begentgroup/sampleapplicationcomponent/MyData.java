package com.begentgroup.sampleapplicationcomponent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016-07-18.
 */
public class MyData implements Parcelable {
    public String keyword;
    public int age;

    public MyData() {

    }

    public MyData(Parcel parcel) {
        keyword = parcel.readString();
        age = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(keyword);
        parcel.writeInt(age);
    }

    public static Parcelable.Creator<MyData> CREATOR = new Parcelable.Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel parcel) {
//            MyData d = new MyData();
//            d.keyword = parcel.readString();
//            d.age = parcel.readInt();
//            return d;
            return new MyData(parcel);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };
}
