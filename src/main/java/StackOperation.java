import java.util.LinkedList;

/**
 * @author saxDev
 * studentnumber 20188141
 **/
public class StackOperation extends Word{
    public StackOperation(String string) {
        super(string);
    }

    public LinkedList<GenericBox> runOperation(LinkedList<GenericBox> stack){

        switch (super.getString()) {
            case "+":
                GenericBox x = stack.removeLast();
                GenericBox y = stack.removeLast();
                if (x.getContents() instanceof Number && y.getContents() instanceof Number) {
                    String n = ((Number) x.getContents()).putEmTogether(((Number) y.getContents()).getString());
                    stack.addLast(new GenericBox(new Number(n)));
                    return stack;
                } else if (x.getContents() instanceof Quote || y.getContents() instanceof Quote ||
                        x.getContents() instanceof Word || y.getContents() instanceof Word){
                    String newWord = ((Word) x.getContents()).putEmTogether(((Word) y.getContents()));
                    stack.addLast(new GenericBox(new Quote(newWord)));
                    return stack;
                }
            case "-":
                GenericBox z = stack.removeLast();
                if (z.getContents() instanceof Number) {
                    Number n = new Number(((Number) z.getContents()).negate());
                    stack.addLast(new GenericBox(n));
                    return stack;
                }
                else if (z.getContents() instanceof Quote) {
                    String n = ((Word) z.getContents()).negate();
                    stack.addLast(new GenericBox(new Quote(n)));
                    return stack;                }
            case "*":
                GenericBox a = stack.removeLast();
                GenericBox b = stack.removeLast();
                if (a.getContents() instanceof Number && b.getContents() instanceof Number) {
                    String n = ((Number) a.getContents()).multiply(((Number) b.getContents()));
                    stack.addLast(new GenericBox(new Number(n)));
                    return stack;
                } else if (a.getContents() instanceof Quote || b.getContents() instanceof Quote){
                    String newWord = ((Word) a.getContents()).multiply(((Word) b.getContents()));
                    stack.addLast(new GenericBox(new Quote(newWord)));
                    return stack;
                }
            case "dup":
                GenericBox box = stack.get(-1);
                stack.addLast(box);
                return stack;
            case "swap":
                GenericBox c = stack.removeLast();
                GenericBox d = stack.removeLast();
                stack.addLast(c);
                stack.addLast(d);
                return stack;
            case "pop":
                stack.removeLast();
                return stack;
            default:
                return null;
        }
    }

}
