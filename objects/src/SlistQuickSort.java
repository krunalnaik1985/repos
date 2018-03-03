

/**
 * File Name: SlistQuickSort 
 * Sort int slist using Quick Sort
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2016
 */

/*
 * To compile you require: IntUtil.java RandomInt.java IntSlist2.java SlistSort.java SlistQuickSort.java
 */

class SlistQuickSort extends SlistSort{
 
  @Override
  protected void sort(boolean ascend) { 
    //WRITE CODE HERE
	  IntSlist2 t1 ;
	  t1 = a;
	  int count = a.size();

	  int [] l1 = create_arr(t1);
	  int low = 0;
	  int high = l1.length - 1;
	  quickSort(l1,low,high);
	  //u.pLn(l1);
	  IntSlist2 s1 = IntSlist2.buildSlist(l1) ;
	 // s1.pLn();
	  //IntSlist2 reversed = reverse_list(s1);
	  //reversed.pLn();
	  //a = merge;
	  a = s1;
	 // a.pLn();
		 //reversed.pLn();
		 //a = merge;
		// a = reversed;
  }
   
  public SlistQuickSort() {
	  super();
	  this.numCompare = 0;
	  this.numRecursion = 0;
	  this.numSwap = 0;
	  
	       }

  
  private int[] create_arr(IntSlist2 t1){
	  node t0 = a.first;
	  int arraysize = t1.size();
	  int [] b1 = new int[arraysize];
	  IntSlist2 right = new IntSlist2();
	  int count1 = 0;
	  node tright = null;
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
  
  public static void quickSort(int[] arr, int low, int high) {
		if (arr == null || arr.length == 0)
			return;

		if (low >= high)
			return;

		int middle = low + (high - low) / 2;
		int pivot = arr[middle];
		
		

		int i = low, j = high;
		while (i <= j) {
			while (arr[i] < pivot) {
				i++;
			    
			}

			while (arr[j] > pivot) {
				j--;
			   
			}

			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			//	b1.numSwap++;
			}
		}

		if (low < j)
			{quickSort(arr, low, j);
		   // b1.numRecursion++;
			}

		if (high > i)
			{quickSort(arr, i, high);
		 //   b1.numRecursion++;
			}
	}
  
  private IntSlist2 reverse_list(IntSlist2 t1){
	  node t0 = a.first;
	  int arraysize = a.size();
	  int [] b1 = new int[arraysize];
	  IntSlist2 right = new IntSlist2();
	  int count1 = arraysize;
	  node tright = null;
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
    System.out.println("SlistQuickSort.java");
    SlistQuickSort u = new SlistQuickSort() ;
    u.testBench();
  }

}