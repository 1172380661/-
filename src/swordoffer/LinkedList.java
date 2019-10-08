package swordoffer;

import datastructure.ListNode;

import java.util.*;

/**
 * @Author: wsw
 * @Date: 2019/10/8 14:19
 */

public class LinkedList {
    private ArrayList<Integer> list = new ArrayList<>();

    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * @param listNode 链表
     * @return list
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
/*        //方法1：利用栈的先进后出 时间复杂度2n 空间复杂度 n
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;*/
        //方法2：利用递归
        if (listNode == null){
            return list;
        }
        printListFromTailToHead(listNode.next);
        list.add(listNode.val);
        return list;
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
     * @param pHead 链表
     * @return 头指针
     */
    public ListNode deleteDuplication(ListNode pHead) {
/*        //方法1 用hashmap记录重复的节点 没有考虑到链表已排序的特点 时间复杂度2n 空间复杂度n
        HashMap<Integer, Integer> map = new HashMap<>();
        ListNode p = pHead;
        ListNode head = null;
        while (p!=null){
            map.put(p.val,map.getOrDefault(p.val, 0)+1);
            p = p.next;
        }
        while (pHead!=null){
            if (map.get(pHead.val)<2){
                if (head == null){
                    head = pHead;
                    p = head;
                }else {
                    p.next = pHead;
                    p = p.next;
                }
            }
            pHead = pHead.next;
        }
        if (p!=null){
            p.next = null;
        }
        return head;*/
        //方法2 时间复杂度n 空间复杂度O(1)
        ListNode head = new ListNode(-1);
        ListNode now = pHead;
        ListNode pre = head;
        while (now!=null){
            ListNode next = now.next;
            if (next == null){
                pre.next = now;
                pre = pre.next;
                break;
            }
            if (next.val == now.val){
                while (next!=null&&next.val == now.val){
                    next = next.next;
                }
                now = next;
            }else {
                pre.next = now;
                pre = pre.next;
                now = next;
            }
        }
        pre.next = null;
        return head.next;
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head 头结点
     * @param k 倒数第k个结点
     * @return 返回值
     */
    public ListNode findKthToTail(ListNode head,int k) {
/*        //方法1 使用栈 时间复杂度n+k 空间复杂度n
        Stack<ListNode> stack = new Stack<>();
        while (head!=null){
            stack.push(head);
            head = head.next;
        }
        int count = 0;
        ListNode res = null;
        while (!stack.isEmpty()){
            count++;
            if (count == k){
                res = stack.pop();
            }else{
                stack.pop();
            }
        }
        return res;*/
/*        //方法2 递归 时间复杂度O(n) 空间复杂度O(n)
        if (head == null){
            return head;
        }
        ListNode temp = findKthToTail(head.next,k);
        count++;
        if (count > k){
            return temp;
        }else if(count == k){
            return head;
        }else {
            return null;
        }*/
        //方法3 双指针 时间复杂度n 空间复杂度O(1)
        ListNode l1 = head;
        ListNode l2 = null;
        int count = 0;
        while (l1!=null){
            count++;
            if (count == k){
                l2 = head;
            }
            l1 = l1.next;
            if (l2!=null&&count!=k){
                l2 = l2.next;
            }
        }
        return l2;
    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头。
     * @param head 头结点
     * @return 新的头结点
     */
    public ListNode reverseList(ListNode head) {
/*        //方法1：递归 时间复杂度O(n) 空间复杂度O(n)
        if (head == null||head.next == null){
            return head;
        }
        ListNode res = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return res;*/
        //方法2：循环 时间复杂度O(n) 空间复杂度O(1)
        ListNode pre = null;
        while(head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     *输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * @param list1 第一个链表
     * @param list2 第二个链表
     * @return 合并后的链表
     */
    public ListNode merge(ListNode list1,ListNode list2) {
        //空间复杂度为O(1)的解法 时间复杂度O(n)
        ListNode res = new ListNode(-1);
        ListNode now = res;
        while(list1!=null&&list2!=null){
            if (list1.val>list2.val){
                now.next = list2;
                now = now.next;
                list2 = list2.next;
            }else{
                now.next = list1;
                now = now.next;
                list1 = list1.next;
            }

        }
        now.next = list1==null?list2:list1;
        return res.next;
    }

    /**
     * 输入两个链表，找出它们的第一个公共结点。
     * @param pHead1 第一个链表
     * @param pHead2 第二个链表
     * @return 公共结点
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        //方法1：使用hashmap将某个结点都保存起来，之后判断是否存在 不采用
        //方法2：遍历完pHead1后跳转到pHead2，如果存在相同结点则1次遍历后会在相同结点相遇，如果不存在则会同时为null
        if (pHead1==null||pHead2==null){
            return null;
        }
        ListNode l1 = pHead1;
        ListNode l2 = pHead2;
        while (l1 != l2){
            l1 = l1==null?pHead2:l1.next;
            l2 = l2==null?pHead1:l2.next;
        }
        return l1;
    }
}
