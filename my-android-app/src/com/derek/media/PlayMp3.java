package com.derek.media;

import com.derek.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class PlayMp3 extends Activity implements OnClickListener,
		OnCompletionListener
{
	private MediaPlayer mediaPlayer;
	private Button btnPause;

	@Override
	public void onCompletion(MediaPlayer mp)
	{
		mp.release();
		setTitle("��Դ�Ѿ��ͷ�");

	}

	@Override
	public void onClick(View view)
	{
		try
		{
			switch (view.getId())
			{
				case R.id.btnStart1:
					mediaPlayer = MediaPlayer.create(this, R.raw.music);
					mediaPlayer.setOnCompletionListener(this);
					if (mediaPlayer != null)
						mediaPlayer.stop();
					mediaPlayer.prepare();
					mediaPlayer.start();
					break;

				case R.id.btnStart2:
					mediaPlayer = new MediaPlayer();
					mediaPlayer.setDataSource("/sdcard/music.mp3");

					mediaPlayer.prepare();
					mediaPlayer.start();
					break;
				case R.id.btnStop:
					if (mediaPlayer != null)
					{
						mediaPlayer.stop();
					}
					break;
				case R.id.btnPause:
					if (mediaPlayer != null)
					{
						if ("����".equals(btnPause.getText().toString()))
						{
							mediaPlayer.start();
							btnPause.setText("��ͣ");
						}
						else if ("��ͣ".equals(btnPause.getText().toString()))
						{
							mediaPlayer.pause();
							btnPause.setText("����");
						}
					}
			}
		}
		catch (Exception e)
		{

		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playmp3);
		Button btnStart1 = (Button) findViewById(R.id.btnStart1);
		Button btnStart2 = (Button) findViewById(R.id.btnStart2);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		btnPause = (Button) findViewById(R.id.btnPause);
		btnStart1.setOnClickListener(this);
		btnStart2.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPause.setOnClickListener(this);

	}
}