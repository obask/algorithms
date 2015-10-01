package graph;

import java.util.*;
import java.util.stream.Stream;

public class Dijkstra {

    public static class Edge {
        final Integer to;
        final Integer weight;

        public Edge(Integer to, Integer weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class VertexPQ {
        final Integer weight;
        final Set<Integer> path;
        final Integer last;

        public VertexPQ(Integer weight, Set<Integer> path, Integer last) {
            this.weight = weight;
            this.path = path;
            this.last = last;
        }
    }

    public static Integer[] process(Integer start, Integer finish, Edge[][] graph) {
        // create heap with comparator
        PriorityQueue<VertexPQ> pq = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
        Set<Integer> objects = new HashSet<>();
        objects.add(start);
        pq.add(new VertexPQ(0, objects, start));
        // reduce with pq
        while (!pq.isEmpty()) {
            VertexPQ curr = pq.poll();
            Integer last = curr.last;
            Set<Integer> path = curr.path;
            if (finish.equals(last)) {
                // todo reorder from start to finish
                return path.toArray(new Integer[path.size()]);
            }
            // else
            Arrays.stream(graph[last])
                .filter(edge -> !path.contains(edge.to))
                    .map(edge ->
                            new VertexPQ(curr.weight + edge.weight,
                                    extendPath(curr, edge),
                                    edge.to))
                .forEach(pq::add);
        }
        return null;
    }

    private static Set<Integer> extendPath(VertexPQ curr, Edge edge) {
        Set<Integer> newPath = new HashSet<>();
        newPath.addAll(curr.path);
        newPath.add(edge.to);
        return newPath;
    }

}









