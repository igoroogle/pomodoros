package ru.hse.java;

import java.io.IOException;
import java.util.Scanner;

class CommandLineParser
{
    static PomodoroTimer timer = new PomodoroTimer(25, 5);
    static Scanner scanner = new Scanner(System. in);
    static String category = "unknown";

    public static void main ( String [] arguments )
    {
        try {
            Statistics.read();
        } catch (IOException e) {
            System.out.println("Optimal time is not available yet.");
            //            e.printStackTrace();
        }
        System.out.println("Welcome! Ready to start?");
        waitCommand();
    }

    private static void waitCommand() {
        String inputString = scanner.nextLine();
        switch (inputString) {
            case "set" -> {
                System.out.print("Enter work period duration: ");
                inputString = scanner.nextLine();
                int workDuration = Integer.valueOf(inputString);

                System.out.print("Enter rest period duration: ");
                inputString = scanner.nextLine();
                int restDuration = Integer.valueOf(inputString);

                timer.setTime(workDuration, restDuration);

                System.out.println("Timer is ready.");
                waitCommand();
            }
            case "optimal" -> {
                timer.setOptimalTime();
                System.out.print("Optimal time is ");
                System.out.print(timer.getWorkDuration() + "/");
                System.out.println(timer.getRestDuration());
                System.out.println("Timer is ready.");
                waitCommand();
            }
            case "start" -> start();
            case "finish" -> finish();
            default -> {
                System.out.println("Unknown command. Try one of set/optimal/start/finish.");
                waitCommand();
            }
        }
    }

    private static void start() {
        timer.start();
    }

    public static void timeIsUp(String nextActivity) {
        System.out.print("Time is up!");
        if (nextActivity.equals("rest")) {
            System.out.print("Have you succeed? (y/n): ");
            String response = scanner.nextLine();
            if (response.equals("y")) {
                try {
                    Statistics.save(new Scanner(timer.getWorkDuration() + " " + timer.getRestDuration() + "\n" + "yes" + "\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (response.equals("n")) {
                try {
                    Statistics.save(new Scanner(timer.getWorkDuration() + " " + timer.getRestDuration() + "\n" + "no" + "\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print("Ready for " + nextActivity + "? (y/n): ");
        String response = scanner.nextLine();
        if (response.equals("y")) {
            start();
        } else if (response.equals("n")) {
            finish();
        }
    }
    private static void finish() {
        timer.stop();
        System.out.println("Good job, goodbye!");
        System.exit(0);
    }
}