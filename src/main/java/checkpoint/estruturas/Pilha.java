package checkpoint.estruturas;

import java.util.NoSuchElementException;

public class Pilha<T> {

    private final Lista<T> lista = new Lista<>();

    public void push(T elemento) {
        lista.inserirInicio(elemento);
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return lista.removerInicio();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pilha vazia");
        }
        return lista.primeiro();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int tamanho() {
        return lista.tamanho();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
