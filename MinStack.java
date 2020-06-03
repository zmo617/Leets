import java.util.Arrays;
import java.util.LinkedList;

public class MinStack {
	private int min;
	private LinkedList<Integer> ll;
	
	 /** initialize your data structure here. */
    public MinStack() {
        ll = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if(x < min) {
        	min = x;
        }
        ll.addFirst(x);
    }
    
    public void pop() {
    	if(ll.isEmpty()) {return;}
    	int t = ll.peek();
    	ll.removeFirst();
        if(t == min) {
        	min = ll.isEmpty()? Integer.MAX_VALUE:ll.peek();
        	for(int i: ll) {
        		if(i < min) {
        			min = i;
        		}
        	}
        }
        
    }
    
    public int top() {
        return ll.peek();
    }
    
    public int getMin() {
        return min;
    }
    //["MinStack","push","push","push",       "top","pop","getMin","pop","getMin","pop","push", "top",getMin,"push","top","getMin","pop","getMin"]
    //[[],[2147483646],[2147483646],[2147483647],[], [],    [],      [],    [],     [],[2147483647],[],[],    [-2147483648],[],[],[],[]]
    public static String[] operate(MinStack ms, String[] cmds, Integer[] input) {
    	String[] rst = new String[cmds.length];
    	String s;
    	for(int i = 0; i < cmds.length; i ++) {
    		s = cmds[i];
    		switch(s) {
    			case "MinStack":
    				ms = new MinStack();
    				rst[i] = "null";
    				break;
    			case "push":
    				ms.push(Integer.valueOf(input[i]));
    				rst[i] = "null";
    				break;
    			case "pop":
    				ms.pop();
    				rst[i] = "null";
    				break;
    			case "top":
    				rst[i] = ""+ms.top();
    				break;
    			case "getMin":
    				rst[i] = ""+ms.getMin();
    				break;
    		}
    		System.out.println(rst[i]);
    	}
    	return rst;
    }
    public static void main(String[] args) {
    	String[] cmds = {"MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"};
    	Integer[] input = {null,2147483646,2147483646,2147483647,null,null,null,null,null,null,2147483647,null,null,-2147483648,null,null,null,null};
    	System.out.println(Arrays.toString(operate(new MinStack(), cmds, input)));
    }
}
