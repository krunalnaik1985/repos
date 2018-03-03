

/**
 * File Name: SlistMergeSort.java 
 * Sort int slist using Merge Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistMergeSort.java
 */

class SlistMergeSort extends SlistSort{
 
  private node tright;
  public static int d8;
  public static int d10;


  @Override
  protected void sort(boolean ascend) {
    //WRITE CODE HERE
	 node t0;
	 IntSlist2 t1 ;
	 t0 = a.first;
	 t1 = a;
	 int count = a.size();
	 int [] list1 = new int[count];
	 IntSlist2 merge = new IntSlist2();
	 IntSlist2 left1 = get_sorted_list_left(t1,count);
	 IntSlist2 right1 = get_sorted_list_right(t1,count);
	 int right = d8;
	 int left = d10;
	 System.out.println("Passed By Reference Left "+d8);
	// System.out.println("Passed By Reference Right "+d10);
	 int left_size = left1.size();
	 while (left_size > 0){
		 IntSlist2 left4 = create_list(left1,d8);
		 left4.pLn();
		 IntSlist2 left5 = get_sorted_list_left(left4,left_size);
		 System.out.println("Passed By Reference Left "+d8);
		 left_size--;
	 }
	 
	
	 u.pLn(list1);
	 //if (left1.size() > 0){
	 //IntSlist2 left3 = get_sorted_list_left(left1,count);
	//	 System.out.println("printing again");
//		 System.out.println("Passed By Reference"+d8);}
	 IntSlist2 b2 = create_list(t1,15);
	 a.first = t0;	  
  }
  

  
  private IntSlist2 create_list(IntSlist2 t1,int remove_id){
	  node t0 = a.first;
	  int arraysize = a.size();
	  int [] b1 = new int[arraysize];
	  IntSlist2 right = new IntSlist2();
	  int count1 = 0;
	  while (t1.first != null){
	  int item = (int)t1.first.d;
	  if (!(remove_id == item)){
		  tright = right.add((int)t1.first.d, count1,tright);
	    //  System.out.println("Removed item"+(int)t1.first.d+"Count "+count1);
	      count1++;}
	  t1.first = t1.first.next;
	  }
	  a.first = t0;
	  return right;
  }
  
 
  private IntSlist2 get_sorted_list_right(IntSlist2 t1,int middle1){
	  node t0 = a.first;
	  middle1 = middle1/2;
	  int count1 = 0;
	  //System.out.println("Inside Middle "+middle1);
	  IntSlist2 right = new IntSlist2();
	  IntSlist2 t4 = new IntSlist2();
	  t4 = t1;
	  if (middle1 == 0)
		  return right;
	  while (t1.first != null){
			int right_middle = middle1 -1;
			//System.out.println("Right Middle "+right_middle);
			if (count1 <= right_middle)
			{  
				int item = (int)t1.first.d;
				if (middle1 == 1){
					d10 = item;}
				tright = right.add(item, count1,tright);
			//	System.out.println("right item"+(int)t1.first.d);
			}
			count1++;
			right_middle--;
			t1.first = t1.first.next;}
	  t1 = t4;
	  a.first = t0;
	  get_sorted_list_right(t1,middle1);
	  return right;
  }
  
  
  private IntSlist2 get_sorted_list_left(IntSlist2 t1,int middle1){
	  node t0 = a.first;
	  middle1 = middle1/2;
	  node tleft = null;
	  int count1 = 0;
	  System.out.println("Inside Middle "+middle1);
	  IntSlist2 left = new IntSlist2();
	  IntSlist2 t4 = new IntSlist2();
	  IntSlist2 t5 = new IntSlist2();
	  t4 = t1;
	  if (middle1 == 0){
		  return left;
		  }
	  while (t1.first != null){
			int right_middle = middle1;
			int index = (int)t1.size();
			//System.out.println("Size Array"+index);
			if (index <= right_middle){
				//System.out.println(middle1 == 1);
				int item = (int)t1.first.d;
				if (middle1 == 1 || middle1 == 2){
					d8 = item;}
					tleft = left.add(item, count1,tleft);
					count1++;
			//	}
				
				System.out.println("left item"+(int)t1.first.d);
			}
			right_middle++;
			t1.first = t1.first.next;}
	  t1 = t4;
	  a.first = t0;
	  get_sorted_list_left(t1,middle1);
	  return left;
  }

  protected void do_sort(IntSlist2 t2, IntSlist2 right,IntSlist2 left,int count){
	  int middle = count/2;
	  System.out.println("Middle Here "+middle);
	  int count1 = 0;
	  //System.out.println("Count Here "+count1);
	  node tright = null ;
	  node tleft = null;
	  if (middle == 0){
		  return; 
	  }
	  if (middle >= 1){
	  while (t2.first != null){
			 int right_middle = middle -1;
			 System.out.println("Right Middle "+right_middle);
			 if (count1 <= right_middle)
			 { tright = right.add((int)t2.first.d, count1,tright);
				 System.out.println("right item"+(int)t2.first.d);}
			else{
				tleft = left.add((int)t2.first.d, count1,tleft);
				System.out.println("left item"+(int)t2.first.d);
			}	 
			 right_middle--;
			 count1++;
			 t2.first = t2.first.next;
		 }}
	  do_sort(t2,right,left,middle);
  }
  
  public static void main(String[] args) {
    System.out.println("SlistMergeSort.java");
    SlistMergeSort u = new SlistMergeSort() ;
    u.testBench();
  }
}


