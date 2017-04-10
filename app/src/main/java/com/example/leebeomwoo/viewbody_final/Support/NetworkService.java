package com.example.leebeomwoo.viewbody_final.Support;

import com.example.leebeomwoo.viewbody_final.QnA.QrItem;
import com.example.leebeomwoo.viewbody_final.QnA.QwItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCbd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFm;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
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

    @GET("board/Food_Diet.jsp")
    Call<ResponseFd> getResult_Diet();

    @GET("board/Food_Fat.jsp")
    Call<ResponseFd> getResult_Fat();

    @GET("board/Food_MuscleUp.jsp")
    Call<ResponseFd> getResult_MuscleUp();

    @GET("board/Food_PowerUp.jsp")
    Call<ResponseFd> getResult_PowerUp();

    @GET("board/Food_Metabolic.jsp")
    Call<ResponseFd> getResult_Metabolic();

    @GET("board/Follow.jsp")
    Call<ResponseFm> getResult_Fm();

    @GET("board/Upper_Bone.jsp")
    Call<ResponseLd> getResult_UpBone();

    @GET("board/Upper_Muscle.jsp")
    Call<ResponseLd> getResult_UpMuscle();

    @GET("board/Lower_Bone.jsp")
    Call<ResponseLd> getResult_LoBone();

    @GET("board/Lower_Muscle.jsp")
    Call<ResponseLd> getResult_LoMuscle();

    @GET("board/QnA.jsp")
    Call<ResponseQ> getResult_Q();

    @GET("board/Person.jsp")
    Call<ResponseCard> getResult_Card();

    @FormUrlEncoded
    @POST("board/Categorybody.jsp")
    Call<ResponseCbd> CATEGORY_BODY(@Field("category") String category);

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
