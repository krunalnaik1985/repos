import java.util.ArrayList;
import java.util.Random;
import java.math.*;

/**
 * File Name: AmicablePair.java 
 * Print 'n' input AmicablePair
 * 
 * @author Krunal Naik
 * @year 2016
 */

class AmicablePair{
	
	private static final IntUtil u = new IntUtil();
	
	
	private static long arraysum(ArrayList<Integer> array2){
		long sum = 0 ;
		for(int i =0;i< array2.size();i++){
			sum = sum + array2.get(i);
		}
		return sum;
	}
	
	private static long findsum(int range){
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		for (int i =1;i<((range/2) +1);i++){
			if (range % i == 0){
				array1.add(i);
			}
		}
		return arraysum(array1);
	}
	
	private static void amicablepair(int number){
		int start = number;
		int count = 0;
		ArrayList<Long> array3 = new ArrayList<Long>();
		for (int i =1 ;i<=start;i++){
			long first_num = findsum(i);
			long second_num = findsum((int)first_num);
			if (true != array3.contains((long) i)) {
			if (second_num == i && first_num != second_num){
				array3.add(first_num);
				System.out.println(count+":"+i+" and "+first_num);
				count++;
			}}
		}
		
	}
	  
	public static void main(String[] args) {
		    //CANNOT CHANGE BELOW
		System.out.println("AmicablePair.java");
		System.out.println("The following are amicable numbers");
		amicablepair(100000000);
		System.out.println("Done");
		}
	
	
}