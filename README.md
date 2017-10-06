# 基于图像处理操作QQ的工具foolqq

目前读写QQ的机器人都是基于爬虫分析smartqq报文来实现的，然而smartqq腾讯已经不再维护，其HTTP服务端经常不可用，且经常需要不定时的重启，foolqq应运而生！

![image](https://github.com/shiyafeng/foolqq/blob/master/cap_hat_poker_128px_4073_easyicon.net.png)

# 原理概述

定时截取屏幕，并识别图片关键点像素，确定群会话窗口的位置，并通过剪贴板实现JVM消息和屏幕消息的交换。通过Robot类操作鼠标和键盘。

# 准备工作

1 运行`QQcls.exe`,这是一个一键清屏(F10键)的程序，简化了清屏的步骤。

2 打开你要监控的群或讨论组，截取群或讨论组的头像，截取的范围不要超出头像的范围，如下红色框中范围差不多即可。将截取后的图像另存为png格式，截取后的图像不能有压缩，并为图片起个英文名字（如下是gj.png），这个名字很重要，对foolqq而言，相当于当前群的id。如果你有多个群，重复此步骤。

![image](https://github.com/shiyafeng/foolqq/blob/master/pic2.jpg)

3 经过步骤2，foolqq可以找到qq对话框，接下来需要找到输入和输出的位置，如下图所示，工具栏之上的部分是输出，下方是我们的输入，由于qq会话窗口可以自定义大小且工具栏也可以上下拖动，因此对不同用户而言，窗口的位置信息是不一定的，因此，我们需要一张关键图片来描述输入和输出的位置，且这张图片的名字必须是`point.png`。

![image](https://github.com/shiyafeng/foolqq/blob/master/pic3.jpg)

关于`point.png`，我们选择红色框中的`A`字体标签，截取其中间的横线部分，如下图红色区域所示，务必保证截图中不要包含背景的任何一个像素，这么做的原因是，QQ的背景面板颜色会根据窗口大小、当前群的头像进行渐变色计算，为了提供公共可用的识别点，我们使用下图红色区域的部分。

![image](https://github.com/shiyafeng/foolqq/blob/master/pic4.jpg)

4 最后请不要使用QQ的合并窗口功能，如果你有多个群，将他们平铺在桌面上，这样foolqq可以监控多个群面板、并保证使用enter键可以发送消息而不是enter+ctrl，关闭多彩气泡功能。上述所有图片路径需要放在工程的根目录下。

![image](https://github.com/shiyafeng/foolqq/blob/master/pic5.jpg)

# 用法

```
public static void main(String[] args) throws AWTException, IOException, NativeHookException {

//创建BaseQQWindowContext的实例、传入之前做好的point.png文件

BaseQQWindowContext context=new BaseQQWindowContext(new File("point.png")) {
	@Override
	public void onMessage(String name, QQMsg msg) {
	
        //name是图片名称(不包括扩展名),对前面提到的gj.png图片而言这里name就是gj，因此可以根据name判断到底是哪个群的消息
	
	System.out.println(msg);   //msg包括内容、发送人QQ、昵称、时间
	}
};
}
  
  ```
  
  # 全局锁——BaseQQWindowContext

你可能注意到了，我们操作的鼠标和键盘是特殊共享资源，如果需要在另一个线程中使用robot类进行自定义操作，可能和foolqq的内部方法冲突，而我们上一步创建的context对象可以作为锁，避免冲突。

```
//如果你需要使用robot类，你的代码必须是这样

synchronized(context){
robot...

}

```

# interval与checkInterval

context提供setInterval来设置一系列操作的间隔时间，如双击后等待interval按键，这是一个细粒度的时间控制，目的是给显卡一个缓冲，我们知道电脑操作过快可能会导致无法响应，默认是200ms，而checkInterval是一个完整的周期时间，如果你有3个群，应保证checkInterval大于读取并处理完这3个群的消息所耗的总时间。



