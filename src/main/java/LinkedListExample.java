import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("!");
        list.add("World");
        list.add("Hello");
        // acts like a Queue
        for (String s: list) {
            System.out.println(s);
        }
        // acts like a stack
        while (!list.isEmpty()){
            System.out.println(list.removeLast());
        }
        //5 6 + 7 8 _ * - out
    }

}
