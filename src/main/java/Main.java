import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Main {
    private static Iterator<Word> wordIterator;
    private static String filename;
    private static LinkedList<Word> words;
    private static LinkedList<GenericBox> stack;

    private static ArrayList<String> wordList = new ArrayList<>();
    private static Definition definition;

    public static void main(String[] args) {
//  TODO: test4 throws error on user input.....
        System.out.println("Enter a valid filename:");
        Scanner scanner = new Scanner(System.in);
        filename = scanner.nextLine();
        System.out.println();

//        String filename = "test8.txt";
        String result = null;
        Scanner s = null;
        try {
            s = new Scanner(new BufferedReader(new FileReader(filename)));
            while (s.hasNext()) {
                wordList.add(s.next().trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        LinkedList<Word> words = new LinkedList<>();
        try {
            words = createWords(wordList, words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = processWords(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

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
                String quoteString = "";
                while (true) {
                    String t = iterator.next();
                    if (t.charAt(t.length() - 1) == '\'') {
                        quoteString += t.replaceAll("\'", "");
                        quoteString = quoteString.trim();
                        break;
                    }
                    quoteString += t + " ";
                }
                Quote quote = new Quote(quoteString.trim().replaceAll("\'", ""));
                words.addLast(quote);
            } else if (':' == s.charAt(0)) {
                String t = iterator.next();
                String program = "";
                ArrayList<String> stringArray = new ArrayList<>();
                while (true) {
                    String u = iterator.next();
                    if (u.charAt(0) == ':') {
                        break;
                    }
                    stringArray.add(u);
                }
                LinkedList<Word> def = new LinkedList<>();
                def = createWords(stringArray, def);
                String result = processWords(def);
                words.addLast(new Definition(t, result));
            } else if (Arrays.asList(iOWord).contains(s)) {
                if (s.equalsIgnoreCase("in")) {
                    IO in = new IO(s);
                    String word = in.in();
                    words.addLast(new Quote(word));
                } else if(s.equalsIgnoreCase("out")) {
                    words.addLast(new IO(s));
                }
            } else if (Arrays.asList(StackOpWord).contains(s)) {
                words.addLast(new StackOperation(s));
            } else if (s instanceof String) {
                words.addLast(new Word(s));
            } else {
                throw new IOException("Cannot process file. Exiting Program");
            }

        }
        return words;
    }

    public static String processWords(LinkedList<Word> words) throws IOException {
        wordIterator = words.iterator();
        stack = new LinkedList<GenericBox>();
        String result = null;
        while (wordIterator.hasNext()) {
            Word word = wordIterator.next();
            GenericBox box = new GenericBox(word);
            if (word instanceof Number) {
                wordIterator.remove();
                stack.addLast(box);
            } else if (word instanceof Quote) {
                wordIterator.remove();
                stack.addLast(box);
            } else if (word instanceof Definition) {
                wordIterator.remove();
                definition = (Definition) box.getContents();
            } else if (word instanceof StackOperation) {
                wordIterator.remove();
                stack = ((StackOperation) box.getContents()).runOperation(stack);
                if (stack.size() == 1 && words.size() == 0) {
                    result = ((Word) stack.get(0).getContents()).getString();
                }
            } else if (word instanceof IO) {
                wordIterator.remove();
                result = ((IO) box.getContents()).out(stack);
            } else {
                if (definition == null) {
                    stack.addLast(box);
                }
                else if (definition.getDefinition().containsKey((word.getString()))){
                    String val = definition.getDefinition().get(word.getString());
                    wordIterator.remove();
                    if (isNumber(val)){
                        stack.addLast(new GenericBox<>(new Number(val)));
                    }
                    else {
                        stack.addLast(new GenericBox<>(new Word(val)));
                    }
                }
                }
            }

        return result;
    }
    public static Boolean isNumber(String word) {
        Pattern numberPattern = Pattern.compile("^\\d+$");
        Matcher matcher = numberPattern.matcher(word);
        boolean b = matcher.matches();
        return matcher.matches();
    }
}
