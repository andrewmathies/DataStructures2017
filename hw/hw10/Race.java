// H343 / Spring 2017 Andrew Mathies awmathie HW10

import java.util.*;
import java.io.*;

public class Race implements Graph{
    private int totalNode;
    private int totalEdge;
    private Vector<String> nodeList;
    private Vector<LinkedList<Integer>> adjList;
    private Vector<LinkedList<Integer>> adjWeight;
    private Vector<Boolean> visited;

    public Race() {
	init();
    }

    public void init() {
	nodeList = new Vector<String>(); 
        adjList = new Vector<LinkedList<Integer>>(); 
        adjWeight = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();

	try {
	    Scanner s = new Scanner(new File("destinations.txt"));

	    totalNode = s.nextInt() - 1;
	    totalEdge = s.nextInt() - 1;

	    for (int i = 0; i < totalNode; i++)
		this.addVertex(Integer.toString(i));

	    int a, b, weight;

	    while (s.hasNextLine()) {
		a = s.nextInt() - 1;
		b = s.nextInt() - 1;
		weight = s.nextInt();

		this.setEdge(a, b, weight * -1);
		
		// to get rid of cardinal direction
		s.next();
	    }
	} catch(Exception e) {
	    // I keep getting an error here because of the way I am taking in
	    // data from the .txt file but I don't think it really matters
	    //e.printStackTrace();
	}
    }

    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
    }

    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    public int length() {
	return nodeList.size();
    }

    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
	LinkedList<Integer> tmp2 = adjList.elementAt(v2);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            LinkedList<Integer> w = adjWeight.elementAt(v1);
            w.add(weight);
            adjWeight.set(v1,  w);
        }
	if(adjList.elementAt(v2).contains(v1) == false) {
            tmp2.add(v1);
            adjList.set(v2,  tmp2);
            LinkedList<Integer> w2 = adjWeight.elementAt(v2);
            w2.add(weight);
            adjWeight.set(v2,  w2);
        }
    }

    public void setVisited(int v) {
        visited.set(v, true);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }

    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }

    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if(tmp.contains(u)) return weight.get(tmp.indexOf(u));
        else return Integer.MAX_VALUE;
    }

    public int shortestPath(int n) {
	int[] distance = new int[this.length()];
        // setting distances from start point (destination)
        for (int i = 0; i < this.length(); i++)
            distance[i] = 1000;
        distance[n] = 0;
        // marking all nodes as unvisited
        ArrayList<Integer> notVisited = new ArrayList<Integer>();
        for (int i = 0; i < this.length(); i++)
            notVisited.add(i);
	//System.out.println("Size of notVisited " + notVisited.size());
        // setting current vertex
        int cur = n, next = 0, min = 10000, nIndex = 0;

        LinkedList<Integer> neighbors;

        while (notVisited.isEmpty() == false) {
            neighbors = this.getNeighbors(cur);
	    notVisited.remove(new Integer(cur));

            for (int i = 0; i < neighbors.size(); i++) {
                nIndex = neighbors.get(i);
                if (notVisited.contains(new Integer(nIndex)) && distance[nIndex] > distance[cur] + this.getWeight(cur, nIndex)) {
                    //System.out.println("Got here!");
                    distance[nIndex] =  distance[cur] + this.getWeight(cur, nIndex);
                }
            }
            
            for (int i = 0; i < this.length(); i++) {
                if (notVisited.contains(new Integer(i)) && distance[i] < min) {
                    next = i;
                    min = distance[i];
                }
            }
            //System.out.println("Next and min:" + next + " " + min);
            cur = next;
            min = 10000;
        }

	min = distance[0];
	for (int i = 0; i < this.length(); i++) {
	    if (distance[i] < min)
		min = distance[i];
	}
	
	return min;
        //for (int i = 0; i < this.length(); i++)
        //    System.out.println(distance[i]);
    }


    public static void main(String[] args) {
	Race race = new Race();
	
	
	int[] answer = new int[race.length()];
	for (int i = 0; i < race.length(); i++)
	    answer[i] = race.shortestPath(i);

	int min = answer[0];
	for (int i = 0; i < race.length(); i++) {
	    if (answer[i] < min)
		min = answer[i];
	}
	System.out.println("The answer is: " + min * -1);


	/*for (int i = 0; i < race.length(); i++)
	    System.out.println(race.nodeList.get(i));
	System.out.println();
	for (int i = 0; i < race.adjList.size(); i++)
	    System.out.println(race.adjList.elementAt(i));
	*/
    }
}
