// a simple implementation for graphs with adjacency lists
// H343 Spring 2017

import java.util.*;

public class AdjGraph implements Graph {
    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList; // adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum; // list of nodes pre visit
    public AdjGraph() {
        init();
    }
    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }
    public void init() {
        nodeList = new Vector<String>(); 
        adjList = new Vector<LinkedList<Integer>>(); 
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;
    }
    // set vertices
    public void setVertex(String[] nodes) {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }
    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNode ++;
    }
    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }
    // return the number of vertices
    public int length() {
        return nodeList.size();
    }
    // add edge from v1 to v2
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;
        }
    }
    public void setEdge(String v1, String v2, int weight) {
        if((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well
            if (digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    // it is important to keep track if a vertex is visited or not (for traversal)
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }
    public boolean ifVisited(int v) {
        return visited.get(v);
    }
    public void clearWalk() {
        // clean up before traverse
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
    }
    public void walk(String method) {
        clearWalk();
        // traverse the graph 
        for(int i = 0; i < nodeList.size(); i ++) {
            if(ifVisited(i) == false) {
                if(method.equals("BFS")) BFS(i);      // i is the start node
                else if(method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }
    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1); 
        }
    }
    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                    toVisit.add(v1);
                }
            }
        }
    }
    public void display() {
        System.out.println("total nodes: " + totalNode);
        System.out.println("total edges: " + totalEdge);
    }
    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    public void topoSort() {
	// output arraylist
	ArrayList<String> sortedVertices = new ArrayList<String>();
        // array where each element is the number of edges going in to the 
	// corresponding vertice
	int[] inDegrees = new int[nodeList.size()];
	for (int i = 0; i < nodeList.size(); i++) {
	    for (int j = 0; j < adjList.elementAt(i).size(); j++) {
		if (adjList.elementAt(j).contains(i))
		    inDegrees[i]++;
	    }
	}
	// array holding all vertices that don't have edges going in to them
	LinkedList<String> eligibleVertices = new LinkedList<String>();
	for (int i = 0; i < nodeList.size(); i++) {
	    if (inDegrees[i] == 0)
		eligibleVertices.offer(nodeList.get(i));
	}

	int indexOfCurrent = 0;
	String current;
	LinkedList<Integer> tmp;
	while (eligibleVertices.peek() != null) {
	    current = eligibleVertices.poll();
	    sortedVertices.add(current);
	    indexOfCurrent = this.getNode(current);
	    tmp = adjList.elementAt(indexOfCurrent);
	    for (int i = 0; i < tmp.size(); i++) {
		inDegrees[tmp.get(i)]--;
		if (inDegrees[tmp.get(i)] == 0)
		    eligibleVertices.offer(nodeList.elementAt(tmp.get(i)));
	    }
	}
	if (sortedVertices.size() == nodeList.size()) {
	    for (int i = sortedVertices.size() - 1; i >= 0; i--)
		System.out.println(sortedVertices.get(i));
	} else
	    System.out.println("Error");
    }

    public void myFloyd() {
	// initializing distances
	int[][] d = new int[nodeList.size()][nodeList.size()];
	for (int i = 0; i < nodeList.size(); i++) {
	    for (int j = 0; j < nodeList.size(); j++) {
		if (i == j)
		    d[i][j] = 0;
		else if (adjList.elementAt(i).contains(j))
		    d[i][j] = 1;
		else
		    d[i][j] = 1000;
	    }
	}

	// calculating distances
	for (int k = 0; k < nodeList.size(); k++) {
	    for (int i = 0; i < nodeList.size(); i++) {
		for (int j= 0; j < nodeList.size(); j++) {
		    if (d[i][j] > d[i][k] + d[k][j])
			d[i][j] = d[i][k] + d[k][j];
		}
	    }
	}

	// printing shortest distances
	int min = 0, print = 0;
	for (int i = 0; i < nodeList.size(); i++) {
	    if (d[0][i] < 100)
		System.out.println("Shortest path between 0 and " + i + " is: " + d[0][i]);
	    else
		System.out.println("There is no path between 0 and " + i);
	}
    }

    public static void main(String[] args) {
	boolean directed = true;
        AdjGraph g1 = new AdjGraph(directed);
        String[] nodes1 = {"J1", "J2", "J3", "J4", "J5", "J6", "J7"};
        int weight = 1;
        g1.setVertex(nodes1);
        g1.setEdge("J1", "J2", weight);
        g1.setEdge("J1", "J3", weight);
        g1.setEdge("J2", "J4", weight);
        g1.setEdge("J3", "J4", weight);
        g1.setEdge("J2", "J6", weight);
        g1.setEdge("J2", "J5", weight);
        g1.setEdge("J4", "J5", weight);
        g1.setEdge("J5", "J7", weight);
        g1.topoSort();        

	
        directed = false;
        AdjGraph g2 = new AdjGraph(directed);
        String[] nodes2 = {"A", "B", "C", "D", "E"};
        g2.setVertex(nodes2);
        g2.setEdge("A", "B", weight);
        g2.setEdge("B", "C", weight);
        g2.setEdge("C", "D", weight);
        g2.setEdge("A", "C", weight);
        g2.myFloyd();
    }
}
