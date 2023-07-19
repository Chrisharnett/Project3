import java.util.function.Function;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class GenericBox <T> {
    private T contents;

    public GenericBox(T contents) {
        this.contents = contents;
    }

    public T getContents() {
        return contents;
    }

    public void setContents(T contents) {
        this.contents = contents;
    }

    public <R> GenericBox<R> map(Function<T, R> function){
        return new GenericBox<>(function.apply(contents));
    }
}

