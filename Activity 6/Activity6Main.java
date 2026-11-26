public class Activity6Main {
    public static void main(String[] args) {
        A6Tree tree = new A6Tree();

        int[] values = {20, 34, 67, 24, 21, 17, 38};
        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("Tree values in descending order:");
        tree.traverseInOrderDescending();

        A6Node minimum = tree.getMin();
        A6Node maximum = tree.getMax();
        System.out.println("Minimum node: " + minimum);
        System.out.println("Maximum node: " + maximum);
    }
}

class A6Tree {
    private A6Node root;

    public void insert(int value) {
        if (root == null) {
            root = new A6Node(value);
            return;
        }
        root.insert(value);
    }

    public void traverseInOrderDescending() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }
        root.traverseInOrderDescending();
    }

    public A6Node getMin() {
        if (root == null) {
            return null;
        }
        return root.getMin();
    }

    public A6Node getMax() {
        if (root == null) {
            return null;
        }
        return root.getMax();
    }
}

class A6Node {
    private final int data;
    private A6Node leftChild;
    private A6Node rightChild;

    A6Node(int data) {
        this.data = data;
    }

    void insert(int value) {
        if (value == data) {
            return;
        }

        if (value < data) {
            if (leftChild == null) {
                leftChild = new A6Node(value);
                return;
            }
            leftChild.insert(value);
            return;
        }

        if (rightChild == null) {
            rightChild = new A6Node(value);
            return;
        }
        rightChild.insert(value);
    }

    A6Node getMin() {
        if (leftChild == null) {
            return this;
        }
        return leftChild.getMin();
    }

    A6Node getMax() {
        if (rightChild == null) {
            return this;
        }
        return rightChild.getMax();
    }

    void traverseInOrderDescending() {
        if (rightChild != null) {
            rightChild.traverseInOrderDescending();
        }
        System.out.println("Data " + data);
        if (leftChild != null) {
            leftChild.traverseInOrderDescending();
        }
    }

    @Override
    public String toString() {
        return "A6Node{data=" + data + '}';
    }
}
