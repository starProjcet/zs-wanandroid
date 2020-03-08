package com.example.zs_wan_android.http

import com.example.zs_wan_android.entity.BannerEntity
import com.example.zs_wan_android.entity.HomeEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

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
     */
    @GET("/banner/json")
    fun getBanner(): Observable<MutableList<BannerEntity>>

}