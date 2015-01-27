package com.example.startviewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.net.VpnService;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnPageChangeListener {
	public static final String TAG = "MainActivity";// 输入log的标识
	private int[] images = { R.drawable.img_guider1, R.drawable.img_guider2,
			R.drawable.img_guider3, R.drawable.img_guider4 };// 所有页面的图片
	private List<View> views = new ArrayList<View>();// 页面
	private ViewPager viewPager;
	private int pagerPos = 0;// 当前的页面编号
	private List<View> pointViews = new ArrayList<View>();// 引导点
	private LinearLayout layout;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 全屏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 无标题
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		viewInit();
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(new StartViewPagerAdapter(views));
		viewPager.setOnPageChangeListener(this);
		viewPager.setOffscreenPageLimit(images.length);// 加载缓存的页面个数。。防止报错。。java.lang.IllegalStateException:
														// The specified child
														// already has a parent.
														// You must call
														// removeView() on the
														// child's parent first.

	}

	private void viewInit() {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		PagerOnClick po = new PagerOnClick();
		// 每个页面的布局
		for (int i = 0; i < images.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setImageResource(images[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			iv.setLayoutParams(lp);
			iv.setTag(i);
			iv.setOnClickListener(po);// 设置点击监听器
			views.add(iv);
		}
		pointInit();
		pointState();
	}

	private void pointState() {
		// 每个点的变化，每次切换
		for (int i = 0; i < pointViews.size(); i++) {
			pointViews.get(i).setEnabled(true);
		}
		pointViews.get(pagerPos).setEnabled(false);
	}

	private void pointInit() {
		// 每个点的初始化
		layout = (LinearLayout) findViewById(R.id.linear);
		PointOnClick po = new PointOnClick();
		for (int i = 0; i < layout.getChildCount(); i++) {
			layout.getChildAt(i).setTag(i);
			layout.getChildAt(i).setOnClickListener(po);// 引导点的点击监听器
			pointViews.add(layout.getChildAt(i));
		}
	}

	public void onPageScrollStateChanged(int arg0) {

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	public void onPageSelected(int arg0) {
		// 滑动页面完之后
		this.pagerPos = arg0;
		pointState();
	}

	private void setCurrentItem(int pos) {
		// 设置当前页面，用于页面和小点的点击事件
		if (pos < 0 || pos > views.size()) {
			return;
		}
		viewPager.setCurrentItem(pos);
	}

	class PointOnClick implements OnClickListener {
		// 小点的点击事件
		public void onClick(View v) {
			Log.i(TAG, "PointOnClick---onClick（）  " + v.getTag());
			setCurrentItem((Integer) v.getTag());
		}
	}

	class PagerOnClick implements OnClickListener {
		// 页面的点击事件
		public void onClick(View v) {
			setCurrentItem((Integer) v.getTag() + 1);
		}
	}
}
