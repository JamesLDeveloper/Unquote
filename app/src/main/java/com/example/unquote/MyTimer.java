package com.example.unquote;

import android.os.CountDownTimer;
//import android.os.Handler;

public class MyTimer extends CountDownTimer {

 //   private Handler handler;
    private boolean paused;
    private int interval;

    private long endTime;

    private TimerCallback timerCallback;

    public MyTimer(long millisInFuture, long countDownInterval, TimerCallback timerCallback) {
super(millisInFuture, countDownInterval);
this.timerCallback = timerCallback;
//this.handler = new Handler();

    }

    @Override
    public void onTick(long millisUntilFinished) {
        timerCallback.onTimerTick(millisUntilFinished);
    }

@Override
public void onFinish(){
        timerCallback.onTimerFinish();
  //      updateUITimer(0);
}

//    private Runnable task = new Runnable() {
//        @Override
//        public void run() {
//            if (!paused) {
//                timerCallback.onTimerTick(calculateTimeLeft());
//                handler.postDelayed(this, interval);
//            }
//        }
//    };

    public interface TimerCallback {
        void onTimerTick(long millisUntilFinished);
        void onTimerFinish();
    }


    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void startTimer() {
        paused = false;
//        handler.postDelayed(task, interval);
        start();
    }

    public void stopTimer() {
        paused = true;
    }

    private long calculateTimeLeft() {
        return Math.max(0, endTime - System.currentTimeMillis());
    }





}
