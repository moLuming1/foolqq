# 基于图像处理操作QQ的工具foolqq

目前读写QQ的机器人都是基于爬虫分析smartqq报文来实现的，然而smartqq腾讯已经不再维护，其HTTP服务端经常不可用，且经常需要不定时的重启，foolqq应运而生！

![image](https://github.com/shiyafeng/foolqq/blob/master/cap_hat_poker_128px_4073_easyicon.net.png)

# 原理概述

定时截取屏幕，并识别图片关键点像素，确定群会话窗口的位置，并通过剪贴板实现JVM消息和屏幕消息的交换。通过Robot类操作鼠标和键盘。

# 准备工作

运行`QQcls.exe`,这是一个一键清屏(F10键)的程序，简化了清屏的步骤。

打开你要监控的群或讨论组，截取群或讨论组的头像，截取的范围不要超出头像的范围，如下红色框中范围差不多即可。将截取后的图像另存为png格式，截取后的图像不能有压缩，并为图片起个英文名字（如下是gj.png），这个名字很重要，对foolqq而言，相当于当前群的id。

![image](https://github.com/shiyafeng/foolqq/blob/master/pic2.jpg)
