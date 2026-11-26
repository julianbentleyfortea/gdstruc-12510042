public class Activity4Main {
    public static void main(String[] args) {
        A4Player[] players = {
                new A4Player(1, "Arabit", 1),
                new A4Player(2, "Corteas", 19),
                new A4Player(3, "Forteat", 20),
                new A4Player(4, "Boolean", 25),
                new A4Player(5, "Fontanerl", 10)
        };

        SimpleHashtable hashtable = new SimpleHashtable();
        for (A4Player player : players) {
            hashtable.put(player.getUsername(), player);
        }

        System.out.println("Hashtable before removal:");
        hashtable.printHashtable();

        A4Player removed = hashtable.remove("Corteas");
        System.out.println("\nRemoved: " + removed);

        System.out.println("\nHashtable after removal:");
        hashtable.printHashtable();

        System.out.println("\nA later colliding key is still found: "
                + hashtable.get("Boolean"));
    }
}

class A4Player {
    private final int id;
    private final String username;
    private final int level;

    A4Player(int id, String username, int level) {
        if (id <= 0) {
            throw new IllegalArgumentException("Player ID must be positive");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (level < 1) {
            throw new IllegalArgumentException("Player level must be positive");
        }

        this.id = id;
        this.username = username;
        this.level = level;
    }

    String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "A4Player{" +
                "ID=" + id +
                ", name='" + username + '\'' +
                ", level=" + level +
                '}';
    }
}

class SimpleHashtable {
    private static final int DEFAULT_CAPACITY = 10;

    private final StoredPlayer[] entries;
    private final boolean[] deleted;
    private int size;

    SimpleHashtable() {
        this(DEFAULT_CAPACITY);
    }

    SimpleHashtable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Hashtable capacity must be positive");
        }
        entries = new StoredPlayer[capacity];
        deleted = new boolean[capacity];
    }

    void put(String key, A4Player value) {
        validateKey(key);
        if (value == null) {
            throw new IllegalArgumentException("Player value cannot be null");
        }

        int existingIndex = findKey(key);
        if (existingIndex >= 0) {
            entries[existingIndex].value = value;
            return;
        }
        if (size == entries.length) {
            throw new IllegalStateException("Hashtable is full");
        }

        int index = hashKey(key);
        int firstDeletedIndex = -1;
        for (int probes = 0; probes < entries.length; probes++) {
            if (entries[index] == null) {
                if (deleted[index]) {
                    if (firstDeletedIndex < 0) {
                        firstDeletedIndex = index;
                    }
                } else {
                    insertAt(firstDeletedIndex >= 0 ? firstDeletedIndex : index, key, value);
                    return;
                }
            }
            index = (index + 1) % entries.length;
        }

        if (firstDeletedIndex >= 0) {
            insertAt(firstDeletedIndex, key, value);
            return;
        }
        throw new IllegalStateException("Hashtable has no available slot");
    }

    A4Player get(String key) {
        validateKey(key);
        int index = findKey(key);
        if (index < 0) {
            return null;
        }
        return entries[index].value;
    }

    A4Player remove(String key) {
        validateKey(key);
        int index = findKey(key);
        if (index < 0) {
            return null;
        }

        A4Player removed = entries[index].value;
        entries[index] = null;
        deleted[index] = true;
        size--;
        return removed;
    }

    int size() {
        return size;
    }

    void printHashtable() {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] != null) {
                System.out.println("Element " + i + ": " + entries[i].value);
            } else if (deleted[i]) {
                System.out.println("Element " + i + ": <deleted>");
            } else {
                System.out.println("Element " + i + ": null");
            }
        }
    }

    private int findKey(String key) {
        int index = hashKey(key);
        for (int probes = 0; probes < entries.length; probes++) {
            if (entries[index] == null && !deleted[index]) {
                return -1;
            }
            if (entries[index] != null && entries[index].key.equals(key)) {
                return index;
            }
            index = (index + 1) % entries.length;
        }
        return -1;
    }

    private int hashKey(String key) {
        return key.length() % entries.length;
    }

    private void insertAt(int index, String key, A4Player value) {
        entries[index] = new StoredPlayer(key, value);
        deleted[index] = false;
        size++;
    }

    private void validateKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Hashtable key cannot be empty");
        }
    }
}

class StoredPlayer {
    final String key;
    A4Player value;

    StoredPlayer(String key, A4Player value) {
        this.key = key;
        this.value = value;
    }
}
