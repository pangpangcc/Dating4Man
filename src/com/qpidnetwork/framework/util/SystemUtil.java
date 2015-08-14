package com.qpidnetwork.framework.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class SystemUtil {
	
	/**
	 * 获取屏幕的长宽属性
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm;
	}
	
	/**
	 * 判断系统是否有SIM卡
	 * @param context
	 * @return
	 */
	public static boolean isSimCanUse(Context context) {
		try { 
	        TelephonyManager mgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	        return TelephonyManager.SIM_STATE_READY == mgr.getSimState(); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	    return false;
	}
	
}
