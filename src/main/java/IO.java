import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class IO extends Word {

    public IO(String string) {
        super(string);
    }

    public String in() {
        System.out.println("Enter a string.");
        Scanner s = new Scanner(System.in);
        String in = s.nextLine();
        return in;
    }
    public String out (LinkedList<GenericBox> stack) {
        GenericBox word = stack.get(0);
        if (word.getContents() instanceof Word) {
            return ((Word) stack.removeLast().getContents()).getString();
        } else if (word.getContents() instanceof Number) {
            return ((Number) stack.removeLast().getContents()).getString();
        } else if (word.getContents() instanceof Quote) {
            return ((Quote) stack.removeLast().getContents()).getQuote();
        } return null;
    }
    public void process() {

    };
}
