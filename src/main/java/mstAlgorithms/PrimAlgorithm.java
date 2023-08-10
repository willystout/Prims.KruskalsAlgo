package main.java.mstAlgorithms;

import graph.CityNode;
import graph.Edge;
import graph.Graph;
import org.w3c.dom.Node;

import java.awt.*;
import java.awt.geom.Point2D;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;

    public class TableElement{
        private boolean added;
        private int cost;
        private int path;

        public TableElement(){
            this.added = false;
            this.cost = Integer.MAX_VALUE;
            this.path = -1;
        }
    }



    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * */
    public int findMinNonAddedVertex(TableElement[] table){
        int minCost = Integer.MAX_VALUE;
        int vertextID = -1;
        for(int j = 0; j < numNodes(); j++){
            if(table[j].cost < minCost){
                if(!table[j].added){
                    minCost = table[j].cost;
                    vertextID = j;
                }
            }
        }
        return vertextID;
    }


    @Override
    public void computeMST() {
        TableElement[] table = new TableElement[numNodes()]; // initialize the table
        for(int v = 0; v < numNodes(); v++){
            table[v] = new TableElement();
        }
        table[sourceVertex].cost = -1;
        table[sourceVertex].path = sourceVertex;
        Edge current;
        for(int j = 0; j < numNodes(); j++) {
            int i = findMinNonAddedVertex(table);
            table[i].added = true;
            for(current = getFirstEdge(i); current != null; current = current.next()) {
                if(current.getCost() < table[current.getId2()].cost && !table[current.getId2()].added) {
                    table[current.getId2()].cost = current.getCost();
                    table[current.getId2()].path = current.getId1();
                }
            }
            addMSTEdge(new Edge(table[i].path, i, table[i].cost));
        }

        // graph = adjacency list, how the graph is connected NOT in table
        // v = findMinNonAddedVertex
        // table[v].added = true
        // for each neighbor u of v
            // if u has not been added
                // if table[u].cost > cost of edge from v to u
                    // table[u].cost = cost of edge from v to u
                    // table[u].path = v

    }
}
