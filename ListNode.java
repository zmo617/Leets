import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class ListNode {
	private int val;
	private ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public ListNode(int x, ListNode next){
		val = x;
		this.next = next;
	}

	/***** helpers  *****/
	public static void printSLL(ListNode head, String s){
		System.out.print(s + "  ");
		while(head != null){
			System.out.print(head.val+" ");
			head = head.next;
		}
		System.out.println();
	}
	public static ListNode arrayToLL(int[] a) {
		ListNode head = new ListNode(a[0]);
		ListNode rst = head;
		for(int i = 1; i < a.length; i ++) {
			head.next = new ListNode(a[i]);
			head = head.next;
		}
		return rst;
	}

	/* Problem 19 FACK!!!! */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode tail = new ListNode(0);
		tail.next = head;
		tail = tail.next;
		ListNode ptr = new ListNode(0);
		ptr.next = head;
		ptr = ptr.next;
		int i = 0;

		while(ptr.next != null){
			ptr = ptr.next;
			i ++;
		}
		int t = i - n;
		if(t < 0){
			return tail.next;
		}else{
			for(int j = 0; j < t; j ++){
				tail = tail.next;
			}
			tail.next = tail.next.next;
			return head;
		} 
	}

	//21.
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode rst = null;
		if(l1 == null && l2 == null){
			return rst;
		}else if(l1 == null){
			return l2;
		}else if(l2 == null){
			return l1;
		}

		if(l1.val < l2.val){
			rst = l1;
			l1 = l1.next;
		}else{
			rst = l2;
			l2 = l2.next;
		}
		ListNode t = rst;
		while(l1 != null && l2 != null){
			if(l1.val < l2.val){
				rst.next = l1;
				l1 = l1.next;
			}else{
				rst.next = l2;
				l2 = l2.next;
			}
			rst = rst.next;
		}

		if(l1 != null){
			rst.next = l1;
		}else if(l2 != null){
			rst.next = l2;
		}
		return t;
	}

	/* Problem 22 !!!! 有空看 */
	public static void helper2(List<String> rst, int[] curAr, int n) {
		if(n < 0) {
			String s = "";
			for(int j = 0; j < curAr.length; j ++) {
				if(curAr[j] == 1) {
					s += "(";
				}else if(curAr[j] == 2) {
					s += ")";
				}else {
					System.out.println("error");
				}
			}
			rst.add(s);
			return;
		}
		int i = 0;
		while(curAr[i] != 0) {
			i ++;
		}
		if(i == curAr.length) {
			return;
		}
		curAr[i] = 1;
		curAr[curAr.length - 1 - i] = 2;

	}
	/* Problem 24 */
	public ListNode swapPairs(ListNode head) {
		if(head == null){
			return null;
		}
		ListNode rst = new ListNode(0);
		ListNode newH = new ListNode(0);
		newH.next = head;
		newH = newH.next;
		ListNode newT = new ListNode(0);
		newT.next = head.next;
		if(head.next == null){
			return head;
		}
		newT = newT.next;//tail at headD.next
		rst = newT;//rst would be the second one
		ListNode rest = new ListNode(0);
		rest.next = newT.next;
		rest = rest.next;//rest set
		//switch
		ListNode lastT = null;

		while(newT != null){
			newH.next = rest;
			newT.next = newH;
			if(lastT != null){
				lastT.next = newT;
			}
			ListNode temp = newH;
			newH = newT;
			newT = temp;//switch head and tail ptrs
			if(newT.next == null){
				return rst;
			}
			lastT = newT;
			newH = newH.next.next;
			newT = newT.next.next;
			if(newT == null){
				return rst;
			}
			if(newT.next == null){
				rest = null;
			}else{
				rest = rest.next.next;
			}
		}
		return rst;
	}

	public static String reverse(int dir, String s) {
		StringBuilder sb = new StringBuilder(s);
		String rst;
		if(dir == 1) {
			rst = sb.reverse()+s;
			System.out.println("<reflect1> to "+rst);

		}else {
			rst = s+sb.reverse();
			System.out.println("<reflect2> to "+rst);
		}
		return rst;
	}

	//83.
	public static ListNode deleteDuplicates(ListNode head) {
		ListNode h = head;
		while(h != null && h.next != null){
			if(h.val == h.next.val){
				h.next = h.next.next;
			}
			h = h.next;
		} 
		return head;
	}
	//141
	public static boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) {return false;}
		ListNode t = head.next;
		while(t != null) {
			if(t == head) {
				return true;
			}
			head = head.next;
			t = t.next.next;
		}
		return true;
	}
	//160
	//for second iteration
	public static ListNode secondIt(ListNode l, ListNode s, int ll, int sl) {
		for(int i = 0; i < (ll-sl); i ++) {
			l = l.next;
		}
		for(int j = 0; j < sl; j ++) {
			if(s.val == l.val) {
				return s;
			}
			l = l.next;
			s = s.next;
		}
		return null;
	}
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA == null || headB == null) {
			return null;
		}
		int la = 0;
		int lb = 0;
		ListNode ha = headA;
		ListNode hb = headB;
		while(headA != null || headB != null){
			if(headA != null) {
				la ++;
				headA = headA.next;
			}
			if(headB != null) {
				lb ++;
				headB = headB.next;
			}
			if(headA != null && headB != null && headA.val == headB.val){
				return headA;
			}
		}
		//the longer one needs to advance (longer - shorter) steps
		ha = la > lb? secondIt(ha, hb, la, lb):secondIt(hb, ha, lb, la);
		return ha;
	}
	//203
	public static ListNode removeElements(ListNode head, int val) {
        if(head == null)return head;
        ListNode h = new ListNode(0, head);
        while(h != null && h.next != null){
        	printSLL(head, h.val+":");
            if(h.next.val == val){
            	if(h.next == head)head = head.next;
            	h.next = h.next.next;
            }else {
            	h = h.next;
            } 
        }
        printSLL(head, "head:");
        return head;
    }
	public static void main(String[] args) {
		int[] a1 = {6, 6,6,3,4,5, 6};
		boolean[] b = new boolean[3];
		System.out.println(Arrays.toString(b));
		//printSLL(removeElements(arrayToLL(a1), 6),"");
	}
}
