package ee.shuhari.day10;

import junit.framework.TestCase;

import java.util.Arrays;

public class AsteroidBaseTest extends TestCase {

  static String sampleWith10TotalAsteroidsBestAt3and4 = ".#..#\n" +
          ".....\n" +
          "#####\n" +
          "....#\n" +
          "...##";
  static String sampleWith5DetectedAsteroidsBestAt1and1 = "###\n" +
          ".#.\n" +
          "#.#";
  static String sampleWith33DetectedAsteroidsBestAt5and8 = "......#.#.\n" +
          "#..#.#....\n" +
          "..#######.\n" +
          ".#.#.###..\n" +
          ".#..#.....\n" +
          "..#....#.#\n" +
          "#..#....#.\n" +
          ".##.#..###\n" +
          "##...#..#.\n" +
          ".#....####";
  static String sampleWith35AsteroidsBestAt1and2 = "#.#...#.#.\n" +
          ".###....#.\n" +
          ".#....#...\n" +
          "##.#.#.#.#\n" +
          "....#.#.#.\n" +
          ".##..###.#\n" +
          "..#...##..\n" +
          "..##....##\n" +
          "......#...\n" +
          ".####.###.";
  static String sampleWith210DetectedAsteroidsBestAt11and13 = ".#..##.###...#######\n" +
          "##.############..##.\n" +
          ".#.######.########.#\n" +
          ".###.#######.####.#.\n" +
          "#####.##.#.##.###.##\n" +
          "..#####..#.#########\n" +
          "####################\n" +
          "#.####....###.#.#.##\n" +
          "##.#################\n" +
          "#####.##.###..####..\n" +
          "..######..##.#######\n" +
          "####.##.####...##..#\n" +
          ".#####..#.######.###\n" +
          "##...#.##########...\n" +
          "#.##########.#######\n" +
          ".####.#.###.###.#.##\n" +
          "....##.##.###..#####\n" +
          ".#.#.###########.###\n" +
          "#.#.#.#####.####.###\n" +
          "###.##.####.##.#..##";
  String input = ".###.###.###.#####.#\n" +
          "#####.##.###..###..#\n" +
          ".#...####.###.######\n" +
          "######.###.####.####\n" +
          "#####..###..########\n" +
          "#.##.###########.#.#\n" +
          "##.###.######..#.#.#\n" +
          ".#.##.###.#.####.###\n" +
          "##..#.#.##.#########\n" +
          "###.#######.###..##.\n" +
          "###.###.##.##..####.\n" +
          ".##.####.##########.\n" +
          "#######.##.###.#####\n" +
          "#####.##..####.#####\n" +
          "##.#.#####.##.#.#..#\n" +
          "###########.#######.\n" +
          "#.##..#####.#####..#\n" +
          "#####..#####.###.###\n" +
          "####.#.############.\n" +
          "####.#.#.##########.";
  private AsteroidBase.Line line = new AsteroidBase.Line(0, 0, 2, 2);

  public void testPointOnLine() {
    AsteroidBase.Point pointOnLine = new AsteroidBase.Point(1, 1);
    AsteroidBase.Point pointNotOnLine = new AsteroidBase.Point(1, 2);

    assertTrue(line.crosses(pointOnLine));
    assertFalse(line.crosses(pointNotOnLine));
  }

  public void testDistance() {
    assertEquals(2 * Math.sqrt(2), line.calcDistance(line.start, line.end));
  }

  public void testAsteroidCount() {
    AsteroidBase base = new AsteroidBase(sampleWith10TotalAsteroidsBestAt3and4);
    assertEquals(10, base.asteroids.size());
  }

  public void testIsBlockedByAny() {
    AsteroidBase.Point x0y0 = new AsteroidBase.Point(0, 0);
    AsteroidBase.Point x10y10 = new AsteroidBase.Point(10, 10);
    AsteroidBase.Point x4y10 = new AsteroidBase.Point(4, 10);
    AsteroidBase.Point x2y5 = new AsteroidBase.Point(2, 5);

    assertFalse(AsteroidBase.isBlockedAny(x0y0, x10y10, Arrays.asList(x0y0, x10y10, x4y10, x2y5)));

  }

  public void testIfCrosses() {
    AsteroidBase.Point x0y0 = new AsteroidBase.Point(0, 0);
    AsteroidBase.Point x10y10 = new AsteroidBase.Point(10, 10);
    AsteroidBase.Point x4y10 = new AsteroidBase.Point(4, 10);
    AsteroidBase.Point x2y5 = new AsteroidBase.Point(2, 5);

    AsteroidBase.Point x1y0 = new AsteroidBase.Point(1, 0);
    AsteroidBase.Point x3y2 = new AsteroidBase.Point(3, 2);
    AsteroidBase.Point x4y3 = new AsteroidBase.Point(4, 3);

    assertTrue(new AsteroidBase.Line(x0y0, x4y10).crosses(x2y5));
    assertFalse(new AsteroidBase.Line(x0y0, x10y10).crosses(x2y5));
    assertFalse(new AsteroidBase.Line(x0y0, x10y10).crosses(x4y10));

    assertTrue(new AsteroidBase.Line(x1y0, x4y3).crosses(x3y2));
  }

  public void testFindAsteroidWithMostVisibleOthers() {
    AsteroidBase base = new AsteroidBase(sampleWith5DetectedAsteroidsBestAt1and1);
    assertEquals(new AsteroidBase.Point(1, 0), base.findBaseLocationWithBestVisibility());
    assertEquals(5, base.maxVisibleAsteroids);

    base = new AsteroidBase(sampleWith10TotalAsteroidsBestAt3and4);
    assertEquals(new AsteroidBase.Point(3, 4), base.findBaseLocationWithBestVisibility());
    assertEquals(8, base.maxVisibleAsteroids);

    base = new AsteroidBase(sampleWith33DetectedAsteroidsBestAt5and8);
    assertEquals(new AsteroidBase.Point(5, 8), base.findBaseLocationWithBestVisibility());
    assertEquals(33, base.maxVisibleAsteroids);

    base = new AsteroidBase(sampleWith35AsteroidsBestAt1and2);
    assertEquals(new AsteroidBase.Point(1, 2), base.findBaseLocationWithBestVisibility());
    assertEquals(35, base.maxVisibleAsteroids);

    base = new AsteroidBase(sampleWith210DetectedAsteroidsBestAt11and13);
    assertEquals(new AsteroidBase.Point(11, 13), base.findBaseLocationWithBestVisibility());
    assertEquals(210, base.maxVisibleAsteroids);
  }

  public void testInput() {
    AsteroidBase base = new AsteroidBase(input);
    base.findBaseLocationWithBestVisibility();
    System.out.println(base.maxVisibleAsteroids);
  }
}
