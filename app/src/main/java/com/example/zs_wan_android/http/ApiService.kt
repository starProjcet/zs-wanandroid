package com.example.zs_wan_android.http

import com.example.zs_wan_android.entity.BannerEntity
import com.example.zs_wan_android.entity.CollectEntity
import com.example.zs_wan_android.entity.HomeEntity
import com.example.zs_wan_android.entity.LoginEntity
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    /**
     * 获取首页文章数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<HomeEntity>

    /**
     * 获取首页置顶文章数据
     */
    @GET("/article/top/json")
    fun getTopList(): Observable<MutableList<HomeEntity.DatasBean>>

    /**
     * banner
     */
    @GET("/banner/json")
    fun getBanner(): Observable<MutableList<BannerEntity>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<LoginEntity>


    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNo: Int):
            Observable<CollectEntity>
}