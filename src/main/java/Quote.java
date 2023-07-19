import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class Quote extends Word{

    public Quote(String string) {
        super(string);
    }

    public String getQuote() {
        return this.getQuote();
    }

//    public LinkedList<GenericBox> putEmTogether(LinkedList<GenericBox> stack) {
//        String x = ((Quote) stack.removeLast().getContents()).getQuote();
//        String y = ((Quote) stack.removeLast().getContents()).getQuote();
//        String s = x + " " + y;
//        stack.push(new GenericBox(new Quote(s)));
//        return stack;
//    }
//    public LinkedList<GenericBox> negate(LinkedList<GenericBox> stack) {
//        char[] x = ((Quote) stack.removeLast().getContents()).getQuote().toCharArray();
//        String reverse = "";
//        for (int i = x.length-1; i >+ 0 ; i--) {
//            reverse += x[i];
//        }
//        stack.push(new GenericBox(new Quote(reverse)));
//        return stack;
//    }
//    public LinkedList<GenericBox> multiply(LinkedList<GenericBox> stack) {
//        String x = ((Quote) stack.removeLast().getContents()).getQuote();
//        String y = ((Quote) stack.removeLast().getContents()).getQuote();
//        int i = y.indexOf(x);
//        stack.addLast(new GenericBox(new Word(x.substring(i+1))));
//        return stack;
//    }

}
