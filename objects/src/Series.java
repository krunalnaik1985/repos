import java.math.BigInteger;
import java.math.*;

/**
 * File Name: Series.java 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

class Series {
  private static final IntUtil u = new IntUtil();
  
  public static double log2(int n)
  {
      return (Math.log(n) / Math.log(2));
  }
  
  private static void testLog(int start, int end, int incr) {
    //WRITE CODE
	  for (int j = start; j <= end; j += incr){
		  System.out.println("    "+j+"  "+Math.log10(j)+"     "+log2(j)+"     "+Math.log(j));
	  }
  }
  
  private static void testLog() {
    System.out.println("    n  base10     base2     base e") ;
    testLog(1,10,1) ;
    testLog(10,100,10) ; 
    testLog(100,1000,100) ;
    testLog(1000,10001,9000) ;
  }
  
  
  private static long getseries(int number){
	  if (number == 1){
		  return 2;
	  }
	  long two_number = 2;
	  long out_number = 2;
	  while (number > 1){
		  out_number = out_number * two_number;
		  number--;
	  }
	  return out_number;
  }
  /*
   * 1 + 1/2 + 1/4 + 1/8 + 1/16 +... + = 2
   */
  private static void seriesConvergesAbsolutely() { 
    //WRITE CODE HERE for n = 100
	 float sum=0;
	 int series = 100;
	 long startnumber = 2;
	 for (int i =1;i<series;i++){
		 startnumber = getseries(i);
		 if (startnumber < 100){
			 sum= sum+(float)1/startnumber;
		 }
		 else
		 {
			 break;
		 }
	 }
	 System.out.println(sum);
  }
  
  /*
   * 1 + 1/2 + 1/3 + 1/4 + 1/5 + .. = 
   */
  private static void harmonicSeries() {
    //WRITE CODE HERE for n = 100
	  float sum=0;
		 for (int i =1;i < 100; i++){
			sum= sum+(float)1/i;
			 
		 }
		 System.out.println(sum);
  }
  
  
  private static void chess() {
    //WRITE CODE
	long out_number = 0;
	BigInteger sum = BigInteger.valueOf(0);
	BigInteger bi1,bi2,bi3;
	bi2 = new BigInteger("0");
	bi3 = new BigInteger("0");
	for (int i =0;i < 64; i++){
		if (i == 0){
			out_number = 1;
		}
		else if(i ==1){
			out_number = 2;
		}
		else{
			out_number = getseries(i); 
		}
		if (out_number < 0){
			String strLong = Long.toString(out_number);
			bi1 = new BigInteger(strLong);
			bi2 = bi1.abs();
		}
        if (bi2.compareTo(bi3) == 1){
        	System.out.println("On Square "+(i+1)+" = "+bi2);
        	sum = sum.add(bi2);
        }
        else{
		sum = sum.add(BigInteger.valueOf(Math.abs(out_number)));
		System.out.println("On Square "+(i+1)+" = "+out_number);
        }
	}
	System.out.println("Total Rice = "+sum);
  }
  
  private static void testbed() {
    testLog();
    seriesConvergesAbsolutely();
    harmonicSeries() ;
    chess() ;
  }
  
  public static void main(String[] args) {
    System.out.println("Series.java");
    testbed();
    System.out.println("Done");
  }
}
