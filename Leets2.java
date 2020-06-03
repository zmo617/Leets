import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class Leets2 {
	/*Problem */
	//switch (in place) 2 elements in an array 
	public static void switchInAr(int[] nums, int i, int j) {
		//switch i and j
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void addToArList(ArrayList<Integer> s, int i) {
		if(s.size() == 0) {
			s.add(i);
		}else {
			int c = 0;
			while(c < s.size() && i > s.get(c)) {
				c ++;
			}
			if(c == s.size()) {
				s.add(i);
			}else {
				s.add(c, i);
			}
		}
	}

	public static int getNxtGreaterInArLst(ArrayList<Integer> s, int numsi) {
		int j = s.indexOf(numsi)+1;
		while(s.get(j) == numsi) {
			j ++;
		}
		return s.get(j);
	}

	public static void nextGreaterLex(int[] nums, ArrayList<Integer> s, int head) {
		System.out.println("set = "+s.toString()+", nums["+head+"] = "+nums[head]);
		nums[head] = getNxtGreaterInArLst(s, nums[head]);
		System.out.println("new nums[head] = "+nums[head]);
		s.remove(s.indexOf(nums[head]));
		for(int i = 0; i < s.size() ; i ++) {
			nums[head+1+i] = s.get(i);
			System.out.println("i = "+i+", nums[i] = "+nums[i]);
		}
		System.out.println("nums = "+Arrays.toString(nums));
	}

	public static void nextPermutation(int[] nums) {
		if(nums == null || nums.length <= 1) {
			System.out.println("original nums");
			return;
		}

		int last = nums.length - 1;
		if(nums[last] > nums[last - 1]) {
			switchInAr(nums, last, last-1);
			System.out.println(Arrays.toString(nums));
			return;
		}
		ArrayList<Integer> s = new ArrayList<>();
		for(int i = last; i > 0; i --) {
			addToArList(s, nums[i]);
			if(nums[i-1] < nums[i]) {
				addToArList(s, nums[i-1]);
				nextGreaterLex(nums, s, i-1);
				return;
			}
		}
		addToArList(s, nums[0]);
		for(int j = 0; j < s.size(); j ++) {
			nums[j] = s.get(j);
		}
	}

	public static void printPixel(String pre, int pix) {
		int r = (pix & 0xff0000)>>16;
		int g = (pix & 0xff00)>>8;
		int b = pix & 0xff;
		System.out.println(pre+" "+Integer.toBinaryString(r)+"|"+Integer.toBinaryString(g)+"|"+Integer.toBinaryString(b));
	}

	//1.
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> hm = new HashMap<>();
		int[] rst = new int[2];
		for(int i = 0; i < nums.length; i ++){
			if(nums[i] <= target){
				Integer comp = target - nums[i];
				if(hm.containsKey(comp)){
					rst[0] = i;
					rst[1] = hm.get(comp);
					System.out.println(rst[1]);
					return rst;
				}
				hm.put(nums[i], i);

			}
		}
		return rst;
	}

	//7.
	public static int reverse(int x) {
		int mod;
		int rst = 0;
		boolean upper;
		boolean lower;
		System.out.println("max = "+Integer.MAX_VALUE+", min = "+Integer.MIN_VALUE);
		while(x != 0){
			mod = x % 10;
			x /= 10;
			System.out.println("mod = "+mod+", rst = "+rst);
			upper = (rst > (Integer.MAX_VALUE/10))|| (rst == Integer.MAX_VALUE && mod > 7);
			lower = (rst < (Integer.MIN_VALUE/10))|| (rst == Integer.MIN_VALUE && mod < -8);
			if(upper || lower) {
				return 0;
			}
			rst = rst*10 + mod;
		}
		return rst;
	}
	//9.
	public static boolean isPalindrome(int x) {
		if(x < 0){
			return false;
		}
		if(x == 0){
			return true;            
		}
		int rev = 0;
		int old = x;
		while(x > 0){
			rev = rev*10 + x%10;
			x /= 10;
			System.out.println("rev = "+rev+", x = "+x);
		}
		return old == rev;
	}

	public static int romanToInt(String s) {
		int rst = 0;
		char prev = 'n';
		char cur;
		for(int i = s.length() - 1; i >= 0; i --){
			cur = s.charAt(i);
			switch(cur){
			case 'I':
				if((prev == 'V')||(prev == 'X')){
					rst --;
				}else{
					rst += 1;
				}
				break;

			case 'V':
				rst += 5;
				break;

			case 'X':
				if(prev == 'L' || prev == 'C'){
					rst -= 10;
				}else{
					rst += 10;
				}
				break;

			case 'L':
				rst += 50;
				break;

			case 'C':
				if(prev == 'D' || prev == 'M'){
					rst -= 100;
				}else{
					rst += 100;
				}
				break;

			case 'D':
				rst += 500;
				break;

			case 'M':
				rst += 1000;
				break;
			}
			prev = cur;
		}
		return rst;
	}
	//14
	public static String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0){
			return "";
		}
		int n = strs.length;
		if(n == 1){
			return strs[0];
		}

		String f = strs[0];
		int i = f.length();
		for(int j = 0; j < n; j ++){
			while(!strs[j].startsWith(f.substring(0, i))){
				i --;
			}
			if(i <= 0){
				return "";
			}
		}
		return f.substring(0,i);  
	}

	public static boolean isValid(String s) {
        if(s == null || s.length() == 0){
            return true;
        }
        if(s.length() % 2 == 1){
            return false;
        }
        Stack<Character> a = new Stack<>();
        Character ch;
        for(int i = 0; i < s.length(); i ++){
            ch = s.charAt(i);
            switch(ch){
                case '(':
                case '{':
                case '[':
                    a.push(ch);
                    break;
                case ')':
                	 if(a.size() == 0){
                         return false;
                     }
                    if(a.peek() == '('){
                        a.pop();
                    }else{
                      
                        a.push(ch);
                    }
                    break;
                    
                case '}':
                	 if(a.size() == 0){
                         return false;
                     }
                    if(a.peek() == '{'){
                        a.pop();
                    }else{
                      
                        a.push(ch);
                    }
                    break;
                case ']':
                	if(a.size() == 0){
                        return false;
                    }
                    if(a.peek() == '['){
                        a.pop();
                    }else{
                       
                        a.push(ch);
                    }
                    break;
            }
        }
         return a.size() == 0;
    }

	public static boolean isValid2(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.push(')');
			}else if (c == '{') {
				stack.push('}');
			}else if (c == '[') {
				stack.push(']');
			}else if (stack.isEmpty() || stack.pop() != c) {
				return false;
			}
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] asrgs) {
		Character[] cs = {'(', ')', '{','}', '[',']'};
		Character ch = ']';

		System.out.println(isValid2("({})"));

	}
}
