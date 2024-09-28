package cp_algorithms.PrimeNumbers;

import java.util.*;

public class SPOJPRIME1 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- > 0) {
      int l = sc.nextInt(), r = sc.nextInt();
      /*find prime < root of r*/
      int rootR = (int) Math.sqrt(r) + 1;
      boolean[] isPrime = new boolean[rootR + 1];
      Arrays.fill(isPrime, true);
      for (int i = 2; i * i <= rootR; i++) {
        if (isPrime[i] == true) {
          for (int j = i * i; j <= rootR; j += i) {
            isPrime[j] = false;
          }
        }
      }
      List<Integer> primes = new ArrayList<>();
      for (int i = 2; i <= rootR; i++) {
        if (isPrime[i] == true) primes.add(i);
      }
      /*mark multiple of those in l to r*/
      boolean[] isPrimeLtoR = new boolean[r - l + 1];
      Arrays.fill(isPrimeLtoR, true);
      if(l==1)isPrimeLtoR[0]=false;
      for (int prime : primes) {
        int start = (l / prime) * prime;
        if (l % prime != 0) start += prime;
        if(start==prime)start += prime;
        for (int j = start; j <= r; j += prime) {
          isPrimeLtoR[j - l] = false;
        }
      }
     
      for (int i = l; i <= r; i++) {
        if (isPrimeLtoR[i - l] == true)  System.out.println(i);
      }
      System.out.println();
     
    }
  }
}
