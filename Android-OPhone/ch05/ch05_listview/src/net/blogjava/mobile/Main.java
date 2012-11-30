package net.blogjava.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class Main extends Activity implements OnItemSelectedListener, OnItemClickListener
{
	private static String[] data = new String[]
		             	                   		{ "��������", "���ν�գ����˰棩2", "�ھ���", "��������", "�˹�����", "������", "���۰�ͯľ ", "δ��սʿ",
		             	                   				"�Ǽʴ���",
		             	                   				"٪�޼͹�԰2:ʧ�������   ��飺��Ƭԭ����ʧ������硷����ʷ���ģ�˹Ƥ���������졶٪�޼͹�԰���ĸ߸�����ѧר�ҽܷ�߲��ף��ػ�������������ɣ�ɵ���" };

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemclick", "click " + position + " item");
		
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id)
	{
		Log.d("itemselected", "select " + position + " item");				
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent)
	{
		Log.d("nothingselected", "nothing selected");		
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ListView lvCommonListView = (ListView) findViewById(R.id.lvCommonListView);

		//��ArrayAdapter���췽�������һ�������ĳ�dataList��ϵͳ�ͻ����List���������
		//List<String> dataList = new ArrayList<String>();
		//dataList.add("��������");
		//dataList.add("��������");
		ArrayAdapter<String> aaData = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data);
		
		lvCommonListView.setAdapter(aaData);		
		lvCommonListView.setSelection(6);		
		lvCommonListView.setOnItemClickListener(this);
		lvCommonListView.setOnItemSelectedListener(this);

		
	}

}
