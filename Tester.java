import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Tester {
protected static <T> boolean listEquals(List<T> ell1, List<T> ell2) {
    for (int i = 0; i < ell1.size(); i++) {
      T x1 = ell1.get(i);
      T x2 = ell2.get(i);
      if (x1 != null && x2 == null) return false;
      if (x1 == null && x2 != null) return false;
      if (x1 != null && !x1.equals(x2)) return false;
    }
    return true;
  }

  protected static boolean testPart2Correctness(List<Integer> ell) {
    int n = 100;
    List<Integer> ell2 = new ArrayList<Integer>(Collections.nCopies(3*n, null));
    for (int i = 0; i < n; i++) {
      ell2.set(2*i, i);
      ell.set(2*i, i);
    }
    if (!listEquals(ell2, ell)) return false;
    for (int i = 2*n-1; i > 0; i -= 2) {
        ell2.add(i, i);
        ell.add(i, i);
    }
    if (!listEquals(ell2, ell)) return false;
    Random rand = new Random(3);
    n = 10000;
    for (int _u = 0; _u < n; _u++) {
      int i, x;
      switch(rand.nextInt(3)) {
        case 0:
          i = rand.nextInt(ell2.size()+1);
          x = rand.nextInt();
          ell.add(i, x);
          ell2.add(i,x);
          break;
        case 1:
          if (ell2.isEmpty()) break;
          i = rand.nextInt(ell2.size());
          ell.remove(i);
          ell2.remove(i);
          break;
        case 2:
          if (ell2.isEmpty()) break;
          i = rand.nextInt(ell2.size());
          x = rand.nextInt();
          ell.set(i, x);
          ell2.set(i, x);
          break;
      }
      if (!listEquals(ell2, ell)) return false;
    }
    return true;
  }

  public static boolean testPart2Performance(List<Integer> ell) {
    Random rand = new Random(3);
    int n = 100000;
    for (int _u = 0; _u < n; _u++) {
      int i, x;
      int range = Integer.MAX_VALUE / 2;
      switch(rand.nextInt(3)) {
        case 0:
          i = rand.nextInt(range);
          x = rand.nextInt();
          ell.add(i, x);
          break;
        case 1:
          i = rand.nextInt(range);
          ell.remove(i);
          break;
        case 2:
          i = rand.nextInt(range);
          x = rand.nextInt();
          ell.set(i, x);
          break;
      }
    }
    return true;
  }

  public static boolean testPart2(List<Integer> ell) {
    System.out.print("Testing correctness...");
		System.out.flush();
    boolean result = testPart2Correctness(ell);
		System.out.println("done");
    if (!result) return result;

    System.out.print("Testing performance...");
		System.out.flush();
    result = testPart2Performance(ell);
		System.out.println("done");
    return result;
  }

  public static void testDefaultList(List<Integer> ell) {
    long start = System.nanoTime();
    boolean result = Tester.testPart2(ell);
    long stop = System.nanoTime();
    double elapsed = (stop-start)/1e9;
    System.out.println("testPart1 returns " + result + " in " + elapsed + "s"
                       + " when testing a " + ell.getClass().getName());
  }

}
