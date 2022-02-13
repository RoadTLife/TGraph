package graph.algo.traverse;

import graph.api.core.GraphApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
    private GraphApi graphApi;
    public DepthFirstSearch(GraphApi graphApi){
        this.graphApi = graphApi;
    }

    public void compute(int startId, boolean recursive) {
        System.out.println("\n=========== DFS ===========");
        boolean[] visited = new boolean[graphApi.getVertexSize()];
        visited[startId] = true;

        if (recursive) {
            dfs(startId, visited, graphApi);
        } else {
            compute(startId, visited);
        }
    }

    //recursive implementation
    public void dfs(int i, boolean[] visited, GraphApi graphApi) {
        System.out.print(i);
        visited[i] = true;
        int[] edgeIds = graphApi.getEdge(i);

        if (edgeIds == null) {
            System.out.print(" -> ");
            return;
        } else {
            System.out.print(" -> ");
        }

        for (int edgeId : edgeIds) {
            if(!visited[edgeId]) {
                dfs(edgeId, visited, graphApi);
            }
        }
    }

    //非递归
    public void compute(int i, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        // 根节点压栈
        stack.push(i);
        visited[i] = true;

        while (!stack.isEmpty()) {
            Integer id = stack.pop();
            System.out.print(id + " -> ");
            // 逻辑处理
            int[] edge = graphApi.getEdge(id);
            if(edge != null) {
                for (int item : edge) {
                    if(!visited[item]) {
                        stack.push(item);
                        visited[item] = true;
                    }
                }
            }
        }
    }

}
