import java.util.List;
import java.util.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value) {
		val = value;
		left = null;
		right = null;
	}
	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	//build tree, assume a length odd.
	public static void buildT(Integer[] a, int i, TreeNode t) {
		if(2*i+1<a.length && a[2*i+1] != null) {
			t.left = new TreeNode(a[2*i+1]);
			buildT(a, 2*i+1, t.left);
		}
		if(2*i+2<a.length && a[2*i+2] != null) {
			t.right = new TreeNode(a[2*i+2]);
			buildT(a, 2*i+2, t.right);
		}
		return;
	}

	public static TreeNode arToTree(Integer[] a) {
		int n = a.length;
		if(a == null || n == 0) {
			return null;
		}
		TreeNode head = new TreeNode(a[0]);
		if(n < 3)return head;
		head.left = new TreeNode(a[1]);
		head.right = new TreeNode(a[2]);
		buildT(a, 1, head.left);
		buildT(a, 2, head.right);
		return head;
	}
	
	//prints binary tree (lied down tree)(root, "");
	private static void printTree(TreeNode n, String spaces) {
		if(n != null){
			printTree(n.right, spaces + "  ");
			System.out.println(spaces + n.val);
			printTree(n.left, spaces + "  ");
		}
	}
	//104
	public static void goDown(int[] max, int cur, TreeNode t){
		if(t == null){
			//max[1] --;
			System.out.println("max = "+max[0]+",cur = "+cur);
			if(cur > max[0])max[0] = cur;
			return;
		}
		goDown(max, cur+1, t.left);
		goDown(max, cur+1, t.right);
		return;
	}
	public static int maxDepth(TreeNode root) {
		int[] max = new int[1];
		goDown(max, 0, root);
		return max[0];
	}

	//107 - 2.BFS
	public static List<List<Integer>> levelOrderBottom2(TreeNode root) {
		//l = the returning list
		List<List<Integer>> l = new ArrayList<>();
		if(root == null)return l;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		//TreeNode t;
		while(!q.isEmpty()) {
			//add a to l at the end
			ArrayList<Integer> a = new ArrayList<>();
			//p = new q
			Queue<TreeNode> p = new LinkedList<>();
			for(TreeNode t:q) {
				a.add(t.val);
				if(t.left != null) {
					p.add(t.left);
				}
				if(t.right != null) {
					p.add(t.right);
				}
			}
			l.add(a);
			q = p;
		}
		Collections.reverse(l);
		return l;
	}
	//107 - 1.recurse
	public static void helper(List<List<Integer>> l, int i, TreeNode t) {
		if(t == null)return;
		//System.out.println(l.size()+", i = "+i);
		if(l.size() < i+1) l.add(new ArrayList<Integer>());	
		l.get(i).add(t.val);
		helper(l, i+1, t.left);
		helper(l, i+1, t.right);
		return;
	}

	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		//l = the returning list
		List<List<Integer>> l = new ArrayList<>();
		helper(l, 0, root);
		Collections.reverse(l);
		return l;
	}

	//108.
	//l - lower index, h - higher index
	public static TreeNode subBST(int[] nums, int l, int h) {
		if(l > h) {
			//done building BST
			return null;
		}
		//root should be mid point
		int m = (h+l)/2;
		TreeNode r = new TreeNode(nums[m]);
		r.left = subBST(nums, l, m-1);
		r.right = subBST(nums, m+1, h);
		return r;
	}
	public static TreeNode sortedArrayToBST(int[] nums) {
		return subBST(nums, 0, nums.length);
	}

	//110.
	public static boolean isBalanced(TreeNode root) {
		//each node has a height, heights of children left and right should differ <= 1
		if(root == null)return true;
		//if leaf, return true
		if(root.left == null && root.right == null) {
			root.val = 1;
			return true;
		}
		//3 other cases where we return true
		//1. have 2 children, both balanced, and diff <= 1
		//2. have only left child, balanced, height <= 1
		//3. have only right child, balanced, height <= 1
		if(root.left != null && root.right != null && isBalanced(root.left) && isBalanced(root.right)) {	
				int diff = root.left.val - root.right.val;
				if(Math.abs(diff) > 1)return false;
				root.val = diff > 0? root.left.val:root.right.val;
				root.val ++;
				return true;
		}else if(root.left == null && root.right != null && isBalanced(root.right) && root.right.val <= 1) {
			return true;	
		}else if(root.right == null && root.left != null && isBalanced(root.left) && root.left.val <= 1) {
			return true;
		}
		return false;
	}
	
	//111
	public static int getMin(TreeNode t, int[] min, int cur){
        if(t == null)return cur-1;
        //leaf
        if(t.left == null && t.right == null){
        	System.out.println("leaf "+t.val+", min = "+min[0]+",cur = "+cur);
            if(cur<min[0])min[0] = cur;
            return min[0];
        }else{
        	 int l = getMin(t.left, min, cur+1);
        	 int r = getMin(t.right, min, cur+1);
        	 System.out.println("t = "+t.val+", min = "+min[0]+", l = "+l+", r = "+r);
            return Math.min(l, r);
        }  
       
    }
    public static int minDepth(TreeNode root) {
        int[] min = new int[1];
        min[0] = Integer.MAX_VALUE;
        return getMin(root, min, 1);
    }
    
    
	public static void main(String[] args) {
		Integer[] a = {1,2,2,3,3,null,null,4,4};
		Integer[] b = {3, null};
		TreeNode root =  arToTree(a);
		System.out.println(minDepth(root));

	}

}
