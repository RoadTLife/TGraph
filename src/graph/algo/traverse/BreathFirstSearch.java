package graph.algo.traverse;

import graph.api.core.GraphApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BreathFirstSearch {
    private GraphApi graphApi;

    public BreathFirstSearch(GraphApi graphApi){
        this.graphApi = graphApi;
    }

    public void compute(int startId) {
        System.out.println("=========== BFS ===========");
        boolean[] visited = new boolean[graphApi.getVertexSize()];
        //visited[startId] = true;

        List<Integer> currentIds = new ArrayList<>();
        currentIds.add(startId);

        int depth = 1;
        while (currentIds.size() != 0) {
            Set<Integer> nextId = new HashSet<Integer>();
            System.out.println("depth is " + (depth++) + " neighbors is " + currentIds);
            for (Integer id : currentIds) {
                if(visited[id]) {
                    continue;
                }
                visited[id] = true;

                int[] edge = graphApi.getEdge(id);
                if (edge != null) {
                    for (int eId : edge) {
                        nextId.add(eId);
                    }
                }
            }
            currentIds.clear();
            currentIds.addAll(nextId);
        }

        System.out.println("");
    }
}
