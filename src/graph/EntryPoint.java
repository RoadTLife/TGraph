package graph;

import graph.algo.traverse.BreathFirstSearch;
import graph.algo.traverse.DepthFirstSearch;
import graph.api.core.GraphApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryPoint {

    public static void main(String[] args) throws IOException {
        System.out.println("==== Instance TGraph ====");
        String graphName = "TGraph";
        String vertexFilePath = "./input/vertex.csv";
        String edgeFilePath = "./input/edges.csv";

        GraphApi graphApi = new GraphApi(graphName);
        loadFile(graphApi, vertexFilePath, edgeFilePath);

        //Traverse the BFS
        BreathFirstSearch bfs = new BreathFirstSearch(graphApi);
        bfs.compute(0);

        //Traverse the DFS
        DepthFirstSearch dfs = new DepthFirstSearch(graphApi);
        dfs.compute(0, true);
        dfs.compute(0, false);
    }

    //加载数据
    private static void loadFile(GraphApi graphApi, String vertexFilePath, String edgeFilePath) throws IOException {
        System.out.println("==== start load vertex ====");
        FileReader fileReader = new FileReader(new File(vertexFilePath));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        List<Integer> list = new ArrayList<>();
        //read vertex header
        bufferedReader.readLine();
        while((line = bufferedReader.readLine()) != null) {
            list.add(Integer.parseInt(line));
        }
        int[] vertex = new int[list.size()];
        for(int i = 0, len = list.size(); i < len; i++){
            vertex[i] = i;
        }
        //load vertex
        graphApi.loadVertex(vertex);
        System.out.println("==== end load vertex ====");

        System.out.println("==== start load edge ====");
        fileReader = new FileReader(new File(edgeFilePath));
        bufferedReader = new BufferedReader(fileReader);
        list.clear();

        Map<Integer, List<Integer>> maps = new HashMap<>(vertex.length, 1);
        //read edge header
        bufferedReader.readLine();
        while((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(",");
            Integer startId = Integer.parseInt(split[0]);
            Integer endId = Integer.parseInt(split[1]);
            List<Integer> values = maps.get(startId);
            if(values == null) {
                values = new ArrayList<>();
                values.add(endId);
                maps.put(startId, values);
            } else {
                values.add(endId);
            }
        }

        int[][] edges = new int[vertex.length][];
        for(int i = 0, len = vertex.length; i < len; i++){
            List<Integer> neighbors = maps.get(i);
            int j = 0;
            if(neighbors != null) {
                int size = neighbors.size();
                edges[i] = new int[size];
                for (Integer item : neighbors) {
                    edges[i][j++] = item;
                }
            }
        }
        maps.clear();
        graphApi.loadEdges(edges);
        System.out.println("==== end load edge ====");
        System.out.println("\n=========== TGraph Info is ===========");

        //print graph
        int[][] allEdge = graphApi.getAllEdge();
        for(int i = 0, len = allEdge.length; i < len; i++) {
            System.out.print(i + " : [ ");
            if(allEdge[i] != null) {
                for (int item : allEdge[i]) {
                    System.out.print(item + " ");
                }
            }
            System.out.println("]");
        }
        System.out.println("");
    }
}
