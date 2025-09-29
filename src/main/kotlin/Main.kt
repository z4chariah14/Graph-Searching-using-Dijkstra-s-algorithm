package org.example

import HeapPriorityQueue

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // Function to find the shortest path in a weighted graph using Dijkstra's algorithm.
    fun <VertexType> dijkstra(graph: Graph<VertexType>, start: VertexType, end: VertexType): List<VertexType>? {
        // 1. INITIALIZATION

        // The "distances" map stores the shortest distance found so far from the start to every other vertex.
        val distances: MutableMap<VertexType, Double> = mutableMapOf()
        // Initialize all distances to infinity, as no paths are known yet.
        for (i in graph.getVertices()) {
            distances.put(i, Double.POSITIVE_INFINITY)
        }
        // The distance from the start vertex to itself is always 0.
        distances[start] = 0.0

        // The "previous" map stores the path. For a given vertex, it stores the vertex that came before it on the shortest path.
        val previous: MutableMap<VertexType, VertexType> = mutableMapOf()
        // The priority queue holds the next vertices to visit, always ordered by the shortest known distance.
        val priorityQueue = HeapPriorityQueue<VertexType>()
        // Start the algorithm by adding the start vertex to the queue with a priority of 0.
        priorityQueue.addWithPriority(start, 0.0)

        // 2. MAIN LOOP

        // The loop continues as long as there are still promising vertices to visit.
        while (!priorityQueue.isEmpty()) {
            // Get the vertex with the lowest distance from the queue. If null, continue.
            val source = priorityQueue.next() ?: continue
            // Get all neighbors of the current vertex from the graph.
            val nextNodes = graph.getEdges(source)

            // This is the "relaxation" step.
            for ((nextNode, priority) in nextNodes) {
                // Calculate the potential new distance to this neighbor through the current source vertex.
                val oldDistance = distances[source]!!
                val newDistance = oldDistance + priority

                // Check if the new path is shorter than any previously found path.
                distances[nextNode]?.let {
                    if (newDistance < it) {
                        // If a shorter path is found, update the data structures.
                        distances[nextNode] = newDistance
                        previous[nextNode] = source
                        // Add the neighbor to the queue with its new, shorter total distance as the priority.
                        priorityQueue.addWithPriority(nextNode, newDistance)
                    }
                }
            }
        }

        // 3. PATH RECONSTRUCTION

        // After the loop, if the distance to the end is still infinity, no path was found.
        if (distances[end] == Double.POSITIVE_INFINITY) {
            return null
        } else {
            // If a path was found, build it by tracing backwards from the end node using the "previous" map.
            val path: MutableList<VertexType> = mutableListOf()
            var element: VertexType? = end
            while (element != null) {
                path.add(element)
                element = previous[element]
            }

            // The path is built in reverse order (end to start), so it must be reversed before returning.
            return path.reversed()
        }
    }

// Implementation
    // The vertices will be Strings (city names).
    val travelMap = AdjacencyListGraph<String>()

    // ## 2. Add Data (Cities and Routes) to the Graph
    // We call the addEdge method to build our map of flight times.
    travelMap.addEdge(from = "Boston", to = "New York", cost = 4.0)
    travelMap.addEdge(from = "Boston", to = "Chicago", cost = 12.0)
    travelMap.addEdge(from = "New York", to = "Chicago", cost = 8.0)
    travelMap.addEdge(from = "New York", to = "Denver", cost = 15.0)
    travelMap.addEdge(from = "Chicago", to = "Denver", cost = 10.0)
    travelMap.addEdge(from = "Denver", to = "San Francisco", cost = 18.0)

    // ## 3. Define the Start and End Points
    val startCity = "Boston"
    val endCity = "San Francisco"

    // ## 4. Call Dijkstra's Algorithm
    // We pass the graph we just built and our desired start/end points
    // to the algorithm to get the result.
    println("Calculating fastest route from $startCity to $endCity...")
    val shortestPath = dijkstra(graph = travelMap, start = startCity, end = endCity)

    // ## 5. Print the Result
    // We check if the path is null (unreachable) or if we have a valid path.
    if (shortestPath != null) {
        println("Fastest route found!")
        println(shortestPath.joinToString(separator = " -> "))
    } else {
        println("No path found from $startCity to $endCity.")
    }

}