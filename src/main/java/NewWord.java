import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class NewWord {
    /**
     * A word that is to be analyzed by the system. The word may be a:
     *  Number
     *  Quote
     *  Definition
     *  Stack Operation
     *  I/O
     */
    private String string;

    public NewWord(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public LinkedList<GenericBox> putEmTogether(LinkedList<GenericBox> stack) {
        String x = ((Word) stack.removeLast().getContents()).getString();
        String y = ((Word) stack.removeLast().getContents()).getString();
        String s = x + " " + y;
        stack.push(new GenericBox(new Word(s)));
        return stack;
    }
    public LinkedList<GenericBox> negate(LinkedList<GenericBox> stack) {
        char[] x = ((Word) stack.removeLast().getContents()).getString().toCharArray();
        String reverse = "";
        for (int i = x.length-1; i >+ 0 ; i--) {
            reverse += x[i];
        }
        stack.push(new GenericBox(new Word(reverse)));
        return stack;
    }
    public LinkedList<GenericBox> multiply(LinkedList<GenericBox> stack) {
        String x = ((Word) stack.removeLast().getContents()).getString();
        String y = ((Word) stack.removeLast().getContents()).getString();
        int i = y.indexOf(x);
        stack.addLast(new GenericBox(new Word(x.substring(i+1))));
        return stack;
    }

    public void process() {

    };
}
