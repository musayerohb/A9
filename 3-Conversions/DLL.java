/**
 *  Class to implement a singly linked list
 *
 *  @author Anonymous
 *  @version Fall 2021
 */
public class DLL<T> {
  /** The head node of the list */
  private NodeDL<T> head;

  /** The tail node of the list */
  private NodeDL<T> tail;

  /** Constructor for an empty list */
  public DLL() {
      head = tail = null;
  }

  /** Copy constructor makes a deep copy of the list */
  public DLL(DLL<T> orig) {
    if (orig.head == null) {
      // handle special case of an empty list
      head = tail = null;
    } else {
      // make copies of each node in list
      NodeDL<T> pos = orig.head;
      tail = head = new NodeDL<T>(pos.getData(),null,null);
      pos = pos.getNext();
      while (pos != null) {
        this.addLast(pos.getData());
        //tail.setNext(new NodeDL<T>(pos.getData(),null,null));
        //tail = tail.getNext();
        pos = pos.getNext();
      }            
    }
  }

  /** 
   *  Accessor for head node
   *  @return the head node
   */
  public NodeDL<T> getHead() {
    return head;
  }
  
  /** 
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeDL<T> getTail() {
    return tail;
  }

  /** 
   *  Determines whether a list is empty
   *  @return T/F is the list empty?
   */
  public boolean isEmpty() {
    return (head==null);
  }

  /** 
   *  Inserts the given item at the head of the list
   *  @param v item to insert 
   */
  public void addFirst(T v) {
    head = new NodeDL<T>(v,null,head);
    if (tail==null) {
      tail = head;
    } else {
      head.getNext().setPrevious(head);
    }
  }

  /** 
   *  Inserts the given item at the tail of the list
   *  @param item to insert 
   */
  public void addLast(T v) {
    if (tail==null) {
      tail = head = new NodeDL<T>(v,null,null);
    } else {
      tail.setNext(new NodeDL<T>(v,tail,null));
      tail = tail.getNext();
    }
  }

  /** 
   *  Inserts the given item after the specified node
   *  @param here node to insert after
   *  @param v item to insert 
   */
  public void addAfter(NodeDL<T> here, T v) {
    if (here == null) {
      // null means put at the head
      addFirst(v);
    } else if (here==tail) {
      addLast(v);
    } else {
      here.setNext(new NodeDL<T>(v,here,here.getNext()));
      here.getNext().getNext().setPrevious(here.getNext());
    }
  }

  /** 
   *  Removes the given item from the head of the list
   *  @return v item removed
   */
  public T removeFirst() {
    T result = null;
    if (head==null) {
      throw new RuntimeException("Missing element");
    } else {
      result = head.getData();
      head = head.getNext();
      if (head == null) {
        tail = null;
      }
    }
    return result;
  }

  /** 
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast() {
    T result = null;
    if (tail==null) {
      // list is empty
      throw new RuntimeException("Missing element");
    } else if (tail == head) {
      // list has one element
      result = tail.getData();
      head = tail = null;
    } else {
      result = tail.getData();
      NodeDL<T> item = head;
      while (item.getNext() != tail) {
        item = item.getNext();
      }
      tail = item;
      tail.setNext(null);
    }
    return result;
  }

  /** 
   *  Removes the node specified
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T remove(NodeDL<T> evicted) {
    T result = evicted.getData();
    if (head == evicted) {
      removeFirst();
    } else if (tail == evicted) {
      removeLast();
    } else {
      evicted.getPrevious().setNext(evicted.getNext());
      evicted.getNext().setPrevious(evicted.getPrevious());
    }
    return result;
  }

  /**
   *  Returns a count of the number of elements in the list
   *  @return current number of nodes
   */
  public int size() {
    int count = 0;
    if (head != null) {
      NodeDL<T> pos = head;
      while ((pos!=null)&&(pos!=tail)) {
        count++;
        pos = pos.getNext();
      }
      if (pos==tail) {
        count++;
      }
    }
    return count;
  }

  /** 
   *  Makes a copy of elements from the original list
   *  @param here  starting point of copy
   *  @param n  number of items to copy
   *  @return the copied list
   */
  public DLL<T> subseqByCopy(NodeDL<T> here, int n) {
    DLL<T> result = new DLL<>();
    NodeDL<T> pos = here;
    for (int i = 0; i < n; i++) {
      if (pos == null) {
        throw new RuntimeException("Missing element");
      } 
      result.addLast(pos.getData());
      pos = pos.getNext();
    }
    return result;
  }

  /**
   *  Places copy of the provided list into this after the specified node.
   *  @param list  the list to splice in a copy of
   *  @param afterHere  marks the position in this where the new list should go
   */
  public void spliceByCopy(DLL<T> list, NodeDL<T> afterHere) {
    if (list == this) {
      throw new RuntimeException("Can't insert list into itself");
    }
    NodeDL<T> thisNode = afterHere;  // our position in this
    NodeDL<T> thatNode = list.head;  // our position in list
    if ((afterHere == null)&&(thatNode != null)) {
      // null indicates prepend; handle first node specially
      addFirst(thatNode.getData());
      thisNode = head;
      thatNode = thatNode.getNext();
    }
    while ((thatNode != null)&&(thatNode != list.tail)) {
      addAfter(thisNode,thatNode.getData());
      thisNode = thisNode.getNext();
      thatNode = thatNode.getNext();
    }
    if (thatNode == list.tail) {
      // we stopped at the tail, so copy it also
      addAfter(thisNode,thatNode.getData());
    }
  }

