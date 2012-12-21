/*
 * Copyright (C) 2012 The Android Open Source Project
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

package com.android.common;

import com.android.utils.ImageWorker.ImageWorkerAdapter;


/**
 * Some simple test data to use for this sample app.
 */
public class Images {

    /**
     * This are PicasaWeb URLs and could potentially change. Ideally the PicasaWeb API should be
     * used to fetch the URLs.
     */
    public final static String[] imageUrls = new String[] {
 	   "http://g.hiphotos.baidu.com/baike/pic/item/e850352ac65c1038b5f3803fb2119313b17e89ba.jpg",
       "http://www.baidujpw.cn/tu/UploadPic/2012-12/201212208584343587.jpg",
       "http://img5.iqilu.com/c/u/2012/1213/1355387049422.jpg",
       "http://www.dazhong.tv/uploads/allimg/c121128/135405415TJ30-J400.jpg",
       "http://imgsrc.baidu.com/forum/pic/item/8e335c6034a85edfd376c5f649540923dc54756a.jpg",
       "http://imgsrc.baidu.com/forum/pic/item/42cd3bc79f3df8dc52f8b444cd11728b4610281b.jpg",
       "http://pic.7y7.com/20129/2012092936893913.jpg",
       "http://www.baby611.com/pic/091224/lyf/01b9e97f43fc98120dd7da37.jpg",
       "http://www.mf06.com/e/pic/201008/20100806070238362.jpg",
       "http://images.kkkwww.com/2010/05/12/39eef294679060a8c577ca3e0723620f.jpg",
       "http://t1.baidu.com/it/u=1667058993,892628457&fm=23&gp=0.jpg",
       "http://gb.cri.cn/mmsource/images/2006/09/29/ea060929026.jpg",
       "http://pic5.nipic.com/20100126/4264500_160313033361_2.jpg",
       "http://www.baby611.com/pic/091224/lyf/a0ab1cd6e93f1a2fa08bb749.jpg",
       "http://wenwen.soso.com/p/20090502/20090502170138-704843979.jpg",
       "http://image.new-more.com/2011/0119/201101191230482082.jpg",
       "http://t1.baidu.com/it/u=28863468,1819625664&fm=52&gp=0.jpg",
       "http://t2.baidu.com/it/u=1025449785,3600033552&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=627942650,3024273542&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=3724291276,413966316&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=3001333522,2030582827&fm=25&gp=0.jpg",
       "http://t1.baidu.com/it/u=2360061703,1037028686&fm=25&gp=0.jpg",
       "http://t1.baidu.com/it/u=3891574805,2533150798&fm=25&gp=0.jpg",
       "http://t2.baidu.com/it/u=4013293146,797366700&fm=25&gp=0.jpg",
       "http://t1.baidu.com/it/u=709656255,3651666456&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=856004311,3480272303&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=1290362942,2833648812&fm=25&gp=0.jpg",
       "http://t3.baidu.com/it/u=3810518164,3270405470&fm=25&gp=0.jpg",
       "http://t1.baidu.com/it/u=998648037,2397926331&fm=25&gp=0.jpg",
       "http://t2.baidu.com/it/u=3375133805,1634009729&fm=25&gp=0.jpg",
    };

    /**
     * This are PicasaWeb thumbnail /СͼƬurl/  URLs and could potentially change. Ideally the PicasaWeb API
     * should be used to fetch the URLs.
     */
    public final static String[] imageThumbUrls = new String[] {
    	   "http://g.hiphotos.baidu.com/baike/pic/item/e850352ac65c1038b5f3803fb2119313b17e89ba.jpg",
           "http://www.baidujpw.cn/tu/UploadPic/2012-12/201212208584343587.jpg",
           "http://img5.iqilu.com/c/u/2012/1213/1355387049422.jpg",
           "http://www.dazhong.tv/uploads/allimg/c121128/135405415TJ30-J400.jpg",
           "http://imgsrc.baidu.com/forum/pic/item/8e335c6034a85edfd376c5f649540923dc54756a.jpg",
           "http://imgsrc.baidu.com/forum/pic/item/42cd3bc79f3df8dc52f8b444cd11728b4610281b.jpg",
           "http://pic.7y7.com/20129/2012092936893913.jpg",
           "http://www.baby611.com/pic/091224/lyf/01b9e97f43fc98120dd7da37.jpg",
           "http://www.mf06.com/e/pic/201008/20100806070238362.jpg",
           "http://images.kkkwww.com/2010/05/12/39eef294679060a8c577ca3e0723620f.jpg",
           "http://t1.baidu.com/it/u=1667058993,892628457&fm=23&gp=0.jpg",
           "http://gb.cri.cn/mmsource/images/2006/09/29/ea060929026.jpg",
           "http://pic5.nipic.com/20100126/4264500_160313033361_2.jpg",
           "http://www.baby611.com/pic/091224/lyf/a0ab1cd6e93f1a2fa08bb749.jpg",
           "http://wenwen.soso.com/p/20090502/20090502170138-704843979.jpg",
           "http://image.new-more.com/2011/0119/201101191230482082.jpg",
           "http://t1.baidu.com/it/u=28863468,1819625664&fm=52&gp=0.jpg",
           "http://t2.baidu.com/it/u=1025449785,3600033552&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=627942650,3024273542&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=3724291276,413966316&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=3001333522,2030582827&fm=25&gp=0.jpg",
           "http://t1.baidu.com/it/u=2360061703,1037028686&fm=25&gp=0.jpg",
           "http://t1.baidu.com/it/u=3891574805,2533150798&fm=25&gp=0.jpg",
           "http://t2.baidu.com/it/u=4013293146,797366700&fm=25&gp=0.jpg",
           "http://t1.baidu.com/it/u=709656255,3651666456&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=856004311,3480272303&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=1290362942,2833648812&fm=25&gp=0.jpg",
           "http://t3.baidu.com/it/u=3810518164,3270405470&fm=25&gp=0.jpg",
           "http://t1.baidu.com/it/u=998648037,2397926331&fm=25&gp=0.jpg",
           "http://t2.baidu.com/it/u=3375133805,1634009729&fm=25&gp=0.jpg",
    };

    /**
     * Simple static adapter to use for images.
     */
    public final static ImageWorkerAdapter imageWorkerUrlsAdapter = new ImageWorkerAdapter() {
        @Override
        public Object getItem(int num) {
            return Images.imageUrls[num];
        }

        @Override
        public int getSize() {
            return Images.imageUrls.length;
        }
    };

    /**
     * Simple static adapter to use for image thumbnails.
     */
    public final static ImageWorkerAdapter imageThumbWorkerUrlsAdapter = new ImageWorkerAdapter() {
        @Override
        public Object getItem(int num) {
            return Images.imageThumbUrls[num];
        }

        @Override
        public int getSize() {
            return Images.imageThumbUrls.length;
        }
    };
}
