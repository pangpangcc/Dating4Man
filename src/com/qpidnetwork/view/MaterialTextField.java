package com.qpidnetwork.view;

import android.content.Context;
import android.os.Vibrator;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.qpidnetwork.dating.R;

public class MaterialTextField extends LinearLayout implements OnClickListener{

	private float density = getContext().getResources().getDisplayMetrics().density;
	
	public int normal_color = getContext().getResources().getColor(R.color.thin_grey);
	public int focus_color = getContext().getResources().getColor(R.color.green);
	
	private ThisTextWatcher mTextWatcher;
	private OnEditorCallback mEditorEvent;
	private OnFocuseChangedCallback mFocuseChangedCallback;
	
	
	public MaterialTextField(Context context, AttributeSet attrs){
		super(context, attrs);
		createView(context);
	}
	
	
	public MaterialTextField(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		createView(context);
	}
	
	private void createView(Context context){

		this.setOrientation(LinearLayout.VERTICAL);
		
		LayoutInflater.from(context).inflate(R.layout.view_material_edittext, this, true);
		
//		this.addView(tile);
		if (isInEditMode()) { 
			return; 
		}
		FrameLayout.LayoutParams dividerParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)(1.0f * density));
		dividerParams.setMargins(0, 0, 0, (int)(8.0f * density));
		dividerParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
		getDivider().setLayoutParams(dividerParams);
		
		getEditor().setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus){
					FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getDivider().getLayoutParams();
					params.height = (int)(2.0f * density);
					getDivider().setLayoutParams(params);
					getDivider().setBackgroundColor(focus_color);
				}else{
					FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getDivider().getLayoutParams();
					params.height = (int)(1.0f * density);
					getDivider().setLayoutParams(params);
					getDivider().setBackgroundColor(normal_color);
				}
				
				if (mFocuseChangedCallback != null) mFocuseChangedCallback.onFocuseChanged(v, hasFocus);
			}
			
		});
		
		
		getEditor().setOnEditorActionListener(new OnEditorActionListener(){

			@Override
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				// TODO Auto-generated method stub
				if (mEditorEvent != null) return mEditorEvent.onEditorAction(v, actionId, event);
				return false;
			}
			
		});
		
		getEditor().addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				if (mTextWatcher != null) mTextWatcher.afterTextChanged(arg0);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				if (mTextWatcher != null) mTextWatcher.beforeTextChanged(arg0, arg1, arg2, arg3);
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (mTextWatcher != null) mTextWatcher.onTextChanged(s, start, before, count);
			}
			
		});
		
		
	}

	
	
	
	public void setOnFocusChangedCallback(OnFocuseChangedCallback callback){
		mFocuseChangedCallback = callback;
	}
	

	public void setEditorEvent(OnEditorCallback callback){
		mEditorEvent = callback;
	}
	
	public void setTextWatcher(ThisTextWatcher callback){
		mTextWatcher = callback;
	}
	
	public void setText(CharSequence text){
		getEditor().setText(text);
	}
	
	public void setHint(CharSequence text){
		getEditor().setHint(text);
	}
	
	public void setTextColor(int color){
		getEditor().setTextColor(color);
	}
	
	public void setTextSize(int size){
		getEditor().setTextSize(size);
	}
	
	public void setNumber(){
		getEditor().setInputType(InputType.TYPE_CLASS_NUMBER);
	}
	
	public void setEmail(){
		getEditor().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
	}
	
	public void setNoPredition(){
		getEditor().setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
	}
	
	public EditText getEditor(){
		return (EditText)this.findViewById(R.id.edit);
	}
	
	public CharSequence getText(){
		return getEditor().getText();
	}
	
	public View getDivider(){
		return (View)this.findViewById(R.id.divider);
	}
	
	public void setFocusedStateColor(int color){
		focus_color = color;
		if (getEditor().isFocused()){
			getDivider().setBackgroundColor(focus_color);
		}
	}
	
	public void setNormalStateColor(int color){
		normal_color = color;
		if (!getEditor().isFocused()){
			getDivider().setBackgroundColor(normal_color);
		}
	}
	
	public void setPassword(){
		getEditor().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
	}
	
	public void setVisiblePassword(){
		getEditor().setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
	}
	
	
	public void setError(int highlightColor, boolean vibrate){
		setError(highlightColor, vibrate, true);
	}
	
	public void setError(int highlightColor, boolean vibrate, boolean shouldFocusIfNotFocused){
		
		if(vibrate){
			try{
				Vibrator vibrator = (Vibrator)this.getContext().getSystemService(Context.VIBRATOR_SERVICE);  
		        long [] pattern = {100, 200, 0};   //stop | vibrate | stop | vibrate
		        vibrator.vibrate(pattern, -1); 
			}catch(Exception e){
				//No vibrate if no permission
			}
		}
		if (shouldFocusIfNotFocused) {
			getEditor().requestFocus();
		}else{
			FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) getDivider().getLayoutParams();
			params.height = (int)(2.0f * density);
			getDivider().setLayoutParams(params);
		}
		getDivider().setBackgroundColor(highlightColor);
		Animation shake = AnimationUtils.loadAnimation(this.getContext(), R.anim.shake_anim);
		this.startAnimation(shake);
	}
	
	
	public interface OnFocuseChangedCallback{
		public void onFocuseChanged(View v, boolean hasFocus);
	}
	

	public interface OnEditorCallback{
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event);
	}
	
	public interface ThisTextWatcher{
		public void afterTextChanged(Editable arg0);
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3);
		public void onTextChanged(CharSequence s, int start, int before, int count);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		getEditor().requestFocus();
	}
	

	
}
