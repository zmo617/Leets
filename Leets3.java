import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leets3 {
	public static void swap(int[] nums, int pre, int next){
		int temp = nums[pre];
		nums[pre] = nums[next];
		nums[next] = temp;
	}


	//26.
	public static int removeDuplicates(int[] nums) {
		if(nums.length <= 1){
			return nums.length;
		}
		int rst = 1;
		int i = 1;
		while(i < nums.length){
			if(nums[rst-1] < nums[i]){
				nums[rst] = nums[i];
				rst ++;
			}
			i ++;
		}
		return rst;
	}
	//27.
	public static int removeElement(int[] nums, int val) {
		int rst = 0;
		for(int i = 0; i < nums.length; i ++){
			if(nums[i] != val){
				nums[rst] = nums[i];
				rst ++;
			}
		}
		return rst + 1;
	}

	//38.
	public static StringBuilder helper(int curInd) {
		if(curInd == 1) {
			return new StringBuilder("1");
		}
		StringBuilder temp = helper(curInd-1);
		char c = temp.charAt(0);
		int ct = 1;
		StringBuilder rst = new StringBuilder();
		// System.out.println(rst.toString());
		for(int i = 1; i < temp.length(); i ++) {
			if(temp.charAt(i) == c) {
				ct ++;
			}else {
				rst.append(ct);
				rst.append(c);
				c = temp.charAt(i);
				ct = 1;
			}
		}
		rst.append(ct);
		return rst.append(c);
	}
	public static String countAndSay(int n) {
		return helper(n).toString();
	}

	//53.
	public static int maxSubArray(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}  
		int n = nums.length;
		int rst = nums[0];
		int[] max = new int[n];
		max[0] = nums[0];
		for(int i = 1; i < n; i ++) {
			max[i] = max[i-1] > 0 ? (nums[i]+max[i-1]) : nums[i];
			if(max[i]>rst) {
				rst = max[i];
			}
		}
		//System.out.println(Arrays.toString(max));
		return rst;
	}

	public static String addBinary(String a, String b) {
		int al = a.length() - 1;
		int bl = b.length() - 1;
		if(al == -1)return b;
		if(bl == -1)return a;
		StringBuilder sb = new StringBuilder();
		int carry = 0;
		int t = 0;
		while(al >= 0 || bl >= 0) {
			t += al>=0 ? a.charAt(al)-'0' : 0;
			System.out.println("al = "+al+", bl = "+bl+", carry = "+carry);
			t += bl >= 0 ? b.charAt(bl)-'0' : 0;
			t += carry;
			System.out.println("t = "+t);
			if(t>1) {
				carry = 1;
				sb.insert(0, ""+t%2);
			}else {
				carry = 0;
				sb.insert(0, ""+t);
			}
			t = 0;
			al --;
			bl --;
			System.out.println(sb.toString());
		}
		if(carry == 1)sb.insert(0, "1");
		return sb.toString();
	}
	//69
	public static int mySqrt(int x) {
		if(x == 1){
			return 1;
		}
		int r = x/2;
		while(r*r > x){

			r = (r+x/r)/2;
			System.out.println("new r = "+r);
		}
		return (int)r;
	}
	//70
	public static int climbStairs(int n) {
		double x = (1+Math.sqrt(5))/2;
		return (int)((Math.pow(x, n+1)-Math.pow((1-Math.sqrt(5))/2, n+1))/Math.sqrt(5));
	}

	//118.
	public static List<List<Integer>> generate(int numRows) {
		List<List<Integer>> l = new ArrayList<>();
		if(numRows == 0) {
			return l;
		}
		ArrayList<Integer> a = new ArrayList<>();
		//ArrayList<Integer> b;
		a.add(1);
		l.add(a);
		for(int i = 2; i <= numRows; i ++) {
			a = new ArrayList<>();
			a.add(1);

			for(int j = 1; j < i-1; j ++) {
				//b = (ArrayList<Integer>) l.get(i-2);
				a.add(l.get(i-2).get(j-1)+l.get(i-2).get(j));
			}
			a.add(1);
			l.add(a);
			//System.out.println("l size = "+l.size());
		}
		return l;
	}
	//119.可以尝试的优化，只存一个ArrayList,往里加新的
	public static List<Integer> getRow(int rowIndex) {
		//只存两个arraylist, 上一个和新的
		ArrayList<Integer> a = new ArrayList<>();
		a.add(1);
		if(rowIndex == 0) {
			return a;
		}
		ArrayList<Integer> b = a;
		for(int i = 1; i <= rowIndex; i ++) {
			//a size should be rowIndex+1, i.e. i+1
			b = new ArrayList<>();
			b.add(1);
			for(int j = 1; j < i; j ++) {
				b.add(a.get(j-1)+a.get(j));
			}
			b.add(1);
			a = b;
		}
		return b;
	}

	//121
	public static int maxProfit(int[] prices) {
		//一个var记录max profit,一个记录min price,因为如果有新的min price而最高sell price在新的后面，肯定sell-新的profit更大
		if(prices == null || prices.length < 2) {
			return 0;
		}
		int maxProfit = 0;
		int minBuy = prices[0];
		for(int i = 0; i < prices.length; i ++) {
			if(prices[i] < minBuy) {
				minBuy = prices[i];
			}else if(prices[i] - minBuy > maxProfit) {
				maxProfit = prices[i] - minBuy;
			}
		}
		return maxProfit;
	}
	//125
	public static boolean isPalindrome(String s) {
		if(s == null || s.length() < 2){
			return true;
		}
		int i = 0;
		s = s.toLowerCase();
		int j = s.length()-1;
		while(i < j){
			System.out.println("i = "+i+", j = "+j);
			while(!Character.isLetterOrDigit(s.charAt(i))) {
				i ++;
				if(i == j)return true;
			}
			while(!Character.isLetterOrDigit(s.charAt(j))) {
				j --;
				if(i == j)return true;
			}
			if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
				return false;
			}
			i ++;
			j --;
		}
		return true;
	}

	//136.
	public static int singleNumber(int[] nums) {
		if(nums.length < 2) {
			return nums[0];
		}
		int a = 0;
		int b = 1;
		while(b < nums.length) {
			if(nums[a] == nums[b]) {
				if(a+1==b) {
					b+=2;
				}else {
					nums[b] = nums[a+1];
					b = a+3;
				}
				a+=2;
			}else {
				b ++;
			}
		}
		return nums[a];
	}
	//168 ***
	public static String convertToTitle(int n) {
		StringBuilder sb = new StringBuilder();
		while(n > 0) {
			sb.insert(0, (char)('A'+(n-1)%26));
			n = (n-1)/26;
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(1);
		al.add(3);
		al.add(5);
		
		
		//System.out.println(b.toString());
	}
}
