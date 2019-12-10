package ee.shuhari.day10;


public class AsteroidBase {
  public static void main(String[] args) {


  }

  static class Point {
    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
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

    boolean crosses(Point point) {
      double pointToStart = calcDistance(point, start);
      double pointToEnd = calcDistance(point, end);
      double startToEnd = calcDistance(start, end);
      return pointToStart + pointToEnd == startToEnd;
    }

    double calcDistance(Point first, Point second) {
      return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
    }


  }
}
