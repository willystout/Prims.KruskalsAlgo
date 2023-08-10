package main.java.mstAlgorithms;

import graph.Edge;
import graph.Graph;
import sets.DisjointSets;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm{

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
    }


    /**
     * Compute minimum spanning tree for this graph.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * Should use Kruskal's algorithm and DisjointSets class.
     */
    @Override
    public void computeMST() {
        DisjointSets sets = new DisjointSets();
        sets.createSets(numNodes());
        ArrayList<Edge> edgeArray = new ArrayList<>();
        Edge current;
        int i;
        for(i = 0; i < numNodes(); i++){
            for(current = getFirstEdge(i); current != null; current = current.next()){
                edgeArray.add(current);
            }
        }
        Collections.sort(edgeArray); // Sorts the edge array using my compareTo method
        for(i = 0; i < edgeArray.size(); i++){
            Edge newEdge = edgeArray.get(i);
            int v1 = newEdge.getId1();
            int v2 = newEdge.getId2();
            int u1 = sets.find(v1);
            int u2 = sets.find(v2);
            if(u1 != u2){
                addMSTEdge(newEdge);
                sets.union(u1, u2);
            }
        }
    }
        /*
        Create n sets (where n is the number of vertices) by placing each vertex in its own set
        Sort edges based on the cost (in increasing order)
        For each edge e = (v1, v2) in the list
        Compute the representatives of the sets that v1 and v2 belong to.
        If v1 and v2 belong to the same set:
            Do not add this edge to MST.
        Otherwise:
            Add e to the minimum spanning tree
            Merge sets that v1 and v2 belong to
         */
    }


