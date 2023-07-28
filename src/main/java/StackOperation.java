import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class StackOperation extends Word{
    public StackOperation(String string) {
        super(string);
    }

    public LinkedList<WordBox<Word>> runOperation(LinkedList<WordBox<Word>> stack){
        switch (super.getString()) {
            case "+":
                WordBox<Word> x = stack.removeLast();
                WordBox<Word> y = stack.removeLast();
                if (x.getWord() instanceof Number && y.getWord() instanceof Number) {
                    String n = x.getWord().putEmTogether(y.getWord().getString());
                    stack.addLast(new WordBox<>(new Number(n)));
                } else {
                    if (x.getWord() instanceof Number){
                        x = new WordBox<Word>(new Quote(x.getWord().getString()));
                    }
                    else if (y.getWord() instanceof Number){
                        y = new WordBox<Word>(new Quote(y.getWord().getString()));
                    }
                    String newWord = (x.getWord()).putEmTogether((y.getWord()).getString());
                    stack.addLast(new WordBox<Word>(new Quote(newWord)));
                }
                return stack;
            case "-":
                WordBox<Word> z = stack.removeLast();
                if (z.getWord() instanceof Number) {
                    Number n = new Number((z.getWord()).negate());
                    stack.addLast(new WordBox<Word>(n));
                    return stack;
                }
                else if (z.getWord() instanceof Quote) {
                    String n = ( z.getWord()).negate();
                    stack.addLast(new WordBox<Word>(new Quote(n)));
                    return stack;
                }
            case "*":
                WordBox<Word> a = stack.removeLast();
                WordBox<Word> b = stack.removeLast();
                if (a.getWord() instanceof Number && b.getWord() instanceof Number) {
                    String n = ((Number) a.getWord()).multiply(b.getWord().getString());
                    stack.addLast(new WordBox<Word>(new Number(n)));
                } else {
                    if (a.getWord() instanceof Number){
                        a = new WordBox<Word>(new Quote(a.getWord().getString()));
                    }
                    else if (b.getWord() instanceof Number){
                        b = new WordBox<Word>(new Quote(b.getWord().getString()));
                    }
                    String newWord = (a.getWord()).multiply((b.getWord()).getString());
                    stack.addLast(new WordBox<Word>(new Quote(newWord)));
                }
                return stack;
            case "dup":
                WordBox<Word> box = stack.get(stack.size()-1);
                stack.addLast(box);
                return stack;
            case "swap":
                WordBox<Word> c = stack.removeLast();
                WordBox<Word> d = stack.removeLast();
                stack.addLast(c);
                stack.addLast(d);
                return stack;
            case "pop":
                stack.removeLast();
                return stack;
            default:
                return stack;
        }
    }
}
