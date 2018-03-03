class ReverseString{
	
	private String s1;
  
	ReverseString(String s){
	 this.s1 = s;
	}
	
	 protected String get_reverse()
     {  
		 // this routine visit each elements for reverse purpose
		 // so time complexity is O(n) and we are storing string 
		 // in char array so Space is O(n) too
         int strlen = s1.length();
         if (strlen == 0) return "";
         char[] arr = s1.toCharArray();
         
         for(int i = 0, j = strlen-1; i < j; i++, j--)
         {   
        	 //swap routine for replacing character
        	 // it will replace last with first and so on
             char temp = arr[i];
             arr[i] = arr[j];
             arr[j] = temp;
         }
         String text = String.valueOf(arr);
         return text;
     }
	 
	 public static void main(String[] args) {
		    System.out.println("ReverseString.java");
	  }
}
  