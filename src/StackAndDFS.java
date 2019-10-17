
import datastructure.Node;
import datastructure.TreeNode;

import java.util.*;

/**
 * @Author: wsw
 * @Date: 2019/10/15 15:04
 */

public class StackAndDFS {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * @param s 字符串
     * @return 是否有效
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (char c:s.toCharArray()){
            if (c=='('||c=='['||c=='{'){
                stack.push(c);
            }else {
                if (!stack.isEmpty()&&map.get(stack.peek()).equals(c)){
                    stack.pop();
                }else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     *根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
     * @param T 气温列表
     * @return 结果集
     */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        res[T.length-1] = 0;
        for (int i=T.length-2;i>=0;i--){
            for (int j=i+1;j<T.length;j=j+res[j]){
                if (T[i]<T[j]){
                    res[i] = j-i;
                    break;
                }else if (res[j] == 0){
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 根据逆波兰表示法，求表达式的值。不存在无效输入
     * @param tokens 表达式
     * @return 值
     */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        for (String s:tokens){
            if ("+".equals(s)){
                stack.push(String.valueOf(Integer.parseInt(stack.pop())+Integer.parseInt(stack.pop())));
            }else if ("-".equals(s)){
                stack.push(String.valueOf(-Integer.parseInt(stack.pop())+Integer.parseInt(stack.pop())));
            }else if ("*".equals(s)){
                stack.push(String.valueOf(Integer.parseInt(stack.pop())*Integer.parseInt(stack.pop())));
            }else if ("/".equals(s)){
                int i1 = Integer.parseInt(stack.pop());
                int i2 = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(i2/i1));
            }else {
                stack.push(s);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 克隆图
     * @param node 当前结点
     * @return 克隆后的当前结点
     */
    public Node cloneGraph(Node node) {
/*        //递归思想 需要用到静态变量nodeMap
        Node node1 = new Node(node.val, new ArrayList<Node>());
        nodeMap.put(node, node1);
        for (Node n:node.neighbors){
            if (!nodeMap.containsKey(n)){
                node1.neighbors.add(cloneGraph(n));
            }else {
                node1.neighbors.add(nodeMap.get(n));
            }
        }
        return node1;*/
        //迭代思想
        Stack<Node> stack = new Stack<>();
        HashMap<Integer, Node> map = new HashMap<>();
        stack.push(node);
        Node node1 = new Node(node.val, new ArrayList<>());
        map.put(node.val, node1);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            Node clone = map.get(pop.val);
            for (Node n:pop.neighbors){
                if (!map.containsKey(n.val)){
                    map.put(n.val, new Node(n.val,new ArrayList<>()));
                    stack.push(n);
                }
                if (!clone.neighbors.contains(map.get(n.val))){
                    clone.neighbors.add(map.get(n.val));
                }
            }
        }
        return node1;
    }

    /**
     *给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     * @param nums 数组
     * @param S 目标值
     * @return 几种满足条件的情况
     */
    public int findTargetSumWays(int[] nums, int S) {
        //有多长的nums就会有多深的二叉树。DFS确定值是否满足
        if (nums == null){
            return 0;
        }
        return getSum(nums,1,S,nums[0])+getSum(nums,1,S,-nums[0]);
    }

    private int getSum(int[] nums, int deep, int target, int now) {
        if (deep==nums.length){
            return now==target?1:0;
        }
        return getSum(nums,deep+1,target,now+nums[deep])+getSum(nums,deep+1,target,now-nums[deep]);
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * @param root 根节点
     * @return 中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
/*        //使用系统栈
        List<Integer> list = new ArrayList<>();
        inorderTraversalCore(list,root);
        return list;*/
        //用栈迭代
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while (root!=null||!stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    private void inorderTraversalCore(List<Integer> list, TreeNode root) {
        if (root == null){
            return;
        }
        inorderTraversalCore(list,root.left);
        list.add(root.val);
        inorderTraversalCore(list,root.right);
    }

    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。
     * @param s string
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        //用双栈一个存数字另一个存字符串
        Stack<Integer> integerStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        int num = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()){
            if (c == '['){
                integerStack.push(num);
                stringStack.push(stringBuilder);
                num=0;
                stringBuilder = new StringBuilder();
            }else if (c == ']'){
                num = integerStack.pop();
                StringBuilder temp = new StringBuilder(stringBuilder);
                for (int i=0;i<num-1;i++){
                    stringBuilder.append(temp);
                }
                stringBuilder = stringStack.pop().append(stringBuilder);
                num = 0;
            }else if (c>='0'&&c<='9'){
                num = num*10+Integer.parseInt(c+"");
            }else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
     * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
     * @param image 图形
     * @param sr i
     * @param sc j
     * @param newColor 新颜色
     * @return 上色后的图画
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
/*        //BFS
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr,sc});
        boolean[][] visited = new boolean[image.length][image[0].length];
        int start = image[sr][sc];
        while (!queue.isEmpty()){
            int[] pop = queue.pop();
            int curi = pop[0];
            int curj = pop[1];
            if (image[curi][curj] == start){
                image[curi][curj] = newColor;
            }

        }
        return image;*/
        //DFS
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFillCore(image,sr,sc,newColor,visited,image[sr][sc]);
        return image;
    }

    private void floodFillCore(int[][] image, int sr, int sc, int newColor, boolean[][] visited,int start) {
        if (sr<0||sr>=image.length||sc<0||sc>=image[0].length||visited[sr][sc]||image[sr][sc]!=start){
            return;
        }
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        floodFillCore(image,sr-1, sc, newColor, visited, start);
        floodFillCore(image,sr+1, sc, newColor, visited, start);
        floodFillCore(image,sr, sc-1, newColor, visited, start);
        floodFillCore(image,sr, sc+1, newColor, visited, start);
    }

    public int[][] updateMatrix(int[][] matrix) {
        //变种的广度优先算法，从任意一个不为0的数开始向外圈查找直到找到为0的点
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    newMatrix[i][j] = 0;
                }else{
                    newMatrix[i][j] = getMin(matrix,i,j);
                }
            }
        }
        return newMatrix;
    }

    private int getMin(int[][] m,int y,int x){
        for(int i=1;;i++){
            if(depth(m,y,x,i)){
                return i;
            }
        }
    }

    private boolean depth(int[][] m,int y,int x,int depth){
        int j;
        for(int i=0;i<=depth;i++){
            j = depth-i;
            if(getValue(m,y+i,x+j)==0){
                return true;
            }
            if(getValue(m,y-i,x-j)==0){
                return true;
            }
            if(getValue(m,y+i,x-j)==0){
                return true;
            }
            if(getValue(m,y-i,x+j)==0){
                return true;
            }
        }
        return false;
    }

    private int getValue(int[][] m,int y,int x){
        if(x<0 || y<0 || y>=m.length || x>=m[0].length){
            return 10;
        }
        return m[y][x];
    }

    /**
     * @param rooms 房间列表，每个房间中包含一些钥匙
     * @return 是否能进入每个房间
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            List<Integer> list1 = rooms.get(poll);
            for (int i:list1){
                if (!visited[i]){
                    //如果没有钥匙i则入队
                    queue.add(i);
                    visited[i] =true;
                }
            }
        }
        for (boolean b:visited){
            if(!b){
                return false;
            }
        }
        return true;
    }

}
