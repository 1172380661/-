import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wsw
 * @Date: 2019/10/17 14:22
 */

public class ArrayAndString {
    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     * @param nums 数组
     * @return 中心索引的位置
     */
    public int pivotIndex(int[] nums) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int sum = 0;
        int left = 0;
        for (int i:nums){
            sum+=i;
        }
        for (int i=0;i<nums.length;i++){
            if (left == sum - left - nums[i]){
                return i;
            }
            left+=nums[i];
        }
        return -1;
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * 如果是，则返回最大元素的索引，否则返回-1。
     * tips:找出最大值和次大值，最大值是次大值的两倍即可
     * @param nums 数组
     * @return 最大元素的索引
     */
    public int dominantIndex(int[] nums) {
        int max = 0;
        int secMax = 0;
        int maxIndex = -1;
        for (int i=0;i<nums.length;i++){
            if (nums[i]>=max){
                secMax = max;
                max = nums[i];
                maxIndex = i;
            }else if (nums[i]>=secMax){
                secMax = nums[i];
            }
        }
        return max>=2*secMax?maxIndex:-1;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * @param digits 数组表示的数字
     * @return 加1后的数组
     */
    public int[] plusOne(int[] digits) {
        for (int i=digits.length-1;i>=0;i--){
            digits[i]+=1;
            digits[i] %= 10;
            if (digits[i]!=0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素
     * @param matrix 矩阵
     * @return 返回数组
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        int max = matrix.length+matrix[0].length-1;
        int[] res = new int[matrix.length * matrix[0].length];
        int j = 0;
        boolean flag = true;
        for (int i=0;i<max;i++){
            if (flag){
                int x = Math.min(i, matrix.length-1);
                int y = i-x;
                while (x>=0){
                    res[j++] = matrix[x][y];
                    x--;
                    y++;
                }
                flag = false;
            }else {
                int x = Math.min(i, matrix[0].length-1);
                int y = i-x;
                while (y>=0){
                    res[j++] = matrix[x][y];
                    x++;
                    y--;
                }
                flag = true;
            }
        }
        return  res;
    }

    /**
     * 螺旋矩阵
     * @param matrix 矩阵
     * @return 螺旋输出值
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix==null||matrix.length==0){
            return new ArrayList<>();
        }
        int rowMin = 0;
        int rowMax = matrix.length-1;
        int colMin = 0;
        int colMax = matrix[0].length-1;
        List<Integer> list = new ArrayList<>();
        for (;;){
            for (int j=colMin;j<=colMax;j++){
                list.add(matrix[rowMin][j]);
            }
            if (rowMin++==rowMax){
                break;
            }
            for (int i=rowMin;i<=rowMax;i++){
                list.add(matrix[i][colMax]);
            }
            if (colMax--==colMin){
                break;
            }
            for (int j=colMax;j>=colMin;j--){
                list.add(matrix[rowMax][j]);
            }
            if (rowMax--==rowMin){
                break;
            }
            for (int i=rowMax;i>=rowMin;i--){
                list.add(matrix[i][colMin]);
            }
            if (colMin++==colMax){
                break;
            }
        }
        return list;
    }

    /**
     *给定两个二进制字符串，返回他们的和（用二进制表示）。
     * 输入为非空字符串且只包含数字 1 和 0。
     * @param a str1
     * @param b str2
     * @return 字符串相加后的结果
     */
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;
        int c = 0;
        StringBuilder res = new StringBuilder();
        while (i>=0||j>=0){
            if (i-->=0){
                c+=a.charAt(i)-'0';
            }
            if (j-->=0){
                c+=b.charAt(j)-'0';
            }
            res.append(c%2);
            c>>=1;
        }
        return c==0?res.reverse().toString():res.append(1).reverse().toString();
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * @param haystack 主字符串
     * @param needle 子串
     * @return haystack中是否存在needle存在返回第一次出现的位置，不存在返回-1
     */
    public int strStr(String haystack, String needle) {
        if (haystack==null||needle==null){
            return -1;
        }
        if ("".equals(needle)){
            return 0;
        }
        for (int i=0;i<haystack.length();i++){
            if (haystack.charAt(i)==needle.charAt(0)){
                int j = 1;
                while (j<needle.length()&&i+j<haystack.length()&&needle.charAt(j)==haystack.charAt(i+j)){
                    j++;
                }
                if (j==needle.length()){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     *编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     * @param strs 字符串数组
     * @return 最长公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs==null||strs.length==0){
            return "";
        }
        int i=0;
        outer:
        for (;i<strs[0].length();i++){
            for (String s:strs){
                if(s.length()<=i||s.charAt(i)!=strs[0].charAt(i)){
                    break outer;
                }
            }
        }
        return strs[0].substring(0,i);
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * @param s 字符数组
     */
    public void reverseString(char[] s) {
        if (s==null||s.length==0){
            return;
        }
        int idx1 = 0;
        int idx2 = s.length-1;
        while (idx1<idx2){
            char temp = s[idx1];
            s[idx1] = s[idx2];
            s[idx2] = s[idx1];
            idx1++;
            idx2--;
        }
    }

    /**
     * 移除数组中值为val的元素
     * @param nums 原数组
     * @param val  target
     * @return 移除后数组个数
     */
    public int removeElement(int[] nums, int val) {
        if(nums==null||nums.length == 0){
            return 0;
        }
        //指向数组末尾指针
        int idx = 0;
        for(int i=0;i<nums.length;i++){
            if (nums[i]!=val){
                nums[idx] = nums[i];
                idx++;
            }
        }
        return idx;
    }

    /**
     * 给定一个二进制数组， 计算其中最大连续1的个数。
     * @param nums 数组
     * @return 最大连续1的个数
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums==null||nums.length==0){
            return 0;
        }
        int res = 0;
        int temp = 0;
        for (int i:nums){
            if (i==1){
                temp++;
            }else {
                res = Math.max(temp, res);
                temp=0;
            }
        }
        return res;
    }

    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，
     * 找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
     * @param s target
     * @param nums 数组
     * @return 满足条件的最小连续子数组
     */
    public int minSubArrayLen(int s, int[] nums) {
        //滑动窗口
        int sum = 0,idx1 = 0,idx2 = 0,res=Integer.MAX_VALUE;
        while (idx2<nums.length){
            if (sum+nums[idx2]<s){
                sum+=nums[idx2++];
            }else {
                res = Math.min(res, idx2-idx1+1);
                sum-=nums[idx1++];
            }
        }
        return res==Integer.MAX_VALUE?0:res;
    }

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * @param nums 数组
     * @param k 移动几个位置
     */
    public void rotate(int[] nums, int k) {
        //翻转的思路  向右移动k位相当于将元素翻转之后再翻转0~k%n-1,和k%n~n-1
        k%=nums.length;
        reverse(nums,0,nums.length);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
/*        //下标连续推
        if (nums.length % k==0){
            int j = k%nums.length;
            do {
                int idx = j-1;
                int temp = nums[idx];
                int temp1 = 0;
                for (int i=0;i<nums.length/k;i++){
                    int next = (idx + k) % nums.length;
                    temp1 = temp;
                    temp = nums[next];
                    nums[next] = temp;
                    idx = next;
                }
                j--;
            }while (j>0);

        }else {
            int idx = 0;
            int temp = nums[0];
            int temp1 = 0;

            for (int i=0;i<nums.length;i++){
                int next = (idx + k) % nums.length;
                temp1 = temp;
                temp = nums[next];
                nums[next] = temp;
                idx = next;
            }
        }*/
    }

    private void reverse(int[] nums, int k, int i) {
        while (k<i){
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            k++;
            i--;
        }
    }

    /**
     * 返回杨辉三角的第rowIndex行
     * @param rowIndex 第几行
     * @return res
     */
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<=rowIndex;i++){
            list.add(i, 1);
            for (int j=i-1;j>0;j--){
                list.set(j, list.get(j)+list.get(j-1));
            }
        }
        return list;
    }

    /**
     * 翻转字符串中的单词  注意翻转后的字符串两边不含有空格，且单词之间只用一个空格分隔
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords(String s) {
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(split[split.length-1]);
        for (int i=split.length-2;i>=0;i--){
            if (!"".equals(split[i])){
                sb.append(" ").append(split[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     * @param s 字符串
     * @return 翻转后的字符串
     */
    public String reverseWords2(String s) {
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(reverseOneWord(split[0]));
        for (int i=1;i<split.length;i++){
            sb.append(" ").append(reverseOneWord(split[i]));
        }
        return sb.toString();
    }

    private char[] reverseOneWord(String s) {
        char[] res = new char[s.length()];
        for (int i=0;i<s.length();i++){
            res[s.length()-1-i] = s.charAt(i);
        }
        return res;
    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * @param nums 数组
     * @return 移除后数组的长度
     */
    public int removeDuplicates(int[] nums) {
        int idx = 0,before = Integer.MAX_VALUE;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=before){
                nums[idx] = nums[i];
                idx++;
                before = nums[i];
            }
        }
        return idx;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * @param nums 数组
     */
    public void moveZeroes(int[] nums) {
        int idx = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                if(idx!=i){
                    nums[idx] = nums[i];
                    nums[i] = 0;
                }
                idx++;
            }
        }
    }
}
