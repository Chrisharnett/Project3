/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Number extends Word {
    private long value;
    private String nString;

    public Number(long value) {
        this.value = value;
    }

    public Number(String nString) {
        this.value = Long.parseLong(nString);
    }

    public void process() {

    }
}
