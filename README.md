# ZbarCode
用Zbar库实现的Android扫一扫
 - **集成**: 

下载打开源码项目,大佬可以先运行看下效果,如果需集成,就使用箭头得扫一扫Module
 
![这里写图片描述](https://github.com/zybieku/ZbarCode/blob/master/image/demo.png)

大佬进入自己的Android studio项目,直接点击``File``->``New``->``Import Module`` 选择Demo文件导入即可.
记得在``App`` 的`` gradle``添加依赖. ``  compile project(':zbarcode')``  

![这里写图片描述](http://img.blog.csdn.net/20161127170654974)


----------



###使用代码
 进入扫一扫
``` java
Intent intent1 = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent1, QR_CODE);
```
获取返回的结果
``` java

@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
 super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_CODE) {
            Bundle b=data.getExtras();
            String result = b.getString(CaptureActivity.EXTRA_STRING);
            Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
        }
```
> **注:** 请根据大佬自己项目的需求,配置右边SO库架构,对应删掉左边多余的文件夹

![这里写图片描述](https://github.com/zybieku/ZbarCode/blob/master/image/ndk.png)
----------
- **效果图**
![这里写图片描述](https://github.com/zybieku/ZbarCode/blob/master/image/GIF.gif)

----------

> **注:** 喜欢的大佬别忘了点个star

----------
#博客  www.znq123.cn