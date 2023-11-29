
  /** Nested class to keep track of nodes */
  public class NodeDL<T> {
      /** The data at the node */
      private T data;
      
      /** Link to the previous node */
      private NodeDL<T> previous;

      /** Link to the next node */
      private NodeDL<T> next;

      /** A constructor */
      NodeDL(T data, NodeDL<T> previous, NodeDL<T> next) {
          this.data = data;
          this.next = next;
          this.previous = previous;
      }

      /** @return data field */
      public T getData() {
        return data;
      }

      /** @param d new data vaue */
      public void setData(T d) {
        this.data = d;
      }

      /** @return next node */
      public NodeDL<T> getPrevious() {
        return previous;
      }

      /** @param d new next node */
      public void setPrevious(NodeDL<T> previous) {
        this.previous = previous;
      }
      
      /** @return next node */
      public NodeDL<T> getNext() {
        return next;
      }

      /** @param d new next node */
      public void setNext(NodeDL<T> next) {
        this.next = next;
      }
    }