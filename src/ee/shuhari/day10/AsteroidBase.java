package ee.shuhari.day10;


import java.util.*;

public class AsteroidBase {
  Point[][] map = new Point[0][0];
  List<Point> asteroids = new ArrayList<>();
  int maxVisibleAsteroids;
  private Point bestCandidate;

  static final double THRESHOLD = .0001;

  public AsteroidBase(String mapString) {
    parse(mapString);
  }

  private void parse(String mapString) {
    String[] lines = mapString.split("\n");
    this.map = new Point[lines[0].length()][lines.length];
    for (int y = 0; y < lines.length; y++) {
      String line = lines[y];
      int index = -1;
      int x;
      while((x = line.indexOf("#", index+1)) > -1) {
        index = x;
        asteroids.add(new Point(x,y));
      }
    }
  }

  public static void main(String[] args) {


  }

  public Point findBaseLocationWithBestVisibility() {
    maxVisibleAsteroids = 0;
    bestCandidate = null;
    Map<Point,Integer> candidates = new HashMap<>();
    for (Point candidate : asteroids) {
      int visibleAsteroidsCount = 0;
      for (Point another : asteroids) {
        if(candidate != another) {

          if(!isBlockedAny(candidate, another, asteroids))
            visibleAsteroidsCount++;
        }
      }
      if(visibleAsteroidsCount > maxVisibleAsteroids) {
        maxVisibleAsteroids = visibleAsteroidsCount;
        bestCandidate = candidate;
      }
    }
    return bestCandidate;
  }

  static boolean isBlockedAny(Point candidate, Point another, List<Point> potentialBlockers) {
    boolean blocked = false;
    Line lineOfSight = new Line(candidate, another);
    for (Point potentialBlocker : potentialBlockers) {
      if(!(potentialBlocker == candidate || potentialBlocker == another)) {
        if(lineOfSight.crosses(potentialBlocker)) {
          blocked = true;
        }
      }
    }
    return blocked;
  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Point point = (Point) o;
      return x == point.x &&
              y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "Point{" +
              "x=" + x +
              ", y=" + y +
              '}';
    }
  }

  static class Line {
    Point start;
    Point end;

    public Line() {
      super();
    }

    public Line(int x1, int y1, int x2, int y2) {
      super();
      this.start = new Point(x1, y1);
      this.end = new Point(x2, y2);
    }

    public Line(Point start, Point end) {
      this.start = start;
      this.end = end;
    }

    boolean crosses(Point point) {
      double pointToStart = calcDistance(point, start);
      double pointToEnd = calcDistance(point, end);
      double startToEnd = calcDistance(start, end);
      return Math.abs(startToEnd - pointToStart - pointToEnd) < THRESHOLD;
    }

    double calcDistance(Point first, Point second) {
      return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }


  }
}
