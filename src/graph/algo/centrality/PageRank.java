package graph.algo.centrality;

import graph.api.core.GraphApi;

import java.util.Arrays;

//TODO: unfinished
public class PageRank {
    private GraphApi graphApi;
    private double[] initValue;
    private double[] preValue;
    private double[] curValue;

    public PageRank(GraphApi graphApi){
        this.graphApi = graphApi;
    }

    public double[] compute(int iteration, double damping) {
        init(damping);
        int vertexSize = graphApi.getVertexSize();
        preValue = new double[vertexSize];
        curValue = new double[vertexSize];
        int[] vertexIds = graphApi.getVertex();

        for(int i = 0; i < iteration; i++) {
            for (int id : vertexIds) {
                double sum = 0;
                int[] inEdge = graphApi.getInEdge(id);
                for (int inId : inEdge) {
                    sum += initValue[inId] * preValue[inId];
                }
                curValue[id] = sum;
            }
            preValue = curValue;
        }
        return curValue;
    }

    private void init(double damping) {
        int vertexSize = graphApi.getVertexSize();
        initValue = new double[vertexSize];
        Arrays.fill(initValue, damping);

        int[] vertexIds = graphApi.getVertex();
        for (int id : vertexIds) {
            int outDegree = graphApi.getOutEdge(id).length;
            if(outDegree != 0) {
                initValue[id] = damping / outDegree;
            }
        }
    }

}
