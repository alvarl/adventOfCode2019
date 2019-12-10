package ee.shuhari.day10;

import junit.framework.TestCase;

public class AsteroidBaseTest extends TestCase {

  private AsteroidBase.Line line = new AsteroidBase.Line(0, 0, 2, 2);

  public void testPointOnLine() {
    AsteroidBase.Point pointOnLine = new AsteroidBase.Point(1,1);
    AsteroidBase.Point pointNotOnLine = new AsteroidBase.Point(1,2);

    assertTrue(line.crosses(pointOnLine));
    assertFalse(line.crosses(pointNotOnLine));


  }

  public void testDistance() {
    assertEquals(2 * Math.sqrt(2), line.calcDistance(line.start, line.end));
  }
}
