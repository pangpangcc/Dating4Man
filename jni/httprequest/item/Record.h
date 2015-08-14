/*
 * Record.h
 *
 *  Created on: 2015-3-2
 *      Author: Max.Chiu
 *      Email: Kingsleyyau@gmail.com
 */

#ifndef RECORD_H_
#define RECORD_H_

#include <string>
using namespace std;


#include <json/json/json.h>
#include "../common/Arithmetic.h"
#include "../RequestLiveChatDefine.h"

class Record {
public:
	void Parse(Json::Value root) {
		if( root.isObject() ) {
			if( root[LIVECHAT_TOFLAG].isString() ) {
				toflag = atoi(root[LIVECHAT_TOFLAG].asString().c_str());
			}

			if( root[LIVECHAT_ADDDATE].isInt() ) {
				adddate = root[LIVECHAT_ADDDATE].asInt();
			}

			string message("");
			if (root[LIVECHAT_MSG].isString()) {
				message = root[LIVECHAT_MSG].asString();
			}

			if( root[LIVECHAT_MSGTYPE].isString() ) {
				int msgType = atoi(root[LIVECHAT_MSGTYPE].asString().c_str());
				switch(msgType) {
				case LRPM_TEXT:
				case LRPM_COUPON:
				case LRPM_AUTO_INVITE:{
					textMsg = message;
					messageType = LRM_TEXT;
				}break;
				case LRPM_INVITE:{
					inviteMsg = message;
					messageType = LRM_INVITE;
				}break;
				case LRPM_WARNING:{
					warningMsg = message;
					messageType = LRM_WARNING;
				}break;
				case LRPM_EMOTION:{
					emotionId = message;
					messageType = LRM_EMOTION;
				}break;
				case LRPM_VOICE:{
					voiceId = message;
					ParsingVoiceMsg(voiceId, voiceType, voiceTime);
					messageType = LRM_VOICE;
				}break;
				case LRPM_PHOTO:{
					ParsingPhotoMsg(message, photoId, photoSendId, photoDesc, photoCharge);
					messageType = LRM_PHOTO;
				}break;
				default:{
					messageType = LRM_UNKNOW;
				}break;
				}
			}
		}
	}

	Record() {
		adddate = 0;
		messageType = LRM_UNKNOW;
		textMsg = "";
		inviteMsg = "";
		warningMsg = "";
		emotionId = "";
		photoId = "";
		photoSendId = "";
		photoDesc = "";
		photoCharge = 0;
		voiceId = "";
		voiceType = "";
		voiceTime = 0;
	}

	virtual ~Record() {

	}

private:
	inline void ParsingVoiceMsg(const string& voiceId, string& fileType, int& timeLen)
	{
		char buffer[512] = {0};
		if (sizeof(buffer) > voiceId.length())
		{
			strcpy(buffer, voiceId.c_str());
			char* pIdItem = strtok(buffer, LIVECHAT_VOICEID_DELIMIT);
			int i = 0;
			while (NULL != pIdItem)
			{
				if (i == 4) {
					fileType = pIdItem;
				}
				else if (i == 5) {
					timeLen = atoi(pIdItem);
				}
				pIdItem = strtok(NULL, LIVECHAT_VOICEID_DELIMIT);
				i++;
			}
		}
	}

	inline void ParsingPhotoMsg(const string& message, string& photoId, string& sendId, string& photoDesc, bool& charge)
	{
		size_t begin = 0;
		size_t end = 0;
		int i = 0;

		while (string::npos != (end = message.find_first_of(LIVECHAT_PHOTO_DELIMIT, begin)))
		{
			if (i == 0) {
				// photoId
				photoId = message.substr(begin, end - begin);
				begin = end + strlen(LIVECHAT_PHOTO_DELIMIT);
			}
			else if (i == 1) {
				// charget
				string strCharget = message.substr(begin, end - begin);
				charge = (strCharget==LIVECHAT_PHOTO_CHARGE_YES ? true : false);
				begin = end + strlen(LIVECHAT_PHOTO_DELIMIT);
			}
			else if (i == 2) {
				// photoDesc
				photoDesc = message.substr(begin, end - begin);
				const int bufferSize = 1024;
				char buffer[bufferSize] = {0};
				if (!photoDesc.empty() && photoDesc.length() < bufferSize) {
					Arithmetic::Base64Decode(photoDesc.c_str(), photoDesc.length(), buffer);
					photoDesc = buffer;
				}
				begin = end + strlen(LIVECHAT_PHOTO_DELIMIT);

				// sendId
				sendId = message.substr(begin);
				break;
			}
			i++;
		}
	}

public:
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
	 * @param photoSendId		图片发送ID
	 * @param photoDesc		图片描述
	 * @param photoCharge	图片是否已付费
	 * @param voiceId		语音ID
	 * @param voiceType		语音文件类型
	 * @param voiceTime		语音时长
	 */
	int toflag;
	long adddate;
	int messageType;
	string textMsg;
	string inviteMsg;
	string warningMsg;
	string emotionId;
	string photoId;
	string photoSendId;
	string photoDesc;
	bool photoCharge;
	string voiceId;
	string voiceType;
	int voiceTime;
};

#endif /* RECORD_H_ */