
/**
 * File Name: PowerBall.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: IntUtil.java RandomInt.java PowerBall.java
 */


class PowerBall {
  /*
   * ALL PRIVATE DATA BELOW
   * CANNOT ADD ANY MORE MEMBER
   */
  private int[] winningNumber ;
  private int[] ticketNumber ;
  private long cash ;
  static private boolean display = true ;
  static final long jackpot = 100000000;
  static private IntUtil u = new IntUtil();
  
  private void printNumbers() {
    if (display) {
      System.out.println("winningNumber ") ;
      u.pLn(winningNumber);
      System.out.println("ticketNumber  ") ;
      u.pLn(ticketNumber);
    }
  }
  
  public long cash() {
    return cash ;
  }
  
  private static int[] sortarray( int [ ] num)
  {
       int j;                  
       int key;        
       int i;  

       for (j = 1; j < num.length; j++)    
      {
             key = num[ j ];
             for(i = j - 1; (i >= 0) && (num[ i ] > key); i--)  
            {
                   num[ i+1 ] = num[ i ];
            }
           num[ i+1 ] = key;    
       }
      return num;
  }
  
  private static int[] copy_array(int[] a,boolean remove_last)
  {   int array_len = a.length;
      int b[] = new int[array_len-1];
	  if (remove_last){
		  for (int i = 0;i < array_len-1;i++)
		  {
			  b[i] = a[i];
			  
		  }
	  }
	  return b;
  }
  
  private static boolean duplicateinarray(int[] a){
	  boolean d = false;
	  for (int i = 0; i < a.length-1; i++)
      {
          for (int j = i+1; j < a.length; j++)
          {
              if( (a[i]== a[j]) && (i != j) )
              {
                  d = true;
              }
          }
        
  }
	  return d;
  }
  private void check() {
	  boolean b = true;
	  int count = 0;
	  int [] win = copy_array(winningNumber,true);
	  int [] ticket = copy_array(ticketNumber,true);
	  int last_win = winningNumber[winningNumber.length -1];
	  int last_ticket = ticketNumber[ticketNumber.length -1];
	  int [] sorted_win = sortarray(win);
	  int [] sorted_ticket = sortarray(ticket);
	  if (sorted_win != null && sorted_ticket != null){
          if (sorted_win.length != sorted_ticket.length){
        	  b = false;  
        	  
          }
          else
          { 
              for (int i = 0; i < sorted_ticket.length; i++) {
                  if (sorted_ticket[i] != sorted_win[i]) {
                      b = false; 
                  }
                  else{
                	  count++;
                  }
            }
          }
        }
	  else{
          b = false;
        }
	 
	  if (duplicateinarray(winningNumber) || duplicateinarray(ticketNumber) ){
		  cash = 0;
	  }
	  
	  else if (b && last_win==last_ticket){
		  cash = jackpot;
	  }
	  else if (b){
		  cash = 1000000;
	  }
	  else if (count == 4 && last_win==last_ticket){
		  cash = 50000;
	  }
	  else if (count == 4 && last_win != last_ticket){
		  cash = 100;
	  }
	  else if (count == 3 && last_win==last_ticket){
		  cash = 100;
	  }
	  else if (count ==3 && last_win != last_ticket){
		  cash = 7;
	  }
	  else if (count == 2 && last_win==last_ticket){
		  cash = 7;
	  }
	  else if (count ==1 && last_win == last_ticket){
		  cash = 4;
	  }
	  else if (!b && last_win == last_ticket){
		  cash = 4;
	  }
	 
     //WRITE CODE HERE
     //YOU CAN WRITE AS MANY PRIVATE ROUTINES AS YOU NEED
  }
  
  PowerBall(int[] w, int [] t) {
    winningNumber = w ;
    ticketNumber = t ;
    cash = 0 ;
    check() ;
  }
  
  private static void test1() {
    //CANNOT CHANGE BELOW
    int[] w = {4,8,19,27,24,10} ;
    {
      int [] n= {4,8,19,27,24,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {24,27,19,8,4,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,10} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14,5} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,127,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }
    {
      int [] n= {124,124,19,119,18,14} ;
      PowerBall x = new PowerBall(w,n);
    }  
  }
  
  private static void testRandom() {
  //CANNOT CHANGE BELOW
    System.out.println("----------testRandom()  starts-------------") ;
    display = false ;
    int[] w = {4,8,19,27,24,10} ;
    int max = 1000000 ;
    long c = 0 ;
    System.out.println("Buying " + max + " tickets of worth " + max * 2 +"$") ;
    for (int i = 0; i < max; ++i) {    
      int [] n = u.generateRandomNumber(6,true,1,60);
      PowerBall x = new PowerBall(w,n); 
      if (x.cash() == jackpot) {
        System.out.println("Won Jacckpot") ;
      }
      c = c + x.cash();
    }
    long p = (c -(max*2)) ;
    System.out.println("Out of " + max + " times you win " + c + "$") ;
    if (p > 0) {
      System.out.println("Profit = " + p) ;
    }else {
      System.out.println("Loss = " + p) ;
    }
    System.out.println("----------testRandom()  ends-------------") ;
  }
  
  private static void testBench() {
  //CANNOT CHANGE BELOW
    test1() ;
    testRandom() ;
  }
  
  public static void main(String[] args) {
  //CANNOT CHANGE BELOW
    System.out.println("PowerBall.java");
    testBench();
    System.out.println("Done");
  }
}