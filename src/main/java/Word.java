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
    private final String string;

    public Word(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public String putEmTogether(String x) {
        return x + " " + this.string;
    }
    public String negate() {
        char[] x = this.string.toCharArray();
        StringBuilder reverse = new StringBuilder();
        for (int i = x.length-1; i >=+ 0 ; i--) {
            reverse.append(x[i]);
        }
        return reverse.toString();
    }
    public String multiply(String x) {
        int i = x.indexOf(this.string);
        return x.substring(i);
    }
}
