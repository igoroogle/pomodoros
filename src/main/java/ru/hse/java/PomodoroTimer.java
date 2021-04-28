package ru.hse.java;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimer {
    private static Status status = Status.STOP;
    private static TimerTask task = null;
    private static Timer timer = new Timer();
    private static int workDuration = 25;
    private static int restDuration =  5;

    PomodoroTimer() { }
    public PomodoroTimer(int workDuration, int restDuration) {
        setTime(workDuration, restDuration);
    }

    public void setTime(int workDuration, int restDuration) {
        this.workDuration = workDuration;
        this.restDuration = restDuration;
    }

    public void setOptimalTime() {
        int[] optimalTime = Statistics.getOptimalTime();
        this.workDuration = optimalTime[0];
        this.restDuration = optimalTime[1];
    }

    public static void start() {
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

    public static void stop() {
        if (status != Status.STOP) {
            status = Status.STOP;
            task.cancel();
            timer.cancel();
        }
    }

    public static void setStatus(Status status) {
        PomodoroTimer.status = status;
    }

    private static int toMilliseconds(int minutes) {
//        return minutes * 60 * 1000;
        return minutes * 1000;
    }

    public static int getWorkDuration() {
        return workDuration;
    }
    public static int getRestDuration() {
        return restDuration;
    }
}