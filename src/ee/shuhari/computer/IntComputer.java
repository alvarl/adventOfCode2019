package ee.shuhari.computer;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IntComputer {

  public static final int ADD_COMMAND = 1;
  public static final int MULTIPLY_COMMAND = 2;
  public static final int INPUT_COMMAND = 3;
  public static final int OUTPUT_COMMAND = 4;
  public static final int JUMP_IF_TRUE_COMMAND = 5;
  public static final int JUMP_IF_FALSE_COMMAND = 6;
  public static final int LESS_THAN_COMMAND = 7;
  public static final int EQUALS_COMMAND = 8;
  public static final int EXIT_COMMAND = 99;
  public static final int RELATIVE_BASE_SET_COMMAND = 9;
  public static final int PARAM_MODE_POSITION = 0;
  public static final int PARAM_MODE_IMMEDIATE = 1;
  public static final int PARAM_MODE_RELATIVE = 2;

  InputStream in = System.in;
  PrintStream out = System.out;
  public List<Long> inputTap;
  public List<Long> outputSink;

  public long[] code;
  private int cursor = 0;
  private int relativeBase = 0;

  public IntComputer(InputStream in, PrintStream out) {
    this.in = in;
    this.out = out;
  }

  public IntComputer(List<Long> inputTap, List<Long> outputSink) {
    this.inputTap = inputTap;
    this.outputSink = outputSink;
  }

  public IntComputer() {

  }



  private long getNextInput() {
    if(inputTap != null) {
      while(inputTap.size() == 0) {
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      };
      return inputTap.remove(0);
    }
    Scanner scanner = new Scanner(in);
    return Integer.parseInt(scanner.nextLine());
  }

  private void addOutput(Long l) {
    if(outputSink != null) {
      outputSink.add(l);
      return;
    }
    out.println(l);
  }

  public void setProgram(long[] program) {
    code = program;
    cursor = 0;
    relativeBase = 0;
  }

  public long[] compute(long[] code) {
    setProgram(code);
    return compute();
  }

  public long[] compute() {
    if(code == null) throw new IllegalStateException("no program set");
    int[] command;
    long arg1;
    long arg2;
    int target;
    boolean exit = false;
    while (!exit) {
      command = parseCommand(Math.toIntExact(code[cursor]));
      switch (command[0]) {
        case ADD_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          target = getPointer(3, command);
          code[target] = arg1 + arg2;
          cursor += 4;
          break;
        case MULTIPLY_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          target = getPointer(3, command);
          code[target] = arg1 * arg2;
          cursor += 4;
          break;
        case JUMP_IF_TRUE_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          if(arg1 != 0) {
            cursor = Math.toIntExact(arg2);
          } else {
            cursor += 3;
          }
          break;
        case JUMP_IF_FALSE_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          if(arg1 == 0) {
            cursor = Math.toIntExact(arg2);
          } else {
            cursor += 3;
          }
          break;
        case LESS_THAN_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          target = getPointer(3, command);
          code[target] = arg1 < arg2 ? 1 : 0;
          cursor += 4;
          break;
        case EQUALS_COMMAND:
          arg1 = getArgValue(1, command);
          arg2 = getArgValue(2, command);
          target = getPointer(3, command);
          code[target] = arg1 == arg2 ? 1 : 0;
          cursor += 4;
          break;
        case INPUT_COMMAND:
          target = getPointer(1, command);
          code[target] = getNextInput();
          cursor += 2;
          break;
        case OUTPUT_COMMAND:
          arg1 = getArgValue(1, command);
          addOutput(arg1);
          cursor += 2;
          break;
        case RELATIVE_BASE_SET_COMMAND:
          arg1 = getArgValue(1, command);
          relativeBase += Math.toIntExact(arg1);
          cursor += 2;
          break;
        case EXIT_COMMAND:
          exit = true;
          break;
        default:
          throw new IllegalStateException("Unrecognized command " + command[0]);
      }
    }
    return code;
  }

  private void setPosition(int pointer, long value) {
    extendHeapIfNeeded(pointer);
    code[pointer] = value;
  }

  private void extendHeapIfNeeded(int pointer) {
    if(pointer > code.length -1) {
      code = Arrays.copyOf(code, pointer+1);
    }
  }

  private long getArgValue(int i, int[] command) {
    int pointer = getPointer(i, command);
    return code[pointer];
  }

  private int getPointer(int i, int[] command) {
    int pointer;
    switch(command[i]) {
      case PARAM_MODE_IMMEDIATE:
        pointer = cursor + i;
        break;
      case PARAM_MODE_POSITION:
        pointer = Math.toIntExact(code[cursor + i]);
        break;
      case PARAM_MODE_RELATIVE:
        pointer = Math.toIntExact(Math.toIntExact(code[cursor + i] + relativeBase));
        break;
      default:
        throw new IllegalArgumentException("Unknown param mode "+ command[i]);
    }
    extendHeapIfNeeded(pointer);
    return pointer;
  }


  public int[] parseCommand(int i) {
    int command = i % 100;
    int paramMode1 = 0, paramMode2 = 0, paramMode3 = 0;
    if (i > 99) paramMode1 = i / 100 % 10;
    if (i > 999) paramMode2 = i / 1000 % 10;
    if (i > 9999) paramMode3 = i / 10000 % 10;
    return new int[]{command, paramMode1, paramMode2, paramMode3};
  }

}
