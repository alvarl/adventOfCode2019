package ee.shuhari.day2;

import java.rmi.UnexpectedException;

public class IntComputer {

  public static final int ADD_COMMAND = 1;
  public static final int MULTIPLY_COMMAND = 2;

  public static void main(String[] args) {

  }


  public int[] compute(int[] code) {
    int cursor = 0;
    int command;
    int arg1;
    int arg2;
    int target;
    while((command = code[cursor]) != 99) {
      switch(command) {
        case ADD_COMMAND:
          arg1 = code[code[cursor+1]];
          arg2 = code[code[cursor+2]];
          target = code[cursor+3];
          code[target] = arg1+arg2;
          cursor += 4;
          break;
        case MULTIPLY_COMMAND:
          arg1 = code[code[cursor+1]];
          arg2 = code[code[cursor+2]];
          target = code[cursor+3];
          code[target] = arg1*arg2;
          cursor += 4;
          break;
        default:
          throw new IllegalStateException("Unrecognized command " + command);
      }
    }
    return code;
  }
}
