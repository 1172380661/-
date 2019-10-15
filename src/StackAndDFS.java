import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wsw
 * @Date: 2019/10/15 15:04
 */

public class StackAndDFS {

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public static Map<Node,Node> nodeMap = new HashMap();

    public Node cloneGraph(Node node) {
/*        //尝试第一种情况，拷贝list但是不拷贝list中的node
        List<Node> neighbors = node.neighbors;
        List<Node> list = new ArrayList<>(neighbors);
        return new Node(node.val,list);*/

        //第二种情况，将所有node都复制一遍
        if (nodeMap.containsKey(node)){
            return nodeMap.get(node);
        }
        Node cloneNode = new Node(node.val, new ArrayList<>());
        nodeMap.put(node,cloneNode);
        List<Node> neighbors = node.neighbors;
        //修改map中的Node引用
        for (Node node1:neighbors){
            cloneNode.neighbors.add(cloneGraph(node1));
        }
        return cloneNode;
    }
}
