package net.blogjava.mobile.widget;

import net.blogjava.mobile.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LabelEditText extends LinearLayout
{
	private TextView textView;
	private String labelText;
	private int labelFontSize;
	private String labelPosition;

	public LabelEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		//  ��ȡlabelText���Ե���ԴID
		int resourceId = attrs.getAttributeResourceValue(null, "labelText", 0);
		//  δ�����ԴID��������ȡ����ֵ
		if (resourceId == 0)
			labelText = attrs.getAttributeValue(null, "labelText");
		//  ����Դ�ļ��л��labelText���Ե�ֵ
		else
			labelText = getResources().getString(resourceId);
		//  ��������ַ�ʽ��δ���labelTex���Ե�ֵ����ʾδ���ø����ԣ��׳��쳣
		if (labelText == null)
		{
			throw new RuntimeException("��������labelText����.");
		}
		//  ���labelFontSize���Ե���ԴID
		resourceId = attrs.getAttributeResourceValue(null, "labelFontSize", 0);
		//  ������ȡlabelFontSize���Ե�ֵ�����δ���ø����ԣ�������ֵ��Ϊ14
		if (resourceId == 0)
			labelFontSize = attrs.getAttributeIntValue(null, "labelFontSize",
					14);
		//  ����Դ�ļ��л��labelFontSize���Ե�ֵ
		else
			labelFontSize = getResources().getInteger(resourceId);
		//  ���labelPosition���Ե���ԴID
		resourceId = attrs.getAttributeResourceValue(null, "labelPosition", 0);
		//  ������ȡlabelPosition���Ե�ֵ
		if (resourceId == 0)
			labelPosition = attrs.getAttributeValue(null, "labelPosition");
		//  ����Դ�ļ��л��labelPosition���Ե�ֵ
		else
			labelPosition = getResources().getString(resourceId);
		//  ���δ����labelPosition����ֵ����������ֵ��Ϊleft
		if (labelPosition == null)
			labelPosition = "left";
		
		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		//  ���LAYOUT_INFLATER_SERVICE����
		li = (LayoutInflater) context.getSystemService(infService);
		LinearLayout linearLayout = null;
		//  ����labelPosition���Ե�ֵװ�ز�ͬ�Ĳ����ļ�
		if("left".equals(labelPosition))
			linearLayout = (LinearLayout)li.inflate(R.layout.labeledittext_horizontal, this);
		else if("top".equals(labelPosition))
			linearLayout = (LinearLayout)li.inflate(R.layout.labeledittext_vertical, this);
		else
			throw new RuntimeException("labelPosition���Ե�ֵֻ����left��top.");
		
		//  ����Ĵ������Ӧ�Ĳ����ļ��л����TextView���󣬲�����LabelTextView������ֵ����TextView������
		textView = (TextView) findViewById(R.id.textview);
		textView.setTextSize((float)labelFontSize);			
		textView.setTextSize(labelFontSize);
		textView.setText(labelText);

	}

}
