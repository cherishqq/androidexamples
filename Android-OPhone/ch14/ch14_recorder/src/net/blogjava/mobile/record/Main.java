package net.blogjava.mobile.record;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener
{
	private MediaPlayer mediaPlayer;
	private MediaRecorder mediaRecorder = new MediaRecorder();
	private File audioFile;

	@Override
	public void onClick(View view)
	{
		try
		{
			String msg = "";			
			switch (view.getId())
			{
				case R.id.btnStart:
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mediaRecorder
							.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					mediaRecorder
							.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					audioFile = File.createTempFile("record_", ".amr");
					mediaRecorder.setOutputFile(audioFile.getAbsolutePath());
					mediaRecorder.prepare();
					mediaRecorder.start();
					msg = "����¼��...";					
					break;
				case R.id.btnStop:

					if (audioFile != null)
					{
						mediaRecorder.stop();
					}
					msg = "�Ѿ�ֹͣ¼��.";
					
					break;
				case R.id.btnPlay:
					if (audioFile != null)
					{		
						mediaPlayer = new MediaPlayer();
						mediaPlayer.setDataSource(audioFile.getAbsolutePath());
						mediaPlayer.prepare();
						
						mediaPlayer.start();
						mediaPlayer.setOnCompletionListener(new OnCompletionListener()
						{							
							@Override
							public void onCompletion(MediaPlayer mp)
							{
								setTitle("¼���������.");
								
								
							}
						});
						msg = "���ڲ���¼��...";						
					}
					break;

			}
			setTitle(msg);
			Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
		}
		catch (Exception e)
		{
			setTitle(e.getMessage());
		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnStart = (Button) findViewById(R.id.btnStart);
		Button btnStop = (Button) findViewById(R.id.btnStop);
		Button btnPlay = (Button) findViewById(R.id.btnPlay);
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPlay.setOnClickListener(this);
	}
}