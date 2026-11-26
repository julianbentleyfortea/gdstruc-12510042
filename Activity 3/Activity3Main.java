import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Activity3Main {
    private static final int PLAYERS_PER_GAME = 5;
    private static final int TARGET_GAMES = 10;

    public static void main(String[] args) {
        String[] names = {
                "Sam Giddings", "Josh Washington", "Emily Davis", "Chris Hartley",
                "Ashley Brown", "Mike Munroe", "Matt Taylor", "Jess Riley",
                "Hannah Washington", "Beth Washington"
        };

        ArrayQueue matchmakingQueue = new ArrayQueue(5);
        Random random = new Random();
        int nextPlayerId = 1;
        int gamesCreated = 0;
        int turnCount = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (gamesCreated < TARGET_GAMES) {
                turnCount++;
                System.out.println("\nTurn " + turnCount);

                int playersToQueue = random.nextInt(7) + 1;
                System.out.println("Enqueuing " + playersToQueue + " new players...");
                for (int i = 0; i < playersToQueue; i++) {
                    Player player = new Player(
                            nextPlayerId++,
                            names[random.nextInt(names.length)],
                            random.nextInt(100) + 1
                    );
                    matchmakingQueue.enqueue(player);
                    System.out.println(" + " + player);
                }

                System.out.println("Current queue size: " + matchmakingQueue.size());
                while (matchmakingQueue.size() >= PLAYERS_PER_GAME
                        && gamesCreated < TARGET_GAMES) {
                    gamesCreated++;
                    System.out.println("\n|| Game #" + gamesCreated + " matched! ||");
                    System.out.println("Dequeued players:");
                    for (int i = 0; i < PLAYERS_PER_GAME; i++) {
                        System.out.println(" - " + matchmakingQueue.dequeue());
                    }
                }

                System.out.println("Remaining in queue: " + matchmakingQueue.size());
                if (gamesCreated >= TARGET_GAMES) {
                    break;
                }

                System.out.print("Press Enter to end the turn...");
                if (!scanner.hasNextLine()) {
                    System.out.println("\nNo input received; stopping the program.");
                    break;
                }
                scanner.nextLine();
            }
        }

        System.out.println("\nGames successfully created: " + gamesCreated);
    }
}

class Player {
    private final int id;
    private final String name;
    private final int level;

    Player(int id, String name, int level) {
        if (id <= 0) {
            throw new IllegalArgumentException("Player ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        if (level < 1 || level > 100) {
            throw new IllegalArgumentException("Player level must be between 1 and 100");
        }

        this.id = id;
        this.name = name;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Player{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}

class ArrayQueue {
    private Player[] elements;
    private int front;
    private int size;

    ArrayQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Queue capacity must be positive");
        }
        elements = new Player[capacity];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void enqueue(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Cannot enqueue a null player");
        }
        if (size == elements.length) {
            grow();
        }

        int back = (front + size) % elements.length;
        elements[back] = player;
        size++;
    }

    Player dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue");
        }

        Player player = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;
        if (isEmpty()) {
            front = 0;
        }
        return player;
    }

    Player peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek at an empty queue");
        }
        return elements[front];
    }

    void printQueue() {
        for (int i = 0; i < size; i++) {
            System.out.println(elements[(front + i) % elements.length]);
        }
    }

    private void grow() {
        Player[] expanded = new Player[elements.length * 2];
        for (int i = 0; i < size; i++) {
            expanded[i] = elements[(front + i) % elements.length];
        }
        elements = expanded;
        front = 0;
    }
}
