// Need this util for gene
import java.util.UUID;

class ReverseStringTester {
	//Unit Test Class for ReverseString Class
	// It will verify functionality in Unit Test Level
	public static void myassert(boolean  x) {
		if (!x) {
			throw new IllegalArgumentException("Assert fail") ;
		    }
	}
	private static void testbasic() {
	    String s1 = "tableau";
	    // Take a object of Reverse String Class
	    ReverseString r1 = new ReverseString(s1);
	    String rs1 = new StringBuffer(s1).reverse().toString();
	    String rs2 = r1.get_reverse();
	    System.out.println(rs1.equals(rs2));
	    //System.out.println(r1.get_reverse());
	    myassert(rs1.equals(rs2));
	}
	
	private static void test1() {
		// Verify long string
	    String s1 = "Tableau Software is a software company headquartered in Seattle, Washington, United States which produces interactive data visualization products focused on business intelligence";
	    ReverseString r1 = new ReverseString(s1);
	    String rs1 = new StringBuffer(s1).reverse().toString();
	    String rs2 = r1.get_reverse();
	    System.out.println(rs1.equals(rs2));
	    //System.out.println(r1.get_reverse());
	    myassert(rs1.equals(rs2));
	    // Verify empty String
	    String s2 = "";
	    ReverseString r2 = new ReverseString(s2);
	    String s21 = r2.get_reverse();
	    myassert(s21.equals(s2));
	    
	}
	
	private static void testrandom() {
		// It will generate random string from 
		// Util UUID ( just want to verify Reverse function works)
		for(int i=0; i < 10000; i++){
			String s1 = UUID.randomUUID().toString();
			ReverseString r1 = new ReverseString(s1);
			String rs1 = new StringBuffer(s1).reverse().toString();
			String rs2 = r1.get_reverse();
			//System.out.println(rs2);
			System.out.println(rs1.equals(rs2));
			myassert(rs1.equals(rs2));
		}
	}
	
	private static void test_ie18(){
		// If product want to ship internationally then
		// we want to support International characters too
		String s1 = "汉字汉字汉字汉字汉字汉字";
		ReverseString r1 = new ReverseString(s1);
		String rs1 = new StringBuffer(s1).reverse().toString();
		String rs2 = r1.get_reverse();
		System.out.println(rs1.equals(rs2));
		//System.out.println(r1.get_reverse());
		myassert(rs1.equals(rs2));
	}
	
	 public static void main(String[] args) {
		    System.out.println("ReverseStringTester.java");
		    testbasic();
		    test1();
		    testrandom();
		    test_ie18();
		    System.out.println("Done");
		  }
}