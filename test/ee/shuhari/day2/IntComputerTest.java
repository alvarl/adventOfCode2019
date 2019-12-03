package ee.shuhari.day2;

import ee.shuhari.day2.IntComputer;
import junit.framework.TestCase;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class IntComputerTest extends TestCase {
  public static final int[] CODE = {1, 12, 2, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 13, 1, 19, 1, 9, 19, 23,
          2, 23, 13, 27, 1, 27, 9, 31, 2, 31, 6, 35, 1, 5, 35, 39, 1, 10, 39, 43, 2, 43, 6, 47, 1, 10, 47, 51, 2, 6,
          51, 55, 1, 5, 55, 59, 1, 59, 9, 63, 1, 13, 63, 67, 2, 6, 67, 71, 1, 5, 71, 75, 2, 6, 75, 79, 2, 79, 6, 83, 1,
          13, 83, 87, 1, 9, 87, 91, 1, 9, 91, 95, 1, 5, 95, 99, 1, 5, 99, 103, 2, 13, 103, 107, 1, 6, 107, 111, 1, 9,
          111, 115, 2, 6, 115, 119, 1, 13, 119, 123, 1, 123, 6, 127, 1, 127, 5, 131, 2, 10, 131, 135, 2, 135, 10, 139,
          1, 13, 139, 143, 1, 10, 143, 147, 1, 2, 147, 151, 1, 6, 151, 0, 99, 2, 14, 0, 0};
  private IntComputer computer = new IntComputer();

  public void testThis() {
    assertEquals(1, 1);
  }

  public void testBasicAdding() {
    int[] result = computer.compute(new int[]{1, 0, 0, 0, 99});
    assertArrayEquals(new int[]{2, 0, 0, 0, 99}, result);
  }

  public void testBasicMultiplication() {
    int[] result = computer.compute(new int[]{2, 3, 0, 3, 99});
    assertArrayEquals(new int[]{2, 3, 0, 6, 99}, result);
  }

  public void testMultiplicationWithFarAwayTarget() {
    int[] result = computer.compute(new int[]{2, 4, 4, 5, 99, 0});
    assertArrayEquals(new int[]{2, 4, 4, 5, 99, 9801}, result);
  }

  public void testSeveralCommands() {
    int[] result = computer.compute(new int[]{1, 1, 1, 4, 99, 5, 6, 0, 99});
    assertArrayEquals(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}, result);
  }

  public void testAdventOfCodeRocketShip() {
    int[] result = computer.compute(CODE);
    System.out.println(result[0]);
  }

  public void testFindNounVerbForAdventOfCodeRocketShip() {
    int[] originalSource = CODE;
    for (int noun = 0; noun < 100; noun++) {
      for (int verb = 0; verb < 100; verb++) {
        if(computer.compute(substituteValues(originalSource, noun, verb))[0] == 19690720) {
          System.out.println("found noun, verb: " + noun + ", " + verb);
          return;
        }
      }
    }
  }

  private int[] substituteValues(int[] original, int noun, int verb) {
    int[] source = Arrays.copyOf(original, original.length);
    source[1] = noun;
    source[2] = verb;
    return source;
  }



}
