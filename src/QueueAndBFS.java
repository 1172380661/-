import java.util.*;

/**
 * @Author: wsw
 * @Date: 2019/10/14 19:00
 */

public class QueueAndBFS {

    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
     * @param grid 二维数组
     * @return 岛屿数量
     */
    public int numIslands(char[][] grid) {
        if (grid==null||grid.length==0){
            return 0;
        }
        int count = 0;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (!visited[i][j]&&grid[i][j]=='1'){
                    count++;
                    BFS(visited,grid,i,j);
                }
            }
        }
        return count;
    }

    private void BFS(boolean[][] visited, char[][] grid, int i, int j) {
        //递归
/*        if (i<0||i>grid.length||j<0||j>grid[0].length||visited[i][j]||grid[i][j]=='0'){
            return;
        }
        visited[i][j] = true;
        BFS(visited, grid, i+1, j);
        BFS(visited, grid, i-1, j);
        BFS(visited, grid, i, j+1);
        BFS(visited, grid, i, j-1);*/
        //循环
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int curi = poll[0];
            int curj = poll[1];
            if (curi>=0&&curi<grid.length&&curj>=0&&curj<grid[0].length&&!visited[curi][curj]&&grid[curi][curj]=='1'){
                visited[curi][curj] = true;
                queue.offer(new int[]{curi+1,curj});
                queue.offer(new int[]{curi-1,curj});
                queue.offer(new int[]{curi,curj+1});
                queue.offer(new int[]{curi,curj-1});
            }
        }
    }

    public int openLock(String[] deadends, String target) {
        //每次转动转盘锁可以有8种情况，将这8种情况入队，如果某一情况在deadends中直接取消该情况的遍历。
        // 注意要记录转动的次数
        Queue<String> queue = new LinkedList<>();
        HashSet<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
        if(deadendsSet.contains("0000")){
            return -1;
        }
        HashSet<String> visited = new HashSet<>();
        queue.offer("0000");
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                String poll = queue.poll();
                if (poll.equals(target)){
                    return count;
                }
                List<String> nexts = getNexts(poll);
                for (String string:nexts){
                    if (!deadendsSet.contains(string)&&visited.add(string)){
                        queue.offer(string);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    private List<String> getNexts(String str){
        List<String> list = new ArrayList<>();
        for (int i=0;i<4;i++){
            StringBuffer sb1 = new StringBuffer(str);
            sb1.setCharAt(i,sb1.charAt(i)=='9'?'0': (char) (sb1.charAt(i) + 1));
            list.add(sb1.toString());
            StringBuffer sb2 = new StringBuffer(str);
            sb2.setCharAt(i,sb2.charAt(i)=='0'?'9': (char) (sb2.charAt(i) - 1));
            list.add(sb2.toString());
        }
        return list;
    }

}
