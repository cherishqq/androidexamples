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
		//  �Զ�ʶ�����ӵķ�ʽ
		TextView tvWebURL = (TextView) findViewById(R.id.tvWebURL);
		tvWebURL.setText("���߲��ͣ�http://nokiaguy.blogjava.net");

		TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
		tvEmail.setText("�����ʼ�:techcast@126.com");

		TextView tvPhone = (TextView) findViewById(R.id.tvPhone);
		tvPhone.setText("��ϵ�绰:024-12345678");
		
		//  �ڴ��������ô�HTML��ǩ���ı�
		TextView textView2 = (TextView) findViewById(R.id.textview2);
		textView2
				.setText(Html
						.fromHtml("���߲��ͣ�<a href='http://nokiaguy.blogjava.net'>http://nokiaguy.blogjava.net</a><h1><i><font color='#0000FF'>h1���֡�б�塢��ɫ</font></i></h5></h1><h3>h3����</h3><h5><font color='#CC0000'>����</font></h5>"));
	}
}