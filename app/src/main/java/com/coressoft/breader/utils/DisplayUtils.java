package com.coressoft.breader.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DisplayUtils {
	/**
	 * 将px值转换为dp值
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dp值转换为px值
	 */
	public static int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}
	private static WindowManager mWindowManager;
	/**
	 * 获取屏幕宽度
	 */
	public static int getScreenWidthPixels(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay().getMetrics(metric);
		return metric.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 */
	public static int getScreenHeightPixels(Context context) {
		DisplayMetrics metric = new DisplayMetrics();
		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		mWindowManager.getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}


}
