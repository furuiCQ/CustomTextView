package com.frain.myapplication.ContentProvider.TestContentProvider;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentBean implements Parcelable{
	public int stuId;
	public String stuName;
	public int stuAge;
	public StudentBean() {
	}
	public StudentBean(int stuId, String stuName, int stuAge) {
		this.stuAge = stuAge;
		this.stuId = stuId;
		this.stuName = stuName;
	}
	/**
	 * 使用Parcel对象构建构造器
	 * @param in
	 */
	public StudentBean(Parcel in) {
		this.stuId = in.readInt();
		this.stuName = in.readString();
		this.stuAge = in.readInt();
	}
	/**
	 * 内容描述，一般返回0
	 */
	@Override
	public int describeContents() {
		return 0;
	}
	/**
	 * 写入缓冲区
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(stuId);
		dest.writeString(stuName);
		dest.writeInt(stuAge);
	}
	/**
	 * 创建器
	 */
	public static final Creator<StudentBean> CREATOR = new Creator<StudentBean>() {
		/**
		 * 从缓冲区构建数组
		 */
		@Override
		public StudentBean[] newArray(int size) {
			return new StudentBean[size];
		}
		/**
		 * 从缓存去创建对象
		 * 以Parcel对象创建
		 */
		@Override
		public StudentBean createFromParcel(Parcel source) {
			return new StudentBean(source);
		}
	};

}
