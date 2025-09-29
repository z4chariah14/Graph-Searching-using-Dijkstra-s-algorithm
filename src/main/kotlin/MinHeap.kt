class MinHeap<T : Comparable<T>> {
    // The internal list that stores the heap's elements.
    private var tree: MutableList<T> = mutableListOf()

    // Moves an element up the heap to its correct position.
    private fun siftUp(index: Int){
        var currentIndex = index
        var parentNode = (currentIndex - 1) / 2
        // Loop continues as long as the element is not the root and is smaller than its parent.
        while (currentIndex > 0 && tree[currentIndex] < tree[parentNode]){
            swap(currentIndex, parentNode)
            // Update indices to continue moving up the tree.
            currentIndex = parentNode
            parentNode = (currentIndex - 1) / 2
        }
    }

    // Moves an element down the heap to its correct position.
    private fun siftDown(index: Int){
        var currentIndex = index

        // Loop continues until the element is in a valid position.
        while(true){
            var leftChildNode = (currentIndex * 2) + 1
            // If there is no left child, the element is a leaf, so stop.
            if (leftChildNode >= tree.count()){
                break
            }
            var rightChildNode = (currentIndex * 2) + 2
            var smallestChildIndex = leftChildNode
            // Check if a right child exists and is smaller than the left child.
            if (rightChildNode < tree.count() && tree[rightChildNode] < tree[leftChildNode]) {
                smallestChildIndex = rightChildNode
            }
            // If the current element is smaller than its smallest child, it's in the correct spot.
            if (tree[currentIndex] <= tree[smallestChildIndex]) {
                break
            }

            // Swap with the smallest child and update the index to continue sifting down.
            swap(currentIndex, smallestChildIndex)
            currentIndex = smallestChildIndex
        }
    }

    // Swaps two elements in the tree at the given indices.
    private fun swap(node: Int, node2: Int){
        val temp = tree[node]
        tree[node] = tree[node2]
        tree[node2] = temp
    }

    // Adds a new element to the heap.
    fun add(node: T) {
        // Add the element to the end of the list to maintain the complete tree structure.
        tree.add(node)
        // Restore the heap property by sifting the new element up.
        siftUp(tree.count() - 1)
    }

    // Removes and returns the minimum element from the heap.
    fun removeMin(): T? {
        if (tree.isEmpty()) return null
        // Handle the case where there is only one element.
        if (tree.size == 1) {
            return tree.removeAt(0)
        }
        // Save the minimum value to return it later.
        val minVal = tree[0]
        // Move the last element to the root to fill the gap.
        tree[0] = tree.removeAt(tree.size - 1)

        // Restore the heap property by sifting the new root down.
        if(!tree.isEmpty()){
            siftDown(0)
        }
        return minVal
    }

    // Checks if the heap is empty.
    fun isHeapEmpty(): Boolean{
        return tree.isEmpty()
    }
}

// 2i +1 left
//2i + 2 right
//parent (i-1)/2
//ADD
//[0,3,5,8,9] - when adding a new element we also add to the end. so the add operation will be fine .add()
// when adding we check if parent(i-1)/2 is smaller, switching using indexing -  while loop to iterate
