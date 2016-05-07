# MagicViewPager
单页显示3个Item的ViewPager炫酷切换效果，适用于Banner等。

## 效果图

* Rotate Y

<img src="screenshot/rotate_y.gif" width="420px"/>


* Rotate Down

<img src="screenshot/rotate_down.gif" width="420px"/>

* Rotate Up

<img src="screenshot/rotate_up.gif" width="420px"/>

* Alpha

<img src="screenshot/alpha.gif" width="420px"/>

* ScaleIn

<img src="screenshot/scaleIn.gif" width="420px"/>


* ScaleIn + Alpha + Rotate Down

<img src="screenshot/zuhe.gif" width="420px"/>

## 使用

###（1）引入

```
compile `com.zhy:magic-viewpager:1.0.0`
```

###（2）示例

* 布局文件

```
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="160dp"
    android:clipChildren="false"
    android:layout_centerInParent="true"
    android:background="#aadc71ff"
    >
    <android.support.v4.view.ViewPager
        android:id="@+id/id_viewpager"
        android:layout_width="match_parent"
        android:layout_marginLeft="60dp"
        android:layout_marginRight="60dp"
        android:layout_height="120dp"
        android:layout_gravity="center"
        >
    </android.support.v4.view.ViewPager>

</FrameLayout>
```
注意外层`android:layout_centerInParent="true"`.

* 编码

```
mViewPager.setPageMargin(20);//设置page间间距，自行根据需求设置
mViewPager.setOffscreenPageLimit(3);//>=3
mViewPager.setAdapter...//写法不变

//setPageTransformer 决定动画效果
mViewPager.setPageTransformer(true, new 
							RotateDownPageTransformer());
```



##目前可选动画

* AlphaPageTransformer
* RotateDownPageTransformer
* RotateUpPageTransformer
* RotateYTransformer
* NonPageTransformer
* ScaleInTransformer

其中AlphaPageTransformer、NonPageTransformer、单独使用，需要调用`  mViewPager.setClipChildren(false);`，其余不用。

动画间可以自由组合，例如：

```
mViewPager.setPageTransformer(true, 
	new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
```

##致谢

本文是我在审公众号投稿时，由本篇文章产生灵感：[Android超高仿QQ附近的人搜索展示（一）](http://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650820073&idx=1&sn=9e084723624180f7ab28e54f2aef132c&scene=23&srcid=0506b08maFirw2pBvnewcDsp#rd) ，ps:欢迎关注我的公众号。

除上文外编码过程中参考：

* http://blog.csdn.net/lmj623565791/article/details/40411921/