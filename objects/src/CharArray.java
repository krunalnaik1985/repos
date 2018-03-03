
/**
 * File Name: CharArray.java Infinite capacity char array
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: CharArray.java
 */

class CharArray {
  /*
   * ALL PRIVATE DATA BELOW
   */
  private int capacity;
  private char[] darray;
  static private boolean display = false;
  static IntUtil u = new IntUtil();

  /*
   * ALL PUBLIC ROUTINES BELOW
   */
  static void setDisplay(boolean x) {
    display = x;
  }

  //Constructor that takes integer
  public CharArray(int s) {
    allocate(s);
    if (display == true) {
      System.out.println("Creating darray of int of capacity " + capacity);
    }
  }
  
  //Constructor that takes nothing
  public CharArray() {
    this(16); // This must be a first line
  }
 
  public char get(int pos) {
    if (pos < 0) {
      u.myassert(false);
      return 'a' ; //Make compiler happy
    }
    if (pos < capacity) {
      return darray[pos];
    }
    grow(pos);
    return darray[pos];
  }

  public void set(int pos, char val) {
    if (pos < 0) {
      u.myassert(false);
    }
    if (pos >= capacity) {
      grow(pos);
    }
    darray[pos] = val;
  }
  
  public void swap(int a, int b) {
    char x = darray[a] ;
    darray[a] = darray[b] ;
    darray[b] = x ;
  }

  /*
   * ALL PRIVATES ROUTINES BELOW
   */

  private void allocate(int s) {
    capacity = s;
    darray = new char[s];
  }

  private void grow(int s) {
    char[] ta = darray;
    int ts = capacity ; 
    int ns = capacity;
    do {
      ns = ns * 2;
    } while (ns <= s);

    if (display == true) {
      System.out.println("Array grew from " + ts + " to " + ns);
    }
    u.myassert(s < ns);
    allocate(ns);
    for (int i = 0; i < ts; ++i) {
      darray[i] = ta[i];
    }
    ta = null;
  }

  /*
   * if 'from' <= 'to' print from 'from' to 'to' else print from 'to to 'from'
   */
  public void p(int from, int to) {
    if (from <= to) {
      int j = 0 ;
      for (int i = from; i <= to; ++i) {
        j++ ;
        if (j > 60) {
          System.out.println() ;
          j = 0 ;
        }
        char x = get(i);
        System.out.print(x);
      }
    } else {
      for (int i = from; i >= to; --i) {
        char x = get(i);
        System.out.print(x);
      }
    }
  }

  public void pLn(int from, int to) {
    p(from, to);
    System.out.println();
  }

  public void p(String t, int from, int to) {
    System.out.print(t);
    p(from, to);
  }

  public void pLn(String t, int from, int to) {
    p(t, from, to);
    System.out.println();
  }
  
  public void pLn(String t) {
    int l = size() - 1 ;
    p(t, 0, l);
    System.out.println();
  }
  
  public int size() {
    int i = 0 ;
    while (darray[i] != '\0') {
      ++i ;
    }
    return i ;
  }
  
  public void reverse() {
    int i = 0 ;
    int j = size() - 1 ;
    while (i < j) {
      swap(i,j) ;
      ++i ;
      --j ;
    }
  }
  
  

  /*
   * All test routines
   */

  private static void test1() {
    CharArray b = new CharArray();
    int s = 0 ;
    for (int i = 0; i < 8; ++i) {
      b.set(i, (char)('a'+i));
      ++s ;
    }
    b.pLn("from 0 to " + (s-1) + ": ", 0, s-1);
    b.pLn("from " + (s-1) + " to 0: ", s-1, 0);
    CharArray a = new CharArray();
    a.set(3, 'Z');
    a.set(56, 'U');
    char x = a.get(3);
    char y = a.get(56);
    char z = a.get(100);
    System.out.println("a[3]= " + x + " a[56] = " + y + " a[100] = " + z);
  }

  private static void testBench() {
    CharArray.setDisplay(true);
    System.out.println("------------test1---------------------");
    test1();
  }

  public static void main(String[] args) {
    System.out.println("CharArray.java");
    testBench();
    System.out.println("Done");
  }
}
