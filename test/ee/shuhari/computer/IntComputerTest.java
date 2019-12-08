package ee.shuhari.computer;

import ee.shuhari.day5.IntComputer;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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

  public void testParameterModeParsingWithNotAllParamModesSet() {
    int[] commandWithParamModes = computer.parseCommand(1002);
    assertEquals(IntComputer.MULTIPLY_COMMAND, commandWithParamModes[0]);
    assertEquals(0, commandWithParamModes[1]);
    assertEquals(1, commandWithParamModes[2]);
    assertEquals(0, commandWithParamModes[3]);
  }

  public void testParameterModeParsingWithAllParamModesSet() {
    int[] commandWithParamModes = computer.parseCommand(11002);
    assertEquals(IntComputer.MULTIPLY_COMMAND, commandWithParamModes[0]);
    assertEquals(0, commandWithParamModes[1]);
    assertEquals(1, commandWithParamModes[2]);
    assertEquals(1, commandWithParamModes[3]);
  }

  public void testParameterModeParsingWithNoParamModesSet() {
    int[] commandWithParamModes = computer.parseCommand(2);
    assertEquals(IntComputer.MULTIPLY_COMMAND, commandWithParamModes[0]);
    assertEquals(0, commandWithParamModes[1]);
    assertEquals(0, commandWithParamModes[2]);
    assertEquals(0, commandWithParamModes[3]);
  }

  public void testComplicatedProgram() {
    int[] program = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'7'}, new byte[]{'9', '9', '9'});
    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'8'}, new byte[]{'1', '0', '0', '0'});
    testInputAndOutput(program, new byte[]{'9'}, new byte[]{'1', '0', '0', '1'});
  }

  public void testJumpProgramPositionMode() {
    int[] program = {3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'1'}, new byte[]{'1'});
    testInputAndOutput(program, new byte[]{'0'}, new byte[]{'0'});
  }

  public void testJumpProgramImmediateMode() {
    int[] program = {3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'1'}, new byte[]{'1'});
    testInputAndOutput(program, new byte[]{'0'}, new byte[]{'0'});
  }

  public void testEqualProgramPositionMode() {
    int[] program = {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'8'}, new byte[]{'1'});
    testInputAndOutput(program, new byte[]{'7'}, new byte[]{'0'});
  }

  public void testEqualProgramImmediateMode() {
    int[] program = {3, 3, 1108, -1, 8, 3, 4, 3, 99};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'8'}, new byte[]{'1'});
    testInputAndOutput(program, new byte[]{'7'}, new byte[]{'0'});
  }

  public void testLessThanProgramPositionMode() {
    int[] program = {3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'8'}, new byte[]{'0'});
    testInputAndOutput(program, new byte[]{'7'}, new byte[]{'1'});
  }

  public void testLessThanProgramImmediateMode() {
    int[] program = {3, 3, 1107, -1, 8, 3, 4, 3, 99};

    testInputAndOutput(Arrays.copyOf(program, program.length), new byte[]{'8'}, new byte[]{'0'});
    testInputAndOutput(program, new byte[]{'7'}, new byte[]{'1'});
  }


  private void testInputAndOutput(int[] program, byte[] input, byte[] expectedOutput) {
    ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(outBytes);
    InputStream in = new ByteArrayInputStream(input);
    IntComputer computer = new IntComputer(in, out);
    computer.compute(program);
    assertArrayEquals(expectedOutput, outBytes.toByteArray());
  }

}
