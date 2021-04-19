import org.apache.commons.io.FileUtils;
import org.junit.Test;
import ru.hse.java.Statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatisticsTest {
  @Test
  public void testSave() throws IOException {
    InputStream stdin = System.in;

    for (int i = 2; i <= 3; i++) {
      System.out.println("testing " + i);
      File stats = new File("stats.txt");
      if (stats.exists()) {
        stats.delete();
      }

      File expected = new File(String.format("src/test/resources/test%d.txt", i));
//      System.out.println(expected.getAbsoluteFile());
      System.setIn(new FileInputStream(expected.getAbsoluteFile()));

      try {
        while (true)
          Statistics.save(null);
      } catch (Exception e) {
        System.setIn(stdin);
      }
      File provided = new File("stats.txt");
      assertTrue(provided.exists());
      assertTrue(FileUtils.contentEquals(expected, provided));
    }
  }

  @Test
  public void testGetOptimalTime() throws IOException {
    int[] testOptimalTime = {5, 2};
    String[] testData = {"5 2 yes\n", "5 2 yes\n", "5 2 yes\n"};

    for (int i = 0; i < 3; i++) {
      Statistics.save(new Scanner(testData[i]));
    }

    int[] ourOptimalTime = Statistics.getOptimalTime();

    assertEquals(ourOptimalTime[0], testOptimalTime[0]);
    assertEquals(ourOptimalTime[1], testOptimalTime[1]);
  }

  @Test
  public void correctOptimalTime() throws IOException {
    int[] testOptimalTime = {5, 2};
    String[] testData = {"5 2 yes\n", "5 2 yes\n", "5 2 yes\n"};

    for (int i = 0; i < 3; i++) {
      Statistics.save(new Scanner(testData[i]));
    }

    int[] ourOptimalTime = Statistics.getOptimalTime();

    assertTrue((ourOptimalTime[0] >= 0) && (ourOptimalTime[0] < 60));
    assertTrue((ourOptimalTime[1] >= 0) && (ourOptimalTime[1] < 60));
  }
}
