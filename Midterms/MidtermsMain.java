package Midterms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MidtermsMain {
    private final CardStack playerDeck = createPlayerDeck();
    private final CardStack discardedPile = new CardStack();
    private final List<Card> playerHand = new ArrayList<>();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private int round;

    public static void main(String[] args) {
        new MidtermsMain().playGame();
    }

    private void playGame() {
        System.out.println("UNO card game started with " + playerDeck.size() + " cards.");

        while (!playerDeck.isEmpty()) {
            round++;
            issueRandomCommand();
            displayRound();
            if (!playerDeck.isEmpty()) {
                waitForNextRound();
            }
        }

        System.out.println("\nPlayer deck is empty. Game over.");
    }

    private void issueRandomCommand() {
        int count = random.nextInt(5) + 1;

        switch (random.nextInt(3)) {
            case 0:
                drawCards(count);
                break;
            case 1:
                discardCards(count);
                break;
            default:
                getCardsFromDiscardPile(count);
                break;
        }
    }

    private void drawCards(int count) {
        if (count <= 0) {
            System.out.println("Draw count must be at least one.");
            return;
        }
        if (playerDeck.isEmpty()) {
            System.out.println("Cannot draw because the player deck is empty.");
            return;
        }

        int cardsToDraw = Math.min(count, playerDeck.size());
        for (int i = 0; i < cardsToDraw; i++) {
            playerHand.add(playerDeck.pop());
        }
        System.out.println("Command: Draw " + count + "; drew " + cardsToDraw + " card(s).");
    }

    private void discardCards(int count) {
        if (count <= 0) {
            System.out.println("Discard count must be at least one.");
            return;
        }
        if (playerHand.isEmpty()) {
            System.out.println("Command: Discard " + count + "; no cards are in your hand.");
            return;
        }

        int cardsToDiscard = Math.min(count, playerHand.size());
        for (int i = 0; i < cardsToDiscard; i++) {
            discardedPile.push(playerHand.remove(playerHand.size() - 1));
        }
        System.out.println("Command: Discard " + count + "; discarded " + cardsToDiscard + " card(s).");
    }

    private void getCardsFromDiscardPile(int count) {
        if (count <= 0) {
            System.out.println("Get count must be at least one.");
            return;
        }
        if (discardedPile.isEmpty()) {
            System.out.println("Command: Get " + count + " from discard pile; the pile is empty.");
            return;
        }

        int cardsToGet = Math.min(count, discardedPile.size());
        for (int i = 0; i < cardsToGet; i++) {
            playerHand.add(discardedPile.pop());
        }
        System.out.println("Command: Get " + count + " from discard pile; got " + cardsToGet + " card(s).");
    }

    private void displayRound() {
        System.out.println("\n--- Round " + round + " ---");
        System.out.println("Cards in hand: " + playerHand);
        System.out.println("Cards remaining in player deck: " + playerDeck.size());
        System.out.println("Cards in discarded pile: " + discardedPile.size());
    }

    private void waitForNextRound() {
        System.out.print("Press Enter to continue to the next round...");
        System.out.flush();
        scanner.nextLine();
    }

    private static CardStack createPlayerDeck() {
        List<Card> cards = new ArrayList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};

        for (String color : colors) {
            cards.add(new Card(color + " 0"));
            cards.add(new Card(color + " 1"));
            cards.add(new Card(color + " 1"));
            cards.add(new Card(color + " 2"));
            cards.add(new Card(color + " 2"));
            cards.add(new Card(color + " Skip"));
            cards.add(new Card(color + " Reverse"));
        }
        cards.add(new Card("Wild"));
        cards.add(new Card("Wild Draw Four"));

        if (cards.size() != 30) {
            throw new IllegalStateException("The player deck must contain exactly 30 cards");
        }

        Collections.shuffle(cards);
        CardStack deck = new CardStack();
        for (Card card : cards) {
            deck.push(card);
        }
        return deck;
    }
}
