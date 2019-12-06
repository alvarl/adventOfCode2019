package ee.shuhari.day6;

import junit.framework.TestCase;

public class OrbitalComputerTest extends TestCase {
  OrbitalComputer computer;

  public void testExampleOfOrbitCount() {
    String input = "D)I\n" +
            "D)E\n" +
            "B)C\n" +
            "C)D\n" +
            "E)F\n" +
            "G)H\n" +
            "B)G\n" +
            "COM)B\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L";
    computer = new OrbitalComputer(input);
    assertEquals(42, computer.countOrbits());
  }

  public void testExampleOfTransferCount() {
    String input = "D)I\n" +
            "D)E\n" +
            "B)C\n" +
            "C)D\n" +
            "E)F\n" +
            "G)H\n" +
            "B)G\n" +
            "COM)B\n" +
            "E)J\n" +
            "J)K\n" +
            "K)L\n" +
            "K)YOU\n" +
            "I)SAN";
    computer = new OrbitalComputer(input);
    assertEquals(4, computer.countTransfers("YOU", "SAN"));
  }
}
