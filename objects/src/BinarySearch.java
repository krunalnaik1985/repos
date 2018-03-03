
class BinarySearch{
	
	private double[] array;
	private double num;
  
  BinarySearch(double[] array, double num){
	 this.array = array;
	 this.num = num;
  }
  
  protected boolean b_search()
  {
      // If array does not have any value
      if (array.length == 0) return false;
      
      int low=0;
      int high = array.length;
      while (low <= high)
      {
    	  int mid_value = low + (high - low)/2;
    	  //suppose once we search in first loop
    	  // mid_value is num
    	  // it is divide and conqur algo thats why
    	  // we get O(logn) time complexity
          if (array[mid_value] == num)
          { 
        	  System.out.println("value found is found:"+array[mid_value]);
              return true;
          }
          else
          {  
        	  //if given num is greater mid value
              if (array[mid_value] > num)
              {
            	  high = mid_value - 1;
              }
              else
              {
                  low = mid_value + 1;
              }
          }
      
      	}
      return false;
  	}
  
  public static void main(String[] args) {
	    System.out.println("BinarySearch.java");
  	}
}
 
