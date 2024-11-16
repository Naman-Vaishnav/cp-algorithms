package cp_algorithms.GCD;
import java.util.Scanner;
/**https://codeforces.com/contest/1916/problem/B */
 
public class TwoDivisors {
    static long  gcd1(long a,long b){
    if(b==0)return a;
    return gcd1(b,a%b);
    }
  public static void main(String args[]) {
    Scanner sc=new Scanner(System.in);
    long t=sc.nextLong();
    
    while(t-->0){
        long a=sc.nextLong();
        long b=sc.nextLong();
    
        long ans=a/gcd1(a,b)*b;
        if(b%a==0)ans=ans*(b/a);
        System.out.println(ans);
    }
    
    
    
  }
}