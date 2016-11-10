package com.frain.myapplication.ContentProvider.TestContentProvider;

import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frain.myapplication.R;

public class StudentAdapter extends BaseAdapter{
	List<StudentBean> list;
	LayoutInflater inflater;
	public StudentAdapter(Context context, List<StudentBean> list) {
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	public void setDataSource(List<StudentBean> list) {
		this.list = list;
		notifyDataSetInvalidated();
	}
	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}
	@Override
	public Object getItem(int position) {
		if(list != null && position < list.size()) {
			return list.get(position);
		}
		return null;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder ;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.student_item, parent, false);
			holder = new Holder();
			holder.stuId = (TextView) convertView.findViewById(R.id.stu_id);
			holder.stuName = (TextView) convertView.findViewById(R.id.stu_name);
			holder.stuAge = (TextView) convertView.findViewById(R.id.stu_age);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if(position < list.size()) {
			holder.stuId.setText(list.get(position).stuId + "");
			holder.stuName.setText(list.get(position).stuName);
			holder.stuAge.setText(list.get(position).stuAge + "");
		}
		return convertView;
	}
	
	class Holder{
		TextView stuId;
		TextView stuName;
		TextView stuAge;
	}

}
