package main;

/* METHOD DESCRIPTION
 * -private initializeSingleSource(Graph g): void
 *      initialize graph
 * -private relaxation(Graph g, int indexU, int indexV): void
 *      relaxation
 * -public bellmanFord(Graph g): boolean
 *      bellman-ford algorithm
 * -public dijkstras(Graph g): void
 *      dijkstra's algorithm
 */

import java.util.*;

public class GraphSearch
{
    private void initializeSingleSource(Graph g)
    {
        for (int i=0; i<g.getNumberOfVertex(); i++) {
            g.getListVertex()[i].setD(Integer.MAX_VALUE);
            g.getListVertex()[i].setPi(-1);
        }
        g.getListVertex()[g.getIndexRoot() ].setD(0);
    }

    private void relaxation(Graph g, int indexU, int indexV)
    {
        Vertex u = g.getListVertex()[indexU];
        Vertex v = g.getListVertex()[indexV];

        if (u.getD() == Integer.MAX_VALUE) {
            return;
        }
        else if (v.getD() == Integer.MAX_VALUE) {
            v.setD(u.getD() + g.getEdgeWeight(u.getIndex(), v.getIndex()) );
            v.setPi(u.getIndex() );
        }
        else if (v.getD() > u.getD() +
                g.getEdgeWeight(u.getIndex(), v.getIndex()))
        {
            v.setD(u.getD() + g.getEdgeWeight(u.getIndex(), v.getIndex()) );
            v.setPi(u.getIndex() );
        }
    }

    public boolean bellmanFord(Graph g)
    {
        initializeSingleSource(g);

        /* Relaxation */
        int gNumVertex = g.getNumberOfVertex();

        for (int i=0; i<gNumVertex-1; i++) { // relax all edge V-1 times
            for (int j=0; j<gNumVertex; j++) { // for each source
                for (Edge e: g.getListEdge()[j]) { // for each destination
                    relaxation(g, e.getIndexSource(), e.getIndexDestination());
                }
            }
        }

        /* check for -ve weight cycle */
        Vertex src; Vertex dest;

        for (int i=0; i<gNumVertex; i++) { // for each source
            for (Edge e: g.getListEdge()[i]) { // for each destination
                src = g.getListVertex()[e.getIndexSource()];
                dest = g.getListVertex()[e.getIndexDestination()];
                if (dest.getD() > src.getD() +
                        g.getEdgeWeight(src.getIndex(), dest.getIndex()))
                {
                    return false; // -ve weight cycle found
                }
            }
        }
        return true; // no -ve weight cycle found
    }

    public void dijkstras(Graph g)
    {
        initializeSingleSource(g);

        ArrayList<Vertex> s = new ArrayList<Vertex>();
        PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(g.getNumberOfVertex());

        for (int i=0; i<g.getNumberOfVertex(); i++) { // add all V to q
            q.add(g.getListVertex()[i] );
        }

        Vertex u = q.poll(); // pick lowest V
        while (u != null) { // while q not empty
            s.add(u);
            for (Edge e: g.getListEdge()[u.getIndex()] ) { // relax all adjacent edge
                relaxation(g, e.getIndexSource(), e.getIndexDestination());
            }
            u = q.poll(); // pick lowest V
        }
    }
}
