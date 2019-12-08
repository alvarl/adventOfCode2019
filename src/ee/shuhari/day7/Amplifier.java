package ee.shuhari.day7;

import ee.shuhari.computer.IntComputer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Amplifier {

  static final int[] PROGRAM = new int[]{3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 34, 51, 68, 89, 98, 179, 260, 341, 422, 99999, 3, 9, 1001, 9, 4, 9, 102, 4, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 5, 9, 1001, 9, 2, 9, 1002, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 3, 9, 102, 3, 9, 9, 101, 4, 9, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 101, 2, 9, 9, 1002, 9, 5, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 102, 2, 9, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 99};
  public static final int INPUT_POWER = 0;
  static int max = 0;

  public static void main(String[] args) {

    ArrayList<Integer> phases = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
 //   permutateAndProcess(phases, 0);

    phases = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9));
    permutateAndProcess(phases, 0);

    System.out.println(max);
  }

  static void permutateAndProcess(java.util.List<Integer> arr, int k) {
    for (int i = k; i < arr.size(); i++) {
      java.util.Collections.swap(arr, i, k);
      permutateAndProcess(arr, k + 1);
      java.util.Collections.swap(arr, k, i);
    }
    if (k == arr.size() - 1) {
      max = Math.max(max, processContinuousPhasePermutations(arr));
    }
  }

  private static void processSinglePassPhasePermutations(List<Integer> arr) {
    int outputPower = 0;
    ArrayList<Integer> output = new ArrayList<>();
    for (Integer integer : arr) {
      output.clear();
      ArrayList<Integer> providedInputs = new ArrayList<>(Arrays.asList(integer, outputPower));
      IntComputer computer = new IntComputer(providedInputs, output);
      computer.compute(PROGRAM);
      outputPower = output.get(0);
    }
    System.out.println(outputPower);
  }

  private static int processContinuousPhasePermutations(List<Integer> phases) {
    System.out.println(phases.toArray().toString());
    int outputPower = 0;
    List<Integer> lastOutput = Collections.synchronizedList(new ArrayList<>());
    List<Thread> amplifiers = new ArrayList<>();

    IntComputer prevAmplifier = null;
    for (Integer phase : phases) {
      IntComputer a = new IntComputer();
      if(prevAmplifier != null) {
        a.inputTap = prevAmplifier.outputSink;
      } else {
        a.inputTap = lastOutput;
      }
      a.inputTap.add(phase);
      if(prevAmplifier == null) {
        a.inputTap.add(INPUT_POWER);
      }
      a.outputSink = Collections.synchronizedList(new ArrayList<>());
      prevAmplifier = a;
      amplifiers.add(new Thread("Amplifier-"+amplifiers.size()) {
        @Override
        public void run() {
          System.out.println("Running " + Thread.currentThread().getName());
          a.compute(Arrays.copyOf(PROGRAM, PROGRAM.length));
        }
      });
    }
    prevAmplifier.outputSink = lastOutput;
    for (Thread amplifier : amplifiers) {
      amplifier.start();
    }
    for (Thread amplifier : amplifiers) {
      try {
        amplifier.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(lastOutput.size());
    System.out.println(lastOutput.get(0));
    return lastOutput.get(0);
  }

}
