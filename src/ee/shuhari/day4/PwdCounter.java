package ee.shuhari.day4;

public class PwdCounter {

  public static final int DIGITS = 6;

  public static void main(String[] args) {
    int lowerBound = 134564; // 134564
    int higherBound = 585159; // 585159

//    int lowerBound = 12; // 134564
//    int higherBound = 99; // 585159

    int count = 0;
    for (int i = lowerBound; i <= higherBound; i++) {
      if (isSuitable(i)) count++;
    }
    System.out.println(count);
  }

  static boolean isSuitable(int passwordCandidate) {
    boolean hasDouble = false;
    int[] digits = new int[DIGITS + 1];
    digits[0] = Integer.MAX_VALUE;
    int[] sameDigitCounter = new int[DIGITS];
    int doubleCounter = 0;
    for (int j = 1; j <= DIGITS; j++) {
      int x = (passwordCandidate % (int) (Math.pow(10, j))) / (int) (Math.pow(10, j - 1));
      if (x > digits[j - 1]) {
        return false;
      }
      if (x == digits[j - 1]) sameDigitCounter[doubleCounter]++;
      else if(j > 1) {
        doubleCounter++;
      }
      digits[j] = x;
    }
    for (int aDouble : sameDigitCounter) {
      if (aDouble == 1) return true;
    }
    return false;
  }
}
