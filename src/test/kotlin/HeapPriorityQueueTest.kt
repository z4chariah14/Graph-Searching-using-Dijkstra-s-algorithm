import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HeapPriorityQueueTest {
    @Test
    fun isEmpty() {
        val pq = HeapPriorityQueue<String>()

        // The new queue should be empty.
        assertTrue(pq.isEmpty())

        // Add an element.
        pq.addWithPriority("A", 1.0)

        //The queue should no longer be empty.
        assertFalse(pq.isEmpty())

        //Remove the element.
        pq.next()

        // The queue should be empty again.
        assertTrue(pq.isEmpty())
    }

    @Test
    fun addWithPriority_and_next_returnsInCorrectOrder() {
        // a new priority queue.
        val pq = HeapPriorityQueue<String>()

        //Add several elements with priorities in a jumbled order.
        pq.addWithPriority("Medium Priority Task", 5.0)
        pq.addWithPriority("Highest Priority Task", 1.0)
        pq.addWithPriority("Lowest Priority Task", 10.0)

        //Check that calling next() repeatedly returns elements
        // in the order of their priority (1.0 -> 5.0 -> 10.0).
        assertEquals("Highest Priority Task", pq.next())
        assertEquals("Medium Priority Task", pq.next())
        assertEquals("Lowest Priority Task", pq.next())

        // The queue should be empty after all items are removed.
        assertNull(pq.next())
        assertTrue(pq.isEmpty())
    }

    @Test
    fun adjustPriority() {
        // Create a queue and add two elements.
        val pq = HeapPriorityQueue<String>()
        pq.addWithPriority("Low Priority", 10.0)
        pq.addWithPriority("Medium Priority", 5.0)

        //Adjust the priority of the "Low Priority" task to be the new highest priority.
        pq.adjustPriority("Low Priority", 2.0)

        // "Low Priority" should now be the first item returned by next(), followed by "Medium Priority".
        assertEquals("Low Priority", pq.next())
        assertEquals("Medium Priority", pq.next())
    }

}