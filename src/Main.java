import bst.Treap;
import graph.Dijkstra;

import java.util.Arrays;

import static graph.Dijkstra.*;

public class Main {

    public static void checkTreap() {
        System.out.println("checkTreap:");

        Treap t0 = Treap.create(0);
        Treap t2 = Treap.create(2);
        Treap t4 = Treap.create(4);

        Treap res = Treap.merge(Treap.merge(t0, t2), t4);
        Treap tmp = res.insert(6).insert(1).insert(3);

        System.out.println(tmp);
    }


    public static void checkDijkstra() {
        System.out.println("checkDijkstra:");

        Edge[][] graph = new Edge[][]{
                new Edge[]{new Edge(1, 10), new Edge(2, 1)},
                new Edge[]{new Edge(3, 2)},
                new Edge[]{new Edge(3, 10)},
                new Edge[]{new Edge(4, 10)}
        };

        Integer[] result = Dijkstra.process(0, 4, graph);
        if (result == null) {
            System.out.println("path not found");
            return;
        }
        // else
        Arrays.stream(result)
                .forEach(x -> System.out.print(x + " "));
        System.out.println();
    }


    public static void main(String[] args) {
        checkDijkstra();
        System.out.println("DONE");
    }

}
