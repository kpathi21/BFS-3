import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class CloneGraph {

    HashMap<Node, Node> map;
    Queue<Node> q;

    public Node cloneGraph(Node node) {
        this.map = new HashMap<>();
        this.q = new LinkedList<>();

        if (node == null) return null;

        map.put(node, new Node(node.val));
        q.add(node);


        while (!q.isEmpty()) {
            Node curr = q.poll();
            Node copyCurr = clone(curr);
            for (Node ne : curr.neighbors) {
                if (!map.containsKey(ne)) {
                    Node newCopy = clone(ne);
                    q.add(ne);
                }
                copyCurr.neighbors.add(map.get(ne));
            }
        }

        return map.get(node);
    }

    private Node clone(Node curr) {
        if (curr == null) return null;

        if (!map.containsKey(curr)) {
            Node copyCurr = new Node(curr.val);
            map.put(curr, copyCurr);
        }
        return map.get(curr);
    }
}

//TC: O(V+E), SC: O(2V)