  /** 
   *  Extracts a subsequence of nodes and returns them as a new list
   *  @param fromHere  marks the first node to extract
   *  @param toHere  marks the node where the extraction ends
   *  @return  the new list
   */
  public DLL<T> subseqByTransfer(NodeDL<T> fromHere, NodeDL<T> toHere) {
    DLL<T> result = new DLL<>();
    result.head = fromHere;
    result.tail = toHere;
    if (fromHere == head) {
      head = toHere.getNext();
    } else {
      fromHere.getPrevious().setNext(toHere.getNext());
    }
    if (toHere == tail) {
      tail = fromHere.getPrevious();
    } else {
      toHere.getNext().setPrevious(fromHere.getPrevious());
    }
    result.head.setPrevious(null);
    result.tail.setNext(null);
    return result;
  }

  /** 
   *  Takes the provided list and inserts its elements into this
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
  public void spliceByTransfer(DLL<T> list, NodeDL<T> afterHere) {
    if (list == this) {
      throw new RuntimeException("Can't insert list into itself");
    }
    if (!list.isEmpty()) {
      if (afterHere == null) {
        // splicing at front of this
        list.tail.setNext(head);
        head.setPrevious(list.tail);
        head = list.head;
        if (tail == null) {
          // this was previously empty
          tail = list.tail;
        }
      } else {
        list.tail.setNext(afterHere.getNext());
        if (afterHere == tail) {
          tail = list.tail;
        } else {
          afterHere.getNext().setPrevious(list.tail);
        }
        afterHere.setNext(list.head);
        list.head.setPrevious(afterHere);
      }
      // empty the list we inserted from:
      list.head = list.tail = null;
    }
  }

  public void check() {
    if ((head==null)&&(tail!=null)) {
      System.err.println("Null head without tail.");
    } else if ((head!=null)&&(tail==null)) {
      System.err.println("Null tail without head.");
    } else {
      NodeDL<T> pos;
      for  (pos = head; (pos != null) && (pos != tail); pos = pos.getNext());
      if ((head != null)&&(pos==null)) {
        System.err.println("Couldn't reach tail.");
      }
      if ((tail!=null)&&(tail.getNext()!=null)) {
        System.err.println("Tail has next.");
      }
    }
  }

  /** Appends a list as a shallow copy */
  public void appendShallow(DLL<T> suffix) {
      if (tail != null) {
          tail.setNext(suffix.head);
          tail = suffix.tail;
      } else {  // special case: this list is empty
          this.head = suffix.head;
          this.tail = suffix.tail;
      }
  }

  /** Appends a list by transferring elements */
  public void appendTransfer(DLL<T> suffix) {
      if (tail != null) {
          tail.setNext(suffix.head);
          tail = suffix.tail;
      } else {  // special case: this list is empty
          this.head = suffix.head;
          this.tail = suffix.tail;
      }
      suffix.head = suffix.tail = null;  // transfer    
  }

  /** Appends a list by copying elements */
  public void appendCopy(DLL<T> suffix) {
      if (suffix.head != null) {  // do nothing if suffix empty
          if (head == null) {  // copy first element
              head = tail = new NodeDL<T>(suffix.head.getData(),null,null);
          } else {
              tail.setNext(new NodeDL<T>(suffix.head.getData(),tail,null));
              tail = tail.getNext();
          }
          // make copies of remaining nodes
          NodeDL<T> pos = suffix.head;
          for (pos = pos.getNext(); pos != null; pos = pos.getNext()) {
              tail.setNext(new NodeDL<T>(pos.getData(),tail,null));
              tail = tail.getNext();
          }            
      }
  }

  /** Inserts a new node with the given element */
  public void insertAfter(NodeDL<T> pos, T c) {
      pos.setNext(new NodeDL<T>(c,pos,pos.getNext()));
      if (tail == pos) {  // special case
          tail = pos.getNext();
      } else {
        pos.getNext().getNext().setPrevious(pos.getNext());
      }
  }
  
  /** Converts to a string representation */
  public String toString() {
    String result = "[";
    if (isEmpty()) {
      result += "]";  
    } else {
      NodeDL<T> item;
      for (item = this.head; item != tail; item = item.getNext()) {
        result += item.getData() + ", ";
      }
      result += item.getData() + "]";
    }
    return result;
  }

  /** Keeps track of position in a sequence */
  public class ListIterator {
      /** Position is on either side of this node */
      private NodeDL<T> pos;

      /** Are we on the left or the right? */
      private boolean onLeft;

      /** Default position is leftmost */
      public ListIterator(DLL<T> list) {
          pos = list.head;
          onLeft = true;
      }

      /** Tests whether there are any more */
      public boolean hasNext() {
          return ((pos!=null) && (onLeft || (pos.getNext()!=null)));
      }

      /** Returns next or throws an exception */
      public T next() {
          if (!hasNext()) {
              throw new RuntimeException("Missing element");
          } else if (onLeft) {
              onLeft = false;
          } else {
              pos = pos.getNext();
          }
          return pos.getData();
      }
  }

  /** Keeps track of position in a sequence */
  public class ArrayIterator {
      /** Position is to left of this node */
      private int index;

      /** Need to keep pointer to array */
      private T[] arr;

      /** Default position is leftmost */
      public ArrayIterator(T[] arr) {
          index = 0;
          this.arr = arr;
      }

      /** Tests whether there are any more */
      public boolean hasNext() {
          return (index<arr.length);
      }

      /** Returns next or throws an exception */
      public T next() {
          if (!hasNext()) {
              throw new RuntimeException("Missing element");
          } else {
              return arr[index++];
          }
      }
  }  
}