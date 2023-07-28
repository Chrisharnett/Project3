import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Number extends Word {
    private final long value;

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
    @Override
    public String putEmTogether(String x) {
        Number a  = new Number(x);
        Number b = new Number(this.value + a.getValue());
        return b.getString();
    }
    @Override
    public String negate() {
        Number n = new Number(this.value * -1);
        return n.getString();
    }


    @Override
    public String multiply(String y) {
        Number x = new Number(y);
        Number z = new Number(this.value*x.getValue());
        return z.getString();
    }
}
