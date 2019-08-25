package com.example.ostadrate.network;

import com.example.ostadrate.Comment;
import com.example.ostadrate.Rate;
import com.example.ostadrate.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Requests {

    @GET("/all_teachers")
    Call<List<Teacher>> getAllTeachers();

    @GET("/teacher")
    Call<Rate> getTeacherRate(@Query("teacher_id") int teacherId);

    @GET("/teacher")
    Call<Comment> getTeacherComments(@Query("teacher_id") int teacherId);
}
