/**
 * Implements binary search trees.
 *
 * @author
 * @version
 */
public class BST<E extends Comparable<E>> extends BinaryTree<Comparable<E>> implements BST_Ops<E> {

    /** Leaf constructor
     * @param data (E) The data being taken in for the constructor
    */
    public BST(E data) {
        super(data);
    }

    /** Branch constructor
     * @param data (E) The data being taken in for the constructor
     * @param left (BST<E>) The left branch
     * @param right (BST<E>) The right branch
    */
    public BST(E data, BST<E> left, BST<E> right) {
        super(data, left, right);
    }

    /** Accessor
     * @return The left branch of the binary tree
    */
    public BST<E> getLeft() {
        return (BST<E>) super.getLeft();
    }
    
    /** Accessor
     * @return The right branch of the binary tree
    */
    public BST<E> getRight() {
        return (BST<E>) super.getRight();
    }

    /** Manipulator
     * @param left (BST <E>) Allows changes to the value of the left branch
    */
    public void setLeft(BST<E> left) {
        if (left instanceof BST<E>) {
            // this.left = left;
            super.setLeft(left);
        } else {
            throw new UnsupportedOperationException("Tried to add non-DecisionTree as child");
        }
    }

    /** Manipulator
     * @param right (BST <E>) Allows changes to the value of the right branch
    */
    public void setRight(BST<E> right) {
        if (right instanceof BST<E>) {
            // this.right = right;
            super.setRight(right);
        } else {
            throw new UnsupportedOperationException("Tried to add non-DecisionTree as child");
        }
    }

    /** Determines whether a tree is empty
     * @param node (BST<E>) The node being checked
    */
    public boolean isEmpty(BST<E> node) {
        return (node == null);
    }

    /**
     * Returns the node of the given element, or null if not found
     * @param data The element to search
     * @return the node of the given element, or null if not found
     */
    public BST<E> lookup(E data) {
        System.out.println(this.getData());
        // System.out.println(this.getData().compareTo(data));
        
        if (this.getData() == null) {
            return null;
        }

        if (this.getData().equals(data)) {
            return this;
        }

        if (this.getData().compareTo(data) >= 0) {
            //gives null error here
            return this.getLeft().lookup(data);
        }

        else {
            return this.getRight().lookup(data);
        }

    }

    /**
     *  Inserts a new node into the tree
     *  @param data The element to insert
     */
    public void insert(E data) {
        if (this.getData() == data) {
            return;
        }
        
        if (this.getData().compareTo(data) >= 0) {
            if (this.getLeft() == null) {
                this.setLeft(new BST<E>(data));
            }
            else {
                this.getLeft().insert(data);
            }
        }
        
        else {
            if (this.getRight() == null) {
                this.setRight(new BST<E>(data));
            }
            else {
                this.getRight().insert(data);
            }
        }
        
    }

    /**
     * Deletes the specified element from the tree
     * Returns the modified tree because the root node
     * may have changed
     * 
     * @param evictee The element to delete
     * @return tree as modified
     */
    public BST<E> deleteWithCopyLeft(E evictee) {
        BST<E> tree = this;
        BST<E> nodeToDelete = this.lookup(evictee);
        
        tree = tree.deleteWithCopyLeft(evictee);
        //handle root node separately
        
        //if node to delete is a leaf.
        if (nodeToDelete.isLeaf()) {
            nodeToDelete = this.lookup(evictee);
            nodeToDelete = null;
        }

        //if node to delete has one child.
        else if (nodeToDelete.getLeft() != null || nodeToDelete.getRight() != null) {
            // Promote the child
            BST<E> leftChild = this.lookup(evictee).getLeft();
            //does this make the node that we evicted its child node, or does it just reassign variable values?
            nodeToDelete.setLeft(null);
            nodeToDelete = leftChild;
            // 
        }

        //if node to delete has two children.
        else {
            
        }
        

        //trying to do it recursively?
        if (this.getData().compareTo(evictee) < 0) {
            tree = tree.getLeft();
            nodeToDelete = tree.lookup(evictee);

            if (tree.getLeft().equals(nodeToDelete)) {
                tree.setLeft(null);
            }
            else {
                deleteWithCopyLeft(evictee);
            }  
            //separate lookup so you get parent of evictee node?
        }
        
        if (this.getData().compareTo(evictee) > 0) {
            tree = tree.getRight();
            nodeToDelete = tree.lookup(evictee);

            if (tree.getRight().equals(nodeToDelete)) {
                tree.setRight(null);
            }
            else {
                deleteWithCopyLeft(evictee);
            }
        }

        return tree;
    }

    /**
     * Apply left rotation
     * Returns the modified tree because the root node
     * may have changed
     *
     * @return tree as modified
     */
    public BST<E> rotateLeft() {
        BST<E> tree = null;

        return tree;
    }

    /**
     * Apply right rotation
     * Returns the modified tree because the root node
     * may have changed
     *
     * @return tree as modified
     */
    public BST<E> rotateRight() {
        BST<E> tree = null;

        return tree;

    }

    public static void main(String[] args) {
        BST root = new BST<>(12);
        root.setLeft(new BST<>(8));
        root.setRight(new BST<>(18));

        root.getLeft().setLeft(new BST<>(4));
        root.getLeft().setRight(new BST<>(11));

        root.getRight().setLeft(new BST<>(16));
        root.getRight().setRight(new BST<>(24));

        root.getLeft().getLeft().setLeft(new BST<>(1));
        root.getLeft().getLeft().setRight(new BST<>(6));

        root.getLeft().getRight().setLeft(new BST<>(9));
        root.getLeft().getRight().getLeft().setRight(new BST<>(10));

        root.getRight().getLeft().setLeft(new BST<>(15));
        root.getRight().getLeft().setRight(new BST<>(17));
        
        root.getRight().getRight().setLeft(new BST<>(21));
        root.getRight().getRight().setRight(new BST<>(32));

        root.getRight().getRight().getLeft().setLeft(new BST<>(20));

        //System.out.println(root.getLeft().getData());
        //System.out.println(root.lookup(21));

        root.insert(2);
        System.out.println(root.lookup(2));


        
        

    }
}
