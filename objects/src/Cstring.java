import java.util.ArrayList;

/**
 * File Name: Cstring.java 
 * Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */
/*
 * To compile you require: CharArray.java Cstring
 */

class Cstring {
  private CharArray d; //Infinte array of char
  static IntUtil u = new IntUtil();
  //Cannot add any more data fields
  
  //WRITE ALL FUNCTIONS SO THAT ALL TESTS WILL PASS
  public Cstring(String c) {
	    int length1 = c.length();
	    d = new CharArray(length1 + 1);
	    for (int i = 0;i < length1; i++){
	    	d.set(i, c.charAt(i));
	    	}
	    d.set(length1+1, '\n');
	  }
  
  public Cstring(char c) {
	  d = new CharArray(3);
	  d.set(0, c);
	  d.set(1, '\n');
	  }
  
  public void pLn(String a){
	  
	  int len2 = d.size();
	  System.out.print(a);
	  d.pLn(0, len2);
  }
  
  private CharArray current(){
	  return d;
	  
  }
 
  
  public Cstring kclone(){
	char[] darray = new char[d.size()];
	for (int i = 0;i < d.size() ; i++){
		darray[i] = d.get(i);
    	}
	String z1 = new String(darray);
	Cstring z2 = new Cstring(z1) ;
	return z2;
	  
  }
  
  public Cstring reverse(){
	    d.reverse();
		char[] darray = new char[d.size()];
		for (int i = 0;i < d.size() ; i++){
			darray[i] = d.get(i);
		}
	    String z1 = new String(darray);
		Cstring z2 = new Cstring(z1) ;
		return z2;
		  
	  }
  
  public CharArray do_add(int dd1 , CharArray dd2,CharArray dd3){
	  int original_len = dd3.size();
	  int next_len = dd2.size();
	  CharArray dd4 ;
	  if (original_len > next_len){
		  dd4 = new CharArray(original_len+ + 1);}
	  else
	  {
		  dd4 = new CharArray(next_len+ + 1);
	  }
	  
	  int count = 0;
	  int carryover = 0;
	  int finalsum = 0;
	  while (original_len > -1 || next_len > -1 || carryover == 1 ){
		  int second_number = 0;
		  int first_number = 0;
		  char first_char = '0';
		  char second_char = '0';
		  if (original_len > -1)
		  { 
			  first_char = dd3.get(original_len);
		  }
		  if (next_len > -1)
		  {  
			  second_char = dd2.get(next_len);
		  }
		  if (first_char !='a' || second_char !='a'){
			  //System.out.println((char)(int)((second_char+first_char) - 48));
			  int final_char = (int)((second_char+first_char) - 48);
			  char final_chr = (char) ((second_char+first_char) - '0');
			  int carry_char = (int)((second_char+first_char) - 58);
			  //System.out.println("FINAL_CHAR"+final_char);
			  if (final_char >= 48 && final_char <= 57 && carryover == 0)
			  {
				  finalsum = final_char;
			  }
			  if (final_char >= 57 && carryover == 1){
				  //System.out.println("yes i am inside");
				  finalsum = final_char + (char) carryover;
				  if (finalsum >= 58)
				  {   finalsum = carry_char + (char)carryover;
					 // System.out.println("yes i am inside carry"+carry_char);
				  }
				  //System.out.println("yes i am inside"+finalsum);
			  }
			  
			  if (final_char >= 58 && carryover == 0){
				  finalsum = carry_char;
				 // System.out.println("above 58::"+finalsum);
				  	carryover = 1;
			  }
			  if (carry_char > 47){
				  //System.out.println("Carry_CHAR"+finalsum);
			  }
			  if (final_char > 47 && final_char < 58 && carryover > 0){
				 // System.out.println("Carry_CHAR2"+finalsum);
				  finalsum = final_char + (char) carryover;
				 // System.out.println((char)finalsum+"finalsum");
				  carryover--;
			  }
			  
			  if (carry_char == 48){
				  dd4.set(count,(char)carry_char);
				  carryover = 1;}
				 
				  	//System.out.println((char)finalsum+"finalsum");
				  }
			 
		
			  if (finalsum >= 46 && finalsum <= 58){
			  char s1 = (char) finalsum;
			  if (s1 == 58)
			  {int ff = (int)'0';
			   finalsum = ff;;
			   carryover = 1;}
			  dd4.set(count,(char)finalsum);}
			 
			  
		  
		 
		  count++;
		  original_len--;
		  next_len--;
		  
	  }

      
	  CharArray dd5 = new CharArray(count+1) ;
	  int count1 = 0;
	  for (int i=0;i < count+1;i++){
		  if(!('\0' == dd4.get(i))){
			  
			  dd5.set(count1, dd4.get(i));
			  count1++;
		  }
	  }
	  return dd5;
	  
  }
  
  
  
