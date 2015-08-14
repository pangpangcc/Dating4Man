package com.qpidnetwork.livechat;

import java.io.Serializable;
import java.util.Comparator;

import com.qpidnetwork.framework.util.Log;
import com.qpidnetwork.request.RequestJniLiveChat.PhotoModeType;
import com.qpidnetwork.request.RequestJniLiveChat.PhotoSizeType;
import com.qpidnetwork.request.item.Record;
import com.qpidnetwork.request.item.Record.ToFlag;

/**
 * 一条聊天消息对象
 * @author Samson Fan
 */
public class LCMessageItem implements Serializable{

	private static final long serialVersionUID = -4130453946668041520L;

	/**
	 * 消息类型
	 */
	public enum MessageType {
		Unknow,		// 未知类型
		Text,		// 文本消息 
		Warning,	// 警告消息
		Emotion,	// 高级表情
		Voice,		// 语音
		Photo,		// 私密照
		System,		// 系统消息
		Custom,     //自定义消息
	}
	
	/**
	 * 消息发送方向 类型
	 */
	public enum SendType {
		Unknow,		// 未知类型
		Send,		// 发出
		Recv,		// 接收
		System,		// 系统
	}
	
	/**
	 * 处理状态
	 * @author Samson Fan
	 *
	 */
	public enum StatusType {
		Unprocess,	// 未处理
		Processing,	// 处理中
		Fail,		// 发送失败
		Finish,		// 发送完成/接收成功
	}
	
	/**
	 * 消息ID
	 */
	public int msgId;
	/**
	 * 消息发送方向
	 */
	public SendType sendType;
	/**
	 * 发送者ID 
	 */
	public String fromId;
	/**
	 * 接收者ID
	 */
	public String toId;
	/**
	 * 接收/发送时间
	 */
	public int createTime;
	/**
	 * 处理状态
	 */
	public StatusType statusType;
	/**
	 * 消息类型
	 */
	public MessageType msgType;
	/**
	 * 文本item
	 */
	private LCTextItem textItem;
	/**
	 * 警告item
	 */
	private LCWarningItem warningItem;
	/**
	 * 高级表情ID
	 */
	private LCEmotionItem emotionItem;
	/**
	 * 语音item
	 */
	private LCVoiceItem voiceItem;
	/**
	 * 图片item
	 */
	private LCPhotoItem photoItem;
	/**
	 * 系统消息item
	 */
	private LCSystemItem systemItem;
	/**
	 * 用户item
	 */
	private LCUserItem userItem;
	/**
	 * 服务器数据库当前时间
	 */
	static private int mDbTime = 0; 
	
	/**
	 * 用于不需要缓存，但需要界面特殊显示的消息类型
	 */
	private LCLocalCustomItem customItem;
	
	public LCMessageItem() {
		clear();
	}
	
	/**
	 * 初始化
	 * @param msgId
	 * @param sendType
	 * @param fromId
	 * @param toId
	 * @param statusType
	 * @return
	 */
	public boolean init(
			int msgId
			, SendType sendType
			, String fromId
			, String toId
			, StatusType statusType)
	{
		boolean result = false;
		if (!fromId.isEmpty() 
			&& !toId.isEmpty()
			&& sendType != SendType.Unknow)
		{
			this.msgId = msgId;
			this.sendType = sendType;
			this.fromId = fromId;
			this.toId = toId;
			this.statusType = statusType;
			
			this.createTime = GetCreateTime();
			result = true;
		}
		return result;
	}
	
