package com.zs.wanandroid.entity;

import java.util.List;

public class CollectEntity {


    /**
     * curPage : 1
     * datas : [{"author":"xiaoyang","chapterId":440,"chapterName":"官方","courseId":13,"desc":"<p>很久以前有Activity.onResume就是界面可见的说法，这种说法毫无疑问是不准确的。<\/p>\r\n<p>问题是：<\/p>\r\n<ol>\r\n<li>Activity.onCreate 和 Activity.onResume，在调用时间上有差别么？可以从Message调度去考虑。<\/li>\r\n<li>有没有一个合理的时机，让我们认为Activity 界面可见了？<\/li>\r\n<\/ol>","envelopePic":"","id":120115,"link":"https://wanandroid.com/wenda/show/12175","niceDate":"2小时前","origin":"","originId":12175,"publishTime":1583732093000,"title":"每日一问 | 很久以前有Activity.onResume就是界面可见的说法，这种说法错了多少？","userId":36628,"visible":0,"zan":0},{"author":"","chapterId":249,"chapterName":"干货资源","courseId":13,"desc":"","envelopePic":"","id":119996,"link":"https://wanandroid.com/blog/show/2701","niceDate":"7小时前","origin":"","originId":10916,"publishTime":1583713076000,"title":"玩 Android 交流星球 限时开放","userId":36628,"visible":0,"zan":0},{"author":"xiaoyang","chapterId":440,"chapterName":"官方","courseId":13,"desc":"<p>之前我们讨论过 <a href=\"https://wanandroid.com/wenda/show/8488\">View的onAttachedToWindow ,onDetachedFromWindow 调用时机<\/a> 。<\/p>\r\n<p>这个机制在RecyclerView卡片中还适用吗？<\/p>\r\n<p>例如我们在RecyclerView的Item的onBindViewHolder时，利用一个CountDownTimer去做一个倒计时显示 / 或者是有一个属性动画效果？<\/p>\r\n<ol>\r\n<li>到底在什么时候可以cancel掉这个倒计时/ 动画，而不影响功能了（滑动到用户可见范围内，倒计时/动画 运作正常）?<\/li>\r\n<li>有什么方法可以和onBindViewHolder 对应吗？就像onAttachedToWindow ,onDetachedFromWindow这样 。<\/li>\r\n<\/ol>","envelopePic":"","id":119994,"link":"https://wanandroid.com/wenda/show/12148","niceDate":"7小时前","origin":"","originId":12148,"publishTime":1583713073000,"title":"每日一问 RecyclerView卡片中持有的资源，到底该什么时候释放？","userId":36628,"visible":0,"zan":0},{"author":"","chapterId":502,"chapterName":"自助","courseId":13,"desc":"","envelopePic":"","id":119502,"link":"https://juejin.im/post/5e60ecd4e51d4526ed66bdcc","niceDate":"2020-03-06 13:52","origin":"","originId":12202,"publishTime":1583473970000,"title":"LiveData详细分析","userId":36628,"visible":0,"zan":0},{"author":"","chapterId":78,"chapterName":"性能优化","courseId":13,"desc":"","envelopePic":"","id":102230,"link":"https://www.jianshu.com/p/28b9ee94d515","niceDate":"2019-11-26 21:04","origin":"","originId":10479,"publishTime":1574773455000,"title":"Android应用测速组件实现原理","userId":36628,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 5
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : xiaoyang
         * chapterId : 440
         * chapterName : 官方
         * courseId : 13
         * desc : <p>很久以前有Activity.onResume就是界面可见的说法，这种说法毫无疑问是不准确的。</p>
         <p>问题是：</p>
         <ol>
         <li>Activity.onCreate 和 Activity.onResume，在调用时间上有差别么？可以从Message调度去考虑。</li>
         <li>有没有一个合理的时机，让我们认为Activity 界面可见了？</li>
         </ol>
         * envelopePic :
         * id : 120115
         * link : https://wanandroid.com/wenda/show/12175
         * niceDate : 2小时前
         * origin :
         * originId : 12175
         * publishTime : 1583732093000
         * title : 每日一问 | 很久以前有Activity.onResume就是界面可见的说法，这种说法错了多少？
         * userId : 36628
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
