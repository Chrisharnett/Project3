import java.util.ArrayList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Stack < T > {
    private int size;
    private int top;
    private ArrayList< T > stack;



    public Stack() {
        this.size = size;
        this.stack = new ArrayList<>();
        this.top = -1;
    }
    public void push(T t) {
        this.stack.add(t);
    }

    public void dup() {
        T duplicate = this.stack.get(top);
        this.stack.add(duplicate);
    }
    public void swap() {
        T itemOne = this.stack.remove(top);
        T itemTwo = this.stack.remove(top);
        this.stack.add(itemOne);
        this.stack.add(itemTwo);
    }

    public T pop() {
        return this.stack.remove(-1);

    }
    public String add() {
        String string = "";
        return string;
    }
    public String multiply() {
        String string = "";
        return string;
    }
    public String subtract() {
        String string = "";
        return string;
    }

}
