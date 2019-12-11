package ee.shuhari.day10;


import java.util.*;

public class AsteroidBase {
  static final double THRESHOLD = .0001;
  Point[][] map = new Point[0][0];
  List<Point> asteroids = new ArrayList<>();
  int maxVisibleAsteroids;
  private Point bestCandidate;

  Map<Double, Set<Point>> sortedAsteroids;

  public AsteroidBase(String mapString) {
    parse(mapString);
  }

  public void vaporize(Point base) {
    sortedAsteroids = new TreeMap<>();
    for (Point asteroid : asteroids) {
      if(asteroid == base) continue;
      double angleFromTop = getAngleFromTop(base, asteroid);
      sortedAsteroids.putIfAbsent(angleFromTop, new TreeSet<>(new PointComparatorByDistanceFromBase(base)));
      sortedAsteroids.get(angleFromTop).add(asteroid);
    }
    int counter = 1;
    while(counter < asteroids.size()) {
      for (Set<Point> asteroids : sortedAsteroids.values()) {
        Point asteroidToRemove = null;
        for (Point asteroid : asteroids) {
          System.out.println("Vaporizing asteroid #"+(counter++) + " " + asteroid);
          asteroidToRemove = asteroid;
          break;
        }
        asteroids.remove(asteroidToRemove);
      }
    }
  }

  public static List<Point> sortByAngleFromTop(Point base, List<Point> otherPoints) {
    List<Point> sortedList = new ArrayList<>(otherPoints);
    sortedList.sort(new PointComparatorByAngleFromTop(base));
    return sortedList;
  }

  static double getAngleFromTop(Point base, Point point) {
    double slope = (double) (point.y - base.y) / (point.x - base.x);
    double angle = Math.abs(Math.atan(slope));
    if (point.x >= base.x && point.y <= base.y) { // top right
      return Math.PI / 2 - angle;
    }
    if (point.x >= base.x) { // bottom right
      return Math.PI / 2 + angle;
    }
    if (point.y > base.y) { // bottom left
      return 3 * Math.PI / 2 - angle;
    }
    return 3 * Math.PI / 2 + angle; // top left
  }

  public static void main(String[] args) {


  }

  static boolean isBlockedAny(Point candidate, Point another, List<Point> potentialBlockers) {
    boolean blocked = false;
    Line lineOfSight = new Line(candidate, another);
    for (Point potentialBlocker : potentialBlockers) {
      if (!(potentialBlocker == candidate || potentialBlocker == another)) {
        if (lineOfSight.crosses(potentialBlocker)) {
          blocked = true;
        }
      }
    }
    return blocked;
  }

  private void parse(String mapString) {
    String[] lines = mapString.split("\n");
    this.map = new Point[lines[0].length()][lines.length];
    for (int y = 0; y < lines.length; y++) {
      String line = lines[y];
      int index = -1;
      int x;
      while ((x = line.indexOf("#", index + 1)) > -1) {
        index = x;
        asteroids.add(new Point(x, y));
      }
    }
  }

  public Point findBaseLocationWithBestVisibility() {
    maxVisibleAsteroids = 0;
    bestCandidate = null;
    Map<Point, Integer> candidates = new HashMap<>();
    for (Point candidate : asteroids) {
      int visibleAsteroidsCount = 0;
      for (Point another : asteroids) {
        if (candidate != another) {

          if (!isBlockedAny(candidate, another, asteroids))
            visibleAsteroidsCount++;
        }
      }
      if (visibleAsteroidsCount > maxVisibleAsteroids) {
        maxVisibleAsteroids = visibleAsteroidsCount;
        bestCandidate = candidate;
      }
    }
    return bestCandidate;
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

    static double calcDistance(Point first, Point second) {
      return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }


  }

  private static class PointComparatorByAngleFromTop implements Comparator<Point> {
    private final Point base;

    public PointComparatorByAngleFromTop(Point base) {
      this.base = base;
    }

    @Override
    public int compare(Point o1, Point o2) {
      double angle1 = getAngleFromTop(base, o1);
      double angle2 = getAngleFromTop(base, o2);
      double diff = angle1 - angle2;
      return (int) (diff / Math.abs(diff));
    }
  }
  private static class PointComparatorByDistanceFromBase implements Comparator<Point> {
    private final Point base;

    public PointComparatorByDistanceFromBase(Point base) {
      this.base = base;
    }

    @Override
    public int compare(Point o1, Point o2) {
      double distance1 = Line.calcDistance(base, o1);
      double distance2 = Line.calcDistance(base, o2);
      double diff = distance1 - distance2;
      return (int) (diff / Math.abs(diff));
    }
  }
}
