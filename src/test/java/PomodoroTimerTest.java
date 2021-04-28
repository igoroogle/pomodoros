import org.apache.commons.io.FileUtils;
import org.junit.Test;
import ru.hse.java.PomodoroTimer;
import ru.hse.java.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PomodoroTimerTest {

    private ru.hse.java.Status Status;

    @Test
    public void testGetWorkDuration() throws FileNotFoundException {
        for (int i = 1; i <= 5; i++) {
            System.out.println("testing work duration " + i);

            PomodoroTimer timer = new PomodoroTimer(25, 5);
            File test = new File(String.format("src/test/resources/tests/test%d.txt", i));
            Scanner scanner = new Scanner(test);

            int testWorkDurationTime = 0;
            int testRestDurationTime = 0;

            if (scanner.hasNextInt()) {
                testWorkDurationTime = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                testRestDurationTime = scanner.nextInt();
            }

            timer.setTime(testWorkDurationTime, testRestDurationTime);
            int ourWorkDurationTime = PomodoroTimer.getWorkDuration();

            assertEquals(testWorkDurationTime, ourWorkDurationTime);
        }
    }

    @Test
    public void testGetRestDuration() throws FileNotFoundException {
        for (int i = 1; i <= 5; i++) {
            System.out.println("testing rest duration " + i);

            PomodoroTimer timer = new PomodoroTimer(25, 5);
            File test = new File(String.format("src/test/resources/tests/test%d.txt", i));
            Scanner scanner = new Scanner(test);

            int testWorkDurationTime = 0;
            int testRestDurationTime = 0;

            if (scanner.hasNextInt()) {
                testWorkDurationTime = scanner.nextInt();
            }
            if (scanner.hasNextInt()) {
                testRestDurationTime = scanner.nextInt();
            }

            timer.setTime(testWorkDurationTime, testRestDurationTime);
            int ourRestDurationTime = PomodoroTimer.getRestDuration();

            assertEquals(testRestDurationTime, ourRestDurationTime);
        }
    }

    @Test
    public void testSetOptimalTime() {
        System.out.println("testing set optimal time");
        PomodoroTimer timer = new PomodoroTimer(25, 5);
        timer.setOptimalTime();
    }

    @Test
    public void testStart() {
        System.out.println("testing start");
        PomodoroTimer.setStatus(ru.hse.java.Status.REST);
        PomodoroTimer.start();

        PomodoroTimer.setStatus(ru.hse.java.Status.WORK);
        PomodoroTimer.start();
    }

    @Test
    public void testStop() {
        System.out.println("testing stop");
        PomodoroTimer.setStatus(ru.hse.java.Status.WORK);
        PomodoroTimer.stop();
        PomodoroTimer.stop();
    }
}
