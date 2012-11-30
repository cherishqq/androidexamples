package net.blogjava.mobile.cube;

import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;

public class MyRender implements Renderer
{
	
	float rotateTri, rotateQuad;
	
	 int one = 0x10000;
		
		private IntBuffer colorBufferForQuad = IntBuffer.wrap(new int[]{
				
				one/2,one,0,one,
				one/2,one,0,one,
				one/2,one,0,one,
				one/2,one,0,one,
				 
				 one, one/2, 0, one,
				 one, one/2, 0, one,
				 one, one/2, 0, one,
				 one, one/2, 0, one,
				 one,one,0,one,
				 one,one,0,one,
				 one,one,0,one,
				 one,one,0,one,
				 one,0,0,one,
				 one,0,0,one,
				 one,0,0,one,
				 one,0,0,one,
				 
				 
				 0,0,one,one,
				 0,0,one,one,
				 0,0,one,one,
				 0,0,one,one,
				 
				 one,0,one,one,
				 one,0,one,one,
				 one,0,one,one,
				 one,0,one,one,
		});
		
		 private IntBuffer colorBuffer = IntBuffer.wrap(new int[]{				
				 0,one,0,one,
				 one,0,0,one,
				 0,0,one,one,	
				 
				 0,one,0,one,
				 one,0,0,one,
				 0,0,one,one,
				 
				 0,one,0,one,
				 one,0,0,one,
				 0,0,one,one,
				 
				 0,one,0,one,
				 one,0,0,one,
				 0,0,one,one,
		 });
		 
		 
		 private IntBuffer triggerBuffer = IntBuffer.wrap(new int[]{
				0,one,0,
				-one,-one,0,
				one,-one,one,
				
				0,one,0,
				one,-one,one,
				one,-one,-one,
				
				0,one,0,
				one,-one,-one,
				-one,-one,-one,
				
				0,one,0,
				-one,-one,-one,
				-one,-one,one
		 
		 });
		 private IntBuffer quaterBuffer = IntBuffer.wrap(new int[]{
					one,one,-one,
					-one,one,-one,
					one,one,one,
					-one,one,one,
					
					one,-one,one,
					-one,-one,one,
					one,-one,-one,
					-one,-one,-one,
					
					one,one,one,
					-one,one,one,
					one,-one,one,
					-one,-one,one,
					
					one,-one,-one,
					-one,-one,-one,
					one,one,-one,
					-one,one,-one,
					
					-one,one,one,
					-one,one,-one,
					-one,-one,one,
					-one,-one,-one,
					
					one, one, -one,
					one, one, one,
					one, -one, -one,
					one, -one, one,
		 });
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();
		// ���� 2.0�� ��λ����������Ļ 6.0
		gl.glTranslatef(2.0f, 0.0f, -6.0f);
		 //��ת����׶
		gl.glRotatef(rotateTri, 0.0f, 1.0f, 0.0f);
		
	    // �������ö���
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// ������ɫ��Ⱦ
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		
		gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
	
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, triggerBuffer);
		//��������׶
	    for(int i=0; i<4; i++)
	    {
	    	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*3, 3);
	    }
		
		// ��ʼ���������� 
	    
	    gl.glLoadIdentity();
	    
	    // ���� 2.0�� ��λ����������Ļ 6.0
	    gl.glTranslatef(-2.0f, 0.0f, -6.0f);
	    
	    //������ת
	    gl.glRotatef(rotateQuad, 1.0f, 0.0f, 0.0f);
	    
	    
	    gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBufferForQuad);
	    gl.glVertexPointer(3, GL10.GL_FIXED, 0, quaterBuffer);

	    //  ��ʼ����������
	    for(int i=0; i<6; i++)
	    {
	    	gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i*4, 4);
	    }
	    
	    //���������ν���
	    gl.glFinish();

	    //ȡ����������
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
	    
	    //�ı���ת�ĽǶ�
	    rotateTri += 1.0f;
	    rotateQuad -= 1.0f;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		// TODO Auto-generated method stub
		float ratio = (float) width / height;
		//����OpenGL�����Ĵ�С
		gl.glViewport(0, 0, width, height);
		//����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//����ͶӰ����
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
		// ������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		
		// ��ɫ����
		gl.glClearColor(0, 0, 0, 0);
		
		// ������Ȼ���
		gl.glClearDepthf(1.0f);							
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);						
		// ������Ȳ��Ե�����
		gl.glDepthFunc(GL10.GL_LEQUAL);							
		
		// ����ϵͳ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
	}
}