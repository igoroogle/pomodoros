//import org.apache.commons.io.FileUtils;
//import org.junit.Test;
//import ru.hse.java.Statistics;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertTrue;
//
//public class StatisticsTest {
//
//
//  @Test
//  public void testSave() throws IOException {
//    for (int i = 1; i <= 5; i++) {
//      System.out.println("testing save " + i);
//
//      File stats = new File("stats.txt");
//      if (stats.exists()) stats.delete();
//
//      File expected = new File(String.format("src/test/resources/tests/test%d.txt", i));
//      Statistics.save(new Scanner(expected));
//
//      File provided = new File("stats.txt");
//
//      assertTrue(provided.exists());
//      assertTrue(FileUtils.contentEquals(expected, provided));
//    }
//  }
//
//  @Test
//  public void testGetOptimalTime() throws IOException {
//    for (int i = 1; i <= 5; i++) {
//      System.out.println("testing optimal time " + i);
//
//      File stats = new File("stats.txt");
//      if (stats.exists()) stats.delete();
//
//      File test = new File(String.format("src/test/resources/tests/test%d.txt", i));
//      Statistics.save(new Scanner(test));
//
//      File answ = new File(String.format("src/test/resources/answers/test%d.txt", i));
//      Scanner scanner = new Scanner(answ);
//      int[] testOptimalTime = {scanner.nextInt(), scanner.nextInt()};
//      int[] ourOptimalTime = Statistics.getOptimalTime();
//
//      assertArrayEquals(testOptimalTime, ourOptimalTime);
//    }
//  }
//}
