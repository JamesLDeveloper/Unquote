package com.example.unquote;

import android.os.Handler;

public class MyTimer {

    private Handler handler;
    private boolean paused;
    private int interval;
    private Runnable task;

    private TimerCallback timerCallback;

    public MyTimer(int startDelay, int interval, TimerCallback timerCallback) {
        this.interval = interval;
        this.timerCallback = timerCallback;
        this.handler = new Handler();
        this.task = new Runnable() {
            @Override
            public void run() {
                if (!paused) {
                    timerCallback.onTimerTick();
                    handler.postDelayed(this, interval);
                }
            }
        };

        handler.postDelayed(task, startDelay);

    }

    public int getInterval(){
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void startTimer() {
        paused = false;
        handler.postDelayed(task, interval);
    }

    public void stopTimer() {
        paused = true;
    }

    public interface TimerCallback {
        void onTimerTick();
    }
}
