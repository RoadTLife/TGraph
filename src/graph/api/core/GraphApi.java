package graph.api.core;

public class GraphApi {
    private String graphName;
    // get all vertex
    private int[] vertex;
    // get all neighbor
    private int[][] edges;

    public GraphApi(String graphName) {
        this.graphName = graphName;
    }

    // load all vertex
    public void loadVertex(int[] vertex) {
        this.vertex = vertex;
    }

    // load all edges vertex
    public void loadEdges(int[][] edges) {
        this.edges = edges;
    }

    // get graph's name
    public String getGraphName() {
        return graphName;
    }

    //get all vertex
    public int[] getVertex() {
        return vertex;
    }

    //get index edge
    public int[] getEdge(int index) {
        return edges[index];
    }

    //TODO get in edge
    public int[] getInEdge(int index) {
        return null;
    }

    public int[] getOutEdge(int index) {
        return null;
    }

    //get all edge
    public int[][] getAllEdge() {
        return edges;
    }

    public int getVertexSize() {
        return vertex.length;
    }

}