	/**
	 * 获取生成时间
	 * @return
	 */
	public static int GetCreateTime()
	{
		return (int)(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 更新生成时间
	 */
	public void RenewCreateTime()
	{
		this.createTime = GetCreateTime();
	}
	
	/**
	 * 设置服务器当前数据库时间
	 * @param dbTime	数据库当前时间
	 */
	static public void SetDbTime(int dbTime)
	{
		mDbTime = dbTime;
	}
	
	/**
	 * 把服务器时间转换为手机时间
	 * @param serverTime	服务器返回的时间
	 * @return
	 */
	static public int GetLocalTimeWithServerTime(int serverTime)
	{
		int synServerTime = 0;
		if(mDbTime > 0){
			/*同步服务器时间成功*/
			int diffTime = GetCreateTime() - mDbTime;
			synServerTime = serverTime + diffTime;
		}else {
			/*同步服务器时间失败，使用默认时间*/
			synServerTime = serverTime;
		}
		return synServerTime;
	}
	
	/**
	 * 使用Record初始化MessageItem
	 * @param record
	 * @return
	 */
	public boolean InitWithRecord(int msgId, String selfId, String userId, Record record, LCEmotionManager emotionMgr, LCVoiceManager voiceMgr, LCPhotoManager photoMgr) 
	{
		boolean result = false;
		this.msgId = msgId;
		this.toId = (record.toflag == ToFlag.Receive ? selfId : userId);
		this.fromId = (record.toflag == ToFlag.Receive ? userId : selfId);
		this.sendType = (record.toflag == ToFlag.Receive ? SendType.Recv : SendType.Send);
		this.statusType = StatusType.Finish;
		this.createTime = GetLocalTimeWithServerTime(record.adddate);
		
		switch(record.messageType) {
		case Text: {
			LCTextItem textItem = new LCTextItem();
			textItem.init(record.textMsg);
			setTextItem(textItem);
			result = true;
		}break;
		case Invite: {
			LCTextItem textItem = new LCTextItem();
			textItem.init(record.inviteMsg);
			setTextItem(textItem);
			result = true;
		}break;
		case Warning: {
			LCWarningItem warningItem = new LCWarningItem();
			warningItem.init(record.warningMsg);
			setWarningItem(warningItem);
			result = true;
		}break;
		case Emotion: {
			LCEmotionItem emotionItem = emotionMgr.getEmotion(record.emotionId);
			setEmotionItem(emotionItem);
			result = true;
		}break;
		case Photo: {
			LCPhotoItem photoItem = new LCPhotoItem();
			// 男士端发送的为已付费
			boolean photoCharge = (this.sendType == SendType.Send ? true : record.photoCharge); 
			photoItem.init(
					record.photoId
					, ""
					, record.photoDesc
					, photoMgr.getPhotoPath(record.photoId, PhotoModeType.Fuzzy, PhotoSizeType.Large)
					, photoMgr.getPhotoPath(record.photoId, PhotoModeType.Fuzzy, PhotoSizeType.Middle)
					, photoMgr.getPhotoPath(record.photoId, PhotoModeType.Clear, PhotoSizeType.Original)
					, photoMgr.getPhotoPath(record.photoId, PhotoModeType.Clear, PhotoSizeType.Large)
					, photoMgr.getPhotoPath(record.photoId, PhotoModeType.Clear, PhotoSizeType.Middle)
					, photoCharge);
			setPhotoItem(photoItem);
			result = true;
		}break;
		case Voice: {
			LCVoiceItem voiceItem = new LCVoiceItem();
			voiceItem.init(record.voiceId
					, voiceMgr.getVoicePath(record.voiceId, record.voiceType)
					, record.voiceTime, record.voiceType
					, ""
					, true);
			setVoiceItem(voiceItem);
			result = true;
		}break;
		default: {
			Log.e("livechat", String.format("%s::%s() unknow message type", "LCMessageItem", "InitWithRecord"));
		}break;
		}
		return result;
	}
	
	public void setVoiceItem(LCVoiceItem theVoiceItem) {
		if (msgType == MessageType.Unknow) {
			voiceItem = theVoiceItem;
			msgType = MessageType.Voice;
		}
	}
	public LCVoiceItem getVoiceItem() {
		return voiceItem;
	}
	
	public void setPhotoItem(LCPhotoItem thePhotoItem) {
		if (msgType == MessageType.Unknow) {
			photoItem = thePhotoItem;
			msgType = MessageType.Photo;
		}
	}
	public LCPhotoItem getPhotoItem() {
		return photoItem;
	}
	
	public void setTextItem(LCTextItem theTextItem) {
		if (msgType == MessageType.Unknow) {
			textItem = theTextItem;
			msgType = MessageType.Text;
		}
	}
	public LCTextItem getTextItem() {
		return textItem;
	}
	
	public void setWarningItem(LCWarningItem theWarningItem) {
		if (msgType == MessageType.Unknow) {
			warningItem = theWarningItem;
			msgType = MessageType.Warning;
		}
	}
	public LCWarningItem getWarningItem() {
		return warningItem;
	}
	
	public void setEmotionItem(LCEmotionItem theEmotionItem) {
		if (msgType == MessageType.Unknow) {
			emotionItem = theEmotionItem;
			msgType = MessageType.Emotion;
		}
	} 
	public LCEmotionItem getEmotionItem() {
		return emotionItem;
	}
	
	public void setSystemItem(LCSystemItem theSystemItem) {
		if (msgType == MessageType.Unknow) {
			systemItem = theSystemItem;
			msgType = MessageType.System;
		}
	}
	public LCSystemItem getSystemItem() {
		return systemItem;
	}
	
	public void setUserItem(LCUserItem theUserItem) {
		userItem = theUserItem;
	}
	public LCUserItem getUserItem() {
		return userItem;
	}
	
	/**
	 * 重置所有成员变量
	 */
	public void clear() 
	{
		msgId = 0;
		sendType = SendType.Unknow;
		fromId = "";
		toId = "";
		createTime = 0;
		statusType = StatusType.Unprocess;
		msgType = MessageType.Unknow;
		textItem = null;
		warningItem = null;
		emotionItem = null;
		voiceItem = null;
		photoItem = null;
		systemItem = null;
		userItem = null;
		customItem = null;
	}
	
	
	static public Comparator<LCMessageItem> getComparator() {
		Comparator<LCMessageItem> comparator = new Comparator<LCMessageItem>() {
			@Override
			public int compare(LCMessageItem lhs, LCMessageItem rhs) {
				// TODO Auto-generated method stub
				int result = -1;
				if (lhs.createTime == rhs.createTime) {
					// 判断msgId
					if (lhs.msgId == rhs.msgId) {
						result = 0;
					}
					else {
						result = lhs.msgId > rhs.msgId ? 1 : -1;
					}
				}
				else {
					result = lhs.createTime > rhs.createTime ? 1 : -1;
				}

				return result;
			}
		};
		return comparator;
	}

	public LCLocalCustomItem getCustomItem() {
		return customItem;
	}

	public void setCustomItem(LCLocalCustomItem customItem) {
		this.customItem = customItem;
	}
}
