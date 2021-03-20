import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsStack<T> {
    private ArrayList<T> stack;
    private int limit = -1;

    public TqsStack(){
        this.stack = new ArrayList<>();
    }

    public TqsStack(int limit){
        this.stack = new ArrayList<>();
        this.limit = limit;
    }

    public void pushElm(T element){
        if(limit > 0 && stack.size() == limit )
            throw new IllegalStateException();
        else
            stack.add(element);
    }

    public T pop(){
        if(stack.size() == 0)
            throw new NoSuchElementException();
        else
            return stack.remove(stack.size()-1);
    }

    public T peek(){
        if(stack.size() == 0)
            throw new NoSuchElementException();
        else
            return stack.get(stack.size()-1);
    }

    public boolean isEmpty(){
        return stack.size() == 0;
    }
    public int size(){
        return stack.size();
    }
}
