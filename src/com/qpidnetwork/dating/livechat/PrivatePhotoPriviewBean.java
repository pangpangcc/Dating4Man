package com.qpidnetwork.dating.livechat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qpidnetwork.livechat.LCMessageItem;

/**
 * 封装以便实现Activity间传递
 * @author Hunter
 *
 */
public class PrivatePhotoPriviewBean implements Serializable{

	private static final long serialVersionUID = -6815179297951124072L;
	
	public List<LCMessageItem> msgList;
	
	public int currPosition;
	
	public PrivatePhotoPriviewBean(){
		currPosition = 0;
		msgList = new ArrayList<LCMessageItem>();
	}
	
	public PrivatePhotoPriviewBean(int currPosition, List<LCMessageItem> msgList){
		this.msgList = msgList;
		this.currPosition = currPosition;
	}
}
