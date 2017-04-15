package com.example.sony.retrofitstackoverflow;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sony on 2017/4/6.
 */

public class Answer {
    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is_accepted")
    public boolean accepted;

    public int score;

    @Override
    public String toString() {
        return answerId + " - Score: " + score + " - Accepted: " + (accepted ? "Yes" : "No");
    }
}
