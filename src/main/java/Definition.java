import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Definition extends Word{

    private final Map<String, LinkedList<Word>> definition;

    public Definition(String key, LinkedList<Word> value) {
        super(key);
        this.definition = new HashMap<>();
        definition.put(key, value);
    }

    public Map<String, LinkedList<Word>> getDefinition() {
        return definition;
    }

    public LinkedList<Word> getValue() {
        return this.definition.values().stream().findFirst().orElse(null);
    }

//    public String program(String key) {
//        return this.definition.get(key);
//    }
}
