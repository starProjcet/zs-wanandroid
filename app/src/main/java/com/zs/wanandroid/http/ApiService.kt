package com.zs.wanandroid.http

import com.zs.wanandroid.entity.*
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    fun getTopList(): Observable<BaseResponse<MutableList<ArticleEntity.DatasBean>>>

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<BaseResponse<UserEntity>>

    @GET("/user/logout/json")
    fun logout():Observable<BaseResponse<Any>>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNo: Int):
            Observable<BaseResponse<CollectEntity>>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    fun getIntegral():Observable<BaseResponse<IntegralEntity>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id")id:Int):Observable<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 获取项目tab
     */
    @GET("/project/tree/json")
    fun getProjectTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目tab
     */
    @GET("/wxarticle/chapters/json  ")
    fun getAccountTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum")pageNum:Int,@Query("cid")cid:Int)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    fun getAccountList(@Path("id")cid:Int,@Path("pageNum")pageNum:Int)
            : Observable<BaseResponse<ArticleEntity>>


    /**
     * 获取项目tab
     */
    @POST("/article/query/{pageNum}/json")
    fun search(@Path("pageNum")pageNum:Int,@Query("k")cid:String)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 体系
     */
    @GET("/tree/json")
    fun getSystemList() : Observable<BaseResponse<MutableList<SystemListEntity>>>

    /**
     * 导航
     */
    @GET("/navi/json")
    fun getNavigation() : Observable<BaseResponse<MutableList<NavigationEntity>>>

    /**
     * 排名
     */
    @GET("/coin/rank/{pageNum}/json")
    fun getRank(@Path("pageNum")pageNum: Int) : Observable<BaseResponse<RankEntity>>

    /**
     * 积分记录
     */
    @GET("/lg/coin/list/{pageNum}/json")
    fun getIntegralRecord(@Path("pageNum")pageNum: Int) : Observable<BaseResponse<IntegralRecordEntity>>
}