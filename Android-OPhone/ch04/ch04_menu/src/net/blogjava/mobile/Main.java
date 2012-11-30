package net.blogjava.mobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnMenuItemClickListener,
		OnClickListener
{
	private Menu menu;
	private int menuItemId = Menu.FIRST;

	// Ϊ��ť��ӵ����¼���ע�������Ĳ˵�
	private void init()
	{
		Button button = (Button) findViewById(R.id.btnAddMenu);
		button.setOnClickListener(this);
		EditText editText = (EditText) findViewById(R.id.edittext);
		TextView textView = (TextView) findViewById(R.id.textview);
		// ע�������Ĳ˵�
		registerForContextMenu(button);
		registerForContextMenu(editText);
		registerForContextMenu(textView);

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
	}

	// ��Activity�˵����10���˵���˵����id��10��ʼ
	@Override
	public void onClick(View view)
	{
		if (menu == null)
			return;
		for (int i = 10; i < 20; i++)
		{
			int id = menuItemId++;
			menu.add(1, id, id, "�˵�" + i);
		}
	}

	private void showDialog(String message)
	{
		new AlertDialog.Builder(this).setMessage("�������ˡ�" + message + "���˵���.")
				.show();

	}

	// ����Activity�˵����Ӳ˵��������Ĳ˵��Ĳ˵���ʱ���ø÷���
	@Override
	public boolean onMenuItemClick(MenuItem item)
	{
		Log.d("onMenuItemClick", "true");
		showDialog(item.getTitle().toString());
		return false;
	}

	// ��Activity�˵����3���˵���
	private void addMenu(Menu menu)
	{

		MenuItem addMenuItem = menu.add(1, menuItemId++, 1, "���");
		addMenuItem.setIntent(new Intent(this, AddActivity.class));
		MenuItem deleteMenuItem = menu.add(1, menuItemId++, 2, "ɾ��");
		deleteMenuItem.setIcon(R.drawable.delete);
		deleteMenuItem.setOnMenuItemClickListener(this);
		MenuItem menuItem1 = menu.add(1, menuItemId++, 3, "�˵�1");
		menuItem1.setOnMenuItemClickListener(this);
		MenuItem menuItem2 = menu.add(1, menuItemId++, 4, "�˵�2");

	}

	private void addSubMenu(Menu menu)
	{
		// ����Ӳ˵�
		SubMenu fileSubMenu = menu.addSubMenu(1, menuItemId++, 5, "�ļ�");

		fileSubMenu.setIcon(R.drawable.file);
		fileSubMenu.setHeaderIcon(R.drawable.headerfile);
		// �Ӳ˵���֧��ͼ��
		MenuItem newMenuItem = fileSubMenu.add(1, menuItemId++, 1, "�½�");
		newMenuItem.setCheckable(true);
		newMenuItem.setChecked(true);
		MenuItem openMenuItem = fileSubMenu.add(2, menuItemId++, 2, "��");
		MenuItem exitMenuItem = fileSubMenu.add(2, menuItemId++, 3, "�˳�");
		exitMenuItem.setChecked(true);
		fileSubMenu.setGroupCheckable(2, true, true);

	}

	// ����Menu��ťʱ���ø÷���������Activity�˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		this.menu = menu;
		addMenu(menu);
		addSubMenu(menu);

		// //////////////////�����׳� 10-03 13:17:32.489: DEBUG/e(1667): Attempt to
		// add a sub-menu to a sub-menu.

		// SubMenu subMenu = fileSubMenu.addSubMenu(3, 1, 1, "�Ӳ˵�");
		// subMenu.add(1, 1, 1, "�˵���1");
		// subMenu.add(1, 2, 2, "�˵���2");
		// ////////////////////

		return super.onCreateOptionsMenu(menu);
	}

	// ��Activity�˵���ʾǰ���ø÷���������������������޸�ָ���Ĳ˵���
	@Override
	public boolean onPrepareOptionsMenu(Menu menu)
	{
		Log.d("onPrepareOptionsMenu", "true");
		return super.onPrepareOptionsMenu(menu);
	}

	// �������Ĳ˵��ر�ʱ���ø÷���
	@Override
	public void onContextMenuClosed(Menu menu)
	{
		Log.d("onContextMenuClosed", "true");
		super.onContextMenuClosed(menu);
	}

	// ��Activity�˵���ʾʱ���ø÷�������������� onPrepareOptionsMenu֮�󱻵���
	@Override
	public boolean onMenuOpened(int featureId, Menu menu)
	{
		Log.d("onMenuOpened", "true");
		return super.onMenuOpened(featureId, menu);
	}

	// ��Activity�˵����ر�ʱ���ø÷���
	@Override
	public void onOptionsMenuClosed(Menu menu)
	{
		Log.d("onOptionsMenuClosed", "true");
		super.onOptionsMenuClosed(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		super.onMenuItemSelected(featureId, item);
		Log.d("onMenuItemSelected:itemId=", String.valueOf(item.getItemId()));
		if ("�˵�1".equals(item.getTitle()))
			showDialog("<" + item.getTitle().toString() + ">");
		else if ("�˵�2".equals(item.getTitle()))
			showDialog("<" + item.getTitle().toString() + ">");
		return false;
	}

	// ����ÿһ��Activity�˵���ʱ���ø÷���
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log
				.d("onOptionsItemSelected:itemid=", String.valueOf(item
						.getItemId()));
		return true;
	}

	// ���������Ĳ˵���ĳ���˵���ʱ���ø÷���
	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		Log
				.d("onContextItemSelected:itemid=", String.valueOf(item
						.getItemId()));
		if (!"�Ӳ˵�".equals(item.getTitle().toString()))
			showDialog("*" + item.getTitle().toString() + "*");
		return super.onContextItemSelected(item);
	}

	// ��ʾ�����Ĳ˵�ʱ���ø÷���������Զ���������Ĳ˵���
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo)
	{
		super.onCreateContextMenu(menu, view, menuInfo);
		// menu.clear();
		menu.setHeaderTitle("�����Ĳ˵�");
		menu.setHeaderIcon(R.drawable.face);
		menu.add(0, menuItemId++, Menu.NONE, "�˵���1").setCheckable(true)
				.setChecked(true);
		menu.add(20, menuItemId++, Menu.NONE, "�˵���2");
		menu.add(20, menuItemId++, Menu.NONE, "�˵���3").setChecked(true);
		menu.setGroupCheckable(20, true, true);
		SubMenu sub = menu.addSubMenu(0, menuItemId++, Menu.NONE, "�Ӳ˵�");
		sub.add("�Ӳ˵���1");
		sub.add("�Ӳ˵���2");

	}

}
