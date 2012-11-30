package net.blogjava.mobile;

import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public class MyRender implements Renderer
{

	int one = 0x10000;
	// �����ε�3������
	private IntBuffer triggerBuffer = IntBuffer.wrap(new int[]
	{ 0, one, 0, // �϶���
			-one, -one, 0, // ���¶���
			one, -one, 0, }); // ���¶���
	// �����ε�4������
	private IntBuffer quaterBuffer = IntBuffer.wrap(new int[]
	{ one, one, 0, // ���ϽǶ���
			-one, one, 0, // ���ϽǶ���
			one, -one, 0, // ���½Ƕ���
			-one, -one, 0 }); // ���½Ƕ���
	private IntBuffer quaterBuffer1 = IntBuffer.wrap(new int[]
	{ one, one, 0, // ���ϽǶ���
			-one, one, 0,-one, -one, 0, // ���ϽǶ���
			// ���½Ƕ���
			one, -one, 0 }); // ���½Ƕ���

	@Override
	public void onDrawFrame(GL10 gl)
	{
		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// �������ö���
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();
		// ���� 1.5 ��λ����������Ļ 6.0
		gl.glTranslatef(1.5f, 0.0f, -6.0f);

		// ���������εĶ�������
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);
		// ����������
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);

		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();

		// ���� 2.0 ��λ����������Ļ 6.0
		gl.glTranslatef(-2.0f, 0.0f, -6.0f);
		// ���������εĶ�������
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, quaterBuffer);
		// ����������
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		// gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float ratio = (float) width / height;
		// ����OpenGL�����Ĵ�С
		gl.glViewport(0, 0, width, height);
		// ����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// ����ͶӰ����
		gl.glLoadIdentity();
		// �����ӿڵĴ�С
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		// ѡ��ģ�͹۲����
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		// ����ģ�͹۲����
		gl.glLoadIdentity();

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
	}

}
