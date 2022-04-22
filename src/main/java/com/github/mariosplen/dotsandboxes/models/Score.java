package com.github.mariosplen.dotsandboxes.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Score {
    private final String score0;
    private final String score1;
    private final String time;

    public Score(String score0, String score1, String time) {
        this.score0 = score0;
        this.score1 = score1;
        this.time = time;
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        //this.time = df.format(new Date().getTime());

    }

    public String getScore0() {
        return score0;
    }

    public String getScore1() {
        return score1;
    }

    public String getTime() {
        return time;
    }


}
