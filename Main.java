package bunchbysoh;

public class Main {
  static class CountsBySoH {
    public int healthy = 0;
    public int exchange = 0;
    public int failed = 0;
  };

  static CountsBySoH countBatteriesByHealth(int[] presentCapacities) {
    CountsBySoH counts = new CountsBySoH();
    for(int capacity:presentCapacities) {
      // if given capacity is above 120 or negative value we should not calculate the soh
      if(capacity > 120 || capacity < 0){
        continue;
      }
      //calculating soh using given formula by assuming rated capacity as 120 Ah
      double soh = (capacity*100.0)/120.0;

      //based on soh calculated and given range healthy, exchange and failed count is increased
     //and assigned counts to CountsBySoH variables
      if(soh > 80 ){
        counts.healthy++;

      }
      else if(soh>=65){
        counts.exchange++;
      }else{
        counts.failed++;
      }
    }
    return counts;
  }

  static void testBucketingByHealth() {
    System.out.println("Counting batteries by SoH...\n");
    int[] presentCapacities = {115, 118, 80, 95, 91, 77};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);
    assert(counts != null);
    assert(counts.healthy == 2);
    assert(counts.exchange == 3);
    assert(counts.failed == 1);
    System.out.println("Done counting :)\n");
  }
  static void testAllHealthyBatteries(){
    System.out.println("Testing when all batteries are healthy...\n");

    int[] presentCapacities = {100, 115,120, 125};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);

    assert counts != null;
    assert counts.healthy == presentCapacities.length;
    assert counts.exchange == 0;
    assert counts.failed ==0;
    System.out.println("Done testing :}\n");

  }
  static void testAllFailedBatteries(){
    System.out.println("Testing when all batteries are failed...\n");

    int[] presentCapacities = {50, 60,55, 45};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);

    assert counts != null;
    assert counts.healthy == 0;
    assert counts.exchange == 0;
    assert counts.failed ==presentCapacities.length;
    System.out.println("Done testing :}\n");
  }

  static void testInvalidCapacityValues(){
    System.out.println("Testing with invalid capacity values...\n");

    int[] presentCapacities = {-10, 130, 140, -20};
    CountsBySoH counts = countBatteriesByHealth(presentCapacities);

    assert counts != null;
    assert counts.healthy == 0;
    assert counts.exchange == 0;
    assert counts.failed ==0;
    System.out.println("Done testing :}\n");
  }


  



  public static void main(String[] args) {
    testBucketingByHealth();
    testAllHealthyBatteries();
    testAllFailedBatteries();
    testInvalidCapacityValues();
  }
}
