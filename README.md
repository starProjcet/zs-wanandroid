# zs-wan-android

## 项目介绍
wan android是基于鸿洋大神提供的api进行开的一款Android社区app，内部共包含五个模块：首页、项目、广场、公众号、我的

## 首页
首页主要包含文章列表和banner位，可以快速对最新最热的文章进行预览

## 项目
可快速预览完整项目、跨平台项目以及一些自定义组件的文章和对应的源码


## 广场
>* 体系:根据知识点划分文章体系
>* 导航:根据知识点类别快速定位


## 公众号
包含Android领域知名公众号博主


### 我的
我的中有八个小模块：
>* 足迹:保存浏览历史，因为没有相关api，所以采用本地数据库保存
>* 排名:查看积分排名
>* 积分:查看积分增长记录
>* 收藏:查看收藏的文章
>* 我的文章:自己分享的文章
>* 关于网站:WanAndroid官网
>* 轻松一下:内含高质量小姐姐照片
>* 设置:


##应用技术
本App基于MVP架构通过Kotlin语言进行开发，用到的第三方库如下
>* rxjava
>* retrofit(对网络层做了二次封装，将服务端数据脱壳，统一处理错误信息)
>* glide
>* SmartRefreshLayout
>* eventbus
>* avi:library
>* BaseRecyclerViewAdapterHelper
>* greendao
>* MagicIndicator
>* easypermissions
>* LabelsView

