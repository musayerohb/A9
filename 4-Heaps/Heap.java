import java.util.*;

/**
 *  Implements a heap data structure, using ArrayList for storage.
 *
 *  @author Nicholas R. Howe
 *  @version CSC 112, 20 November 2006
 */
public class Heap<E extends Comparable<E>> {
    /** Elements of the heap are stored in a ArrayList
     * @attribute storage (ArrayList<E>) The ArrayList storing the heaps
     */ 
    private ArrayList<E> storage;

    /** Default constructor creates an empty heap */
    public Heap() {
        storage = new ArrayList<E>();
    }
    
    /** Copy constructor creates an empty heap for heapsort method
     * @param array (ArrayList<E>) The array being made into a heap
    */
    private Heap(ArrayList<E> array) {
        storage = array;
    }
    /** Gets the size of the heap
    /** @return  heap size */
    public int size() {
        return storage.size();
    }
    /** Gets the largest element in the heap
     * @return largest element in heap */
    public E peekTop() {
        return storage.get(0);
    }

    /** Gets the index of the parent
     * @param pos (int) the parent
     * @return index of parent */ 
    private static int parent(int pos) {
        //FILL IN
        int parentpos = (pos - 1)/2;
        return parentpos;
    }

    /** Gets the index of the left child
     * @param pos (int) The left child
     * @return index of left child */
    private static int leftChild(int pos) {
        //FILL IN
        int leftChild = (2 * pos) + 1;
        return leftChild;
    }

    /** Gets the index of the right child
     * @param pos (int) The right child
     * @return index of right child */
    private static int rightChild(int pos) {
        //FILL IN
        int rightChild = (2*pos) + 2;
        return rightChild;
    }

    /** Checks if the left child exists in the binary tree or not
     * @param pos (int) The left child
     * @return T/F does left child exist in tree? */
    private boolean hasLeftChild(int pos) {
        return (leftChild(pos)<size());
    }

    /** Checks if the right child exists in the binary tree or not

     * @param pos The right child
     * @return T/F does right child exist in tree? */
    private boolean hasRightChild(int pos) {
        return (rightChild(pos)<size());
    }

    /** 
     *  Swaps an element upwards
     * @param pos  Position of element to swap upwards
     */
    private void swapWithParent(int pos) {
        E tmp = storage.get(parent(pos));
        storage.set(parent(pos),storage.get(pos));
        storage.set(pos,tmp);
    }

    /** 
     *  Swaps an element downwards to the right
     * @param pos  Position of element to swap 
     */
    private void swapWithRightChild(int pos) {
        E tmp = storage.get(rightChild(pos));
        storage.set(rightChild(pos),storage.get(pos));
        storage.set(pos,tmp);
    }

    /** 
     *  Swaps an element downwards to the left
     * @param pos  Position of element to swap 
     */
    private void swapWithLeftChild(int pos) {
        E tmp = storage.get(leftChild(pos));
        storage.set(leftChild(pos),storage.get(pos));
        storage.set(pos,tmp);
    }

    /**
     *  Compares to elements in the heap
     *  @return  true iff the first is bigger than the second
     */
    private boolean isBigger(int pos1, int pos2) {
        return storage.get(pos1).compareTo(storage.get(pos2)) > 0;
    }

    /** 
     *  Bubbles an item down toward the larger of its two children, if any.
     *  It starts at the root (position 0), and follows the item as it sinks.
     *  At each point it should perform several comparisons to determine
     *  what swap is necessary.
     *
     *  First, if the current item has a right child, and that right
     *  child is larger than the current item and its left child, 
     *  then swap the current item with its right child.
     *
     *  Otherwise, if the current item has a left child, and that left
     *  child is larger than the current item, 
     *  then swap the current item with its left child.
     *
     *  Otherwise, don't swap anything.  You are done.
     *
     *  If the current position is swapped with either child, continue
     *  the process with the child position.  
     */
    private void bubbleDown() {
        int pos = 0;
        boolean done = false;

        while (!done) {
            // FILL IN
            if (hasRightChild(pos) && isBigger(rightChild(pos), pos) && isBigger(rightChild(pos), leftChild(pos))) {
                swapWithRightChild(pos);
                pos = rightChild(pos);
            }
            else if (hasLeftChild(pos) && isBigger(leftChild(pos), pos)) {
                swapWithLeftChild(pos);
                pos = leftChild(pos);
            }
            else {
                done = true;
            }
            
        }
    }

