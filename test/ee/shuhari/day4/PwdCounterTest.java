package ee.shuhari.day4;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class PwdCounterTest extends TestCase {

  public void testSameDigitPassword() {
    assertFalse(PwdCounter.isSuitable(111111));
  }

  public void testDecreasingPassword() {
    assertFalse(PwdCounter.isSuitable(223450));
  }

  public void testNoDoublePassword() {
    assertFalse(PwdCounter.isSuitable(123789));
  }

  public void testRepeatsNoLongerThanTwicePassword() {
    assertTrue(PwdCounter.isSuitable(112233));
    assertFalse(PwdCounter.isSuitable(111222));
    assertFalse(PwdCounter.isSuitable(123444));
    assertTrue(PwdCounter.isSuitable(111122));
    assertTrue(PwdCounter.isSuitable(112222));
    assertTrue(PwdCounter.isSuitable(112344));

  }


}