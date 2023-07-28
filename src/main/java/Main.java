import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Main {
    private final static ArrayList<String> wordList = new ArrayList<>();
    private static Definition definition;

    public static void main(String[] args) {
        System.out.println("Enter a valid filename:");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        System.out.println();

        String result = null;
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (s.hasNext()) {
                wordList.add(s.next().trim());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File note found. Ending program.");
            e.printStackTrace();
        }

        LinkedList<Word> words = new LinkedList<>();
        try {
            createWords(wordList, words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = processWords(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Word> createWords(ArrayList<String> wordList, LinkedList<Word> words) throws IOException {
        String[] iOWord = {"in", "out"};
        String[] StackOpWord = {"-", "+", "*", "dup", "swap", "pop"};
        Iterator<String> iterator = wordList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();

            if (isNumber(s)) {
                words.addLast(new Number(s));
            } else if ('\'' == s.charAt(0)) {
                StringBuilder quoteString = new StringBuilder();
                quoteString.append(s.replaceAll("'", "") + " ");
                while (true) {
                    String t = iterator.next();
                    if (t.charAt(t.length() - 1) == '\'') {
                        quoteString.append(t.replaceAll("'", ""));
                        break;
                    }
                    quoteString.append(t).append(" ");
                }
                words.addLast(new Quote(quoteString.toString().trim()));
            } else if (':' == s.charAt(0)) {
                String t = iterator.next();
                ArrayList<String> stringArray = new ArrayList<>();
                while (true) {
                    String u = iterator.next();
                    if (u.charAt(0) == ':') {
                        break;
                    }
                    else {stringArray.add(u);}
                }
                LinkedList<Word> def = new LinkedList<>();
                String result = processWords(createWords(stringArray, def));
                words.addLast(new Definition(t, result));
            } else if (Arrays.asList(iOWord).contains(s)) {
                if (s.equalsIgnoreCase("in")) {
                    IO in = new IO(s);
                    words.addLast(new Quote(in.in()));
                } else {
                    words.addLast(new IO(s));
                }
            } else if (Arrays.asList(StackOpWord).contains(s)) {
                words.addLast(new StackOperation(s));
            } else {
                words.addLast(new Word(s));
            }
        }
        return words;
    }

    public static String processWords(LinkedList<Word> words) throws IOException {
        Iterator<Word> wordIterator = words.iterator();
        LinkedList<WordBox<Word>> stack = new LinkedList<>();
        String result = null;
        while (wordIterator.hasNext()) {
            Word word = wordIterator.next();
            WordBox<Word> box = new WordBox<>(word);
            if (word instanceof Definition) {
                wordIterator.remove();
                definition = (Definition) box.getWord();
            } else if (word instanceof StackOperation) {
                wordIterator.remove();
                stack = ((StackOperation) box.getWord()).runOperation(stack);
                if (stack.size() == 1 && words.size() == 0) {
                    result = stack.get(0).getWord().getString();
                }
            } else if (word instanceof IO) {
                wordIterator.remove();
                ((IO) word).out(stack.removeLast().getWord().getString());
            } else if (definition == null) {
                    wordIterator.remove();
                    stack.addLast(box);
            } else if (definition.getDefinition().containsKey((word.getString()))){
                String val = definition.getValue(word.getString());
                wordIterator.remove();
                if (isNumber(val)){
                    stack.addLast(new WordBox<>(new Number(val)));
                }
                else {
                    stack.addLast(new WordBox<>(new Word(val)));
                }
            } else {
                wordIterator.remove();
                stack.addLast(box);
            }
        } return result;
    }
    public static Boolean isNumber(String word) {
        Pattern numberPattern = Pattern.compile("^\\d+$");
        Matcher matcher = numberPattern.matcher(word);
        boolean b = matcher.matches();
        return matcher.matches();
    }
}
