package net.blogjava.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Main extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		TextView textView1 = (TextView)findViewById(R.id.textview1);
		textView1.getPaint().setFakeBoldText(true);
		
		TextView textView2 = (TextView)findViewById(R.id.textview2);
		textView2.setText(Html.fromHtml("<b>ʹ��HTML���ô���</b>"));
		textView2.getPaint().setFakeBoldText(true);
		
		TextView textView3 = (TextView)findViewById(R.id.textview3);
		textView3.setText("��һ��\r\n�ڶ���\n������");
	}
}