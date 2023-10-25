package com.example.udemybitirme2.retrofit

import android.util.Log
import com.example.udemybitirme2.data.entity.CRUDCevap
import com.example.udemybitirme2.data.entity.SepetCevap
import com.example.udemybitirme2.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle():YemeklerCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                           @Field("yemek_resim_adi") yemek_resim_adi:String,
                           @Field("yemek_fiyat") yemek_fiyat:Int,
                           @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                           @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetGetir(@Field("kullanici_adi") kullanici_adi: String):SepetCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepetYemekSil(@Field("sepet_yemek_id") sepet_yemek_id:Int,
                              @Field("kullanici_adi") kullanici_adi: String):CRUDCevap

}