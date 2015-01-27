package com.example.startviewpager;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class StartViewPagerAdapter extends PagerAdapter {

	private static final String TAG = "StartViewPagerAdapter";
	private List<View> views;// ÿ��ҳ��Ĳ���view

	public StartViewPagerAdapter(List<View> views) {
		this.views = views;
	}

	private void log(String info) {
		Log.i(TAG, info);
	}

	// ����ĳ��Ԫ��ʱ�����
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeViewAt(position);
		log("destroyItem()  " + position);
	}

	// ����ҳ����ͼ��ɺ���õķ���
	public void finishUpdate(View container) {
		log("finishUpdate()");
	}

	// ������ͼ
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(views.get(position));
		log("instantiateItem()  " + position);
		return views.get(position);
	}

	public int getCount() {
		return views.size();
	}

	// �ж��Ƿ������ɵĶ���
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
