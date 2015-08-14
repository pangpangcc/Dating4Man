package com.qpidnetwork.request.item;

public class Record {
	public Record() {
		
	}
	
	/**
	 * 5.4.查询聊天记录
	 * @param toflag		发送类型
	 * @param adddate		消息生成时间
	 * @param messageType	消息类型
	 * @param textMsg		文本消息
	 * @param inviteMsg		邀请消息
	 * @param warningMsg	警告消息
	 * @param emotionId		高级表情ID
	 * @param photoId		图片ID
	 * @param photoSendId	图片发送ID
	 * @param photoDesc		图片描述
	 * @param photoCharge	图片是否已付费
	 * @param voiceId		语音ID
	 * @param voiceType		语音文件类型
	 * @param voiceTime		语音时长
	 */
	public Record(
			int toflag,
			int adddate,
			int messageType,
			String textMsg, 
			String inviteMsg,
			String warningMsg,
			String emotionId,
			String photoId,
			String photoSendId,
			String photoDesc,
			boolean photoCharge,
			String voiceId,
			String voiceType,
			int voiceTime
			) {
		
		if( toflag < 0 || toflag >= ToFlag.values().length ) {
			this.toflag = ToFlag.values()[0];
		} else {
			this.toflag = ToFlag.values()[toflag];
		}
		
		this.adddate = adddate;
		
		if( messageType < 0 || messageType >= MessageType.values().length ) {
			this.messageType = MessageType.values()[0];
		} else {
			this.messageType = MessageType.values()[messageType];
		}
		
		// -- text --
		this.textMsg = textMsg;
		// -- invite --
		this.inviteMsg = inviteMsg;
		// -- warning --
		this.warningMsg = warningMsg;
		// -- emotion --
		this.emotionId = emotionId;
		// -- photo --
		this.photoId = photoId;
		this.photoSendId = photoSendId;
		this.photoDesc = photoDesc;
		this.photoCharge = photoCharge;
		// -- voice -- 
		this.voiceId = voiceId;
		this.voiceType = voiceType;
		this.voiceTime = voiceTime;
	}
	
	public enum ToFlag {
		Receive,
		Send,
	}
	public enum MessageType {
		Text,
		Invite,
		Warning,
		Emotion,
		Voice,
		Photo
	}
	
	public ToFlag toflag;
	public int adddate;
	public MessageType messageType;
	// -- text --
	public String textMsg;
	// -- invite --
	public String inviteMsg;
	// -- warning --
	public String warningMsg;
	// -- emotion --
	public String emotionId;
	// -- photo --
	public String photoId;
	public String photoSendId;
	public String photoDesc;
	public boolean photoCharge;
	// -- voice --
	public String voiceId;
	public String voiceType;
	public int voiceTime;
}