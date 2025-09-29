import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MinHeapTest {
    @Test
    fun add_and_removeMin_maintainsHeapOrder() {
        //Create a MinHeap for integers.
        val heap = MinHeap<Int>()

        // Add several numbers in a non-sorted order.
        heap.add(10)
        heap.add(5)
        heap.add(20)
        heap.add(2)
        heap.add(8)

        // Verify that removeMin() returns the elements in ascending order.
        assertEquals(2, heap.removeMin())
        assertEquals(5, heap.removeMin())
        assertEquals(8, heap.removeMin())
        assertEquals(10, heap.removeMin())
        assertEquals(20, heap.removeMin())

        // After removing all elements, the heap should be empty.
        assertTrue(heap.isHeapEmpty())
    }

    @Test
    fun removeMin_handlesEdgeCases() {
        // Arrange: Create a MinHeap.
        val heap = MinHeap<Int>()

        //Calling removeMin on an empty heap should return null.
        assertNull(heap.removeMin())

        //Add a single element.
        heap.add(42)

        // Check that the single element can be removed correctly.
        assertEquals(42, heap.removeMin())

        //The heap should be empty again.
        assertTrue(heap.isHeapEmpty())

        // Calling removeMin again should return null.
        assertNull(heap.removeMin())
    }

    @Test
    fun isHeapEmpty() {
        val heap = MinHeap<Int>()

        //A new heap should be empty.
        assertTrue(heap.isHeapEmpty())

        // Add an element.
        heap.add(1)

        // The heap should no longer be empty.
        assertFalse(heap.isHeapEmpty())

        // Remove the element.
        heap.removeMin()

        //The heap should be empty again.
        assertTrue(heap.isHeapEmpty())
    }

}