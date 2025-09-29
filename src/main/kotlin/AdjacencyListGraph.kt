package org.example

import kotlin.collections.getOrPut

// An implementation of the Graph interface using an adjacency list.
// The graph is directed and weighted. The <VertexType> makes it generic.
class AdjacencyListGraph<VertexType>: Graph<VertexType>{
    // A set to store all unique vertices present in the graph.
    private var vertices : MutableSet<VertexType> = mutableSetOf()
    // The core adjacency list structure. It's a map where each key is a source vertex,
    // and the value is another map containing destination vertices and the edge costs.
    private var edges: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    // Returns the set of all vertices in the graph.
    override fun getVertices(): Set<VertexType>{
        return vertices
    }

    // Adds a directed edge from one vertex to another with a given cost.
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        // Ensure both vertices are tracked in the main set of vertices.
        vertices.add(from)
        vertices.add(to)

        // Find the inner map for the 'from' vertex, creating it if it doesn't exist.
        // Then, add or update the edge to the 'to' vertex with the specified cost.
        edges.getOrPut(from) { mutableMapOf() }[to] = cost
    }

    // Retrieves all outgoing edges and their costs for a given vertex.
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        // Looks up the 'from' vertex in the outer map.
        // If the vertex is not found (meaning it has no outgoing edges),
        // the elvis operator `?:` returns a new, empty map.
        return edges[from] ?: mutableMapOf()
    }

    // Removes all vertices and edges from the graph, resetting it to an empty state.
    override fun clear(){
        vertices.clear()
        edges.clear()
    }
}