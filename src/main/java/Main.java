import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Main {
    private final static ArrayList<String> wordList = new ArrayList<>();
    private final static ArrayList<Definition> definitions = new ArrayList<>();

    public static void main(String[] args) {
        String filename = "";
            try{
                if (args[0].length() > 0) {
                    filename = args[0];
                    Paths.get(args[0]);
                }
                else{
                    throw new InvalidPathException(filename, "Invalid Path to file.");
                }
            } catch (InvalidPathException e) {
                e.printStackTrace();
                System.exit(0);
            }

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (s.hasNext()) {
                wordList.add(s.next().trim());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. Ending program.");
            System.exit(0);
        }

        LinkedList<Word> words = new LinkedList<>();
        try {
            createWords(wordList, words);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            processWords(words);
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
                quoteString.append(s.replaceAll("'", "")).append(" ");
                while (iterator.hasNext()) {
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
                while (iterator.hasNext()) {
                    String u = iterator.next();
                    if (u.charAt(0) == ':') {
                        break;
                    }
                    else {stringArray.add(u);}
                }
                LinkedList<Word> def = new LinkedList<>();
                LinkedList<Word> w;
                w = createWords(stringArray, def);
                if (!(w.getFirst() instanceof Number) || !(w.getFirst() instanceof Quote)){
                    Definition d = new Definition(t, w);
                     words.addLast(d);
                    definitions.add(d);
                }
                else if (w.getFirst() instanceof Number){
                    String result = processWords(w);
                    LinkedList<Word> a = new LinkedList<>();
                    a.add(new Number(result));
                    Definition d = new Definition(t, a);
                    words.addLast(d);
                    definitions.add(d);
                }
                else if (w.getFirst() instanceof Quote){
                    String result = processWords(w);
                    LinkedList<Word> a = new LinkedList<>();
                    a.add(new Quote(result));
                    Definition d = new Definition(t, a);
                    words.addLast(d);
                    definitions.add(d);
                }


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
            boolean definitionWord = false;
            for (Definition d: definitions){
                if (d.getDefinition().containsKey(word.getString())) {
                    definitionWord = true;
                    break;
                }
            }
            WordBox<Word> box = new WordBox<>(word);
            if (word instanceof Definition) {
                wordIterator.remove();
//                definitions.add((Definition) box.getWord());
            } else if (word instanceof StackOperation) {
                wordIterator.remove();
                stack = ((StackOperation) box.getWord()).runOperation(stack);
                if (stack.size() == 1 && words.size() == 0) {
                    result = stack.get(0).getWord().getString();
                }
            } else if (word instanceof IO) {
                wordIterator.remove();
                ((IO) word).out(stack.removeLast().getWord().getString());
            } else if (definitions.size() == 0) {
                    wordIterator.remove();
                    stack.addLast(box);
            } else if (definitionWord){
                for (Definition d: definitions) {
                    if (d.getDefinition().containsKey(word.getString())){
                        LinkedList<Word> val = d.getValue();
                        wordIterator.remove();
                        for (Word w: val) {
                            if (w instanceof Number || w instanceof Quote) {
                                stack.addLast(new WordBox<>(w));
                            } else if (w instanceof StackOperation) {
                                ((StackOperation) w).runOperation(stack);
                                if (stack.size() == 1 && words.size() == 0) {
                                    result = stack.get(0).getWord().getString();
                                }
                            }
                        }
                    }
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
        return matcher.matches();
    }
}
