package com.zs.wanandroid.entity

/**
 * 项目实体类
 *
 * @author zs
 * @data 2020-03-14
 */
class ProjectEntity {
    /**
     * apkLink :
     * audit : 1
     * author : devallever
     * canEdit : false
     * chapterId : 294
     * chapterName : 完整项目
     * collect : false
     * courseId : 13
     * desc : 介绍
     * 【文本翻译器】是一款免费的简洁实用的翻译软件。文本翻译器应用程序完全免费，可以非常快速翻译您的单词，帮助您与外国人交流。文本翻译器适用于旅行者、学生、商人和其他语言爱好者，使用文本翻译器可以轻松了解其他语言。文本翻译器支持多国语言，全新领先的翻译引擎，让各种变得更加可靠有保证。界面设计简洁、优雅，体积小巧，但是功能很强大哦。赶快下载来试试吧~
     * descMd :
     * envelopePic : https://www.wanandroid.com/blogimgs/9fc6e10c-b3e8-46bb-928b-05ccd2147335.png
     * fresh : false
     * id : 12244
     * link : https://www.wanandroid.com/blog/show/2719
     * niceDate : 2020-03-08 19:01
     * niceShareDate : 2020-03-08 19:01
     * origin :
     * prefix :
     * projectLink : https://github.com/devallever/TranslationTextOpenSource
     * publishTime : 1583665265000
     * selfVisible : 0
     * shareDate : 1583665265000
     * shareUser :
     * superChapterId : 294
     * superChapterName : 开源项目主Tab
     * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
     * title : TranslationTextOpenSource-文本翻译器开源版
     * type : 0
     * userId : -1
     * visible : 1
     * zan : 0
     */
    var apkLink: String? = null
    var audit = 0
    var author: String? = null
    var canEdit = false
    var chapterId = 0
    var chapterName: String? = null
    var collect = false
    var courseId = 0
    var desc: String? = null
    var descMd: String? = null
    var envelopePic: String? = null
    var fresh = false
    var id = 0
    var link: String? = null
    var niceDate: String? = null
    var niceShareDate: String? = null
    var origin: String? = null
    var prefix: String? = null
    var projectLink: String? = null
    var publishTime: Long = 0
    var selfVisible = 0
    var shareDate: Long = 0
    var shareUser: String? = null
    var superChapterId = 0
    var superChapterName: String? = null
    var title: String? = null
    var type = 0
    var userId = 0
    var visible = 0
    var zan = 0
    var tags: List<TagsBean>? = null

    class TagsBean {
        /**
         * name : 项目
         * url : /project/list/1?cid=294
         */
        var name: String? = null
        var url: String? = null

    }
}