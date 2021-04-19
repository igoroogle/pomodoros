package ru.hse.java;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private Timer timer = new Timer();
    private int workDuration = 25;
    private int restDuration =  5;
    private Status status = Status.STOP;
    private TimerTask task = null;

    PomodoroTimer() { }
    PomodoroTimer(int workDuration, int restDuration) {
        setTime(workDuration, restDuration);
    }

    void setTime(int workDuration, int restDuration) {
        this.workDuration = workDuration;
        this.restDuration = restDuration;
    }

    void setOptimalTime() {
        int[] optimalTime = Statistics.getOptimalTime();
        this.workDuration = optimalTime[0];
        this.restDuration = optimalTime[1];
    }

    void start() {
        if (status.equals(Status.REST)) {
            System.out.println("Rest period started");
            task = new TimerTask() {
                @Override
                public void run() {
                    status = Status.WORK;
                    CommandLineParser.timeIsUp("work");
                }
            };
            timer.schedule(task, toMilliseconds(restDuration));
        } else {
            System.out.println("Work period started");
            task = new TimerTask() {
                @Override
                public void run() {
                    status = Status.REST;
                    CommandLineParser.timeIsUp("rest");
                }
            };
            timer.schedule(task, toMilliseconds(workDuration));
        }
    }

    void stop() {
        if (status != Status.STOP) {
            task.cancel();
            timer.cancel();
        }
    }

    private int toMilliseconds(int minutes) {
//        return minutes * 60 * 1000;
        return minutes * 1000;
    }

    public int getWorkDuration() {
        return workDuration;
    }
    public int getRestDuration() {
        return restDuration;
    }
}

enum Status {
    WORK,
    REST,
    PAUSE,
    STOP,
}