package ee.shuhari.day3;

import java.util.*;

public class WireBoard {
  List<List<String>> rawWires = new ArrayList<>();
  List<List<Segment>> wires = new ArrayList<>();

  public void addWire(String wireString) {
    String[] segmentStrings = wireString.split(",");
    int x = 0, y = 0;
    List<Segment> segments = new ArrayList<>();
    for (int i = 0; i < segmentStrings.length; i++) {
      String s = segmentStrings[i];
      Segment segment = buildSegment(s, x, y);
      segments.add(segment);
      x = segment.end.x;
      y = segment.end.y;
    }
    wires.add(segments);
  }

  private Segment buildSegment(String s, int startX, int startY) {
    String direction = s.substring(0, 1);
    int segmentLength = Integer.parseInt(s.substring(1));
    int endX = startX, endY = startY;
    if (direction.equals("D")) {
      endY = startY - segmentLength;
    }
    if (direction.equals("U")) {
      endY = startY + segmentLength;
    }
    if (direction.equals("L")) {
      endX = startX - segmentLength;
    }
    if (direction.equals("R")) {
      endX = startX + segmentLength;
    }
    return new Segment(direction, startX, startY, endX, endY);
  }

  public Collection<Crossing> getCrossings() {
    Set<Crossing> crossings = new HashSet<>();
    for (List<Segment> first : wires) {
      for (List<Segment> second : wires) {
        if(first == second) break;
        int firstWalked = 0;
        for (Segment firstSegment : first) {
          int secondWalked = 0;
          for (Segment secondSegment : second) {
            if (firstSegment.isCrossing(secondSegment)) {
              Point crossingPoint = firstSegment.getCrossingPoint(secondSegment);
              int firstSteps, secondSteps;
              firstSteps = getStepsToCrossing(firstSegment, crossingPoint);
              secondSteps = getStepsToCrossing(secondSegment, crossingPoint);
              crossings.add(new Crossing(crossingPoint, firstWalked + firstSteps, secondWalked + secondSteps));
            }
            secondWalked += secondSegment.length;
          }
          firstWalked += firstSegment.length;
        }
      }
    }
    return crossings;
  }

  private int getStepsToCrossing(Segment segment, Point crossingPoint) {
    int firstSteps;
    if("D".equals(segment.direction)) {
      firstSteps = segment.top() - crossingPoint.y;
    } else if("U".equals(segment.direction)) {
      firstSteps = crossingPoint.y - segment.bottom();
    } else if("L".equals(segment.direction)) {
      firstSteps = segment.right() - crossingPoint.x;
    } else {
      firstSteps = crossingPoint.x - segment.left();
    }
    return firstSteps;
  }

  public int getDistanceOfClosestCrossing() {
    int min = Integer.MAX_VALUE;
    for (Crossing crossing : getCrossings()) {
      int distance = Math.abs(crossing.point.x) + Math.abs(crossing.point.y);
      min = Math.min(min, distance);
    }
    return min;
  }

  public int getDistanceOfClosestCrossingByPathWalked() {
    int min = Integer.MAX_VALUE;
    for (Crossing crossing : getCrossings()) {
      int distance = Math.abs(crossing.firstPathLength) + Math.abs(crossing.secondPathLength);
      min = Math.min(min, distance);
    }
    return min;
  }

  public List<List<Segment>> getWires() {
    return wires;
  }


  public static class Segment {
    String direction;
    Point start;
    Point end;
    int length;

    public Segment(String direction, int startX, int startY, int endX, int endY) {
      this.start = new Point(startX, startY);
      this.end = new Point(endX, endY);
      this.direction = direction;
      length = Math.max(right() - left(), top()-bottom());
    }

    public int left() {
      return Math.min(start.x, end.x);
    }

    public int right() {
      return Math.max(start.x, end.x);
    }

    public int top() {
      return Math.max(start.y, end.y);
    }

    public int bottom() {
      return Math.min(start.y, end.y);
    }

    public boolean isVertical() {
      return start.x == end.x;
    }

    public boolean isHorizontal() {
      return start.y == end.y;
    }

    public boolean isCrossing(Segment other) {
      if (this.isHorizontal()) {
        if (other.isHorizontal()) return false;
        return (this.left() < other.left() && this.right() > other.right()) &&
                (other.top() > this.top() && other.bottom() < this.bottom());
      } else {
        if (other.isVertical()) return false;
        return (this.top() > other.top() && this.bottom() < other.bottom()) &&
                (other.left() < this.left() && other.right() > this.right());

      }
    }

    public Point getCrossingPoint(Segment other) {
      return this.isVertical() ? new Point(this.left(), other.top()) : new Point(other.left(), this.top());
    }
  }

  public static class Crossing {
    Point point;
    int firstPathLength, secondPathLength;

    public Crossing(Point point, int firstPathLength, int secondPathLength) {
      this.point = point;
      this.firstPathLength = firstPathLength;
      this.secondPathLength = secondPathLength;
    }
  }

  public static class Point {
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
  }
}
