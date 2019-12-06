package ee.shuhari.day6;

import java.util.*;

public class OrbitalComputer {
  Map<String,Body> bodies = new HashMap<>();

  public static void main(String[] args) {
    OrbitalComputer computer = new OrbitalComputer(INPUT);
    int i = computer.countOrbits();
    System.out.println(i);
    int j = computer.countTransfers("YOU", "SAN");
    System.out.println(j);
  }

  public OrbitalComputer(String input) {
    parseBodies(input.split("\n"));
  }

  public int countOrbits() {
    int totalOrbits = 0;
    for (Body body : bodies.values()) {
      totalOrbits += body.orbits;
    }
    return totalOrbits;
  }

  public int countTransfers(String firstName, String secondName) {
    Body first = bodies.get(firstName);
    Body second = bodies.get(secondName);
    List<String> firstParentNames = first.getParentNames();
    List<String> secondparentNames = second.getParentNames();

    String lastMatchingParent;
    for (int i = 0; i< Math.min(firstParentNames.size(), secondparentNames.size()); i++) {
      if(firstParentNames.get(i).equals(secondparentNames.get(i))) {
        lastMatchingParent = firstParentNames.get(i);
      } else {
        return (firstParentNames.size() - i) + (secondparentNames.size() - i);
      }
    }
    return 0;
  }

  private void parseBodies(String[] orbits) {
    for (String orbit : orbits) {
      String[] parts = orbit.split("\\)");

      String orbiterName = parts[1];
      Body orbiter = bodies.get(orbiterName);
      if(orbiter == null) {
        orbiter = new Body(orbiterName);
        bodies.put(orbiterName, orbiter);
      }

      String bodyName = parts[0];

      Body body = bodies.get(bodyName);
      if(body == null) {
        body = new Body(bodyName);
        bodies.put(bodyName, body);
      }
      body.addChild(orbiter);
      orbiter.propagateOrbits(body.orbits + 1);
    }
  }

  static class Body {
    String name;
    int orbits;
    Body parent;
    List<Body> children = new LinkedList<>();

    public Body(String name) {
      this.name = name;
    }

    void addChild(Body child) {
      children.add(child);
      child.parent = this;
    }

    List<String> getParentNames() {
      List<String> parentNames = new ArrayList<>();
      Body parent = this.parent;
      while(parent != null) {
        parentNames.add(0, parent.name);
        parent = parent.parent;
      }
      return parentNames;
    }

    void propagateOrbits(int count) {
      orbits += count;
      for (Body child : children) {
        child.propagateOrbits(count);
      }
    }
  }

