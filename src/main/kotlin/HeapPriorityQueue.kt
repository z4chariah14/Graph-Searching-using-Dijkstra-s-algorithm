// A MinPriorityQueue implementation that uses a MinHeap as its underlying data structure.
class HeapPriorityQueue<T>: MinPriorityQueue<T> {
    // A private helper class to bundle an element with its associated priority.
    private data class PrioritizedElement<T>(var element: T, var priority: Double) : Comparable<PrioritizedElement<T>> {
        // Implements the Comparable interface to define the sorting order.
        override fun compareTo(other: PrioritizedElement<T>): Int {
            // The comparison is based only on the priority value, which is essential for a priority queue.
            return this.priority.compareTo(other.priority)
        }
    }
    // The internal MinHeap instance that manages the prioritized elements.
    // This is the "engine" of the priority queue.
    private val minHeap = MinHeap<PrioritizedElement<T>>()

    // Checks if the priority queue is empty by checking the internal heap.
    override fun isEmpty(): Boolean {
        return minHeap.isHeapEmpty()
    }

    // Adds an element with a specified priority.
    override fun addWithPriority(elem: T, priority: Double) {
        // Bundles the element and its priority into a single object.
        val newPackage = PrioritizedElement(elem, priority)
        // Adds the bundled object to the heap, which will sort it automatically.
        minHeap.add(newPackage)
    }

    // Removes and returns the element with the lowest priority.
    override fun next(): T? {
        // Retrieves and removes the root of the heap (the PrioritizedElement with the lowest priority).
        // The safe-call operator `?.` is used to access the `.element` property.
        // If the heap is empty and removeMin() returns null, this entire expression will return null.
        return minHeap.removeMin()?.element
    }

    // Adjusts the priority of an element using the "lazy" approach.
    override fun adjustPriority(elem: T, newPriority: Double) {
        // Instead of searching for and updating an existing element, this method
        // simply adds a new element with the updated priority. The old element
        // remains, but the new, lower-priority one will be returned first.
        val replacePackage = PrioritizedElement(elem, newPriority)
        minHeap.add(replacePackage)
    }
}
