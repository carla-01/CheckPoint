package checkpoint.estruturas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista<T> implements Iterable<T> {

    private static class No<T> {
        T dado;
        No<T> proximo;

        No(T dado) {
            this.dado = dado;
        }
    }

    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public void inserirInicio(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("Elemento nao pode ser nulo");
        }
        No<T> novo = new No<>(elemento);
        novo.proximo = inicio;
        inicio = novo;
        if (fim == null) {
            fim = novo;
        }
        tamanho++;
    }

    public void inserirFim(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("Elemento nao pode ser nulo");
        }
        No<T> novo = new No<>(elemento);
        if (fim == null) {
            inicio = novo;
            fim = novo;
        } else {
            fim.proximo = novo;
            fim = novo;
        }
        tamanho++;
    }

    public T removerInicio() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }
        No<T> removido = inicio;
        inicio = inicio.proximo;
        if (inicio == null) {
            fim = null;
        }
        removido.proximo = null;
        tamanho--;
        return removido.dado;
    }

    public boolean remover(T elemento) {
        if (elemento == null || inicio == null) {
            return false;
        }
        if (elemento.equals(inicio.dado)) {
            removerInicio();
            return true;
        }
        No<T> anterior = inicio;
        No<T> atual = inicio.proximo;
        while (atual != null) {
            if (elemento.equals(atual.dado)) {
                anterior.proximo = atual.proximo;
                if (atual == fim) {
                    fim = anterior;
                }
                atual.proximo = null;
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    public boolean contem(T elemento) {
        if (elemento == null) {
            return false;
        }
        for (No<T> atual = inicio; atual != null; atual = atual.proximo) {
            if (elemento.equals(atual.dado)) {
                return true;
            }
        }
        return false;
    }

    public T primeiro() {
        if (isEmpty()) {
            throw new NoSuchElementException("Lista vazia");
        }
        return inicio.dado;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public int tamanho() {
        return tamanho;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private No<T> atual = inicio;

            @Override
            public boolean hasNext() {
                return atual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T dado = atual.dado;
                atual = atual.proximo;
                return dado;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean primeiro = true;
        for (T elemento : this) {
            if (!primeiro) {
                sb.append(", ");
            }
            sb.append(elemento);
            primeiro = false;
        }
        return sb.append("]").toString();
    }
}
