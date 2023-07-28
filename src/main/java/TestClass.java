import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class TestClass {
    private static Iterator<Word> wordIterator;
    private static String filename;
    private static LinkedList<Word> words;
    private static LinkedList<Word> stack;

    private static ArrayList<String> wordList = new ArrayList<>();
    private static Definition definition;

    public static void main(String[] args) {
        Quote quote = new Quote("quote");
        stack = new LinkedList<>();
        stack.add(quote);
        Number three = new Number(3);
        stack.add(three);
        wordIterator = stack.iterator();
        for (Word word: stack){
            System.out.println(word.getString());
        }

    }
}
