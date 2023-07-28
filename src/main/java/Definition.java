import java.util.HashMap;
import java.util.Map;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Definition extends Word{

    private final Map<String, String> definition;

    public Definition(String key, String value) {
        super(key);
        this.definition = new HashMap<>();
        definition.put(key, value);
    }

    public Map<String, String> getDefinition() {
        return definition;
    }

    public String getValue(String key) {
        if(this.definition.containsKey(key)){
            return this.definition.get(key);
        }
        else {return "No Value";}
    }

    public String program(String key) {
        return this.definition.get(key);
    }
}
