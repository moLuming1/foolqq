# 基于图像处理操作QQ的工具foolqq

目前读写QQ的机器人都是基于爬虫分析smartqq报文来实现的，然而smartqq腾讯已经不再维护，其HTTP服务端经常不可用，且经常需要不定时的重启，foolqq应运而生！

![image](https://github.com/shiyafeng/foolqq/blob/master/cap_hat_poker_128px_4073_easyicon.net.png)

# 原理概述

定时截取屏幕，并识别图片关键点像素，确定群会话窗口的位置，并通过剪贴板实现JVM消息和屏幕消息的交换。通过Robot类操作鼠标和键盘。

# 准备工作

运行`QQcls.exe`,这是一个一键清屏(F10)的程序，简化了清屏的步骤。
