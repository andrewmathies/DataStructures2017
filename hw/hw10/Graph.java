// H343 / Spring 2017 Andrew Mathies awmathie HW10
// a simple interface for graphs
// H343 Spring 2017
public interface Graph {
    public void init();
    public int length();
    public void addVertex(String node);
    public void setEdge(int v1, int v2, int weight);
    public void setVisited(int v);
    public boolean ifVisited(int v);
}
