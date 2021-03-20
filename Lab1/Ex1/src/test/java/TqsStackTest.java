import org.junit.jupiter.api.DisplayName;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    private TqsStack stack;
    private TqsStack stackThreeEl;

    @org.junit.jupiter.api.BeforeEach //ocorre antes de cada um dos testes, qdo a inicializacao é comum a varios testes
    void setUp() {
        stack = new TqsStack();
        stackThreeEl = new TqsStack(3);

        stackThreeEl.pushElm("Anadia");
        stackThreeEl.pushElm("Guimaraes");
        stackThreeEl.pushElm("Figueira");

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    // ----------------------------------------------------------------------------------

    @DisplayName("a) Empty Stack") //nome facultativo, apenas para ajudar na visualizacao do relatorio
    @org.junit.jupiter.api.Test
    void isEmpty() {
        assertTrue(stack.isEmpty(), "Warn: Stack is not empty!"); //verificamos se está vazia e msg de erro (razao de falha no teste)
    }

    @DisplayName("b) Size is 0")
    @org.junit.jupiter.api.Test
    void sizeOnConst() {
        assertEquals(0, stack.size(),"Warn: Stack size has no sense!");
    }

    @DisplayName("c) n pushes and then size")
    @org.junit.jupiter.api.Test
    void pushElm() {
        stack.pushElm("Aveiro");
        stack.pushElm("Porto");
        stack.pushElm("Lisboa");
        stack.pushElm("Coimbra");

        assertEquals(4,stack.size());
        assertFalse(stack.isEmpty(),"Stack should not be empty"); //maybe desnecessário mas permite perceber se o erro foi na inserção de um elemento (o q seria weird) apenas ou no push em geral
    }

    @DisplayName("d) Push then Pop")
    @org.junit.jupiter.api.Test
    void popOnNonEmpty() {
        stack.pushElm("Braga");
        assertEquals("Braga",stack.pop(),"Warn: error with popped value");
    }

    @DisplayName("e) Push then Peek and check size")
    @org.junit.jupiter.api.Test
    void pushThenPeek() {
        stack.pushElm("Braga");
        int s = stack.size();

        assertEquals("Braga",stack.peek(),"Warn: error with peeked value");
        assertEquals(s,stack.size(), "Warn: after peek stack size should not change");
    }

    @DisplayName("f) Size after n (initial size) pops")
    @org.junit.jupiter.api.Test
    void popSize() {
        int s = stackThreeEl.size();

        for (int i = 0; i < s; i++)
            stackThreeEl.pop();
        assertEquals(0,stackThreeEl.size(),"Warn: error stack size incorrect");
        assertTrue(stackThreeEl.isEmpty(), "Warn: stack not empty");
    }

    @DisplayName("g) Pop on Empty Stack")
    @org.junit.jupiter.api.Test
    void popEmpty() {
        assertThrows(NoSuchElementException.class, () -> { stack.pop();},"Warn: empty stack");
    } //se no codigo da funcao pop a excecao for lançada quer dizer que a stack está vazia

    @DisplayName("h) Peek on Empty Stack")
    @org.junit.jupiter.api.Test
    void peekEmpty() {
        assertThrows(NoSuchElementException.class, () -> { stack.peek();});
    }

    @DisplayName("i) Push to full stack")
    @org.junit.jupiter.api.Test
    void pushLimit() {
        assertThrows(IllegalStateException.class, () -> { stackThreeEl.pushElm("Ananas");},"error: should detect a full stack");
    }
}