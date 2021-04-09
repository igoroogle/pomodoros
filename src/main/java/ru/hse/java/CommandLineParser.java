package ru.hse.java;

import java.util.Scanner;

class CommandLineParser
{
    static PomodoroTimer timer = new PomodoroTimer(25, 5);
    static Scanner scanner = new Scanner(System. in);

    public static void main ( String [] arguments )
    {
        System.out.println("Welcome! Ready to start?");
        waitCommand();
    }

    private static void waitCommand() {
        String inputString = scanner.nextLine();
        if (inputString.equals("set")) {
            System.out.print("Enter work period duration: ");
            inputString = scanner.nextLine();
            int workDuration = Integer.valueOf(inputString);

            System.out.print("Enter rest period duration: ");
            inputString = scanner.nextLine();
            int restDuration = Integer.valueOf(inputString);

            timer = new PomodoroTimer(workDuration, restDuration);

            System.out.println("Timer is ready.");
            waitCommand();
        } else if (inputString.equals("start")) {
            start();
        } else if (inputString.equals("finish")){
            finish();
        } else {
            System.out.println("Unknown command. Try one of set/start/finish.");
            waitCommand();
        }
    }

    private static void start() {
        timer.start();
    }

    public static void timeIsUp(String nextActivity) {
        System.out.print("Time is up! Ready for " + nextActivity + "? (y/n): ");
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