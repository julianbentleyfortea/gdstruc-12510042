package Midterms;

public class Card {
    private final String name;

    public Card(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Card name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
