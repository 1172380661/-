package swordoffer;


import com.company.datastructure.ListNode;
import com.sun.deploy.panel.ITreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Author: wsw
 * @Date: 2019/10/7 9:18
 */

public class Day01 {

    /**
     * 将一个长为n的绳子剪成m段，求绳子的最大长度 n、m都大于1
     * @param n 绳子长度
     * @return 分割后最长长度
     */
    public int cutRope(int n){
        int[] resultArr = new int[n+1];
        if (n == 2){
            return 1;
        }else if (n == 3){
            return 2;
        }
        resultArr[1] = 1;
        resultArr[2] = 2;
        resultArr[3] = 3;
        cutRopeCore(resultArr,n);
        return resultArr[n];
    }

    private void cutRopeCore(int[] resultArr, int n) {
        //为resultArr赋值
        for (int i=4;i<=n;i++){
            int max = 0;
            for (int j=1;j<=i/2;j++){
                int temp = resultArr[j]*resultArr[i-j];
                if (max < temp){
                    max = temp;
                }
            }
            resultArr[i] = max;
        }
    }

    /**
     * 给定一个由字母组成的字符串，A代表1 Z代表26 判断它等于几
     * @param str 字符串
     * @return 对应的int值
     */
    public int letter(String str){
        int len = str.length();
        int res = 0;
        for (int i=0;i<len;i++){
            res += Math.pow((str.charAt(i)-64)*26,len-1-i);
        }
        return res;
    }

    private int numberOf1(int n) {
        int count = 0;
        while (n!=0){
            n = n&(n-1);
            count++;
        }
        return count;
    }

    /**
     * 长度为n的数组，数字范围在0~n-1找出其中任意重复的数字
     * @param numbers 数组
     * @param length 数组长度
     * @param duplication ???
     * @return 是否有重复数字
     */
    public boolean duplicate(int[] numbers,int length,int [] duplication) {
        for (int i=0;i<length;){
            if (numbers[i] != i){
                //如果不等则判断numbers[numbers[i]]和numbers[i]是否相等
                if (numbers[numbers[i]] == numbers[i]){
                    duplication[0] = numbers[i];
                    return true;
                }else {
                    int temp = numbers[i];
                    numbers[i] = numbers[numbers[i]];
                    numbers[temp] = temp;
                }
            }else {
                i++;
            }
        }
        return false;
    }

    /**
     * 二维数组，从左往右递增，从上往下递增，查找数组中是否有指定值
     * @param target 目标数字
     * @param array 数组
     * @return 数组中是否存在该数字
     */
    public boolean find(int target, int [][] array) {
        int row = array.length;
        int col = array[0].length;
        int i = 0;
        int j = col-1;
        while (i<row&&j>=0){
            if (array[i][j]>target){
                j--;
            }else if (array[i][j]<target){
                i++;
            }else {
                return true;
            }
        }
        return false;
    }

    /**
     * 将str中的空格替换成%20
     * @param str 需要替换的字符串
     * @return 替换后的字符串
     */
    public String replaceSpace(StringBuffer str) {
        int spaceCount = 0;
        for (int i=0;i<str.length();i++){
            if (str.charAt(i) == ' '){
                spaceCount++;
            }
        }
        int indexOld = str.length()-1;
        int newLength = 2*spaceCount + str.length();
        str.setLength(newLength);
        int indexNew = str.length()-1;
        for (int i=indexOld;i>=0;i--){
            char c = str.charAt(i);
            if (c ==' '){
                str.setCharAt(indexNew--,'0');
                str.setCharAt(indexNew--,'2');
                str.setCharAt(indexNew--,'%');
            }else {
                str.setCharAt(indexNew--, c);
            }
        }
        return str.toString();
    }

    /**
     * 从尾到头打印链表
     * @param listNode 链表结点
     * @return 从尾到头链表的值
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        printListFromTailToHeadCore(listNode, list);
        return list;
    }

    private void printListFromTailToHeadCore(ListNode listNode,ArrayList<Integer> list) {
        if (listNode!=null){
            printListFromTailToHeadCore(listNode.next,list);
            list.add(listNode.val);
        }
    }

    /**
     * 删除链表中的重复节点
     * @param pHead 头结点
     * @return 去除重复节点后的链表
     */
    public ListNode deleteDuplication(ListNode pHead) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        ListNode node = pHead;
        //先遍历一遍找出重复节点
        while (node!=null){
            map.put(node.val,map.getOrDefault(node.val, 0)+1);
            node = node.next;
        }
        for (int i: map.keySet()){
            if (map.get(i)>1){
                list.add(i);
            }
        }
        ListNode newHead = null;
        ListNode now = null;
        //遍历链表去除重复的节点
        while (pHead!=null){
            if (!list.contains(pHead.val)){
                if (newHead == null){
                    newHead = pHead;
                    now = pHead;
                }else {
                    now.next = pHead;
                    now = now.next;
                }
            }
            pHead = pHead.next;
        }
        if (now!=null){
            now.next = null;
        }
        return newHead;
    }

    /**
     * 倒数第k个节点
     * @param head 头结点
     * @param k k
     * @return 倒数第k个节点
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode p = head;
        ListNode q = head;
        int i = 0;
        for (;p!=null;i++){
            if (i>=k){
                q = q.next;
            }
            p = p.next;
        }
        return i<k?null:q;
    }

    /**
     *
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        return null;
    }

    public static void main(String[] args) {
        Day01 day01 = new Day01();
    }
}
