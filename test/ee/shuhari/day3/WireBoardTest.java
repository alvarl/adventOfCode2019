package ee.shuhari.day3;

import junit.framework.TestCase;

public class WireBoardTest extends TestCase {
  private WireBoard wireBoard;

  public void testWiresCutIntoSegments() {
    wireBoard = new WireBoard();
    wireBoard.addWire("L10,D10,R200,D20");
    assertEquals(4, wireBoard.getWires().get(0).size());

    WireBoard.Segment segment = wireBoard.getWires().get(0).get(3);
    assertEquals(190, segment.end.x);
    assertEquals(-30, segment.end.y);
  }

  public void testWiresCrossing() {
    wireBoard = new WireBoard();
    wireBoard.addWire("L10,D10,R200,D20");
    wireBoard.addWire("U10,R15,D30,R5,U30");

    assertEquals(2, wireBoard.getCrossings().size());
  }

  public void testWireDoesntCrossWithItselfCrossing() {
    wireBoard = new WireBoard();
    wireBoard.addWire("L10,D10,R200,D20,L20,U30");
    wireBoard.addWire("U10,R15,D30,R5,U30");

    assertEquals(2, wireBoard.getCrossings().size());
  }

  public void testFindDistanceOfClosestCrossing() {
    wireBoard = new WireBoard();
    wireBoard.addWire("L10,D10,R200,D20");
    wireBoard.addWire("U10,R15,D30,R5,U30");

    assertEquals(25, wireBoard.getDistanceOfClosestCrossing());

  }

  public void testFindDistanceOfLeastStepsCrossing() {
    wireBoard = new WireBoard();
    wireBoard.addWire("L100,D10,R200,D10,L200");
    wireBoard.addWire("D20,L30,U30");

    assertEquals(100, wireBoard.getDistanceOfClosestCrossingByPathWalked());
    assertEquals(10, wireBoard.getDistanceOfClosestCrossing());

  }

  public void testCrossingPointDirectDistanceWithRealData() {
    wireBoard = new WireBoard();
    wireBoard.addWire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
    wireBoard.addWire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
    assertEquals(135, wireBoard.getDistanceOfClosestCrossing());

    wireBoard = new WireBoard();
    wireBoard.addWire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
    wireBoard.addWire("U62,R66,U55,R34,D71,R55,D58,R83");
    assertEquals(159, wireBoard.getDistanceOfClosestCrossing());

  }

  public void testCrossingPointPathWalkedDistanceWithRealData() {
    wireBoard = new WireBoard();
    wireBoard.addWire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
    wireBoard.addWire("U62,R66,U55,R34,D71,R55,D58,R83");
    assertEquals(610, wireBoard.getDistanceOfClosestCrossingByPathWalked());

    wireBoard = new WireBoard();
    wireBoard.addWire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
    wireBoard.addWire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");
    assertEquals(410, wireBoard.getDistanceOfClosestCrossingByPathWalked());

  }


  public void testParallelSegmentsDontCross() {
    WireBoard.Segment s1 = new WireBoard.Segment("", 1, 1, 1, 9);
    WireBoard.Segment s2 = new WireBoard.Segment("", 3, 1, 3, 9);

    assertFalse(s1.isCrossing(s2));

    WireBoard.Segment s3 = new WireBoard.Segment("", 1, 1, 5555, 1);
    WireBoard.Segment s4 = new WireBoard.Segment("", 1, 3, 10000, 3);

    assertFalse(s3.isCrossing(s4));
  }

  public void testTouchingSegmentsDontCross() {
    WireBoard.Segment s1 = new WireBoard.Segment("", 1, 1, 1, 9);
    WireBoard.Segment s2 = new WireBoard.Segment("", 1, 5, 10, 5);

    assertFalse(s1.isCrossing(s2));
    assertFalse(s2.isCrossing(s1));
  }

  public void testCrossingSegmentsCross() {
    WireBoard.Segment s1 = new WireBoard.Segment("", 1, 1, 1, 9);
    WireBoard.Segment s2 = new WireBoard.Segment("", -5, 5, 10, 5);

    assertTrue(s1.isCrossing(s2));
    assertTrue(s2.isCrossing(s1));
  }

  public void testGetCrossing() {
    WireBoard.Segment s1 = new WireBoard.Segment("", 1, 1, 1, 9);
    WireBoard.Segment s2 = new WireBoard.Segment("", -5, 5, 10, 5);

    WireBoard.Point crossing = s1.getCrossingPoint(s2);
    assertEquals(1,crossing.x);
    assertEquals(5,crossing.y);

    crossing = s2.getCrossingPoint(s1);
    assertEquals(1,crossing.x);
    assertEquals(5,crossing.y);
  }
}
