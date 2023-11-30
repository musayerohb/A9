import java.util.ArrayList;
import java.util.Arrays;

public class HeapTest {
    public static void main(String[] args) {
        Heap<Integer> h = new Heap<Integer>();
        h.insert(38);
        h.insert(24);
        h.insert(22);
        h.insert(3);
        h.insert(15);
        
        System.out.println("----");
        h.print();

        h.insert(5);
        h.insert(12);
        h.insert(1);
        h.insert(2);
        h.insert(7);
        
        System.out.println("----");
        h.print();
        
        h.insert(1);
        h.insert(3);
        h.insert(4);
        System.out.println("Finished making heap.");
        System.out.println("\nStart poptop, showing original heap below!");
        h.print();
        System.out.println("\npop top.");
        System.out.println("----");
        System.out.println(h.popTop());
        h.print();
        System.out.println("----");
        System.out.println(h.popTop());
        h.print();
        System.out.println("----");
        System.out.println(h.popTop());
        h.print();

        System.out.println("\ntesting heapSort.");
        Integer arr[] = {-2,3,9,-7,1,2,6,-3};
        ArrayList<Integer> al = new ArrayList<>(Arrays.asList(arr));
        Heap.heapSort(al);
    }
}
