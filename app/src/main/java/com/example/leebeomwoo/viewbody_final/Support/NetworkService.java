package com.example.leebeomwoo.viewbody_final.Support;

import com.example.leebeomwoo.viewbody_final.Item.LikeItem;
import com.example.leebeomwoo.viewbody_final.Item.UserInformationItem;
import com.example.leebeomwoo.viewbody_final.QnA.QrItem;
import com.example.leebeomwoo.viewbody_final.QnA.QwItem;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCard;
import com.example.leebeomwoo.viewbody_final.Response.ResponseCbd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseFm;
import com.example.leebeomwoo.viewbody_final.Response.ResponseLd;
import com.example.leebeomwoo.viewbody_final.Response.ResponseQ;
import com.example.leebeomwoo.viewbody_final.Response.ResponseQrp;
import com.example.leebeomwoo.viewbody_final.Response.ResponseTr;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by LBW on 2016-05-25.
 */


public interface NetworkService { //retrofit2부터 url뒤에 /를 입력해야 합니다.

    @GET("board/QnA.jsp")
    Call<ResponseQ> getResult_Q();

    @FormUrlEncoded
    @POST("board/Like.jsp")
    Call<LikeItem> getResult_List(@Field("table") String table, @Field("sourcid") int sourcid, @Field("userid") String userid);

    @GET("board/{id}.jsp")
    Call<ResponseCard> getResult_Card(@Path("id") String id);

    @GET("board/{id}.jsp")
    Call<ResponseLd> getResult_Ld(@Path("id") String id);

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
    @POST("board/UserJoin.jsp")
    Call<UserInformationItem> joinpush(@Body UserInformationItem item);


    @FormUrlEncoded
    @POST("board/Qna_Reply_table.jsp")
    Call<ResponseQrp> QrpPut(@Field("content") String content, @Field("writer") String writer,
                             @Field("pagenum") String pagenum);

}
