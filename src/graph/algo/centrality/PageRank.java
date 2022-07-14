package graph.algo.centrality;

import graph.api.core.GraphApi;

import java.util.Arrays;

/**
 * version: 1.0
 * author: road
 * data: 20220714
 */
public class PageRank {
    private GraphApi graphApi;
    //reduce computational cost
    private double[] initValues;
    private double[] preValue;
    private double[] curValue;
    private double delta;

    public PageRank(GraphApi graphApi){
        this.graphApi = graphApi;
    }

    public double[] compute(int iteration, double damping, double initValue) {
        delta = 1 - damping;
        init(damping, initValue);
        int vertexSize = graphApi.getVertexSize();
        preValue = new double[vertexSize];
        curValue = new double[vertexSize];
        int[] vertexIds = graphApi.getVertex();

        for(int i = 0; i < iteration; i++) {
            System.arraycopy(curValue, 0, preValue, 0, vertexSize);
            for (int id : vertexIds) {
                double sum = 0;
                int[] inEdge = graphApi.getInEdge(id);
                for (int inId : inEdge) {
                    sum += initValues[inId] * preValue[inId];
                }
                //(1-d) + sum
                curValue[id] = delta +sum;
            }
        }
        return curValue;
    }

    private void init(double damping, double initValue) {
        int vertexSize = graphApi.getVertexSize();
        initValues = new double[vertexSize];
        Arrays.fill(curValue, initValue);

        int[] vertexIds = graphApi.getVertex();
        for (int id : vertexIds) {
            int outDegree = graphApi.getOutEdge(id).length;
            if(outDegree != 0) {
                initValues[id] = damping / outDegree;
            }
        }
    }

}
