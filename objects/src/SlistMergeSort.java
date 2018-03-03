

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
	// node t0;
	 IntSlist2 t1 ;
	// t0 = a.first;
	 t1 = a;
	 int count = a.size();
	 int [] list1 = new int[count];
	 IntSlist2 left1 = get_sorted_list_left(t1,count);
	 IntSlist2 right1 = get_sorted_list_right(t1,count);
	 int right = d8;
	 int left = d10;
	 //System.out.println("Passed By Reference Left "+d8);
	 
	 IntSlist2 b2 = create_list(t1,15);
	 int [] l1 = create_arr(left1);
	 int [] r1 = create_arr(right1);
	 mergeSort(l1);
	 mergeSort(r1);
	 IntSlist2 left2 = IntSlist2.buildSlist(l1) ;
	 IntSlist2 right2 = IntSlist2.buildSlist(r1) ;
	 IntSlist2 merge = merge_two(right2,left2);
	 //System.out.println("Merging Two");
	 //merge.reverse();
	 IntSlist2 reversed = reverse_list(merge);
	 //reversed.pLn();
	 //a = merge;
	 a = reversed;
	// a.first = t0;	  
  }
  
  private IntSlist2 merge_two(IntSlist2 r1,IntSlist2 l1){
	  IntSlist2 m1 = new IntSlist2();
	 // System.out.println("Merge Routine");
	  node merge_n = null;
	  int count1 = 0;
	  while (r1.first != null && l1.first != null ){
		  int r1_d = (int)r1.first.d;
		  int l1_d = (int)l1.first.d;
		  if (r1_d < l1_d){
			  //System.out.println((int)r1.first.d);
			  merge_n = m1.add(r1_d, count1,merge_n);
			  count1++;
			  r1.first = r1.first.next;
		  }
		  else
		  {   
			  //System.out.println((int)l1.first.d);
			  merge_n = m1.add(l1_d, count1,merge_n);
			  l1.first = l1.first.next;
			  count1++;
		  }
		  
	  }
	  //Check if this Ptr is not null
	 // System.out.println("right ptr"+r1.first);
	  //System.out.println("left ptr"+l1.first);
	  while (r1.first != null){
		  int r1_d = (int)r1.first.d;
		  merge_n = m1.add(r1_d, count1,merge_n);
		  count1++;
		  r1.first = r1.first.next;
	  }
	  while (l1.first != null){
		  int l1_d = (int)l1.first.d;
		  merge_n = m1.add(l1_d, count1,merge_n);
		  count1++;
		  l1.first = l1.first.next;
	  }
	  // Check again
	  return m1;
  }
  
  private int[] create_arr(IntSlist2 t1){
	  node t0 = a.first;
	  int arraysize = t1.size();
	  int [] b1 = new int[arraysize];
	  IntSlist2 right = new IntSlist2();
	  int count1 = 0;
	  while (t1.first != null){
	  int item = (int)t1.first.d;
		  tright = right.add((int)t1.first.d, count1,tright);
		  b1[count1] = (int)t1.first.d;
	    //  System.out.println("Removed item"+(int)t1.first.d+"Count "+count1);
	      count1++;
	  t1.first = t1.first.next;
	  }
	  a.first = t0;
	  return b1;
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
	  //System.out.println("Inside Middle "+middle1);
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
				
			//	System.out.println("left item"+(int)t1.first.d);
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
		//	 System.out.println("Right Middle "+right_middle);
			 if (count1 <= right_middle)
			 { tright = right.add((int)t2.first.d, count1,tright);
	//			 System.out.println("right item"+(int)t2.first.d);}
			 }
			else{
				tleft = left.add((int)t2.first.d, count1,tleft);
		//		System.out.println("left item"+(int)t2.first.d);
			}	 
			 right_middle--;
			 count1++;
			 t2.first = t2.first.next;
		 }}
	  do_sort(t2,right,left,middle);
  }
  
  public static void mergeSort(int[] array) {
      if (array.length > 1) {
        
          int[] left = left_a(array);
          int[] right = right_a(array);
          
          
          mergeSort(left);
          mergeSort(right);
          
         
          merge(array, left, right);
      }
  }
  
  public static int[] left_a(int[] array) {
      int size1 = array.length / 2;
      int[] left = new int[size1];
      for (int i = 0; i < size1; i++) {
          left[i] = array[i];
      }
      return left;
  }
  
  
  public static int[] right_a(int[] array) {
      int size1 = array.length / 2;
      int size2 = array.length - size1;
      int[] right = new int[size2];
      for (int i = 0; i < size2; i++) {
          right[i] = array[i + size1];
      }
      return right;
  }
  
  public static void merge(int[] result, 
          int[] left, int[] right) {
	  int i1 = 0;   
	  int i2 = 0;   

	  for (int i = 0; i < result.length; i++) {
		  if (i2 >= right.length || (i1 < left.length && 
				  left[i1] <= right[i2])) {
			  result[i] = left[i1];    
			  i1++;
		  } else {
			  result[i] = right[i2];   
			  i2++;
		  }
	  }
  }
  
  private IntSlist2 reverse_list(IntSlist2 t1){
	  node t0 = a.first;
	  int arraysize = a.size();
	  int [] b1 = new int[arraysize];
	  IntSlist2 right = new IntSlist2();
	  int count1 = arraysize;
	  while (t1.first != null){
	  int item = (int)t1.first.d;
	  tright = right.add((int)t1.first.d, count1,tright);
	  b1[count1-1] = item;
	    //  System.out.println("Removed item"+(int)t1.first.d+"Count "+count1);
	      count1--;
	  t1.first = t1.first.next;
	  }
	  a.first = t0;
	  IntSlist2 reversed = IntSlist2.buildSlist(b1);
	  return reversed;
  }
  
  public static void main(String[] args) {
    System.out.println("SlistMergeSort.java");
    SlistMergeSort u = new SlistMergeSort() ;
    u.testBench();
  }
}


