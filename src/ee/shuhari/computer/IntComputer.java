package ee.shuhari.computer;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

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

  InputStream in = System.in;
  PrintStream out = System.out;
  public List<Integer> inputTap;
  public List<Integer> outputSink;

  public IntComputer(InputStream in, PrintStream out) {
    this.in = in;
    this.out = out;
  }

  public IntComputer(List<Integer> inputTap, List<Integer> outputSink) {
    this.inputTap = inputTap;
    this.outputSink = outputSink;
  }

  public IntComputer() {

  }

  private int getNextInput() {
    if(inputTap != null) {
      while(inputTap.size() == 0) {
        try {
          Thread.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      };
      return inputTap.remove(0);
    }
    Scanner scanner = new Scanner(in);
    return Integer.parseInt(scanner.nextLine());
  }

  private void addOutput(Integer i) {
    if(outputSink != null) {
      outputSink.add(i);
      return;
    }
    out.println(i);
  }

  public int[] compute(int[] code) {
    int cursor = 0;
    int[] command;
    int arg1;
    int arg2;
    int target;
    boolean exit = false;
    while (!exit) {
      command = parseCommand(code[cursor]);
      switch (command[0]) {
        case ADD_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          target = code[cursor + 3];
          code[target] = arg1 + arg2;
          cursor += 4;
          break;
        case MULTIPLY_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          target = code[cursor + 3];
          code[target] = arg1 * arg2;
          cursor += 4;
          break;
        case JUMP_IF_TRUE_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          if(arg1 != 0) {
            cursor = arg2;
          } else {
            cursor += 3;
          }
          break;
        case JUMP_IF_FALSE_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          if(arg1 == 0) {
            cursor = arg2;
          } else {
            cursor += 3;
          }
          break;
        case LESS_THAN_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          code[code[cursor + 3]] = arg1 < arg2 ? 1 : 0;
          cursor += 4;
          break;
        case EQUALS_COMMAND:
          arg1 = command[1] > 0 ? code[cursor + 1] : code[code[cursor + 1]];
          arg2 = command[2] > 0 ? code[cursor + 2] : code[code[cursor + 2]];
          code[code[cursor + 3]] = arg1 == arg2 ? 1 : 0;
          cursor += 4;
          break;
        case INPUT_COMMAND:
          arg1 = code[cursor + 1];
          code[arg1] = getNextInput();
          cursor += 2;
          break;
        case OUTPUT_COMMAND:
          arg1 = code[cursor + 1];
          addOutput(code[arg1]);
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


  public int[] parseCommand(int i) {
    int command = i % 100;
    int paramMode1 = 0, paramMode2 = 0, paramMode3 = 0;
    if (i > 99) paramMode1 = i / 100 % 10;
    if (i > 999) paramMode2 = i / 1000 % 10;
    if (i > 9999) paramMode3 = i / 10000 % 10;
    return new int[]{command, paramMode1, paramMode2, paramMode3};
  }

}