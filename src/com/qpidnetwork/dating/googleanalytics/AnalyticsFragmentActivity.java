package com.qpidnetwork.dating.googleanalytics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * 仅用于跟踪统计的FragmentActivity基类（如：GoogleAnalytics）
 * @author Samson Fan
 *
 */
public class AnalyticsFragmentActivity extends FragmentActivity
{
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		// 统计activity onCreate()状态
		AnalyticsManager.newInstance().ReportCreate(this);
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		// 统计activity onDestroy()状态
		AnalyticsManager.newInstance().ReportDestroy(this);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		
		// 统计activity onRestart()状态
		AnalyticsManager.newInstance().ReportStart(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		// 统计activity onStart()状态
		AnalyticsManager.newInstance().ReportStart(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
		// 统计activity onResume()状态
		AnalyticsManager.newInstance().ReportResume(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// 统计activity onPause()状态
		AnalyticsManager.newInstance().ReportPause(this);
	}
	
	@Override
	protected void onStop() 
	{
		super.onStop();
		
		// 统计activity onStop()状态
		AnalyticsManager.newInstance().ReportStop(this);
	}

	/**
	 * 统计activity tag
	 * @param page
	 */
	public void onAnalyticsPageSelected(int page) 
	{
		// 统计activity onPageSelected()状态
		AnalyticsManager.newInstance().ReportPageSelected(this, page);
	}
	
	/**
	 * 统计activity tag
	 * @param fragment
	 */
	public void onAnalyticsPageSelected(Fragment fragment, int page) 
	{
		if (null != fragment) 
		{
			String fragmentName = fragment.getClass().getName();
			if (fragmentName.lastIndexOf(".") > 0) {
				String tagName = fragmentName.substring(fragmentName.lastIndexOf(".")+1);
				// 统计activity onPageSelected()状态
				onAnalyticsPageSelected(tagName, page);
			}
		}
	}
	
	/**
	 * 统计activity tag
	 * @param fragmentName
	 */
	public void onAnalyticsPageSelected(String fragmentName, int page) 
	{
		if (null != fragmentName
			&& !fragmentName.isEmpty()) 
		{
			String tagName = fragmentName;
			
			// 统计activity onPageSelected()状态
			AnalyticsManager.newInstance().ReportPageSelected(this, tagName, page);
		}
	}
	
	/**
	 * 统计activity tag的子页
	 * @param page
	 * @param subPage
	 */
	public void onAnalyticsPageSelected(int page, int subPage) 
	{
		// 统计activity onPageSelected()的子页状态
		AnalyticsManager.newInstance().ReportPageSelected(this, page, subPage);
	}
	
	/**
	 * 统计activity tag页的sub页的子页
	 * @param page
	 * @param subPage
	 */
	public void onAnalyticsPageSelected(int page, int subPage, int subPage2) 
	{
		// 统计activity onPageSelected()的子页状态
		AnalyticsManager.newInstance().ReportPageSelected(this, page, subPage, subPage2);
	}

	/**
	 * 统计activity event
	 * @param category
	 * @param action
	 * @param label
	 */
	public void onAnalyticsEvent(String category, String action, String label)
	{
		// 统计activity event
		AnalyticsManager.newInstance().ReportEvent(category, action, label);
	}
	
	/**
	 * 设置是否page activity（若是，则onResume时不统计）
	 * @param isPageActivity
	 */
	protected void SetPageActivity(boolean isPageActivity)
	{
		AnalyticsManager.newInstance().SetPageActivity(this, isPageActivity);
	}
}