  public ArrayList do_mult(int dd1 , CharArray dd2,CharArray dd3){
	  int original_len = dd3.size();
	  int next_len = dd2.size();
	  int first = 0;
	  int second = 0;
	  CharArray first_char ;
	  CharArray second_char;
	  CharArray dd4;
	  ArrayList<String> values = new ArrayList<String>();
	  String z1 = "hey";
	  Cstring sum = new Cstring("0");
	  if (original_len > next_len){
		  first = original_len;
		  second = next_len;
		  first_char = dd3;
		  second_char = dd2;
		  dd4 = new CharArray(original_len+ + 1);}
	  else
	  {
		  dd4 = new CharArray(next_len+ + 1);
		  first = next_len;
		  second = original_len;
		  first_char = dd2;
		  second_char = dd3;
	  }
	  CharArray firstsum = new CharArray(10);
	  CharArray secondsum = new CharArray(10);
	  int count1 = 0;
	  int replace = 0;
	  int pad = 0;
	  while (second > 0){
	  CharArray e1 = do_multi1(second,first_char,second_char,first);
	  CharArray e2 = new CharArray(replace+2);
	  if (replace != 0){
		  int pad1 = replace;
		  int count2 = 0;
		  while (pad1>0){
		  int zero = 0;
		  e2.set(count2, '0');
		  pad1--;
		  count2++;}
	  }
	  e1.p(0,5);
	  e2.p(0, 5);
	  CharArray e3 = new CharArray(e1.size()+e2.size());
	  int e2_size = e2.size();
	  int count4 = 0;
	  int count5 = 0;
	  while (e2_size > 0){
		  e3.set(count4, e2.get(count5));
		  e2_size--;
		  count5++;
	  }
	  for (int j = e2.size(); j< e1.size()+e2.size(); j++){
		  e3.set(j, e1.get(count4));
		  count4++;
	  }
	  e3.p(0, 9);
	  second--;
	  replace++;
	  char[] darray = new char[e3.size()];
		for (int i = 0;i < e3.size() ; i++){
			darray[i] = e3.get(i);
		}
	   z1 = new String(darray);
	   Cstring s2 = new Cstring(z1);
	   Cstring s3 = s2.reverse();
	   values.add(s3.to_string());
	   
	  }
	  return values;
  }
  
  
  public CharArray do_multi1(int second,CharArray first_char,CharArray second_char,int first){
	  char element1 = '0';
	  char element2 = '0';
	  char element4 = '0';
	  int count = 0;
	  int carryover = 0;
	  CharArray output = new CharArray(first+1);
	  while(first > 0){
		  element1 = first_char.get(first-1);
		  element2 = second_char.get(second-1);
		  int i1 = element1 - '0';
		  int i2 = element2 - '0';
		  int i3 = i1 *i2;
		  int div1 = 0;
		  if ( carryover > 0){
			  i3 = i3 + carryover;
			  carryover = 0;
		  }
		  if (i3 > 9)
		  {
			  div1 = i3 %  10;
			  carryover = i3 /10;
		  }
		  if (i3/10 != 0 ){
		  element4 = (char) (div1 +'0');}
		  else
		  {
			  element4 = (char) (i3 +'0'); 
		  }
		  
		  //System.out.println("OUT "+i3);
		  output.set(count,element4);
		  first--;  
		  count++;
		  if (first == 0 && i3 >9 ){
		 // System.out.println("I am inside"+carryover);
		  element4 = (char) (carryover +'0'); 
		  output.set(count,element4);
		  }
	  }
	  return output;
  }
  
