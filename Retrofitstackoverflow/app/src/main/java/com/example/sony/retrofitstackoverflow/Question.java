package com.example.sony.retrofitstackoverflow;
import com.google.gson.annotations.SerializedName;
/**
 * Created by sony on 2017/4/6.
 */

public class Question {
    public String title;
    public String body;

    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return(title);
    }
}
