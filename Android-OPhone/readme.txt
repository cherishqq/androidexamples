第一部分 准备篇——大军未动，草粮先动 
第1章 Android入门 
1.1 Android的基本概念 
1.1.1 Android简介 
1.1.2 Android的系统构架 
1.2 Android 2.3.x新功能介绍 
1.2.1 显示电池使用状况 
1.2.2 SIP网络电话 
1.2.3 近场通信（NFC） 
1.2.4 控制多个摄像头 
1.3 Android 3.x新功能介绍 
1. 3.1 重新设计的UI 
1.3.2 系统状态条 
1.3. 3 多任务管理 
1.3.4 多重桌面 
1. 3.5 电子邮件（E—mail） 
1. 3.6 联系人管理 
1.3.7 虚拟键盘与剪贴功能 
1. 3.8 相机功能 
1.3.9 视频电话 
1.3.10 支持多核处理器 
1.4 Android开发环境的搭建 
1.4.1 开发Android程序需要些什么 
1.4.2 安装Android SDK 
1.4.3 安装Eclipse插件ADT 
1.4.4 测试ADT是否安装成功 
1.5 Android SDK中的常用命令行工具 
1.5.1 启动和关闭adb服务（adb start.server和adb kill.server） 
1.5.2 查询当前模拟器／设备的实例（adb devices） 
1.5. 3 安装、卸载和运行程序（adb install、adb uninstall和am） 
1.5.4 PC与模拟器或真机交换文件（adbpull和adb push） 
1.5.5 Shell命令 
1.5.6 创建、删除和浏览AVD设备（android） 
1.5.7 获取Android版本对应的ID 
1.5.8 创建SD卡 
1.6 PC上的Android 
1.6.1 Android LiveCD 
1.6.2 AndroidX86 
1.7 Android的学习资源 
1.8 应用程序商店 
1.8.1 Android Market 
1.8.2 Mobile Market（MM） 
1.8. 3 其他应用程序商店 
1.9 小结 
第二部分 基础篇——Android世界的精彩之旅 
第3章 Android应用程序架构 
3.1 Android应用程序中的资源 
3.1.1资源存放在哪里 
3.1.2资源的种类 
3.1.3资源的基本使用方法 
3.2 Android的应用程序组件 
3.2.1活动（Activity）组件 
3.2.2服务（Service）组件 
3.2.3广播接收者（Broadcast receivers）组件 
3.2.4内容提供者（Content providers）组件 
3.3 AndroidManifest.xml文件的结构 
3.4小结 
第4章 建立用户接口 
4.1建立、配置和使用Activity 
4.1.1建立和配置Activity 
4.1.2 Activity的生命周期 
4.1.3 Activity生命周期的演示 
4.2视图（View） 
4.2.1视图简介 
4.2.2使用XML布局文件控制视图 
4.2.3在代码中控制视图 
4.2.4混合使用XML布局文件和代码来控制视图 
4.2.5定制控件（Widget）的3种方式 
4.2.6定制控件——带图像的TrextView 
4.2.7定制控件——带文本标签的EditText 
4.2.8定制控件——可更换表盘的指针时钟 
4.3使用AlertDialog类创建对话框 
4.3.1 AlertDialog类简介 
4.3.2 （确认／取消）对话框 
4.3.3创建询问是否删除文件的 
（确认／取消）对话框 
4.3.4带3个按钮的对话框 
4.3.5创建（覆盖／忽略／取消）对话框 
4.3.6简单列表对话框 
4.3.7单选列表对话框 
4.3.8多选列表对话框 
4.3.9创建3种选择省份的列表对话框 
4.3.10水平进度对话框和圆形进度对话框 
4.3.11水平进度对话框和圆形进度对话框演示 
4.3.12自定义对话框 
4.3.13创建登录对话框 
4.3.14用Activity托管对话框 
4.3.15创建悬浮对话框和触摸任何位置都可以关闭的对话框 
4.4 Toast和Notification 
4.4.1用Toast显示提示信息框 
4.4.2阻止关闭Toast信息框 
4.4.3 Notification与状态栏信息 
4.5菜单 
4.5.1创建选项菜单 
4.5.2设置与菜单项关联的图像和Activity 
4.5.3 响应选项菜单项单击事件的3种方式 
4.5.4动态添加、修改和删除选项菜单 
4.5.5创建带复选框和选项按钮的子菜单 
4.5.6创建上下文菜单 
4.5.7菜单事件 
4.5.8 Activity菜单、子菜单、上下文菜单演示 
4.6布局 
4.6.1框架布局（FrameLayout） ch04_logindialog
4.6.2霓虹灯效果的TextView 
4.6.3线性布局（LinearLayout） 
4.6.4利用LinearLayout将按钮放在屏幕的四角和中心位置 
4.6.5相对布局（RelativeLa）rout） 
4.6.6利用RelativeLayout实现梅花效果的布局 
4.6.7表格布局（TableLayout） 
4.6.8计算器按钮的布局 
4.6.9绝对布局（AbsoluteLayout） 
4.6.10查看apk文件中的布局 
4.7小结 
第5章 控件详解 
5.1显示和编辑文本的控件 
5.1.1显示文本的控件：TextView 
5.1.2在TextView中显示URL及不同字体大小、不同颜色的文本 
5.1.3带边框的TextView 
5.1.4设置TextView控件的行间距 
5.1.5输入文本的控件：EditText 
5.1.6在EditText中输入特定的字符 
5.1.7按回车键显示EditText 
5.1.8 自动完成输入内容的控件：Auto Complete Text View 
5.2按钮与复选框控件 
5.2.1普通按钮控件：Button 
5.2.2异形（圆形、五角星、螺旋形和箭头）按钮 
5.2-3图像按钮控件：Image Button 
5.2.4同时显示图像和文字的按钮 
5.2.5选项按钮控件：RadioButton 
5.2.6开关状态按钮控件：ToggleButton 
5.2.7复选框控件：CheckBox 
5.2.8利用XML布局文件动态创建CheckBox 
5.3 日期与时间控件 
5.3.1输入日期的控件：DatePicker 
5.3.2输入时间的控件：TimePicker 
5.3.3 DatePicker、TimePicker与TextView同步显示日期和时间 
5.3.4显示时钟的控件：AnalogClock和DigitalClock 
5.4进度条控件 
5.4.1进度条控件：ProgressBar 
5.4.2拖动条控件：SeekBar 
5.4.3改变ProgmssBar和SeekBar的颜色 
5.4.4评分控件：RatingBar 
5.5其他重要控件 
5.5.1显示图像的控件：ImageView 
5.5.2可显示图像指定区域的ImageView控件 
5.5.3动态缩放和旋转图像 
5.5.4列表控件：ListView 
5.5.5可以单选和多选的ListView 
5.5.6动态添加、删除ListView列表项 
5.5.7改变ListView列表项选中状态的背景颜色 
5.5.8封装ListView的Activity：ListActivity 
5.5.9使用SimpleAdapter建立复杂的列表项 
5.5.10给应用程序评分 
5.5.11可展开的列表控件：ExpandableListView 
5.5.12下拉列表控件：Spinner 
5.5.13垂直滚动视图控件：ScrollView 
5.5.14水平滚动视图控件：HorizontalScrollView 
5.5.15可垂直和水平滚动的视图 
5.5.16网格视图控件：GridView 
5.5.17可循环显示和切换图像的控件：Gallery和ImageSwitcher 
5.5.18标签控件：TabHost 
5.6小结 
第6章 移动存储解决方案 
6.1最简单的数据存储方式：SharedPreferences 
6.1.1使用SharedPreferences存取数据 
6.1.2数据的存储位置和格式 
6.1.3存取复杂类型的数据 
6.1.4设置数据文件的访问权限 
6.1.5可以保存设置的Activity：PreferenceActivity 
6.2文件的存储 
6.2.1 openFileOutput和openFileInput方法 
6.2.2 SD卡文件浏览器 
6.2.3存取SD卡中的图像 
6.2.4 SAX引擎读取XML文件的原理 
6.2.5将XML数据转换成Java对象 
6.3 SQLite数据库 
6.3.1 SQLite数据库管理工具 
6.3.2创建数据库和表 
6.3.3模糊查询 
6.3.4分页显示记录 
6.3.5事务 
6.4在Android中使用SQLite数据库 
6.4.1 SQLiteOpenHelper类与自动升级数据库 
6.4.2 SimpleCursorAdapter类与数据绑定 
6.4.3带照片的联系人管理系统 
6.4.4将数据库与应用程序一起发布 
6.4.5英文词典 
6.5持久化数据库引擎（db4o） 
6.5.1什么是db4o 
6.5.2下载和安装db4o 
6.5.3创建和打开数据库 
6.5.4向数据库中插入Java对象 
6.5.5从数据库中查询Java对象 
6.5.6高级数据查询 
6.5.7更新数据库中的Java对象 
6.5.8删除数据库中的Java对象 
6.6小结 
第7章 应用程序之间的通信 
7.1 Intent与Activity 
7.1.1用Intent启动Activity，并在Activity之间传递数据 
7.1.2调用其他应用程序中的Activity（拨打电话、浏览网页、发E-mail等） 
7.1.3定制自己的ActivityAction 
7.1.4将电子词典的查询功能共享成一个Activity Action 
7.2接收和发送广播 
7.2.1接收系统广播 
7.2.2开机可自动运行的程序 
7.2.3收到短信了，该做点什么 
7.2.4显示手机电池的当前电量 
7.2.5在自己的应用程序中发送广播 
7.2.6接收联系人系统中发送的添加联系人广播 
7.3小结 
第8章 Android服务 
8.1 Service起步 
8.1.1 Service的生命周期 
8.1.2绑定Activity和Service 
8.1.3在BroadcastReceiver中启动Service 
8.2系统服务 
8.2.1获得系统服务 
8.2.2监听手机来电 
8.2.3来电黑名单 
8.2.4在模拟器上模拟重力感应 
8.2.5手机翻转静音 
8.3时间服务 
8.3.1计时器：Chronometer 
8.3.2预约时间：Handler 
8.3.3定时器：Timer 
8.3.4在线程中更新GUI组件 
8.3.5全局定时器：AlarmManager 
8.3.6定时更换壁纸 
8.3.7多次定时提醒 
8.4跨进程访问（AIDL，服务） 
8.4.1什么是AIDL服务 
8.4.2建立AIDL服务的步骤 
8.4.3建立AIDL服务 
8.4.4传递复杂数据的AIDL服务 
8.5小结 
第9章 网络 
9.1可装载网络数据的控件 
9.1.1装载网络数据的原理 
9.1.2将网络图像装载到ListView控件中 
9.1.3 Google图像画廊（Gallery） 
9.2 WebView控件 
9.2.1用WebView控件浏览网页 
9.2.2手机浏览器 
9.2.3 用WebView控件装载HTML代码 
9.2.4将英文词典整合到Web页中（JavaScript调用Java方法） 
9.3访问HTTP资源 
9.3.1 提交HTTP GET和HTTP POST请求 
9.3.2 HttpURLConnection类 
9.3.3上传文件 
9.3.4远程Apk安装器 
9.3.5调用WebService 
9.3.6通过WebService查询产品信息 
9.4 Internet地址 
9.4.1 Internet地址概述 
9.4.2创建InetAddress对象 
9.4.3判断IP地址类型 
9.5客户端Socket 
9.5.1 Socket类基础 
9.5.2多种连接服务端的方式 
9.5.3客户端Socket的超时 
9.5.4 Socket类的getter和setter方法 
9.5.5 Socket的异常 
9.6服务端Socket 
9.6.1创建ServerSocket对象 
9.6.2设置请求队列的长度 
9.6.3绑定IP地址 
9.6.4默认构造方法的使用 
9.6.5读取和发送数据 
9.6.6关闭连接 
9.7小结 
第10章 多媒体 
10.1 图形 
10.1.1图形绘制基础 
10.1.2绘制基本的图形和文本 
10.1.3绘制位图 
10.1.4用两种方式绘制位图 
10.1.5设置颜色的透明度 
10.1.6可任意改变透明度的位图 
10.1.7旋转图像 
10.1.8旋转动画 
10.1.9扭曲图像 
10.1.10按圆形轨迹扭曲图像 
10.1.11拉伸图像 
10.1.12拉伸图像演示 
10.1.13路径 
10.1.14沿着路径绘制文本 
10.1.15可在图像上绘制图形的画板 
10.2音频和视频 
10.2.1使用MediaPlayer播放MP3文件 
10.2.2使用MediaRecorder录音 
10.2.3使用VideoView播放视频 
10.2.4使用SurfaceView播放视频 
10.3小结 