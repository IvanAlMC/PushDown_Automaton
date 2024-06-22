package ndfa.stack;

public class Stack {

    public static final char NULL_CHAR = Character.MIN_VALUE;
    public static final char LAMBDA = '\u03BB';
    private StackNode stack;

    public void pushSymbol(char symbol){
        if (symbol == LAMBDA) return;//No agrega ningún simbolo
        if(isEmpty()){
            stack = new StackNode(symbol);
        }else{
            StackNode newNode = new StackNode(symbol);
            newNode.setNext(stack);
            stack = newNode;
        }
    }

    /**
     * Saca el simbólo especificado de la pila si fue el último en ser introducido a la pila y lo retorna, <br>
     * si la pila está vacia o el simbolo no corresponde con el último introducido retorna el caracter null
     * @param symbol símbolo a sacar de la pila
     * @see <a href="https://www.asciitable.com/">Tabla Ascii caracter nulo</a>
     * @return caracter especificado, o caracter null
     */
    public char popSymbol(char symbol){
        if (symbol == LAMBDA) return LAMBDA;
        if (isEmpty() || stack.getData() != symbol){
            return NULL_CHAR;
        }
        stack = stack.getNext();
        return symbol;
    }

    public boolean isEmpty(){
        return stack == null;
    }

    public void printStack(){
        if (isEmpty()){
            System.out.println("empty");
        }else {
            print(stack);
        }
    }

    private void print(StackNode node){
        System.out.println(node);
        if(node.getNext() != null){
            print(node.getNext());
        }
    }
}
