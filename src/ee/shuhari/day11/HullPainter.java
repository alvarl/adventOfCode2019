package ee.shuhari.day11;

import ee.shuhari.computer.IntComputer;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HullPainter {
  static long[] program = new long[]{3,8,1005,8,332,1106,0,11,0,0,0,104,1,104,0,3,8,102,-1,8,10,101,1,10,10,4,10,108,1,8,10,4,10,101,0,8,28,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,101,0,8,51,1,1103,5,10,1,1104,9,10,2,1003,0,10,1,5,16,10,3,8,102,-1,8,10,101,1,10,10,4,10,108,0,8,10,4,10,1001,8,0,88,1006,0,2,1006,0,62,2,8,2,10,3,8,1002,8,-1,10,101,1,10,10,4,10,1008,8,1,10,4,10,102,1,8,121,1006,0,91,1006,0,22,1006,0,23,1006,0,1,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,101,0,8,155,1006,0,97,1,1004,2,10,2,1003,6,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,0,8,10,4,10,1002,8,1,187,1,104,15,10,2,107,9,10,1006,0,37,1006,0,39,3,8,1002,8,-1,10,1001,10,1,10,4,10,108,0,8,10,4,10,102,1,8,223,2,2,17,10,1,1102,5,10,3,8,1002,8,-1,10,101,1,10,10,4,10,108,0,8,10,4,10,1001,8,0,253,3,8,102,-1,8,10,1001,10,1,10,4,10,1008,8,1,10,4,10,1002,8,1,276,1006,0,84,3,8,102,-1,8,10,101,1,10,10,4,10,1008,8,0,10,4,10,1001,8,0,301,2,1009,9,10,1006,0,10,2,102,15,10,101,1,9,9,1007,9,997,10,1005,10,15,99,109,654,104,0,104,1,21102,1,936995738516L,1,21101,0,349,0,1105,1,453,21102,1,825595015976L,1,21102,1,360,0,1105,1,453,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,3,10,104,0,104,1,3,10,104,0,104,0,3,10,104,0,104,1,21102,46375541763l,1,1,21101,0,407,0,1105,1,453,21102,1,179339005019l,1,21101,0,418,0,1106,0,453,3,10,104,0,104,0,3,10,104,0,104,0,21102,825012036372l,1,1,21102,441,1,0,1105,1,453,21101,988648461076l,0,1,21101,452,0,0,1105,1,453,99,109,2,22102,1,-1,1,21102,40,1,2,21102,484,1,3,21101,0,474,0,1106,0,517,109,-2,2105,1,0,0,1,0,0,1,109,2,3,10,204,-1,1001,479,480,495,4,0,1001,479,1,479,108,4,479,10,1006,10,511,1102,1,0,479,109,-2,2105,1,0,0,109,4,2102,1,-1,516,1207,-3,0,10,1006,10,534,21101,0,0,-3,21202,-3,1,1,22101,0,-2,2,21102,1,1,3,21102,553,1,0,1106,0,558,109,-4,2106,0,0,109,5,1207,-3,1,10,1006,10,581,2207,-4,-2,10,1006,10,581,22102,1,-4,-4,1105,1,649,21202,-4,1,1,21201,-3,-1,2,21202,-2,2,3,21101,0,600,0,1105,1,558,21201,1,0,-4,21101,0,1,-1,2207,-4,-2,10,1006,10,619,21101,0,0,-1,22202,-2,-1,-2,2107,0,-3,10,1006,10,641,22102,1,-1,1,21102,1,641,0,106,0,516,21202,-2,-1,-2,22201,-4,-2,-4,109,-5,2105,1,0};

  int robotX=0,robotY = 0;
  int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
  int direction = 0;
  int outputCursor = 0;

  Map<Point,Integer> points = new HashMap<>();

  public static void main(String[] args) {
    HullPainter painter = new HullPainter();
    painter.paint();
    System.out.println(painter.points.size());
    painter.report();
  }

  private void report() {
    int yDelta = maxY - minY;
    int xDelta = maxX - minX;
    int[][] hullPoints = new int[yDelta+1][xDelta+1];
    for (Point point : points.keySet()) {
      hullPoints[point.y][point.x] = points.get(point);
    }
    for (int y = 0; y < hullPoints.length; y++) {
      for (int x = 0; x < hullPoints[y].length; x++) {
        System.out.print(hullPoints[y][x] > 0 ? "#" : " ");
      }
      System.out.println();
    }

  }

  private void paint() {
    ArrayList<Long> output = new ArrayList<>();
    ArrayList<Long> input = new ArrayList<>();
    IntComputer computer = new IntComputer(input, output);

    Thread computerThread = new Thread("IntComputer") {
      @Override
      public void run() {
        computer.compute(program);
      }
    };
    computerThread.start();
    while(computerThread.isAlive()) {
      Point p = new Point(robotX, robotY);
      points.putIfAbsent(p, points.size() == 0 ? 1 : 0);
      long color = points.get(p);
      input.add(color);

      color = getOutput(output);
      points.put(p, (int) color);

      int d = (int) getOutput(output);
      switch(d) {
        case 0:
          direction--; break;
        case 1:
          direction++;
      }
      if(direction < 0) direction += 4;
      if(direction > 3) direction -= 4;
      switch(direction) {
        case 0: robotY--; break;
        case 1: robotX++; break;
        case 2: robotY++; break;
        case 3: robotX--; break;
      }
      minX = Math.min(robotX, minX);
      minY = Math.min(robotY, minY);
      maxX = Math.max(robotX, maxX);
      maxY = Math.max(robotY, maxY);
      System.out.println(robotX +", "+ robotY);
    }

  }

  private long getOutput(List<Long> outputSource) {
    while(outputSource.size() == outputCursor) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return outputSource.get(outputCursor++);
  }


}