  static String INPUT = "Z52)127\n" +
          "271)7TV\n" +
          "CJN)HYP\n" +
          "TCQ)65C\n" +
          "2R9)6QM\n" +
          "VND)VMR\n" +
          "1KM)47Y\n" +
          "Z83)CGH\n" +
          "8RF)17J\n" +
          "Q5D)S5F\n" +
          "WGK)HWX\n" +
          "QZH)671\n" +
          "RZ2)Z2F\n" +
          "6K1)9ZH\n" +
          "973)NL2\n" +
          "6YN)YFY\n" +
          "FCS)288\n" +
          "X8D)K15\n" +
          "QQY)2X8\n" +
          "95J)6QQ\n" +
          "W21)YN2\n" +
          "ZRC)9LQ\n" +
          "J23)2SW\n" +
          "F9T)G7L\n" +
          "GC4)5YL\n" +
          "QYS)152\n" +
          "9YX)5ZR\n" +
          "T8Q)Z4K\n" +
          "SK8)B8N\n" +
          "XQH)ZVG\n" +
          "XQZ)LHX\n" +
          "PSM)M9M\n" +
          "H1K)1WV\n" +
          "P81)ZCN\n" +
          "TGJ)44W\n" +
          "JNL)352\n" +
          "44W)2NZ\n" +
          "V5D)LPJ\n" +
          "VML)86P\n" +
          "5CK)4FB\n" +
          "53S)JKL\n" +
          "TQV)HJ8\n" +
          "9WS)S6K\n" +
          "5S9)43T\n" +
          "KKQ)KHD\n" +
          "S2N)YNN\n" +
          "RXB)5X6\n" +
          "T4G)ND4\n" +
          "9J3)JRY\n" +
          "T9C)3R1\n" +
          "7NF)F26\n" +
          "TL7)SMJ\n" +
          "521)4NS\n" +
          "YXH)8Z5\n" +
          "KWX)WF3\n" +
          "65C)NWB\n" +
          "3S7)SZ1\n" +
          "288)SDL\n" +
          "GWL)QLT\n" +
          "CYX)6RF\n" +
          "RFV)JKN\n" +
          "PND)3K9\n" +
          "CRT)V5D\n" +
          "ZY3)2V8\n" +
          "QDR)DLR\n" +
          "MXS)1X7\n" +
          "2KK)T44\n" +
          "P2L)8B4\n" +
          "GZ8)C71\n" +
          "SLZ)LQV\n" +
          "JTZ)WGK\n" +
          "1QG)3S7\n" +
          "YZN)9XL\n" +
          "HKG)X8D\n" +
          "RHS)VL1\n" +
          "X1Z)K51\n" +
          "KDP)XFP\n" +
          "P6B)YL6\n" +
          "237)76W\n" +
          "PV1)Z1M\n" +
          "DY6)GWL\n" +
          "1X7)WBM\n" +
          "8RF)XQZ\n" +
          "DPQ)RQ4\n" +
          "54D)9QW\n" +
          "YNN)LQ9\n" +
          "WTT)V79\n" +
          "2R3)Y1J\n" +
          "69B)GK3\n" +
          "Q4Z)2DK\n" +
          "F8R)5QG\n" +
          "KD1)HGK\n" +
          "J5N)9ZR\n" +
          "4GR)67G\n" +
          "HXT)R2T\n" +
          "NDY)9YH\n" +
          "QJH)62P\n" +
          "SLP)JCZ\n" +
          "SSS)TY9\n" +
          "BCF)93Q\n" +
          "CWV)SLP\n" +
          "1YG)Y5C\n" +
          "72D)KPT\n" +
          "6FY)JK6\n" +
          "WQ8)CPN\n" +
          "RCN)P6B\n" +
          "NBV)QJH\n" +
          "G7W)P9Q\n" +
          "K9B)S9G\n" +
          "G8X)14T\n" +
          "FTS)QLY\n" +
          "LNC)T8Q\n" +
          "2DK)51S\n" +
          "4FT)QHB\n" +
          "Z7W)KZ2\n" +
          "39B)LXJ\n" +
          "GNK)1PS\n" +
          "BCX)D82\n" +
          "FFC)6TP\n" +
          "3JC)SXQ\n" +
          "CTM)YXV\n" +
          "3Q4)763\n" +
          "L6C)YMM\n" +
          "GBL)S2T\n" +
          "SHB)Q3B\n" +
          "GLD)NGP\n" +
          "1R6)R3V\n" +
          "68P)LNL\n" +
          "QLT)4NJ\n" +
          "CLD)7G6\n" +
          "6S4)973\n" +
          "JSD)7FS\n" +
          "Y4P)488\n" +
          "HK9)X65\n" +
          "7WC)VVV\n" +
          "67S)DQP\n" +
          "HWX)9G7\n" +
          "68M)M7T\n" +
          "78F)BCF\n" +
          "JCZ)X4V\n" +
          "VNJ)FH2\n" +
          "82Z)MNC\n" +
          "3K9)7LZ\n" +
          "X1P)GZ5\n" +
          "PJ7)GVH\n" +
          "KH7)1YG\n" +
          "Z12)XG7\n" +
          "DPL)PYW\n" +
          "476)QKB\n" +
          "SPC)794\n" +
          "ZZD)LCB\n" +
          "CRC)B6M\n" +
          "Z9P)9YX\n" +
          "2X8)GMM\n" +
          "HKY)765\n" +
          "VSN)G5V\n" +
          "SL7)LF3\n" +
          "XLP)SLZ\n" +
          "8Z5)XDC\n" +
          "1PS)ZKF\n" +
          "CPZ)Q9J\n" +
          "38V)JJX\n" +
          "FF8)ZXT\n" +
          "43T)4DP\n" +
          "H6R)2CP\n" +
          "B57)YQD\n" +
          "1BT)QZH\n" +
          "74M)25B\n" +
          "D2F)FYK\n" +
          "P9Q)Q5D\n" +
          "ZZW)7V2\n" +
          "F6G)ZRZ\n" +
          "JK6)Y3J\n" +
          "YL6)97X\n" +
          "424)2CW\n" +
          "NPC)8Z8\n" +
          "RTC)PKX\n" +
          "NH4)PND\n" +
          "QLY)MXS\n" +
          "Q9J)Z84\n" +
          "ZRZ)GHQ\n" +
          "L1M)1GF\n" +
          "GBQ)N44\n" +
          "JQ3)PWM\n" +
          "6ZZ)K4D\n" +
          "67S)RR8\n" +
          "ZYM)YM1\n" +
          "69K)ZP6\n" +
          "TJS)HM1\n" +
          "P9W)Z8V\n" +
          "5NV)DZB\n" +
          "KHD)Q65\n" +
          "488)GH3\n" +
          "TML)H2G\n" +
          "C71)8HF\n" +
          "P9W)ZL7\n" +
          "GPM)MVP\n" +
          "S6K)3BJ\n" +
          "RVZ)9QB\n" +
          "2PS)BW7\n" +
          "NS9)V6B\n" +
          "L9R)NF5\n" +
          "V1S)FT5\n" +
          "DPR)87B\n" +
          "PZW)JVJ\n" +
          "T6D)TQ5\n" +
          "Z9D)THN\n" +
          "JGQ)M5X\n" +
          "1LY)WGR\n" +
          "H8Y)6KZ\n" +
          "LNL)G4X\n" +
          "R3V)719\n" +
          "9J7)DV9\n" +
          "HG9)476\n" +
          "6CP)XZS\n" +
          "GZ4)VML\n" +
          "V4G)LZ4\n" +
          "XZS)7NK\n" +
          "4LL)P81\n" +
          "S9G)Q4Z\n" +
          "62P)Z7F\n" +
          "B4V)TGJ\n" +
          "RLZ)ZFW\n" +
          "3R1)TL7\n" +
          "ZSX)CHL\n" +
          "7FH)229\n" +
          "4NS)9WS\n" +
          "2C4)W2C\n" +
          "ZBY)1NF\n" +
          "F8Y)GZ8\n" +
          "LZ4)355\n" +
          "KTH)8NJ\n" +
          "731)NZ1\n" +
          "THN)9GD\n" +
          "QV7)Z8W\n" +
          "71P)CYX\n" +
          "P3S)K4J\n" +
          "H2G)ZWD\n" +
          "XJ7)7KR\n" +
          "NH4)KDK\n" +
          "763)ZHP\n" +
          "1Q7)W3Q\n" +
          "RWJ)22Z\n" +
          "VNQ)FYT\n" +
          "2H9)T9C\n" +
          "2QM)LXV\n" +
          "BCT)M1F\n" +
          "HCM)YJY\n" +
          "C97)K6Q\n" +
          "671)89Z\n" +
          "9QB)14P\n" +
          "T5T)GDQ\n" +
          "76W)3ZN\n" +
          "ZFT)GL2\n" +
          "FVJ)T9D\n" +
          "794)J9J\n" +
          "86P)F5M\n" +
          "FN7)FRX\n" +
          "ZQ9)1F6\n" +
          "8B4)2N6\n" +
          "ZVG)781\n" +
          "S2T)JYM\n" +
          "LPJ)R4Y\n" +
          "Y4P)W34\n" +
          "6RF)MG1\n" +
          "ZCL)67L\n" +
          "P81)CJK\n" +
          "M1F)QFD\n" +
          "Z63)RZ2\n" +
          "GZ5)731\n" +
          "R2T)KDT\n" +
          "TTZ)9S5\n" +
          "4DP)128\n" +
          "78F)ZY3\n" +
          "SZ1)T6D\n" +
          "TR4)BSW\n" +
          "FQ6)FHT\n" +
          "Y1Y)PHY\n" +
          "TDC)85T\n" +
          "853)DZL\n" +
          "152)TML\n" +
          "6KZ)QYS\n" +
          "SXQ)6WT\n" +
          "B8N)4JF\n" +
          "6X3)J23\n" +
          "XZP)68P\n" +
          "F5M)DPR\n" +
          "FH2)7NF\n" +
          "8R6)18X\n" +
          "DJJ)W1W\n" +
          "WF3)GLD\n" +
          "BL6)2MQ\n" +
          "79G)PJ7\n" +
          "TBM)JTZ\n" +
          "TQJ)424\n" +
          "W6J)642\n" +
          "FH9)1KM\n" +
          "128)SK8\n" +
          "KX6)64R\n" +
          "5M9)XQ9\n" +
          "LQV)R2N\n" +
          "ZW3)FYJ\n" +
          "9PR)5NY\n" +
          "6YN)NCV\n" +
          "XP8)8W9\n" +
          "XV7)4J2\n" +
          "XTD)GR6\n" +
          "4TB)RJ4\n" +
          "KPT)B57\n" +
          "HXC)5HD\n" +
          "LNC)WMK\n" +
          "7LZ)NS9\n" +
          "HM1)9SY\n" +
          "WSL)CDZ\n" +
          "V1V)Z63\n" +
          "WGR)81T\n" +
          "2YF)ZK9\n" +
          "S5Q)34X\n" +
          "TGX)TQJ\n" +
          "LNQ)LLD\n" +
          "PVM)JF3\n" +
          "HKP)PV1\n" +
          "FQC)SK1\n" +
          "F26)GCT\n" +
          "GH3)ZDP\n" +
          "WFC)8V4\n" +
          "RX5)6W4\n" +
          "14P)H1K\n" +
          "RR8)GV7\n" +
          "R12)W69\n" +
          "YS2)X4J\n" +
          "TLV)LCJ\n" +
          "29X)PSM\n" +
          "K15)PMQ\n" +
          "HF4)XTC\n" +
          "3GN)W42\n" +
          "D6J)H7F\n" +
          "JF3)562\n" +
          "89Z)2KK\n" +
          "WMS)WTZ\n" +
          "7YV)FH9\n" +
          "229)QNR\n" +
          "9XL)PQQ\n" +
          "S6H)7KF\n" +
          "DQP)HG9\n" +
          "87B)LGW\n" +
          "SK1)GYB\n" +
          "Z9S)6SP\n" +
          "RZ4)TQV\n" +
          "562)5KN\n" +
          "V5X)GBL\n" +
          "N7H)XWL\n" +
          "9YH)Y1Y\n" +
          "N44)2QM\n" +
          "NSX)DL1\n" +
          "KDT)7Q6\n" +
          "CNH)QXY\n" +
          "HLL)R7Z\n" +
          "JV8)RVZ\n" +
          "16X)DY6\n" +
          "14T)38V\n" +
          "LCJ)ZYM\n" +
          "6WT)RHS\n" +
          "2KS)MZM\n" +
          "KV9)FJ1\n" +
          "SDH)521\n" +
          "8LZ)8Q4\n" +
          "9ZH)F8R\n" +
          "9ZR)B9X\n" +
          "4FR)XJ7\n" +
          "FRX)39B\n" +
          "L6N)BX2\n" +
          "TQ5)3RG\n" +
          "PRN)HHQ\n" +
          "GMX)413\n" +
          "DZL)6YN\n" +
          "S2N)NR8\n" +
          "6SP)16V\n" +
          "RTC)XTD\n" +
          "7KR)L1B\n" +
          "355)W71\n" +
          "ZPD)QDR\n" +
          "BW3)3FF\n" +
          "YJY)8R6\n" +
          "W9N)GBQ\n" +
          "D2M)7GG\n" +
          "DWV)HYQ\n" +
          "6TM)8XR\n" +
          "FYV)628\n" +
          "CJK)C5X\n" +
          "FYJ)J1C\n" +
          "2CP)23F\n" +
          "51S)ZCL\n" +
          "9LQ)L1M\n" +
          "RHS)L9R\n" +
          "QGJ)HXC\n" +
          "4ZY)PJR\n" +
          "1KL)QQY\n" +
          "FZV)BJF\n" +
          "1YL)54D\n" +
          "NLP)TJS\n" +
          "1KM)D39\n" +
          "91W)6X3\n" +
          "8PX)13W\n" +
          "ZP6)RCN\n" +
          "K5H)CRC\n" +
          "RP7)1XK\n" +
          "4L2)4D1\n" +
          "F4V)GZ4\n" +
          "8XF)42D\n" +
          "HCM)389\n" +
          "H28)RJC\n" +
          "389)CWT\n" +
          "XKB)4FR\n" +
          "JKL)P92\n" +
          "FT5)54H\n" +
          "C1Z)67S\n" +
          "LRC)4LL\n" +
          "QBV)4V7\n" +
          "TTR)19L\n" +
          "PS6)38X\n" +
          "M5X)DBQ\n" +
          "L1B)S5Q\n" +
          "D9T)2R3\n" +
          "WVQ)JSD\n" +
          "9GD)7YV\n" +
          "MG1)L86\n" +
          "PCZ)F4V\n" +
          "WJV)M99\n" +
          "K4J)GHX\n" +
          "Q3B)R25\n" +
          "D82)TXX\n" +
          "6JK)CHM\n" +
          "ZXT)HK9\n" +
          "V79)HPW\n" +
          "M7T)53S\n" +
          "Z21)XZX\n" +
          "R25)D6J\n" +
          "W8S)2YF\n" +
          "VBF)RXB\n" +
          "X4J)FQ6\n" +
          "352)M1Z\n" +
          "JTF)MSF\n" +
          "CMY)GMX\n" +
          "9SY)CXS\n" +
          "BX2)72D\n" +
          "422)MP7\n" +
          "RJC)6BD\n" +
          "MVP)WQ8\n" +
          "ZFW)Z7W\n" +
          "XDY)TDC\n" +
          "3K9)W6J\n" +
          "LV7)WYN\n" +
          "YFY)158\n" +
          "Z84)HCM\n" +
          "9S5)C1Z\n" +
          "DBQ)FQC\n" +
          "YV7)N5Z\n" +
          "LGW)34Q\n" +
          "MNC)V5X\n" +
          "NHG)XGZ\n" +
          "Z4K)YYP\n" +
          "2MQ)79T\n" +
          "44V)VG5\n" +
          "8W9)BG4\n" +
          "FYV)RQQ\n" +
          "979)K9B\n" +
          "YXK)HLX\n" +
          "W9X)PZW\n" +
          "PLD)PW8\n" +
          "XQ9)TR5\n" +
          "C16)77Q\n" +
          "XWL)P2L\n" +
          "4J2)C16\n" +
          "38Y)Z83\n" +
          "53G)F6G\n" +
          "GV7)NC8\n" +
          "277)B73\n" +
          "WJR)Y4P\n" +
          "781)FFH\n" +
          "5HD)46Q\n" +
          "WDV)HKY\n" +
          "M1Z)J1Y\n" +
          "4V7)QFJ\n" +
          "CLL)ZPD\n" +
          "G2P)2WN\n" +
          "3ZN)QCT\n" +
          "YTQ)LV7\n" +
          "JVJ)1WW\n" +
          "1WV)5V1\n" +
          "V1X)8VN\n" +
          "4QK)4ZY\n" +
          "CDZ)TTR\n" +
          "M9M)WCV\n" +
          "XDC)7SR\n" +
          "TR4)WMS\n" +
          "4FB)Z9P\n" +
          "QTZ)4K2\n" +
          "Z2F)KHT\n" +
          "CLK)JK4\n" +
          "MN5)Z9S\n" +
          "4PN)HK5\n" +
          "COM)8RF\n" +
          "DVY)HJV\n" +
          "4TL)MYK\n" +
          "7TB)3JC\n" +
          "KWJ)QBV\n" +
          "4JF)3PZ\n" +
          "X4G)78F\n" +
          "XLQ)ZM5\n" +
          "BSW)YOU\n" +
          "4RG)MWX\n" +
          "79T)JV8\n" +
          "2SW)S2F\n" +
          "R4Y)RSQ\n" +
          "6Y4)5S9\n" +
          "23F)NDY\n" +
          "182)M32\n" +
          "ZHP)V1V\n" +
          "MP7)KWJ\n" +
          "V5X)L6N\n" +
          "L6C)M19\n" +
          "642)SL7\n" +
          "Z1M)V1X\n" +
          "GYH)F95\n" +
          "FH2)5CK\n" +
          "6QQ)SVV\n" +
          "BJK)7ZL\n" +
          "YM1)MP6\n" +
          "CPN)P6J\n" +
          "W34)F8Y\n" +
          "VTD)D9T\n" +
          "HKP)LNC\n" +
          "SS8)B9H\n" +
          "NS9)CMY\n" +
          "NGP)HN1\n" +
          "BG7)NH4\n" +
          "R2N)WSL\n" +
          "5YL)VR2\n" +
          "ZCN)BDN\n" +
          "K51)CX9\n" +
          "HHQ)2H9\n" +
          "CWT)GGB\n" +
          "3BJ)CJN\n" +
          "XG7)PZM\n" +
          "GDQ)YPN\n" +
          "BJF)ZZD\n" +
          "3PZ)SS8\n" +
          "GFJ)JQ3\n" +
          "X3M)44F\n" +
          "6W4)X69\n" +
          "Q74)CTM\n" +
          "FFH)71P\n" +
          "5X6)44V\n" +
          "2N6)GJT\n" +
          "1W4)VSN\n" +
          "FYT)68M\n" +
          "BB1)4RG\n" +
          "DL1)ND8\n" +
          "JK4)WJR\n" +
          "M32)4L2\n" +
          "L3H)CWV\n" +
          "78L)CLK\n" +
          "127)1Q7\n" +
          "FKZ)V1S\n" +
          "22Z)YS2\n" +
          "NWB)8R4\n" +
          "5X8)2JB\n" +
          "ZKF)HKG\n" +
          "HPW)77Y\n" +
          "MXS)6Y4\n" +
          "ZQC)BX7\n" +
          "47Y)4ZP\n" +
          "CP8)38Y\n" +
          "HJV)Y4N\n" +
          "HK5)76F\n" +
          "8N2)29X\n" +
          "BDH)RS9\n" +
          "NR8)K5H\n" +
          "GYB)G2P\n" +
          "PZM)ZZW\n" +
          "G8X)4FT\n" +
          "ZM5)95J\n" +
          "PJR)PPF\n" +
          "BW7)KD1\n" +
          "SCK)H31\n" +
          "HGC)TGB\n" +
          "PZ6)DVY\n" +
          "JF3)V33\n" +
          "521)PQG\n" +
          "H7F)SAN\n" +
          "1XK)JC2\n" +
          "PQQ)W6F\n" +
          "MHN)XMG\n" +
          "SVV)H28\n" +
          "RJ4)DPL\n" +
          "7KF)YNZ\n" +
          "7NK)HF4\n" +
          "Y3J)PCT\n" +
          "X69)HGC\n" +
          "PKG)QPF\n" +
          "V33)DWV\n" +
          "TK8)8T1\n" +
          "YWK)69K\n" +
          "64R)FZV\n" +
          "PMQ)H8Y\n" +
          "KDK)NTK\n" +
          "WCV)92J\n" +
          "RQQ)TK8\n" +
          "R2N)GPM\n" +
          "YQK)NBV\n" +
          "ND4)TBC\n" +
          "V5D)WDV\n" +
          "HYQ)SSS\n" +
          "GMM)H8B\n" +
          "W1X)VNJ\n" +
          "TLW)BL6\n" +
          "Q65)ZJB\n" +
          "GHX)XQH\n" +
          "YGS)TLV\n" +
          "49H)6JK\n" +
          "5QG)RD5\n" +
          "HGP)PTC\n" +
          "V6B)SMM\n" +
          "W2C)2KS\n" +
          "S5F)1KL\n" +
          "F6D)NSX\n" +
          "18X)CP8\n" +
          "N5Z)TBM\n" +
          "F6G)D4Y\n" +
          "3Q4)XZP\n" +
          "C7K)BCT\n" +
          "Z4K)WTT\n" +
          "7FS)RWJ\n" +
          "CHM)GFJ\n" +
          "QPG)Z52\n" +
          "RF1)8N2\n" +
          "B73)YWK\n" +
          "QPF)T58\n" +
          "J1C)K4W\n" +
          "2NZ)N86\n" +
          "QHB)4LG\n" +
          "JTJ)WFZ\n" +
          "DPR)JTF\n" +
          "128)53G\n" +
          "ZDP)RFV\n" +
          "413)WJS\n" +
          "M19)CLL\n" +
          "ND8)1YL\n" +
          "CHL)277\n" +
          "VR2)78L\n" +
          "QGJ)P9W\n" +
          "PYW)YV7\n" +
          "VNQ)RP7\n" +
          "Z8V)FN7\n" +
          "LGW)PRN\n" +
          "17J)ZQ9\n" +
          "3RG)9BV\n" +
          "Y8S)KTH\n" +
          "F95)2R9\n" +
          "9G7)ZGN\n" +
          "JC2)6K1\n" +
          "GBL)JGQ\n" +
          "FZY)16X\n" +
          "8HF)975\n" +
          "K4D)KH7\n" +
          "YV7)JC1\n" +
          "QXY)4PN\n" +
          "BX7)WNT\n" +
          "38Y)FYV\n" +
          "7LZ)LNQ\n" +
          "8NJ)YGN\n" +
          "P2L)S2N\n" +
          "FHT)XP8\n" +
          "TBC)V81\n" +
          "34Q)KDP\n" +
          "1NF)V4G\n" +
          "WTZ)S39\n" +
          "NL2)PVM\n" +
          "W71)Q6D\n" +
          "9FB)L6C\n" +
          "NF5)JNL\n" +
          "2V8)V3F\n" +
          "W6F)FF8\n" +
          "92J)YGS\n" +
          "SQ7)FKZ\n" +
          "HN1)YQK\n" +
          "8T1)8PX\n" +
          "HLX)JTJ\n" +
          "2WN)3CY\n" +
          "1F6)79G\n" +
          "TXX)G8X\n" +
          "LQ9)1W4\n" +
          "ZWD)KQL\n" +
          "G7L)YTQ\n" +
          "BD2)4QK\n" +
          "9FB)1BT\n" +
          "8XR)W9N\n" +
          "1F4)8XF\n" +
          "JYM)6S4\n" +
          "TML)KKQ\n" +
          "HWX)S3Y\n" +
          "SND)TR4\n" +
          "42D)6TM\n" +
          "81T)QZG\n" +
          "S39)D8G\n" +
          "YQD)78P\n" +
          "YMM)5X8\n" +
          "JJX)FTS\n" +
          "Q74)SCK\n" +
          "278)X5Y\n" +
          "PJY)49H\n" +
          "S4R)9C4\n" +
          "H7N)Z12\n" +
          "M2B)RLZ\n" +
          "Y1J)QTZ\n" +
          "93Q)X1P\n" +
          "6BD)XKB\n" +
          "1GF)VL6\n" +
          "6TP)736\n" +
          "RD5)C97\n" +
          "B6M)D2F\n" +
          "37Q)S4R\n" +
          "8VN)BB1\n" +
          "S2F)YZN\n" +
          "G5V)M2B\n" +
          "LHX)YXK\n" +
          "PWM)P3S\n" +
          "ZG1)GNK\n" +
          "2JB)B4V\n" +
          "4XY)1NG\n" +
          "9C4)VV7\n" +
          "7NC)PZ6\n" +
          "WSL)271\n" +
          "FYK)S6H\n" +
          "KQL)7NC\n" +
          "PKX)BD2\n" +
          "FJ1)HLL\n" +
          "4TL)QPG\n" +
          "YGN)T2J\n" +
          "T44)XLQ\n" +
          "54H)PJY\n" +
          "87N)2PS\n" +
          "Z7F)SPC\n" +
          "FRX)ZSX\n" +
          "B9X)CB1\n" +
          "MZM)VNQ\n" +
          "WFZ)NHG\n" +
          "CX9)HK7\n" +
          "8V4)9GM\n" +
          "W69)TTZ\n" +
          "Z8W)4TB\n" +
          "7V2)T5T\n" +
          "7Q6)9J3\n" +
          "8R4)NPC\n" +
          "76F)MFJ\n" +
          "QCT)HMW\n" +
          "5KN)X4N\n" +
          "7TV)QV7\n" +
          "46Q)278\n" +
          "JC1)X3M\n" +
          "97X)N7H\n" +
          "WMK)ZBY\n" +
          "WCV)KX6\n" +
          "MFJ)82Z\n" +
          "19L)W21\n" +
          "G4X)7GP\n" +
          "5ZR)9J7\n" +
          "TR5)ZFT\n" +
          "D39)1F4\n" +
          "NTK)69B\n" +
          "KZ2)37Q\n" +
          "3CY)SND\n" +
          "LMH)VBF\n" +
          "RSQ)3YV\n" +
          "736)16S\n" +
          "Y5C)ZG1\n" +
          "85T)XLP\n" +
          "9YQ)TGX\n" +
          "765)FFC\n" +
          "158)TCQ\n" +
          "8S6)J5N\n" +
          "5CK)F9T\n" +
          "GVH)6ZZ\n" +
          "V81)WJV\n" +
          "1NG)3Q4\n" +
          "5NY)NCW\n" +
          "BJQ)CRT\n" +
          "MWX)PRV\n" +
          "YTQ)GC4\n" +
          "CXS)7FH\n" +
          "Q6D)PCZ\n" +
          "9S5)F6D\n" +
          "T58)BG7\n" +
          "MP6)RZ4\n" +
          "PHY)QGJ\n" +
          "ZGN)X4G\n" +
          "34X)HGP\n" +
          "4K2)5NV\n" +
          "4LG)853\n" +
          "SDL)V7L\n" +
          "C5X)XV7\n" +
          "PQG)87N\n" +
          "8W9)NLP\n" +
          "PPF)HKP\n" +
          "PL5)Q74\n" +
          "VL1)4XY\n" +
          "GC4)1QG\n" +
          "CGH)YXH\n" +
          "4DP)422\n" +
          "719)QFB\n" +
          "V7L)PS6\n" +
          "7T6)G7W\n" +
          "D4Y)3GN\n" +
          "9GM)T4G\n" +
          "YXK)CLD\n" +
          "D8G)ZQC\n" +
          "W6J)8LZ\n" +
          "13W)3W1\n" +
          "HK7)DPQ\n" +
          "43T)FVJ\n" +
          "YXV)W9X\n" +
          "H8B)RX5\n" +
          "6QM)HXT\n" +
          "QKB)PLD\n" +
          "78P)BW3\n" +
          "9QW)PL5\n" +
          "GR6)W1X\n" +
          "GHQ)ZRC\n" +
          "3W1)D8F\n" +
          "VV7)F3R\n" +
          "YYP)9PR\n" +
          "HJ8)SBC\n" +
          "M99)H6R\n" +
          "B9H)8S6\n" +
          "7GG)CNH\n" +
          "MZM)MHN\n" +
          "HN1)5M9\n" +
          "ZK9)MN5\n" +
          "7G6)182\n" +
          "XTC)2LV\n" +
          "PTC)4D9\n" +
          "LXV)9FB\n" +
          "P6J)7T6\n" +
          "GZ4)Z9D\n" +
          "PW8)SJF\n" +
          "JRY)RTC\n" +
          "NCW)SDH\n" +
          "3JC)237\n" +
          "ZJB)WFC\n" +
          "WYN)7WC\n" +
          "P92)BDH\n" +
          "NC8)1LY\n" +
          "NCV)DJJ\n" +
          "QNR)2C4\n" +
          "YN2)KV9\n" +
          "VNJ)H7N\n" +
          "67G)1R6\n" +
          "6S4)9YQ\n" +
          "K6Q)BFH\n" +
          "QFB)SHB\n" +
          "RQ4)RF1\n" +
          "W3Q)QSM\n" +
          "QFJ)PKG\n" +
          "XMG)FZY\n" +
          "MSF)LMH\n" +
          "SBC)979\n" +
          "F3R)GYH\n" +
          "7SR)NJC\n" +
          "7ZL)BCX\n" +
          "X4N)L3H\n" +
          "HMW)VTD\n" +
          "5V1)WVQ\n" +
          "S3Y)TLW\n" +
          "67L)4GR\n" +
          "L86)BJQ\n" +
          "ZL7)VND\n" +
          "4ZP)R12\n" +
          "44F)6FY\n" +
          "6X3)ZW3\n" +
          "LXJ)LRC\n" +
          "Y3J)6CP\n" +
          "LLD)4TL\n" +
          "4D9)SQ7\n" +
          "GL2)5HB\n" +
          "3YV)BJK\n" +
          "77Y)74M\n" +
          "LF3)C7K\n" +
          "GJT)91W\n" +
          "GK3)Y8S\n" +
          "K4W)XDY\n" +
          "KDK)KWX\n" +
          "SMM)CPZ\n" +
          "WBM)D2M\n" +
          "VMR)FCS\n" +
          "4NJ)W8S\n" +
          "BFH)X1Z\n" +
          "BG4)7TB\n" +
          "CPN)Z21";
}