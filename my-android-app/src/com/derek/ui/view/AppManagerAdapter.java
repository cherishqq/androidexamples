package com.derek.ui.view;

import java.util.ArrayList;

import com.derek.ui.view.PinnedHeaderListView.PinnedHeaderAdapter;

import android.content.Context;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;

public abstract class AppManagerAdapter extends BaseAdapter implements PinnedHeaderAdapter,
		OnItemClickListener, OnItemLongClickListener{

	/** ���е�����Ԫ�� */
	protected ArrayList<String> dataset = null;
	/** Context */
	protected Context mContext = null;

	/**
	 * ���캯��
	 * 
	 * @param context
	 *            Context
	 * @param data
	 *            ����Դ
	 */
	public AppManagerAdapter(Context context, ArrayList<String> data) {
		this.mContext = context;
		this.dataset = data;
	}
}
