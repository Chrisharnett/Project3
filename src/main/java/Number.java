import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Number extends Word {
    private long value;

    public Number(String string) {
        super(string);
        this.value = Long.parseLong(string);
    }

    public Number(long value) {
        super(String.format("%d", value));
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public String putEmTogether(String x) {
        Number a  = new Number(x);
        Number b = new Number(this.value + a.getValue());
        return b.getString();
    }
    public String negate() {
        Number n = new Number(this.value * -1);
        return n.getString();
    }
    public String multiply(Number y) {
        Number z = new Number(this.value*y.getValue());
        return z.getString();
    }
}
