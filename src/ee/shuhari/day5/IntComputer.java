package ee.shuhari.day5;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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

  static final int[] PROGRAM = new int[]{3, 225, 1, 225, 6, 6, 1100, 1, 238, 225, 104, 0, 1102, 79, 14, 225, 1101, 17, 42, 225, 2, 74, 69, 224, 1001, 224, -5733, 224, 4, 224, 1002, 223, 8, 223, 101, 4, 224, 224, 1, 223, 224, 223, 1002, 191, 83, 224, 1001, 224, -2407, 224, 4, 224, 102, 8, 223, 223, 101, 2, 224, 224, 1, 223, 224, 223, 1101, 18, 64, 225, 1102, 63, 22, 225, 1101, 31, 91, 225, 1001, 65, 26, 224, 101, -44, 224, 224, 4, 224, 102, 8, 223, 223, 101, 3, 224, 224, 1, 224, 223, 223, 101, 78, 13, 224, 101, -157, 224, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 3, 224, 1, 224, 223, 223, 102, 87, 187, 224, 101, -4698, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 4, 224, 1, 223, 224, 223, 1102, 79, 85, 224, 101, -6715, 224, 224, 4, 224, 1002, 223, 8, 223, 1001, 224, 2, 224, 1, 224, 223, 223, 1101, 43, 46, 224, 101, -89, 224, 224, 4, 224, 1002, 223, 8, 223, 101, 1, 224, 224, 1, 223, 224, 223, 1101, 54, 12, 225, 1102, 29, 54, 225, 1, 17, 217, 224, 101, -37, 224, 224, 4, 224, 102, 8, 223, 223, 1001, 224, 3, 224, 1, 223, 224, 223, 1102, 20, 53, 225, 4, 223, 99, 0, 0, 0, 677, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1105, 0, 99999, 1105, 227, 247, 1105, 1, 99999, 1005, 227, 99999, 1005, 0, 256, 1105, 1, 99999, 1106, 227, 99999, 1106, 0, 265, 1105, 1, 99999, 1006, 0, 99999, 1006, 227, 274, 1105, 1, 99999, 1105, 1, 280, 1105, 1, 99999, 1, 225, 225, 225, 1101, 294, 0, 0, 105, 1, 0, 1105, 1, 99999, 1106, 0, 300, 1105, 1, 99999, 1, 225, 225, 225, 1101, 314, 0, 0, 106, 0, 0, 1105, 1, 99999, 107, 226, 226, 224, 1002, 223, 2, 223, 1006, 224, 329, 101, 1, 223, 223, 1108, 677, 226, 224, 1002, 223, 2, 223, 1006, 224, 344, 101, 1, 223, 223, 7, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 359, 101, 1, 223, 223, 108, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 374, 101, 1, 223, 223, 8, 226, 677, 224, 1002, 223, 2, 223, 1006, 224, 389, 101, 1, 223, 223, 1108, 226, 226, 224, 102, 2, 223, 223, 1006, 224, 404, 101, 1, 223, 223, 1007, 677, 677, 224, 1002, 223, 2, 223, 1006, 224, 419, 101, 1, 223, 223, 8, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 434, 1001, 223, 1, 223, 1008, 226, 226, 224, 102, 2, 223, 223, 1005, 224, 449, 1001, 223, 1, 223, 1008, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 464, 101, 1, 223, 223, 1107, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 479, 101, 1, 223, 223, 107, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 494, 1001, 223, 1, 223, 1107, 226, 677, 224, 1002, 223, 2, 223, 1005, 224, 509, 101, 1, 223, 223, 1108, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 524, 101, 1, 223, 223, 7, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 539, 101, 1, 223, 223, 108, 677, 677, 224, 1002, 223, 2, 223, 1005, 224, 554, 101, 1, 223, 223, 8, 677, 226, 224, 1002, 223, 2, 223, 1005, 224, 569, 1001, 223, 1, 223, 1008, 677, 677, 224, 102, 2, 223, 223, 1006, 224, 584, 101, 1, 223, 223, 107, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 599, 1001, 223, 1, 223, 7, 226, 677, 224, 102, 2, 223, 223, 1005, 224, 614, 101, 1, 223, 223, 1007, 226, 226, 224, 1002, 223, 2, 223, 1005, 224, 629, 101, 1, 223, 223, 1107, 677, 226, 224, 1002, 223, 2, 223, 1006, 224, 644, 101, 1, 223, 223, 108, 226, 677, 224, 102, 2, 223, 223, 1006, 224, 659, 101, 1, 223, 223, 1007, 677, 226, 224, 102, 2, 223, 223, 1006, 224, 674, 101, 1, 223, 223, 4, 223, 99, 226};

  InputStream in = System.in;
  PrintStream out = System.out;

  public static void main(String[] args) {
    IntComputer computer = new IntComputer();
    computer.compute(PROGRAM);
  }

  public IntComputer() {
    super();
  }

  public IntComputer(InputStream in, PrintStream out) {
    this.in = in;
    this.out = out;
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
          Scanner scanner = new Scanner(in);
          String s = scanner. nextLine();
          code[arg1] = Integer.parseInt(s);
          cursor += 2;
          break;
        case OUTPUT_COMMAND:
          arg1 = code[cursor + 1];
          out.print(code[arg1]);
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