    /**Overloaded bubbleDown method
     * @param validHeapPortion (int) The portion of the heap being bubbled down
    */
    public void bubbleDown(int validHeapPortion) {
        int pos = 0;
        boolean done = false;

        while (!done) {
        // FILL IN
        if (rightChild(pos) < validHeapPortion && isBigger(rightChild(pos), pos) && isBigger(rightChild(pos), leftChild(pos))) {
            swapWithRightChild(pos);
            pos = rightChild(pos);
        }
        else if (leftChild(pos) < validHeapPortion && isBigger(leftChild(pos), pos)) {
            swapWithLeftChild(pos);
            pos = leftChild(pos);
        }
        else {
            done = true;
        }
        
    }
    }

    /** 
     *  Pops largest element off heap and returns it.
     *
     *  The last element in the heap is copied to the root, 
     *  and removed from its position at the end.  Then it is bubbbled down.
     *  Finally, return the value of the original root
     *
     *  @return the former root element
     */
    public E popTop() {
        // FILL IN
        E top = peekTop();


        E last = storage.get(storage.size() - 1);
        storage.remove(storage.size() - 1);
        
        storage.set(0, last);

        bubbleDown();
        
        return top;
    }

    /**
     *  Bubbles an item up until it reaches equilibrium.
     *
     *  As long as the current item is greater than its parent, swap upwards.
     *
     *  @param pos  The position to work with
     */
    private void bubbleUp(int pos) {
        // FILL IN
        boolean done = false;

        while (!done) {

            if (isBigger(pos, parent(pos))) {
                int pos2 = parent(pos);
                swapWithParent(pos);
                pos = pos2;
                bubbleUp(pos);
            }
            
            else {
                done = true;
            }

        }
    }

    /**
     *  Inserts a new item and re-heapifies
     *
     *  Add the item at the end of the heap, and bubble it up.
     *
     *  @param item  The item to insert
     */
    public void insert(E item) {
        // FILL IN
        this.storage.add(item);
        for (int i = 0; i < this.storage.size(); i++) {
            this.bubbleUp(i);
        }
    }

    /**
     * Swaps the root of a given heap with the bottom-most element.
     * @param size The index of the heap's bottom-most element, while doing in-place heapsort.
     */
    public void swap(int size) {
        E top = peekTop();

        E last = this.storage.get(size - 1);
        
        this.storage.set(0, last);
        this.storage.set(size - 1, top);
    }
        
    
    /**
     *  Sort an array list in place
     *  @param array list to sort
     */
    public static <T extends Comparable<T>> void heapSort(ArrayList<T> v) {
        // FILL IN
        Heap heap = new Heap(v);
        
        //Heapifies the heap object (Phase 1 of heapSort)
        for (int i = 0; i < heap.storage.size(); i++) {
            heap.bubbleUp(i);
        }

        //Phase 2 of heapSort
        int validHeapPortion = heap.storage.size();

        while (validHeapPortion > 0) {
            heap.swap(validHeapPortion);
            validHeapPortion--;
            heap.bubbleDown(validHeapPortion);
        }
        
        System.out.println(heap.storage.toString());

    }

    /** Prints heap for debugging */
    public void print() {
        int j = 1, k = 0;
        System.out.println("Heap contents:");
        for (E item : storage) {
            System.out.print(item+" ");
            k++;
            if (k >= j) {
                j = j << 1;
                k = 0;
                System.out.println("");
            }
        }
        System.out.println("");
    }

}