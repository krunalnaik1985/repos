

public class LinkedList {
	
	Nodes first;
	public LinkedList(){
		first = null;
	}
	
	public Nodes add(int x,Nodes last){
		Nodes newnode = new Nodes(x);
		//System.out.println(t1.d);
		//System.out.println(t1.t);
		if (first != null){
			System.out.println(last.next);
			last.next = newnode;
			System.out.println("changed");
			System.out.println(last.next);
		}
		else{
			first = newnode;
		}
		//last = newnode;
		return newnode;
	}
	
	public void printlinked(){
		Nodes t1 = first;
		while (t1 != null){
			System.out.println(t1.value);
			t1 = t1.next;
			//System.out.println(t1.value);
		}
	}
	 public static void main(String[] args) {
		    System.out.println("LinkedList.java");
		    LinkedList ln = new LinkedList();
		    Nodes last = null;
		    last =ln.add(2,last);
		    System.out.println(last.next);
		    last =ln.add(4,last);
		    last =ln.add(5,last);
		    last =ln.add(6,last);
		    ln.printlinked();
		    System.out.println("Done");
		  }
}
