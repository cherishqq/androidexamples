package com.derek.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CustomView  extends View{

	
	public CustomView(Context context) {
		super(context);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		
		super.onDraw(canvas);
		
		RectF rectF = new RectF();
		Paint p = new Paint();
//	    p.setAntiAlias(true);  
	//    p.setColor(Color.GREEN);
	    rectF.set(0, 0, this.getWidth(), this.getHeight());
	    // inset 就是画边框...正向里,负向外
//	    rectF.inset(10, 10);
	    rectF.intersect(5, 5, 30, 30);
		canvas.drawRect(rectF, p);
//		canvas.drawBitmap(null, null, rectF, null);
	}
	

}
