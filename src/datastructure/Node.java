package datastructure;

import java.util.List;

/**
 * @Author: wsw
 * @Date: 2019/10/16 17:07
 */

public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int val,List<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}
