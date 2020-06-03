import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Leets4 {
	//169.
	public static int majorityElement(int[] nums) {
		if(nums.length < 3) {
			return nums[0];
		}
		int cur = nums[0];
		int ct = 0;
		for(int i: nums) {
			if(cur == i) {
				ct ++;
				if(ct > nums.length/2)return cur;
			}else {
				ct --;
				if(ct <= 0) {
					cur = i;
					ct = 0;
				}
			}
		}
		return cur;
	}

	//171.
	public static int titleToNumber(String s) {
		int cur = 1;
		int rst = 0;
		for(int i = s.length()-1; i >= 0; i --){
			rst += (s.charAt(i)+1-'A') * cur;
			cur *= 26;
		}
		return rst;
	}
	//189.
	public static void rotate(int[] nums, int k) {
		if(k == nums.length || nums.length == 1){
			return;
		}
		if(k > nums.length){
			k %= nums.length; 
		}
		int[] buf = new int[k];
		for(int i = 0; i < k; i ++) {
			buf[i] = nums[i+nums.length - k];
		}
		for(int j = nums.length-1; j >= k; j --) {
			nums[j] = nums[j-k];
		}
		for(int c = 0; c < k; c ++) {
			nums[c] = buf[c];
		}
		System.out.println(Arrays.toString(nums));
	}
	//don't get it
	public static void rotate2(int[] nums, int k) {
		k = k % nums.length;
		int count = 0;
		for (int start = 0; count < nums.length; start++) {
			int current = start;
			int prev = nums[start];
			do {
				int next = (current + k) % nums.length;
				int temp = nums[next];
				nums[next] = prev;
				prev = temp;
				current = next;
				count++;
				System.out.println("start = "+start+", cur = "+current+""+Arrays.toString(nums));
			} while (start != current);
		}
	}

	//190
	public static int reverseBits(int n) {
		System.out.println(Integer.toBinaryString(n));
		//System.out.println(Integer.toBinaryString(964176192));
		int rst = 0;
		for(int i = 0; i < 32; i ++){
			rst += n&1;
			System.out.println(Integer.toBinaryString(rst));
			rst <<= 1;
			//System.out.println(", "+Integer.toBinaryString(rst));
			n >>>= 1;
		}

		return rst;
	}
	//helper
	public static int binaryToInteger(String binary) {
		char[] numbers = binary.toCharArray();
		int result = 0;
		for(int i=numbers.length - 1; i>=0; i--)
			if(numbers[i]=='1')
				result += Math.pow(2, (numbers.length-i - 1));
		return result;
	}
	//202
	public static boolean isHappy(int n) {
		HashSet<Integer> s = new HashSet<>();
		int cur = n;
		while(n != 1){
			cur = 0;
			while(n > 0) {
				cur += (int)Math.pow(n%10, 2);
				n /= 10;
			}
			if(cur == 1)return true;
			if(s.contains(cur))return false; 
			s.add(cur);
			n = cur;
			System.out.println("cur = "+cur+",s: "+s.toString());
		}
		return false;
	}
	
	public static void printAL(ArrayList<int[]> al) {
		System.out.print("al:");
		for(int j = 0; j < al.size(); j ++) {
			System.out.print(", "+Arrays.toString(al.get(j)));
		}
		System.out.println();
	}

	public static void main(String[] args){
		int[] ar = {1,3,5};
		ArrayList<int[]> al = new ArrayList<>();
		int[] first = {};
		al.add(first);
		for(int i = 0; i < ar.length; i ++) {
			int elt = ar[i];
			int size = al.size();
			for(int c = 0; c < size; c ++) {
				int[] cur = al.get(c);
				int[] newA = Arrays.copyOf(cur, cur.length+1);
				newA[newA.length-1] = elt;
				System.out.println(Arrays.toString(cur)+"->"+Arrays.toString(newA));
				al.add(newA);
			}
		}
		printAL(al);
		//System.out.println(Integer.toBinaryString(i));
	}
}
