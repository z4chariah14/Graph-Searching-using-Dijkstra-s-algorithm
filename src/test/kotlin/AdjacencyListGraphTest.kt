import org.example.AdjacencyListGraph
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AdjacencyListGraphTest {
    @Test
    fun getVertices() {
        val graph = AdjacencyListGraph<Char>()

        // Add multiple edges, ones that share vertices.
        graph.addEdge('A', 'B', 1.0)
        graph.addEdge('A', 'C', 2.0)
        graph.addEdge('C', 'D', 3.0)

        // Verify that the returned set contains all unique vertices.
        val expectedVertices = setOf('A', 'B', 'C', 'D')
        assertEquals(4, graph.getVertices().size)
        assertEquals(expectedVertices, graph.getVertices())
    }

    @Test
    fun addEdge() {
        val graph = AdjacencyListGraph<String>()

        // simple edge to the graph.
        graph.addEdge(from = "A", to = "B", cost = 5.0)

        // The graph should now contain both "A" and "B".
        val expectedVertices = setOf("A", "B")
        assertEquals(expectedVertices, graph.getVertices())

        // The edges for "A" should map to "B" with a cost of 5.0.
        val expectedEdges = mapOf("B" to 5.0)
        assertEquals(expectedEdges, graph.getEdges("A"))

        // "B" should have no outgoing edges.
        assertTrue(graph.getEdges("B").isEmpty())
    }

    @Test
    fun getEdges() {
        val graph = AdjacencyListGraph<String>()

        // Add multiple outgoing edges from the same vertex.
        graph.addEdge("New York", "Chicago", 8.0)
        graph.addEdge("New York", "Boston", 4.0)
        graph.addEdge("New York", "Denver", 15.0)

        // returned map contains all correct neighbors and costs.
        val expectedEdges = mapOf(
            "Chicago" to 8.0,
            "Boston" to 4.0,
            "Denver" to 15.0
        )
        assertEquals(expectedEdges, graph.getEdges("New York"))
    }

    @Test
    fun clear() {
        val graph = AdjacencyListGraph<String>()
        graph.addEdge("A", "B", 1.0)
        graph.addEdge("B", "C", 2.0)

        //Call the clear method.
        graph.clear()

        //Verify the graph is now empty.
        assertEquals(0, graph.getVertices().size)
        assertTrue(graph.getVertices().isEmpty())
    }

}