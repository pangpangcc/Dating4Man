package com.qpidnetwork.framework.base;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseFragment extends Fragment implements OnClickListener{
	
	protected Context mContext;
	
	@Override
    public void onAttach(Activity activity) {
    	// TODO Auto-generated method stub
    	super.onAttach(activity);
    	mContext = activity;
    }
	
	protected Handler mUiHandler = new UiHandler(this) {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            if (getFragmentReference() != null && getFragmentReference().get() != null) {
                handleUiMessage(msg);
            }
        };
    };

    private static class UiHandler extends Handler {
        private final WeakReference<Fragment> mFragmentReference;

        public UiHandler(Fragment activity) {
            mFragmentReference = new WeakReference<Fragment>(activity);
        }

        public WeakReference<Fragment> getFragmentReference() {
            return mFragmentReference;
        }
    }

    /**
     * 处理更新UI任务
     * 
     * @param msg
     */
    protected void handleUiMessage(Message msg) {
    }

    /**
     * 发送UI更新操作
     * 
     * @param msg
     */
    protected void sendUiMessage(Message msg) {
        mUiHandler.sendMessage(msg);
    }

    protected void sendUiMessageDelayed(Message msg, long delayMillis) {
        mUiHandler.sendMessageDelayed(msg, delayMillis);
    }

    /**
     * 发送UI更新操作
     * 
     * @param what
     */
    protected void sendEmptyUiMessage(int what) {
        mUiHandler.sendEmptyMessage(what);
    }

    protected void sendEmptyUiMessageDelayed(int what, long delayMillis) {
        mUiHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	/*用于Fragment页统计*/
	public void onFragmentSelected(int arg0){
		
	}
	
	public void onFragmentPause(int arg0){
		
	}
    
}
