package Midterms;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CardStack {
    private final ArrayList<Card> cards = new ArrayList<>();

    public void push(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("A stack cannot contain a null card");
        }
        cards.add(card);
    }

    public Card pop() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("Cannot draw from an empty stack");
        }
        return cards.remove(cards.size() - 1);
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
