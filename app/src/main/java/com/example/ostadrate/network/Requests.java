package com.example.ostadrate.network;

import com.example.ostadrate.model.Comment;
import com.example.ostadrate.model.Rate;
import com.example.ostadrate.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Requests {

    @GET("/all_teachers")
    Call<List<Teacher>> getAllTeachers();

    @GET("/teacher")
    Call<Rate> getTeacherRate(@Query("teacher_id") int teacherId);

    @GET("/teacher")
    Call<Comment> getTeacherComments(@Query("teacher_id") int teacherId);

    @POST("/comment")
    Call<Teacher> postComment(@Field("name") String name,
                              @Field("teacher_id") String teacherId,
                              @Field("comment") String comment);
}
