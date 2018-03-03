import java.util.ArrayList;

/**
 * File Name: BigUnsignedNumber.java 
 * Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: BigUnsignedNumber.java CharArray.java Cstring IntUtil.java
 */

class BigUnsignedNumber {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  //Cannot add any more data fields
  
  //WRITE ALL FUNCTIONS SO THAT ALL TESTS WILL PASS
  
  //CANNOT CHANGE ANYTHING BELOW 
  
  public BigUnsignedNumber(int c) {
	  char [] e = String.valueOf(c).toCharArray();
	  String e1 = String.valueOf(e);
	  d = new Cstring(e1);
	  }
  
  public BigUnsignedNumber(char c) {
	  d = new Cstring(c);
  }
  
  public BigUnsignedNumber(String c) {
	  d = new Cstring(c);
  }
  
  public void pLn(String a){
	  d.pLn(a);
  }
  
  public Cstring current(){
	  return d;  
  }
  
  public BigUnsignedNumber kclone(){
		return this;
	  }
  
  public BigUnsignedNumber add(BigUnsignedNumber e1){
	  Cstring current = this.d;
	  Cstring next = e1.d;
	  Cstring output ;
	  output = next.add(current);
	  output.reverse();
	  String z2 = output.to_string();
	  BigUnsignedNumber b1 = new BigUnsignedNumber(z2);
	  return b1;
  }
  
  public BigUnsignedNumber mult(BigUnsignedNumber e1){
	  Cstring current = this.d;
	  Cstring next = e1.d;
	  ArrayList output = next.mult(current) ;
	  int size1 = output.size();
	  BigUnsignedNumber sum = new BigUnsignedNumber("0") ;
	  for (int i = 0; i < size1;i++){
		  String out1 = (String) output.get(i);
		  BigUnsignedNumber next1 = new BigUnsignedNumber(out1) ;
		  sum = next1.add(sum) ;
		  //System.out.println("Out1: "+out1);
	  }
	 // sum.pLn("MULTIPY:: ");
	 // System.out.println(output);
	  return sum;
  }
 
  public boolean isEqual(BigUnsignedNumber b1){
	  Cstring current = this.d;
	  Cstring next = b1.d;
	  boolean is_equal = next.isEqual(current);
	  return is_equal;
  }
  
  public boolean isEqual(String b1){
	  Cstring current = this.d;
      String z1 = current.to_string();
      boolean status = false;
      if (z1.equals(b1)){
    	  status = true;
      }
      return status;
  }
  
  public boolean isEqual(int b1){
	  //System.out.println("Output"+b1);
	  String b2 = ""+b1;
	  Cstring current = this.d;
      String z1 = current.to_string();
      boolean status = false;
      if (z1.equals(b2)){
    	  status = true;
      }
      return status;
  }
  
  private static void test1() {
    BigUnsignedNumber b = new BigUnsignedNumber(10);
  }
  
  private static void testBench() {
    System.out.println("------------test1---------------------");
    test1();
  }
  
  public static void main(String[] args) {
    System.out.println("BigUnsignedNumber.java");
    testBench();
    System.out.println("Done");
  } 
}
