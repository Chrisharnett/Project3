import org.w3c.dom.ls.LSOutput;

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
        return s.nextLine();
    }
    public void out (String s) {
        System.out.println(s);;
    }
//    public String out (LinkedList<WordBox<Word>> stack) {
//        return stack.get(0).getWord().getString();
//    }
}
