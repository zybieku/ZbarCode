# ZbarCode
用Zbar库实现的Android扫一扫
 - **集成**: 

打开源码项目,找到里面的箭头扫一扫Module
 
![这里写图片描述](http://img.blog.csdn.net/20170829171423532?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvenliaWVrdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

进入大佬自己的Android studio项目,直接点击``File``->``New``->``Import Module`` 选择Demo文件导入即可.
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

![这里写图片描述](http://img.blog.csdn.net/20170829172023788?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvenliaWVrdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
----------
- **效果图**
![这里写图片描述](http://img.blog.csdn.net/20170829170752060?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvenliaWVrdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

----------
#博客  www.znq123.cn