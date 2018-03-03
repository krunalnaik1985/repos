import java.util.Random;
import java.math.*;

/**
 * File Name: TruthTable.java 
 * Print 'n' input truth table
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

class TruthTable{
  private int n ;
  TruthTable(int n) {
    this.n = n ;
    printTruthTable() ;
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
  
  
  private void printTruthTable() {
    //WRITE CODE HERE. 
    //Show truth table for n < 8 along with print number of rows in truth table
    //For others just print number of rows in truth table, but internally it should build truth table like n < 8
	long number_of_rows = getseries(n);
	if (n <= 8) {
	for (int i=0;i<number_of_rows;i++){
		for (int j=n-1; j>=0; j--) {
			System.out.print((i/(int) Math.pow(2, j))%2 + " ");
		}
		System.out.println();
	}
	}
	System.out.println("For "+n+" inputs,"+"table size is "+getseries(n));
	
    
  }
  
  private static void testBench() { 
    //CANNOT CHANGE BELOW
    for (int i = 1 ; i < 40; ++i) {
      System.out.println("------------Truth table of " + i + " inputs function --------------");
      TruthTable t = new TruthTable(i) ;
    }
  }

  public static void main(String[] args) {
    //CANNOT CHANGE BELOW
    System.out.println("TruthTable.java");
    testBench();
    System.out.println("Done");
  }
}