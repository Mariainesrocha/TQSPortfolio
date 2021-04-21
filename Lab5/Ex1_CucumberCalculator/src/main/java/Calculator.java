import java.util.*;

public class Calculator {
    private final Deque<Number> stack = new LinkedList<Number>();
    private static final List<String> OPS = Arrays.asList("-", "+", "*", "/");

    public void push(Object arg) {
        if (OPS.contains(arg)) {
            Number y = stack.removeLast();
            Number x = stack.isEmpty() ? 0 : stack.removeLast();
            Double val = null;
            if (arg.equals("-")) {
                val = x.doubleValue() - y.doubleValue();
            } else if (arg.equals("+")) {
                val = x.doubleValue() + y.doubleValue();
            } else if (arg.equals("*")) {
                val = x.doubleValue() * y.doubleValue();
            } else if (arg.equals("/")) {
                val = x.doubleValue() / y.doubleValue();
            }
            push(val);
        } else {
            stack.add((Number) arg);
        }
    }

    public Number value() {
        return stack.getLast();
    }
}