package net.blogjava.mobile.camera;

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class CameraPreview extends Activity
{

	private Preview preview;
	private ImageView ivFocus;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		preview = new Preview(this);
		setContentView(preview);

		ivFocus = new ImageView(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
			preview.takePicture();
		return super.onTouchEvent(event);
	}

	class Preview extends SurfaceView implements SurfaceHolder.Callback
	{
		private SurfaceHolder holder;
		private Camera camera;
		// ����һ��PictureCallback���󣬲�ʵ�����е�onPictureTaken����
		private PictureCallback pictureCallback = new PictureCallback()
		{
			// �÷������ڴ�����������Ƭ����
			@Override
			public void onPictureTaken(byte[] data, Camera camera)
			{
				
				// data����ֵ������Ƭ���ݣ�����Щ������key-value��ʽ���棬�Ա��������ø�Activity�ĳ����
				// �Ի����Ƭ����
				getIntent().putExtra("bytes", data);
				setResult(20, getIntent());
				// ֹͣ��Ƭ����
				camera.stopPreview();
				camera = null;
				// �رյ�ǰ��Activity
				finish();
			}
		};

		// Preview��Ĺ��췽��
		public Preview(Context context)
		{
			super(context);
			// ���SurfaceHolder����
			holder = getHolder();
			// ָ�����ڲ�׽�����¼���SurfaceHolder.Callback����
			holder.addCallback(this);
			// ����SurfaceHolder���������
			holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	
		}

		// ��������ʱ���ø÷���
		public void surfaceCreated(SurfaceHolder holder)
		{
			// ���Camera����
			camera = Camera.open();
			try
			{
				
				// ����������ʾ���������SurfaceHolder����
				camera.setPreviewDisplay(holder);
			}
			catch (IOException exception)
			{
				// �ͷ��ֻ�����ͷ
				camera.release();
				camera = null;
			}
		}

		// ֹͣ����ʱ���ø÷���
		public void surfaceDestroyed(SurfaceHolder holder)
		{
			// �ͷ��ֻ�����ͷ
			camera.release();
		}

		// ����״̬�仯ʱ���ø÷���
		public void surfaceChanged(final SurfaceHolder holder, int format,
				int w, int h)
		{
			try
			{
				Camera.Parameters parameters = camera.getParameters();
				// ������Ƭ��ʽ
				parameters.setPictureFormat(PixelFormat.JPEG);
	
			
				// ������Ļ��������Ԥ䯳ߴ�
				if (getWindowManager().getDefaultDisplay().getOrientation() == 0)
					parameters.setPreviewSize(h, w);
				else
					parameters.setPreviewSize(w, h);
				// ����������Ƭ��ʵ�ʷֱ��ʣ��ڱ����еķֱ�����1024*768
				parameters.setPictureSize(1024, 768);
				// ���ñ����ͼ���С
				camera.setParameters(parameters);
				// ��ʼ����
				camera.startPreview();
				// ׼�����ڱ�ʾ�Խ�״̬��ͼ������ͼ14.7��ʾ�ĶԽ����ţ�
				ivFocus.setImageResource(R.drawable.focus1);
				LayoutParams layoutParams = new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				ivFocus.setScaleType(ScaleType.CENTER);
				addContentView(ivFocus, layoutParams);
				ivFocus.setVisibility(VISIBLE);
				// �Զ��Խ�
				camera.autoFocus(new AutoFocusCallback()
				{
					@Override
					public void onAutoFocus(boolean success, Camera camera)
					{
						if (success)
						{
							// successΪtrue��ʾ�Խ��ɹ����ı�Խ�״̬ͼ��һ����ɫ��pngͼ��
							ivFocus.setImageResource(R.drawable.focus2);
						}
					}
				});
			}
			catch (Exception e)
			{
			}
		}

		// ֹͣ���գ������������Ƭ����PictureCallback�ӿڵ�onPictureTaken����
		public void takePicture()
		{
			if (camera != null)
			{
				
				camera.takePicture(null, null, pictureCallback);
			}
		}
	}

}