/*
 * The 40 Method searches an UNSORTED list, because sorting first would be
 * way too much work for one search.
 *
 * It checks up to two entries per step: O(n) time and O(1) extra space.
 * No card shuffling, no sorting, and no asking the list for directions.
 */
 
public class Activity5Main {
    public static void main(String[] args) {
        String[] usernames = {
                "Arabit", "Corteas", "Forteat", "Boolean",
                "Fontanerl", "DelaCruz", "Joshify", "Mango"
        };

        String target = "Boolean";
        int foundAt = A5Search.search(usernames, target);

        System.out.println("The 40 Method searches an unsorted list from both ends.");
        System.out.println("Searching for: " + target);
        if (foundAt >= 0) {
            System.out.println("Found " + target + " at index " + foundAt + ".");
        } else {
            System.out.println(target + " was not found.");
        }

        String missingTarget = "NotAPlayer";
        System.out.println("\nSearching for: " + missingTarget);
        System.out.println("Result index: " + A5Search.search(usernames, missingTarget));
    }
}

class A5Search {
    private A5Search() {
    }

    static int search(String[] data, String target) {
        if (data == null) {
            throw new IllegalArgumentException("Search data cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Search target cannot be null");
        }

        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            if (target.equals(data[left])) {
                return left;
            }
            if (left != right && target.equals(data[right])) {
                return right;
            }
            left++;
            right--;
        }
        return -1;
    }
}
