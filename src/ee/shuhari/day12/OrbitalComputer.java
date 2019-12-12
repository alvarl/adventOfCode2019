package ee.shuhari.day12;

import java.math.BigInteger;
import java.util.*;

public class OrbitalComputer {

  List<Body> bodies;
  List<Body> initial;
  Map<Body, Triple> cycles = new HashMap<>();
  Triple matchTicks = new Triple(0, 0, 0);

  public OrbitalComputer(List<Body> bodies) {
    this.bodies = bodies;
    initial = getCopyOfBodies(bodies);
  }

  public static void main(String[] args) {
    OrbitalComputer input = new OrbitalComputer(Arrays.asList(
            new Body(7, 10, 17),
            new Body(-2, 7, 0),
            new Body(12, 5, 12),
            new Body(5, -8, 6)));
    OrbitalComputer smallTest = new OrbitalComputer(Arrays.asList(
            new Body(-1, 0, 2),
            new Body(2, -10, -7),
            new Body(4, -8, 8),
            new Body(3, 5, -1)));
    OrbitalComputer largeTest = new OrbitalComputer(Arrays.asList(
            new Body(-8, -10, 0),
            new Body(5, 5, 10),
            new Body(2, -7, 3),
            new Body(9, -8, -3)));

    List<OrbitalComputer> ocs = Arrays.asList(input, smallTest, largeTest);
    OrbitalComputer oc = ocs.get(Integer.parseInt(args[0]));
    oc.model();
    System.out.println(oc.matchTicks.x + ", " + oc.matchTicks.y + ", " + oc.matchTicks.z);
  }

  private static BigInteger gcd(BigInteger x, BigInteger y) {
    return (y.equals(BigInteger.ZERO)) ? x : gcd(y, x.mod(y));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrbitalComputer that = (OrbitalComputer) o;
    return bodies.equals(that.bodies);
  }

  void model() {
    long i = 1;
    while (matchTicks.x == 0 || matchTicks.y == 0 || matchTicks.z == 0) {
      if (i % 1000000 == 0) {
        System.out.println(i);
      }
      tick(i++);
    }
  }

  public int calculateEnergy() {
    int total = 0;
    for (Body body : bodies) {
      total += body.getEnergy();
    }
    return total;
  }

  private void tick(long tick) {
    for (Body body : bodies) {
      for (Body other : bodies) {
        if (body != other)
          body.adjustVelocity(other);
      }
    }
    boolean xMatchesInitial = true, yMatchesInitial = true, zMatchesInitial = true;
    for (int i = 0; i < bodies.size(); i++) {
      Body body = bodies.get(i);
      body.updateLocation();
      Body initialState = initial.get(i);
      if (body.location.x != initialState.location.x || body.velocity.x != 0) {
        xMatchesInitial = false;
      }

      if (body.location.y != initialState.location.y || body.velocity.y != 0) {
        yMatchesInitial = false;
      }

      if (body.location.z != initialState.location.z || body.velocity.z != 0) {
        zMatchesInitial = false;
      }
    }
    if(xMatchesInitial && matchTicks.x == 0) {
      matchTicks.x = tick;
    }
    if(yMatchesInitial && matchTicks.y == 0) {
      matchTicks.y = tick;
    }
    if(zMatchesInitial && matchTicks.z == 0) {
      matchTicks.z = tick;
    }
  }

  private Triple getCycleTripleForBody(Body body) {
    if (!cycles.containsKey(body)) {
      cycles.put(body, new Triple());
    }
    return cycles.get(body);
  }

  OrbitalComputer getCopy() {
    List<Body> copyOfBodies = new ArrayList<>();
    getCopyOfBodies(copyOfBodies);
    return new OrbitalComputer(copyOfBodies);
  }

  private List<Body> getCopyOfBodies(List<Body> bodies) {
    List<Body> copy = new ArrayList<>();
    for (Body body : bodies) {
      copy.add(body.copyByLocation());
    }
    return copy;
  }

  static class Body {
    Triple location, velocity = new Triple(0, 0, 0);

    public Body(long locationX, long locationY, long locationZ) {
      this.location = new Triple(locationX, locationY, locationZ);
    }

    long getEnergy() {
      long potential = Math.abs(location.x) + Math.abs(location.y) + Math.abs(location.z);
      long kinetic = Math.abs(velocity.x) + Math.abs(velocity.y) + Math.abs(velocity.z);
      return potential * kinetic;
    }

    void adjustVelocity(Body other) {
      long diff = other.location.x - location.x;
      if (diff != 0) velocity.x += (diff / Math.abs(diff));

      diff = other.location.y - location.y;
      if (diff != 0) velocity.y += (diff / Math.abs(diff));

      diff = other.location.z - location.z;
      if (diff != 0) velocity.z += (diff / Math.abs(diff));
    }

    public void updateLocation() {
      this.location.x += this.velocity.x;
      this.location.y += this.velocity.y;
      this.location.z += this.velocity.z;
    }

    Body copyByLocation() {
      return new Body(location.x, location.y, location.z);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Body body = (Body) o;
      return Objects.equals(location, body.location) &&
              Objects.equals(velocity, body.velocity);
    }

    @Override
    public String toString() {
      return location + ", " + velocity;
    }
  }

  static class Triple {
    long x, y, z;

    public Triple(long x, long y, long z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public Triple() {

    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Triple triple = (Triple) o;
      return x == triple.x &&
              y == triple.y &&
              z == triple.z;
    }

    @Override
    public String toString() {
      return x + ", " + y + ", " + z;
    }
  }

//  public static BigInteger lcm(long[] numbers) {
//    return Arrays.stream(numbers).reduce(1, (x, y) -> {
//      return BigInteger.valueOf(x).multiply(BigInteger.valueOf(y).divide(gcd(BigInteger.valueOf(x), BigInteger.valueOf(y))));
//    });
//  }
}
