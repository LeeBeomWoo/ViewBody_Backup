package com.example.leebeomwoo.viewbody_final.Support;

import com.example.leebeomwoo.viewbody_final.QnA.QrItem;
import com.example.leebeomwoo.viewbody_final.QnA.QwItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseBd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCbd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCec;
import com.example.leebeomwoo.viewbody_final.Response.ResponseEc;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFm;
import com.example.leebeomwoo.viewbody_final.Response.ResponseQ;
import com.example.leebeomwoo.viewbody_final.Response.ResponseQrp;
import com.example.leebeomwoo.viewbody_final.Response.ResponseTr;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by LBW on 2016-05-25.
 */


public interface NetworkService { //retrofit2부터 url뒤에 /를 입력해야 합니다.

    @GET("board/Food.jsp")
    Call<ResponseFd> getResult_Fd();

    @GET("board/Follow.jsp")
    Call<ResponseFm> getResult_Fm();

    @GET("board/Body.jsp")
    Call<ResponseBd> getResult_Bd();

    @GET("board/Exercise.jsp")
    Call<ResponseEc> getResult_Ec();

    @GET("board/QnA.jsp")
    Call<ResponseQ> getResult_Q();

    @GET("board/Person.jsp")
    Call<ResponseCard> getResult_Card();

    @FormUrlEncoded
    @POST("board/Categorybody.jsp")
    Call<ResponseCbd> CATEGORY_BODY(@Field("category") String category);

    @FormUrlEncoded
    @POST("board/Categoryexercise.jsp")
    Call<ResponseCec>  CATEGORY_EXERCISE(@Field("category") String category);

    @FormUrlEncoded
    @POST("board/trainerinfo.jsp")
    Call<ResponseTr> TR_CALL(@Field("tr_id") String tr_id);

    @FormUrlEncoded
    @POST("board/QnaReceive.jsp")
    Call<QwItem> QwPut(@Field("content") String content, @Field("writer") String writer,
                       @Field("title") String title, @Field("password") String password);

    @FormUrlEncoded
    @POST("board/QnaRead.jsp")
    Call<QrItem> QrPost(@Field("pagenum") String pagenum);


    @FormUrlEncoded
    @POST("board/Qna_Reply_table.jsp")
    Call<ResponseQrp> QrpPut(@Field("content") String content, @Field("writer") String writer,
                             @Field("pagenum") String pagenum);

}
