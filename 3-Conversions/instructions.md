# Great Recursive Conversions

For this project you will create two methods that convert to and from a binary search tree.  One will create a balanced BST starting from an array.  The other will relink the nodes of a BST so that they form a linked list with elements following the inorder traversal of the original tree.  (The tree itself is destroyed by this process.)

For grading convenience, both methods will be implemented as static methods in a class named `Conversion`.

## Array to BinaryTree

The `static` method `arrayToBinaryTree` should take the array to be converted as its argument and return a balanced `BinaryTree` with the same elements in the same order (assuming inorder traversal).  Your function will want to call a recursive helper.  This helper will accept a sorted array, plus low and high index numbers.  It will only pay attention to the portion of the array between the low index (included in the range) and the high index (excluded).  With these elements it will build a balanced binary tree by splitting the active portion around its middle element (called the pivot) and recursively calling itself on the subranges remaining on either side of the pivot.

Kudos:  If you complete the BST project, you can create a second conversion method that  returns a `BST` instead of a `BinaryTree`.

## BinaryTree to Linked List

This `static` method will be called `binaryTreeToDLL`.  To make it work, the `DLL` class included here needs to be modified so that it uses `BinaryTree` nodes as its list nodes.  You'll need to replace all the appearances of `NodeDL` with `BinaryTree`, and also substitute `getLeft` and `getRight` for `getPrevious` and `getNext`.  You can remove the `NodeDL` class entirely when this is done. 

The conversion then follows a simple recursive process:  get lists for the left and right subtrees.  Form a new list by sticking them together with the root node in the middle between the two sides, and return the result.  The key thing here is that you should never need to create new nodes, or call methods which do so.  The entire conversion should be carried out just by updating the links between nodes.

You should test both of your methods thoroughly to see that they work.