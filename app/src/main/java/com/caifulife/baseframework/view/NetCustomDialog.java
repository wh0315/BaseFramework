package com.caifulife.baseframework.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.caifulife.baseframework.R;

public class NetCustomDialog extends Dialog {

	public NetCustomDialog(Context context, int theme) {
		super(context, theme);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.net_loading_dialog);
	}

}