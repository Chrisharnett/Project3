/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class WordBox<T extends Word> {
    private T contents;

    public WordBox(T contents) {
        this.contents = contents;
    }

    public T getWord() {
        return contents;
    }

    public void setContents(T contents) {
        this.contents = contents;
    }

}

