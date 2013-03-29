/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.view;

import com.example.android.apis.R;
import com.example.android.apis.view.listview.List1;
import com.example.android.apis.view.listview.List8;

import android.app.ActivityGroup;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.Intent;

/**
 * An example of tab content that launches an activity via {@link android.widget.TabHost.TabSpec#setContent(android.content.Intent)}
 */

/**
 * 
 * @author Derek.pan
 * 
 *  tabHost ÊÇ·Å TabSpec µÄÈÝÆ÷
 *
 */
public class TabsBottom extends ActivityGroup  {

	public static TabHost tab_host;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomtab);

        tab_host = (TabHost) findViewById(R.id.edit_item_tab_host);
        
        tab_host.setup(this.getLocalActivityManager());

        TabSpec ts1 = tab_host.newTabSpec("TAB_WEATHER");
        ts1.setIndicator("Weather");
        
        ts1.setContent(new Intent(this, List1.class));
        tab_host.addTab(ts1);

        TabSpec ts2 = tab_host.newTabSpec("TAB_MAIL");
        ts2.setIndicator("Mail");
        ts2.setContent(new Intent(this, List8.class));
        tab_host.addTab(ts2);

        TabSpec ts3 = tab_host.newTabSpec("TAB_JUMP");
        ts3.setIndicator("Jump");
        ts3.setContent(new Intent(this, Controls2.class));
        tab_host.addTab(ts3);

        tab_host.setCurrentTab(0);
    }
}
