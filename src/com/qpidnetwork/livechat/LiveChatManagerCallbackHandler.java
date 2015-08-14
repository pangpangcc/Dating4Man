package com.qpidnetwork.livechat;

import java.util.ArrayList;
import java.util.Iterator;

import com.qpidnetwork.framework.util.Log;
import com.qpidnetwork.livechat.jni.LiveChatClientListener.KickOfflineType;
import com.qpidnetwork.livechat.jni.LiveChatClientListener.LiveChatErrType;
import com.qpidnetwork.livechat.jni.LiveChatClientListener.TalkEmfNoticeType;
import com.qpidnetwork.livechat.jni.LiveChatClientListener.TryTicketEventType;
import com.qpidnetwork.request.item.Coupon;
import com.qpidnetwork.request.item.OtherEmotionConfigItem;

/**
 * LiveChatManager回调处理类
 * @author Samson Fan
 *
 */
public class LiveChatManagerCallbackHandler implements LiveChatManagerOtherListener
													 , LiveChatManagerTryTicketListener
													 , LiveChatManagerMessageListener
													 , LiveChatManagerEmotionListener
													 , LiveChatManagerPhotoListener
													 , LiveChatManagerVoiceListener
{
	/**
	 * 回调OtherListener的object列表
	 */
	private ArrayList<LiveChatManagerOtherListener> mOtherListeners;
	/**
	 * 回调TryTicketListener的object列表
	 */
	private ArrayList<LiveChatManagerTryTicketListener> mTryTicketListeners;
	/**
	 * 回调MessageListener的object列表
	 */
	private ArrayList<LiveChatManagerMessageListener> mMessageListeners;
	/**
	 * 回调EmotionListener的object列表
	 */
	private ArrayList<LiveChatManagerEmotionListener> mEmotionListeners;
	/**
	 * 回调PhotoListener的object列表
	 */
	private ArrayList<LiveChatManagerPhotoListener> mPhotoListeners;
	/**
	 * 回调VoiceListener的object列表
	 */
	private ArrayList<LiveChatManagerVoiceListener> mVoiceListeners;
	
	
	public LiveChatManagerCallbackHandler() {
		mOtherListeners = new ArrayList<LiveChatManagerOtherListener>();
		mTryTicketListeners = new ArrayList<LiveChatManagerTryTicketListener>();
		mMessageListeners = new ArrayList<LiveChatManagerMessageListener>();
		mEmotionListeners = new ArrayList<LiveChatManagerEmotionListener>();
		mPhotoListeners = new ArrayList<LiveChatManagerPhotoListener>();
		mVoiceListeners = new ArrayList<LiveChatManagerVoiceListener>();
	}
	
	// ----------------------- 注册/注销回调 -----------------------
	/**
	 * 注册Other回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterOtherListener(LiveChatManagerOtherListener listener) 
	{
		boolean result = false;
		synchronized(mOtherListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerOtherListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mOtherListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterOtherListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销Other回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterOtherListener(LiveChatManagerOtherListener listener) 
	{
		boolean result = false;
		synchronized(mOtherListeners)
		{
			result = mOtherListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterOtherListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	/**
	 * 注册试聊券(TryTicket)回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterTryTicketListener(LiveChatManagerTryTicketListener listener) 
	{
		boolean result = false;
		synchronized(mTryTicketListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerTryTicketListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mTryTicketListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterTryTicketListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销试聊券(TryTicket)回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterTryTicketListener(LiveChatManagerTryTicketListener listener) 
	{
		boolean result = false;
		synchronized(mTryTicketListeners)
		{
			result = mTryTicketListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterTryTicketListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	/**
	 * 注册文本消息(Message)回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterMessageListener(LiveChatManagerMessageListener listener) 
	{
		boolean result = false;
		synchronized(mMessageListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerMessageListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mMessageListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterMessageListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销文本消息(Message)回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterMessageListener(LiveChatManagerMessageListener listener) 
	{
		boolean result = false;
		synchronized(mMessageListeners)
		{
			result = mMessageListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterMessageListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	/**
	 * 注册高级表情(Emotion)回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterEmotionListener(LiveChatManagerEmotionListener listener) 
	{
		boolean result = false;
		synchronized(mEmotionListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerEmotionListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mEmotionListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterEmotionListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销高级表情(Emotion)回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterEmotionListener(LiveChatManagerEmotionListener listener) 
	{
		boolean result = false;
		synchronized(mEmotionListeners)
		{
			result = mEmotionListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterEmotionListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	/**
	 * 注册私密照(Photo)回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterPhotoListener(LiveChatManagerPhotoListener listener) 
	{
		boolean result = false;
		synchronized(mPhotoListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerPhotoListener> iter = mPhotoListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerPhotoListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mPhotoListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterPhotoListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销私密照(Photo)回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterPhotoListener(LiveChatManagerPhotoListener listener) 
	{
		boolean result = false;
		synchronized(mPhotoListeners)
		{
			result = mPhotoListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterPhotoListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	/**
	 * 注册语音(Voice)回调
	 * @param listener
	 * @return
	 */
	public boolean RegisterVoiceListener(LiveChatManagerVoiceListener listener) 
	{
		boolean result = false;
		synchronized(mVoiceListeners) 
		{
			if (null != listener) {
				boolean isExist = false;
				
				for (Iterator<LiveChatManagerVoiceListener> iter = mVoiceListeners.iterator(); iter.hasNext(); ) {
					LiveChatManagerVoiceListener theListener = iter.next();
					if (theListener == listener) {
						isExist = true;
						break;
					}
				}
				
				if (!isExist) {
					result = mVoiceListeners.add(listener);
				}
				else {
					Log.d("livechat", String.format("%s::%s() fail, listener:%s is exist", "LiveChatManagerCallbackHandler", "RegisterVoiceListener", listener.getClass().getSimpleName()));
				}
			}
			else {
				Log.e("livechat", String.format("%s::%s() fail, listener is null", "LiveChatManagerCallbackHandler", "RegisterListener"));
			}
		}
		return result;
	}
	
	/**
	 * 注销语音(Voice)回调
	 * @param listener
	 * @return
	 */
	public boolean UnregisterVoiceListener(LiveChatManagerVoiceListener listener) 
	{
		boolean result = false;
		synchronized(mVoiceListeners)
		{
			result = mVoiceListeners.remove(listener);
		}

		if (!result) {
			Log.e("livechat", String.format("%s::%s() fail, listener:%s", "LiveChatManagerCallbackHandler", "UnregisterVoiceListener", listener.getClass().getSimpleName()));
		}
		return result;
	}
	
	// ---------------------------- Other ----------------------------
	/**
	 * 登录回调
	 * @param errType	错误类型
	 * @param errmsg	错误代码
	 * @param isAutoLogin	自动重登录
	 */
	public void OnLogin(LiveChatErrType errType, String errmsg, boolean isAutoLogin)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnLogin(errType, errmsg, isAutoLogin);
			}
		}
	}
	
	/**
	 * 注销/断线回调
	 * @param errType	错误类型
	 * @param errmsg	错误代码
	 * @param isAutoLogin	是否自动重新登录
	 */
	public void OnLogout(LiveChatErrType errType, String errmsg, boolean isAutoLogin)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnLogout(errType, errmsg, isAutoLogin);
			}
		}		
	}
	
	/**
	 * 获取在聊及邀请用户列表回调
	 * @param errType	错误类型
	 * @param errmsg	错误代码
	 */
	public void OnGetTalkList(LiveChatErrType errType, String errmsg)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnGetTalkList(errType, errmsg);
			}
		}
	}
	
	/**
	 * 获取单个用户历史聊天记录（包括文本、高级表情、语音、图片）
	 * @param success	是否成功
	 * @param errno		错误代码
	 * @param errmsg	错误描述
	 * @param userItem	用户item
	 */
	public void OnGetHistoryMessage(boolean success, String errno, String errmsg, LCUserItem userItem)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnGetHistoryMessage(success, errno, errmsg, userItem);
			}
		}
	}
	
	/**
	 * 获取多个用户历史聊天记录（包括文本、高级表情、语音、图片）
	 * @param success 	是否成功
	 * @param errno		错误代码
	 * @param errmsg	错误描述
	 * @param userItem	用户item
	 */
	public void OnGetUsersHistoryMessage(boolean success, String errno, String errmsg, LCUserItem[] userItems)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnGetUsersHistoryMessage(success, errno, errmsg, userItems);
			}
		}
	}
	
	// ---------------- 在线状态相关回调函数(online status) ----------------
	/**
	 * 设置在线状态回调
	 * @param errType	处理结果类型
	 * @param errmsg	处理结果描述
	 */
	public void OnSetStatus(LiveChatErrType errType, String errmsg)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnSetStatus(errType, errmsg);
			}
		}
	}
	
	
	/**
	 * 获取用户在线状态回调
	 * @param errType	错误类型
	 * @param errmsg	错误代码
	 * @param userStatusArray	用户在线状态数组
	 */
	public void OnGetUserStatus(LiveChatErrType errType, String errmsg, LCUserItem[] userList)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnGetUserStatus(errType, errmsg, userList);
			}
		}
	}
	
	/**
	 * 接收他人在线状态更新消息回调
	 * @param userItem	用户item
	 */
	public void OnUpdateStatus(LCUserItem userItem)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnUpdateStatus(userItem);
			}
		}
	}
	
	/**
	 * 他人在线状态更新回调
	 * @param userItem	用户item
	 */
	public void OnChangeOnlineStatus(LCUserItem userItem)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnChangeOnlineStatus(userItem);
			}
		}
	}
	
	/**
	 * 接收被踢下线消息回调
	 * @param kickType	被踢下线原因
	 */
	public void OnRecvKickOffline(KickOfflineType kickType)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnRecvKickOffline(kickType);
			}
		}
	}
	
	// ---------------- 试聊券相关回调函数(Try Ticket) ----------------
	/**
	 * 使用试聊券回调
	 * @param errType	处理结果类型
	 * @param errmsg	处理结果描述
	 * @param userId	用户ID
	 * @param eventType	试聊券使用情况
	 */
	public void OnUseTryTicket(
			LiveChatErrType errType
			, String errno
			, String errmsg
			, String userId
			, TryTicketEventType eventType)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnUseTryTicket(errType, errno, errmsg, userId, eventType);
			}
		}
	}
	
	/**
	 * 接收试聊开始消息回调
	 * @param toId		接收者ID
	 * @param fromId	发起者ID
	 * @param time		试聊时长
	 */
	public void OnRecvTryTalkBegin(LCUserItem userItem, int time)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnRecvTryTalkBegin(userItem, time);
			}
		}
	}
	
	/**
	 * 接收试聊结束消息回调
	 * @param userId	聊天对象ID
	 */
	public void OnRecvTryTalkEnd(LCUserItem userItem)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnRecvTryTalkEnd(userItem);
			}
		}
	}
	
	/**
	 * 查询是否能使用试聊券回调
	 * @param success	是否成功
	 * @param errno		错误代码
	 * @param errmsg	错误描述
	 * @param item		查询结果
	 */
	public void OnCheckCoupon(boolean success, String errno, String errmsg, Coupon item)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnCheckCoupon(success, errno, errmsg, item);
			}
		}
	}
	
	// ---------------- 对话状态改变或对话操作回调函数(try ticket) ----------------
	/**
	 * 结束对话回调
	 * @param errType	错误类型
	 * @param errmsg	错误代码
	 * @param userItem	用户item
	 */
	public void OnEndTalk(LiveChatErrType errType, String errmsg, LCUserItem userItem)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnEndTalk(errType, errmsg, userItem);
			}
		}
	}
	
	/**
	 * 接收聊天事件消息回调
	 * @param item	用户item
	 */
	public void OnRecvTalkEvent(LCUserItem item)
	{
		synchronized(mTryTicketListeners) 
		{
			for (Iterator<LiveChatManagerTryTicketListener> iter = mTryTicketListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerTryTicketListener listener = iter.next();
				listener.OnRecvTalkEvent(item);
			}
		}
	}
	
	// ---------------- 文字聊天回调函数(message) ----------------
	/**
	 * 发送文本聊天消息回调
	 * @param errType	错误代码
	 * @param errmsg	错误描述
	 * @param item		消息item
	 * @return
	 */
	public void OnSendMessage(LiveChatErrType errType, String errmsg, LCMessageItem item)
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnSendMessage(errType, errmsg, item);
			}
		}
	}
	
	/**
	 * 接收聊天文本消息回调
	 * @param item		消息item
	 */
	public void OnRecvMessage(LCMessageItem item)
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnRecvMessage(item);
			}
		}
	}
	
	/**
	 * 接收警告消息回调
	 * @param item		消息item
	 */
	public void OnRecvWarning(LCMessageItem item)
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnRecvWarning(item);
			}
		}
	}
	
	/**
	 * 接收用户正在编辑消息回调 
	 * @param fromId	用户ID
	 */
	public void OnRecvEditMsg(String fromId)
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext();) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnRecvEditMsg(fromId);
			}
		}
	}
	
	/**
	 * 接收系统消息回调
	 * @param item		消息item
	 */
	public void OnRecvSystemMsg(LCMessageItem item) 
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext();) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnRecvSystemMsg(item);
			}
		}
	}
	
	/**
	 * 接收发送待发列表不成功消息
	 * @param errType	不成功原因
	 * @param msgList	待发列表
	 */
	public void OnSendMessageListFail(LiveChatErrType errType, ArrayList<LCMessageItem> msgList)
	{
		synchronized(mMessageListeners) 
		{
			for (Iterator<LiveChatManagerMessageListener> iter = mMessageListeners.iterator(); iter.hasNext();) {
				LiveChatManagerMessageListener listener = iter.next();
				listener.OnSendMessageListFail(errType, msgList);
			}
		}
	}
	
	// ---------------- 高级表情回调函数(Emotion) ----------------
	/**
	 * 获取高级表情配置回调
	 * @param success	是否成功
	 * @param errType	处理结果错误代码
	 * @param errmsg	处理结果描述
	 */
	public void OnGetEmotionConfig(boolean success, String errno, String errmsg, OtherEmotionConfigItem item)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnGetEmotionConfig(success,errno, errmsg, item);
			}
		}
	}
	
	
	/**
	 * 发送高级表情回调
	 * @param errType	处理结果错误代码
	 * @param errmsg	处理结果描述
	 * @param item		消息item
	 * @return
	 */
	public void OnSendEmotion(LiveChatErrType errType, String errmsg, LCMessageItem item)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnSendEmotion(errType, errmsg, item);
			}
		}
	}
	
	/**
	 * 接收高级表情消息回调
	 * @param item		消息item
	 */
	public void OnRecvEmotion(LCMessageItem item)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnRecvEmotion(item);
			}
		}
	}
	
	/**
	 * 下载高级表情图片成功回调
	 * @param emotionId	高级表情ID
	 * @param filePath	文件路径
	 */
	public void OnGetEmotionImage(boolean success, LCEmotionItem emotionItem)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnGetEmotionImage(success, emotionItem);
			}
		}
	}
	
	/**
	 * 下载高级表情播放图片成功回调
	 * @param emotionId	高级表情ID
	 * @param filePath	文件路径
	 */
	public void OnGetEmotionPlayImage(boolean success, LCEmotionItem emotionItem)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnGetEmotionPlayImage(success, emotionItem);
			}
		}
	}
	
	/**
	 * 下载高级表情3gp文件成功回调
	 * @param emotionId	高级表情ID
	 * @param filePath	文件路径
	 */
	public void OnGetEmotion3gp(boolean success, LCEmotionItem emotionItem)
	{
		synchronized(mEmotionListeners) 
		{
			for (Iterator<LiveChatManagerEmotionListener> iter = mEmotionListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerEmotionListener listener = iter.next();
				listener.OnGetEmotion3gp(success, emotionItem);
			}
		}
	}
	
	// ---------------- 图片回调函数(Private Album) ----------------
	/**
	 * 发送图片（包括上传图片文件(php)、发送图片(livechat)）回调
	 * @param errType	处理结果错误代码
	 * @param errmsg	处理结果描述
	 * @param item		消息item
	 * @return
	 */
	public void OnSendPhoto(LiveChatErrType errType, String errno, String errmsg, LCMessageItem item)
	{
		synchronized(mPhotoListeners) 
		{
			for (Iterator<LiveChatManagerPhotoListener> iter = mPhotoListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerPhotoListener listener = iter.next();
				listener.OnSendPhoto(errType, errno, errmsg, item);
			}
		}
	}
	
	/**
	 * 购买图片（包括付费购买图片(php)）
	 * @param success	是否成功
	 * @param errno		请求错误代码
	 * @param errmsg	错误描述
	 * @param item		消息item
	 */
	public void OnPhotoFee(boolean success, String errno, String errmsg, LCMessageItem item)
	{
		synchronized(mPhotoListeners) 
		{
			for (Iterator<LiveChatManagerPhotoListener> iter = mPhotoListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerPhotoListener listener = iter.next();
				listener.OnPhotoFee(success, errno, errmsg, item);
			}
		}
	}
	
	/**
	 * 获取/购买图片（包括付费购买图片(php)、获取对方私密照片(php)、显示图片(livechat)）回调
	 * @param errType	处理结果错误代码
	 * @param errno		购买/下载请求失败的错误代码
	 * @param errmsg	处理结果描述
	 * @param item		消息item
	 * @return
	 */
	public void OnGetPhoto(LiveChatErrType errType, String errno, String errmsg, LCMessageItem item)
	{
		synchronized(mPhotoListeners) 
		{
			for (Iterator<LiveChatManagerPhotoListener> iter = mPhotoListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerPhotoListener listener = iter.next();
				listener.OnGetPhoto(errType, errno, errmsg, item);
			}
		}
	}
	
	/**
	 * 接收图片消息回调
	 * @param item		消息item
	 */
	public void OnRecvPhoto(LCMessageItem item)
	{
		synchronized(mPhotoListeners) 
		{
			for (Iterator<LiveChatManagerPhotoListener> iter = mPhotoListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerPhotoListener listener = iter.next();
				listener.OnRecvPhoto(item);
			}
		}
	}
	
	// ---------------- 语音回调函数(Voice) ----------------
	/**
	 * 发送语音（包括获取语音验证码(livechat)、上传语音文件(livechat)、发送语音(livechat)）回调
	 * @param errType	处理结果错误代码
	 * @param errmsg	处理结果描述
	 * @param item		消息item
	 * @return
	 */
	public void OnSendVoice(LiveChatErrType errType, String errno, String errmsg, LCMessageItem item)
	{
		synchronized(mVoiceListeners) 
		{
			for (Iterator<LiveChatManagerVoiceListener> iter = mVoiceListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerVoiceListener listener = iter.next();
				listener.OnSendVoice(errType, errno, errmsg, item);
			}
		}
	}
	
	/**
	 * 获取语音（包括下载语音(livechat)）回调
	 * @param errType	处理结果错误代码
	 * @param errmsg	处理结果描述
	 * @param item		消息item
	 * @return
	 */
	public void OnGetVoice(LiveChatErrType errType, String errmsg, LCMessageItem item)
	{
		synchronized(mVoiceListeners) 
		{
			for (Iterator<LiveChatManagerVoiceListener> iter = mVoiceListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerVoiceListener listener = iter.next();
				listener.OnGetVoice(errType, errmsg, item);
			}
		}
	}
	
	/**
	 * 接收语音消息回调
	 * @param item		消息item
	 */
	public void OnRecvVoice(LCMessageItem item)
	{
		synchronized(mVoiceListeners) 
		{
			for (Iterator<LiveChatManagerVoiceListener> iter = mVoiceListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerVoiceListener listener = iter.next();
				listener.OnRecvVoice(item);
			}
		}
	}
	
	// ---------------- 其它回调函数(Other) ----------------
	/**
	 * 接收邮件更新消息回调
	 * @param fromId		发送者ID
	 * @param noticeType	邮件类型
	 */
	public void OnRecvEMFNotice(String fromId, TalkEmfNoticeType noticeType)
	{
		synchronized(mOtherListeners) 
		{
			for (Iterator<LiveChatManagerOtherListener> iter = mOtherListeners.iterator(); iter.hasNext(); ) {
				LiveChatManagerOtherListener listener = iter.next();
				listener.OnRecvEMFNotice(fromId, noticeType);
			}
		}
	}
}
