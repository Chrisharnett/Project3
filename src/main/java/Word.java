import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Word {
    /**
     * A word that is to be analyzed by the system. The word may be a:
     *  Number
     *  Quote
     *  Definition
     *  Stack Operation
     *  I/O
     */
    private String string;

    public Word(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public String putEmTogether(Word x) {
        String s = x.getString() + " " + this.string;
        return s;
    }
    public String negate() {
        char[] x = this.string.toCharArray();
        String reverse = "";
        for (int i = x.length-1; i >=+ 0 ; i--) {
            reverse += x[i];
        }
        return reverse;
    }
    public String multiply(Word x) {
        int i = (x.getString()).indexOf(this.string);
        String sub = x.getString().substring(i);
        return sub;
    }
}
