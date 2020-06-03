import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Leets1 {
	/* Problem 3
	 * Complexity Analysis
Time complexity: O(2n) = O(n). In the worst case each character will be visited twice by i and j.

Space complexity: O(min(m, n))
				We need O(k) space for the sliding window, where k is the size of the Set. 
				The size of the Set is upper bounded by the size of the string n and the size of the charset/alphabet m.
	 */
	public static int slidingWindow(String s) {
		//find the longest substring of all unique chars in s    
		int n = s.length();
		Set<Character> set = new HashSet<>();
		int ans = 0, i = 0, j = 0;
		while (i < n && j < n) {
			// try to extend the range [i, j]
			if (!set.contains(s.charAt(j))){
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			}
			else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	/* Problem 3
	 * If we know that the charset is rather small, replace the Map with an integer array.
int[26] for Letters 'a' - 'z' or 'A' - 'Z'
int[128] for ASCII
int[256] for Extended ASCII

Optimization: if s[j] duplicates with s[j'], we skip all the elements in the range [i, j'] and let i = j'+1. 
这一'很重要！！
	 */
	public static int slideWindowOpt(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);//put j+1 for corner case: there's only 1 char in s or s is all unique chars
		}
		return ans;
	}

	/* Problem 5
	 * Dynamic Programming solution. Not very fast. 
	 * Time = O(n^2). Space = O(n^2). 
	 * */
	public String longestPalindrome1(String s) {
		int max = 0;
		int st = 0;
		int en = 0;
		int n = s.length();
		if(n <= 1){
			return s;
		}
		boolean[][] ar = new boolean[n][n];
		for(int i = 0; i < n; i ++){
			ar[i][i] = true;
		}
		for(int i = 0; i < n-1; i ++){
			if(s.charAt(i) == s.charAt(i+1)){
				ar[i][i+1] = true;
				if(max < 2){
					max = 2;
					st = i;
					en = i+2;
				}
			}else{
				ar[i][i+1] = false;
			}
		}
		int j;
		for(int k = 2; k < n; k ++){
			for(int i = 0; i < n-k; i ++){
				j = i+k;
				if(s.charAt(i) == s.charAt(j) && ar[i+1][j-1]){
					ar[i][j] = true;
					if(k+1>max){
						max = k+1;
						st = i;
						en = j+1;
					}

				}else{
					ar[i][j] = false;
				}
			}
		}
		if(max == 0){
			return s.substring(0,1);
		}
		return s.substring(st, en);  
	}

	//debugging, prints out a square matrix
	public static void printMat(boolean[][] ar) {//square matrix
		int t;
		System.out.print(" ");
		for(int i = 0; i < ar.length; i ++) {
			System.out.print(" "+i);
		}
		System.out.println();
		for(int r = 0; r < ar.length; r ++) {
			System.out.print(r+":");
			for(int c = 0; c < ar.length; c ++) {
				if(ar[r][c]) {
					t = 1;
				}else {
					t = 0;
				}
				System.out.print(t+" ");
			}
			System.out.println();
		}
	}

	/* Problem 5: expand from center
	 * Time O(n^2), Space O(1)
	 * third soln:
	 * https://web.archive.org/web/20190420104610/http://articles.leetcode.com/longest-palindromic-substring-part-ii/
	 * */
	public static int expandFrom(String s, int l, int r){//l - left, r - right
		int n = s.length();
		while(l >= 0 && r < n && s.charAt(l)==s.charAt(r)){
			l --;
			r ++;
		}
		return r - l - 1;
	}

	public static String longestPalindrome2(String s) {
		int max = 0;
		int st = 0;
		int en = 0;
		int n = s.length();
		if(n <= 1){
			return s;
		}
		int temp;
		for(int i = 0; i < n; i ++){
			temp = Math.max(expandFrom(s, i, i), expandFrom(s, i, i+1));
			if(temp > max){
				max = temp;
				st = i - (temp-1)/2;
				en = i + temp/2;
				System.out.println("i = "+st+", j = "+en+", "+s.substring(st, en));
			}
		}
		return s.substring(st, en + 1);  
	}

	/* Problem 5 !!!! 
	 * 
	 * */
	public String convert(String s, int numRows) {

		if (numRows == 1) return s;

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++)
			rows.add(new StringBuilder());

		int curRow = 0;
		boolean goingDown = false;

		for (char c : s.toCharArray()) {
			rows.get(curRow).append(c);
			if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ret = new StringBuilder();
		for (StringBuilder row : rows) ret.append(row);
		return ret.toString();
	}
	/* Problem 11 !!!!Proof
	 * We starts with the widest container, l = 0 and r = n - 1. 
	 * Let's say the left one is shorter: h[l] < h[r]. 
	 * Then, this is already the largest container the left one can possibly form. 
	 * There's no need to consider it again. 
	 * Therefore, we just throw it away and start again with l = 1 and r = n -1.
	 * */
	public int maxArea(int[] height) {
		int maxarea = 0, l = 0, r = height.length - 1;
		while (l < r) {
			maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r])
				l++;
			else
				r--;
		}
		return maxarea;
	}

	/* Problem 17 !!!! Recursion, backtracing
	 * 
	 * */
	public static void helper(List<String> rst, HashMap<Character, String> map, String curStr, int indexDigits, String digits){
		if(indexDigits == digits.length()){
			rst.add(curStr);
			//System.out.println(rst.toString());
			return;
		}
		String lettersOfDigits = map.get(digits.charAt(indexDigits));
		//System.out.println("letters for digit \""+digits.charAt(indexDigits)+"\" is "+lettersOfDigits);
		for(int i = 0; i < lettersOfDigits.length(); i ++){
			//for each letter of this digit
			String newStr = curStr + (lettersOfDigits.substring(i, i+1));
			helper(rst, map, newStr, indexDigits+1, digits);
		}
	} 

	public static List<String> letterCombinations(String digits) {
		List<String> rst = new ArrayList<>();
		HashMap<Character, String> map = new HashMap<>();
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		helper(rst, map, "", 0, digits);
		return rst;
	}

	

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
				
	}
}
