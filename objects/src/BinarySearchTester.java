class BinarySearchTester {
	//Unit Test Class for BinarySearch Class
	// It will verify functionality in Unit Test Level
	public static void myassert(boolean  x) {
		if (!x) {
			throw new IllegalArgumentException("Assert fail") ;
		    }
	}
	private static void testbasic() {
	    double[] w1 = {1};
	    // Take a object of Binary Search Class
	    BinarySearch b1 = new BinarySearch(w1,1);
	    myassert(b1.b_search());
	}
	 private static void test2() {
		    double[] w2 = {2,3,4,5,6,8,10,11,12};
		    int arr_len = w2.length;
		    for(int i = 0; i < arr_len; i++){
		    	BinarySearch b1 = new BinarySearch(w2,w2[i]);
			    myassert(b1.b_search());
		    }
	 }
	 
	 private static void test3() {
		    double[] w3 = {1.2,3,4.3,5.4,6,8,10.0,11.3,12.5};
		    int arr_len = w3.length;
		    for(int i = 0; i < arr_len; i++){
		    	BinarySearch b1 = new BinarySearch(w3,w3[i]);
			    myassert(b1.b_search());
		    }
	 }
	 public static void main(String[] args) {
		    System.out.println("BinarySearch.java");
		    testbasic();
		    test2();
		    test3();
		    System.out.println("Done");
		  }
}