  public Cstring add(Cstring c){
	   int len1 = d.size();
	   CharArray dd5 = c.do_add(len1, c.current(),d);
	   char[] darray = new char[dd5.size()];
		for (int i = 0;i < dd5.size() ; i++){
			darray[i] = dd5.get(i);
		}
	    String z1 = new String(darray);
		Cstring z2 = new Cstring(z1) ;
		return z2;
	  }
  
  public ArrayList mult(Cstring c){
	   int len1 = d.size();
	   ArrayList dd5 = c.do_mult(len1, c.current(),d);
	  
		return dd5;
	  }
  
  public String to_string(){
	  char[] darray = new char[d.size()];
		for (int i = 0;i < d.size() ; i++){
			darray[i] = d.get(i);
		}
	    String z1 = new String(darray);
	    return z1;  
  }
  
  private CharArray extend_array(int dd1 , CharArray dd2,CharArray dd3){
	  int original_len = dd3.size();
	  int next_len = dd2.size();
	  int count = next_len;
	 for (int i = 0;i<count;i++){
		 
		 dd3.set(original_len, dd2.get(i));
		 original_len++;
	 }
	 return dd3;
  }
  
  public void append(Cstring c){
	  int len2 = d.size();
	  CharArray dd4 = c.extend_array(len2, c.current(),d);
	  d = dd4;
	  }
  
  public void append(String c){
	  int len2 = d.size();
	  Cstring ab = new Cstring(c);
	  CharArray dd4 = this.extend_array(len2, ab.current(),d);
	  d = dd4;
	  }
  
  public Cstring add(String c){
	  Cstring ab = new Cstring(c);
		return ab;
		  
	  }
  
  public boolean isEqual(Cstring c){
	  int len2 = d.size();
	  CharArray current = c.current();
	  int len3 = current.size();
	  boolean status = true;
	  if (len2 == len3){
		  for (int i =0;i< len2;i++){
			  if (current.get(i) == d.get(i)){
				  status = true;
			  }
			  else
			  {
				  status = false;
			  }
				  
		  }
	  }
	  else{
		  status = false;
	  }
	  return status;
  }
  
  //CANNOT CHANGE ANYTHING BELOW 
  private static void testBasic() {
    Cstring a = new Cstring('b') ;
    a.pLn("a = ") ;
    Cstring b = new Cstring('7') ;
    b.pLn("b = ") ;
    Cstring c = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    c.pLn("c = ") ;
    Cstring d = c.kclone() ;
    d.pLn("d = ") ;
    Cstring e = new Cstring("A quick brown fox junped over a lazy dog") ;
    e.pLn("e = ") ;
    Cstring f = new Cstring("Gateman sees name garageman sees nametag") ;
    f.pLn("f =  ") ;
    f.reverse() ;
    f.pLn("f' = ") ;
  }
 
  
  private static void testAdd() {
    Cstring a = new Cstring("UCSC") ;
    Cstring b = new Cstring("Extension") ;
    Cstring c = a.add(b) ;
    a.pLn("a = ") ;
    b.pLn("b = ") ;
    c.pLn("c = ") ;
    Cstring d = c.add("USA") ;
    d.pLn("d = ") ;
    a.append(b) ;
    a.pLn("a+b = ") ;
    a.append("World") ;
    a.pLn("a+b+World = ") ;
  }
  
  private static void testEqual() {
    Cstring a = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    a.pLn("a = ") ;
    Cstring b = new Cstring("123456789012345678901234567890123456789012345678901234567890") ;
    b.pLn("b = ") ;
    u.myassert(a.isEqual(b)) ;
    Cstring c = new Cstring("12345678901234567890123456789012345678901234567890123456789") ;
    c.pLn("c = ") ;
    u.myassert(a.isEqual(c) == false) ;
  }
  
  
  private static void testBench() {
    System.out.println("-----------Basic----------------");
    testBasic() ;
    System.out.println("-----------Addition----------------");
    testAdd() ;
    System.out.println("-----------Equal----------------");
    testEqual() ;
  }
  
  public static void main(String[] args) {
    System.out.println("Cstring.java");
    testBench();
    System.out.println("Done");
  }
}