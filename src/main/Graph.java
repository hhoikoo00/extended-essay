package main;

/* ATTRIBUTE DESCRIPTION
 * -int numberOfVertex
 *      number of vertices in the graph
 * -int indexSource
 *      source vertex = most edges
 * -LinkedList<Edge>[] listEdge
 * -Vertex[] listVertex
 *
 * METHOD DESCRIPTION
 * --public Graph (int numberOfVertex)
 *      constructor: initialize numberOfVertex, indexSource
 *      initialize numberOfVertex, call resetGraph()
 * -public resetGraph(): void
 *      initialize listEdge, listVertex
 * -public addEdge(int src, int dest, int weight): void
 *      add an edge to the graph (listEdge]
 * -public getEdgeWeight(int src, int dest): int
 *      return weight of edge(src, dest)
 *      return 0 if not found
 * -public generateGraph(): void
 *      generate a random graph
 * -public printAdjacencyList(): void
 *      print graphs's adjacency list
 * -public printShortestPath(): void
 *      print shortest path of all vertices
 * -private getPreviousPath(int index, int count): String
 *      recursive method used in printShortestPath()
 */

import java.util.*;

public class Graph
{
    private int numberOfVertex;
    private int indexRoot;
    private LinkedList<Edge>[] listEdge;
    private Vertex[] listVertex;

    public Graph(int numberOfVertex)
    {
        this.numberOfVertex = numberOfVertex;
        this.indexRoot = 0;
        this.resetGraph();
    }

    public int getNumberOfVertex()
    {
        return numberOfVertex;
    }

    public int getIndexRoot()
    {
        return indexRoot;
    }

    public LinkedList<Edge>[] getListEdge()
    {
        return listEdge;
    }

    public Vertex[] getListVertex()
    {
        return listVertex;
    }

    @SuppressWarnings("unchecked")
    public void resetGraph()
    {
        listEdge = new LinkedList[numberOfVertex];
        listVertex = new Vertex[numberOfVertex];

        for (int i = 0; i < numberOfVertex; i++) {
            listEdge[i] = new LinkedList<Edge>();
            listVertex[i] = new Vertex(i);
        }
    }

    public void addEdge(int src, int dest, int weight)
    {
        listEdge[src].addFirst(new Edge(src, dest, weight));
    }

    public int getEdgeWeight(int src, int dest)
    {
        int lengthEdgeList = listEdge[src].size();
        Edge tempEdge;

        for (int i = 0; i < lengthEdgeList; i++) { // for all edgeList
            tempEdge = listEdge[src].get(i);

            if (tempEdge.getIndexDestination() == dest) {
                return tempEdge.getWeight(); // if found
            }
        }
        return 0; // if not found
    }

    public void generateGraph(int numberOfVertex)
    {
        this.resetGraph();

        final double CHANCE = 20;
        final int MAX_WEIGHT = 100;
        int[] numberOfConnection =  new int[numberOfVertex];
        for (int i=0; i<numberOfVertex; i++) {
            numberOfConnection[i] = 0;
        }
        int weight; // randomly-generated weight [1,MAX_WEIGHT]

        /* Generate edges (i,j) if probability true
         */
        for (int i=0; i<numberOfVertex; i++) {
            for (int j=0; j<numberOfVertex; j++) {
                weight = (int) (Math.random() * (MAX_WEIGHT-1) + 1);

                /* create if probability true && NOT edge to itself
                 */
                if ((Math.random() * 100) < CHANCE && i != j) {
                    this.addEdge(i, j, weight);
                    numberOfConnection[i]++;
                }
            }
        }

        /* find the root vertex
         */
        for (int i=0; i<numberOfVertex; i++) {
            if (numberOfConnection[i] > numberOfConnection[indexRoot]) {
                indexRoot = i;
            }
        }
    }

    public void printAdjacencyList()
    {
        for (int i=0; i<numberOfVertex; i++) { // for each source
            System.out.printf("V %d:", i);
            for (Edge e: listEdge[i]) { // for each destination
                System.out.printf(" %d [%d] ->",  e.getIndexDestination(), e.getWeight());
            }
            System.out.printf(" \\end\n");
        }
    }

    public void printShortestPath()
    {
        System.out.printf("SHORTEST PATH source: %d\n", indexRoot);
        System.out.printf("____________________________\n");

        String tempOutput;
        for (int i=0; i<numberOfVertex; i++) {
            System.out.printf("V%d", i);

            if (i == indexRoot) { // if source
                System.out.printf(" SOURCE\n");
                continue;
            }

            tempOutput = getPreviousPath(i, 0);
            if (tempOutput.endsWith("null")) { // if null case
                System.out.printf(" NOT CONNECTED\n");
                continue;
            }

            System.out.printf(": %s (Dist: %d)\n", tempOutput, listVertex[i].getD());
        }
    }

    private String getPreviousPath(int index, int count)
    {
        if (count > numberOfVertex || index == - 1) { // null: loop or no connection
            return "null";
        }
        else if (index == indexRoot) { // Base case
            return String.valueOf(index) + "";
        }
        else {
            return index + " <- " + getPreviousPath(listVertex[index].getPi(), count+1);
        }
    }
}
