import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {2, 10, 56, 34, 83, 6, 45, 67};
        bubbleSort(numbers.clone());
        selectionSort(numbers.clone());
    }

    public static void bubbleSort(int[] array) {
        System.out.println("-------- Bubble Sort Array -----------");
        System.out.println("Unsorted Array: " + Arrays.toString(array));

        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }

    public static void selectionSort(int[] array) {
        System.out.println("-------- Selection Sort Array -----------");
        System.out.println("Unsorted Array: " + Arrays.toString(array));

        for (int i = array.length - 1; i > 0; i--) {
            int smallestIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (array[j] < array[smallestIndex]) {
                    smallestIndex = j;
                }
            }

            int temp = array[smallestIndex];
            array[smallestIndex] = array[i];
            array[i] = temp;
        }

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }
}
