# Heaps of Fun

For this project, you'll be creating a new class called `Heap`. To avoid worrying about memory allocation issues, the base storage will use an `ArrayList`. A skeleton heap implementation is provided. You'll need to fill in the definitions for some of the methods. When you are done, you will write a short test program to try everything out.

## Phase One: Implementation
Some of the method definitions in Heap.java have been left empty. Your job is to fill in the implementations. Note that the comments in the function header tell you what each one should do, and sometimes give you some hints as to how to go about it.

Test your heap thoroughly using a `HeapTest` class.  At minimum we suggest the following:
* Create a heap h, and insert about a dozen numbers, in no particular order. Print out the heap every so often so you can check that it is developing properly.
* Pop a few elements off the heap.  Make sure that you are getting the largest remaining number each time, and that the heap structure is updated properly.

## Phase Two: Heap Sort

For the second part of the project you will implement heap sort using the `bubbleUp` and `bubbleDown` methods defined in class `Heap`.

Since the `heapSort` method is defined as taking in an `ArrayList` of elements, you will find it useful to build a temporary `Heap` object around the existing list.  One way to do this is by defining a private constructor that takes the external `ArrayList` as its argument.  Having done this, you can call all the existing `Heap` methods on it.  At the end, return the original `ArrayList`, whose elements should now be sorted.

When testing, you may find it useful to use the following syntax to create and initialize your array:

    Integer arr[] = {-2,3,9,-7,1,2,6,-3};
    ArrayList<Integer> al = new ArrayList<>(Arrays.asList(arr));
