package net.blogjava.mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity implements OnClickListener
{
	private EditText etPhone;

	@Override
	public void onClick(View view)
	{
		
		switch (view.getId())
		{
			
			case R.id.btnCall:
				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
						.parse("tel:" + etPhone.getText().toString()));
				startActivity(callIntent);
				break;
			case R.id.btnCallButton:
				Intent callButtonIntent = new Intent(Intent.ACTION_CALL_BUTTON);
				startActivity(callButtonIntent);
				break;
			case R.id.btnDial:
				Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel:" + etPhone.getText().toString()));
				startActivity(dialIntent);
				break;
			case R.id.btnWeb:
				Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse("http://nokiaguy.blogjava.net"));
				startActivity(webIntent);

				break;
			case R.id.btnSendToEmail:
				Uri uri = Uri.parse("mailto:xxx@abc.com");
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				startActivity(intent);
				break;

			case R.id.btnSendEmail:

				Intent sendEmailIntent = new Intent(Intent.ACTION_SEND);

				sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]
				{ "techcast@126.com" });
				sendEmailIntent.putExtra(Intent.EXTRA_CC, new String[]
				{ "abc@126.com", "test@126.com" });
				sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT,
						"����Android��������������");
				sendEmailIntent.putExtra(Intent.EXTRA_TEXT,
						"1. ��ε�������Ӧ�ó����е�Activity?\n2. ��Ӧ�ó������������ϵͳ�㲥��");

				sendEmailIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendEmailIntent,
						"ѡ������Ϣ�Ŀͻ���"));
				break;
			case R.id.btnAudio:
				Intent audioIntent = new Intent(Intent.ACTION_GET_CONTENT);
				audioIntent.setType("audio/*");
				startActivity(Intent.createChooser(audioIntent, "ѡ����Ƶ����"));
				
				break;
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnCall = (Button) findViewById(R.id.btnCall);
		Button btnCallButton = (Button) findViewById(R.id.btnCallButton);
		Button btnDial = (Button) findViewById(R.id.btnDial);
		Button btnWeb = (Button) findViewById(R.id.btnWeb);
		Button btnAudio = (Button) findViewById(R.id.btnAudio);
		Button btnSendToEmail = (Button) findViewById(R.id.btnSendToEmail);
		Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
		etPhone = (EditText) findViewById(R.id.etPhone);
		btnCall.setOnClickListener(this);
		btnCallButton.setOnClickListener(this);
		btnDial.setOnClickListener(this);
		btnWeb.setOnClickListener(this);
		btnAudio.setOnClickListener(this);
		btnSendToEmail.setOnClickListener(this);
		btnSendEmail.setOnClickListener(this);
	}
}