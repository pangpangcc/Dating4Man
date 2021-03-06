package com.qpidnetwork.livechat;

import android.content.Context;
import android.text.TextUtils;

import com.qpidnetwork.framework.util.Log;
import com.qpidnetwork.tool.FileDownloader;

public class LCThemeItemDownloader implements FileDownloader.FileDownloaderCallback{
	
	private FileDownloader mFileDownloader;
	private String themeId;
	private String filePath;
	private LCThemeItemDownloaderCallback mCallback;
	
	public LCThemeItemDownloader(Context context){
		mFileDownloader = new FileDownloader(context);
		themeId = "";
		filePath = "";
		mCallback = null;
	} 
	
	/**
	 * 开始下载
	 * @param url		文件下载URL
	 * @param filePath	文件本地路径
	 * @param themeId	主题Id
	 * @param callback	回调
	 */
	public boolean Start(String url, String filePath, String themeId, LCThemeItemDownloaderCallback callback) {
		boolean result = false;
		if (!url.isEmpty() 
			&& !filePath.isEmpty()
			&& !TextUtils.isEmpty(themeId)
			&& callback != null)
		{
			Log.i("LCThemeItemDownloader", "LCThemeItemDownloader url: " + url + " ----themeId: " + themeId + " ----filePath: " + filePath);
			this.themeId = themeId;
			this.filePath = filePath;
			mCallback = callback;
			mFileDownloader.SetBigFile(true);
			mFileDownloader.StartDownload(url, filePath, this);
			result = true;
		}
		return result;
	}
	
	/**
	 * 停止下载
	 */
	public void Stop() {
		mFileDownloader.Stop();
	}

	@Override
	public void onSuccess(FileDownloader loader) {
		if (mCallback != null) {
			mCallback.onSuccess(themeId, filePath);
		} 
		Log.i("LCThemeItemDownloader", "LCThemeItemDownloader onSuccess themeId: " + themeId + " ----filePath: " + filePath);
		mCallback = null;
		mFileDownloader = null;
		themeId = "";
		filePath = "";	
	}

	@Override
	public void onFail(FileDownloader loader) {
		if (mCallback != null) {
			mCallback.onFail(themeId, filePath);
		}
		
		Log.i("LCThemeItemDownloader", "LCThemeItemDownloader onFail themeId: " + themeId + " ----filePath: " + filePath);
		mCallback = null;
		mFileDownloader = null;
		themeId = "";
		filePath = "";		
	}

	@Override
	public void onUpdate(FileDownloader loader, int progress) {
		// TODO Auto-generated method stub
		if (mCallback != null) {
			mCallback.onUpdate(themeId, filePath, progress);
		}
		Log.i("LCThemeItemDownloader", "LCThemeItemDownloader onUpdate themeId: " + themeId + " ----progress: " + progress);
	}
	
	public interface LCThemeItemDownloaderCallback {
		void onSuccess(String themeId, String localPath);
		void onFail(String themeId, String localPath);
		void onUpdate(String themeId, String localPath, int progress);
	}
	
}
