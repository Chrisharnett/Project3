import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class WordList<GenericBox> extends LinkedList {
    private LinkedList<GenericBox> stack;

    private Iterator<GenericBox> iterator;

    /**
     * Constructs an empty list.
     */
    public WordList(LinkedList<GenericBox> stack) {
        this.stack = stack;
    }
//    public LinkedList<GenericBox> negate(LinkedList<GenericBox> stack) {
//        char[] x = ((Word) stack.removeLast().getContents()).getString().toCharArray();
//        String reverse = "";
//        for (int i = x.length-1; i >+ 0 ; i--) {
//            reverse += x[i];
//        }
//        stack.push(new GenericBox(new Word(reverse)));
//        return stack;
//    }
//    public LinkedList<GenericBox> multiply(LinkedList<GenericBox> stack) {
//        String x = ((Word) stack.removeLast().getContents()).getString();
//        String y = ((Word) stack.removeLast().getContents()).getString();
//        int i = y.indexOf(x);
//        stack.addLast(new GenericBox(new Word(x.substring(i+1))));
//        return stack;
//    }
}