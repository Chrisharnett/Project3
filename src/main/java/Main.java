import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Main {
    private static Iterator< Object > iterator;
    private static String filename;
    private static Stack words;
    private static ArrayList<String> wordList = new ArrayList<>();
    public static void main(String[] args) {
// TODO Uncomment this code to get user input
//        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
//            filename = reader.readLine();
//        }catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
        String filename = "test1.txt";
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

        Pattern stackOperationPattern = Pattern.compile("[-+*](dup)(swap)(pop)");
        Stack words = new Stack();
        words = buildStack(wordList, words);

    }
public static Stack buildStack(ArrayList<String> wordList, Stack stack) {
    String[] iOWord = {"in", "out"};
    String[] StackOpWord = {"-", "+", "*", "dup", "swap", "pop"};
    Iterator<String> iterator = wordList.iterator();
    while (iterator.hasNext()) {
        String s = iterator.next();
        if (isNumber(s)) {
            stack.push(new Number(s));
        } else if ('\'' == s.charAt(0)) {
            String quoteString = s;
            while (true) {
                String t = iterator.next();
                quoteString += t;
                if (t.charAt(t.length()-1) == '\''){
                    break;
                }
            }
            Quote quote = new Quote(quoteString.trim().replaceAll("\'", ""));
            stack.push(quote);
        } else if (':' == s.charAt(0)){
            String t = iterator.next();
            String def = "";
            while (true) {
                String u = iterator.next();
                if (u.charAt(0) == ':') {
                    break;
                }
                def += u + " ";
            }
            stack.push(new Definition(t, def.trim()));
        } else if (Arrays.asList(iOWord).contains(s)){
            words.push(new IO(s));
        } else if (Arrays.asList(StackOpWord).contains(s)){
            words.push(new StackOperation(s));
        } else {words.push(s);}
    }
    return stack;
}


public static Boolean isNumber(String word) {
    Pattern numberPattern = Pattern.compile("\\d");
    Matcher matcher = numberPattern.matcher(word);
    return matcher.matches();
    }
}
