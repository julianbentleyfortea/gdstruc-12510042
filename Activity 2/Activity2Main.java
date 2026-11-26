import java.util.NoSuchElementException;
import java.util.Objects;

public class Activity2Main {
    public static void main(String[] args) {
        LinkedList<String> singlyLinkedList = new LinkedList<>();
        singlyLinkedList.add("Heathcliff");
        singlyLinkedList.add("Asuna");
        singlyLinkedList.add("LethalBacon");
        singlyLinkedList.add("HPDeskjet");

        System.out.println("Singly linked list: " + singlyLinkedList);
        System.out.println("Size: " + singlyLinkedList.size());
        System.out.println("Contains Asuna: " + singlyLinkedList.contains("Asuna"));
        System.out.println("Index of LethalBacon: " + singlyLinkedList.indexOf("LethalBacon"));
        System.out.println("Removed first: " + singlyLinkedList.removeFirst());
        System.out.println("After removal: " + singlyLinkedList);
        System.out.println("Size after removal: " + singlyLinkedList.size());

        DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.add("Heathcliff");
        doublyLinkedList.add("Asuna");
        doublyLinkedList.add("LethalBacon");
        doublyLinkedList.add("HPDeskjet");

        System.out.println("Doubly linked list forward: " + doublyLinkedList);
        System.out.println("Doubly linked list backward: " + doublyLinkedList.toStringBackward());
    }
}

class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from an empty linked list");
        }

        T value = head.value;
        head = head.next;
        size--;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(Object value) {
        int index = 0;
        for (Node<T> node = head; node != null; node = node.next) {
            if (Objects.equals(node.value, value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Node<T> node = head; node != null; node = node.next) {
            if (node != head) {
                result.append(", ");
            }
            result.append(node.value);
        }
        return result.append("]").toString();
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;

        private Node(E value) {
            this.value = value;
        }
    }
}

class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void add(T value) {
        Node<T> node = new Node<>(value);
        node.previous = tail;
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Cannot remove from an empty linked list");
        }

        T value = head.value;
        head = head.next;
        size--;
        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }
        return value;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    public int indexOf(Object value) {
        int index = 0;
        for (Node<T> node = head; node != null; node = node.next) {
            if (Objects.equals(node.value, value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (Node<T> node = head; node != null; node = node.next) {
            if (node != head) {
                result.append(", ");
            }
            result.append(node.value);
        }
        return result.append("]").toString();
    }

    public String toStringBackward() {
        StringBuilder result = new StringBuilder("[");
        for (Node<T> node = tail; node != null; node = node.previous) {
            if (node != tail) {
                result.append(", ");
            }
            result.append(node.value);
        }
        return result.append("]").toString();
    }

    private static class Node<E> {
        private final E value;
        private Node<E> next;
        private Node<E> previous;

        private Node(E value) {
            this.value = value;
        }
    }
}
