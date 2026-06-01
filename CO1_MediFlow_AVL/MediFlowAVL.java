package CO1_MediFlow_AVL;

import java.util.Scanner;

public class MediFlowAVL {

    // Node Class
    class Node {
        int data, height;
        Node left, right;

        Node(int value) {
            data = value;
            height = 1;
        }
    }

    Node root;

    // Height
    int height(Node n) {
        if (n == null)
            return 0;
        return n.height;
    }

    // Maximum
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Balance Factor
    int getBalance(Node n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    // Right Rotation
    Node rightRotate(Node y) {

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left Rotation
    Node leftRotate(Node x) {

        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert
    Node insert(Node node, int value) {

        if (node == null)
            return new Node(value);

        if (value < node.data)
            node.left = insert(node.left, value);

        else if (value > node.data)
            node.right = insert(node.right, value);

        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && value < node.left.data)
            return rightRotate(node);

        // RR
        if (balance < -1 && value > node.right.data)
            return leftRotate(node);

        // LR
        if (balance > 1 && value > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && value < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Minimum Value Node
    Node minValueNode(Node node) {

        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    // Delete
    Node delete(Node root, int value) {

        if (root == null)
            return root;

        if (value < root.data)
            root.left = delete(root.left, value);

        else if (value > root.data)
            root.right = delete(root.right, value);

        else {

            if ((root.left == null) || (root.right == null)) {

                Node temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null) {
                    root = null;
                } else {
                    root = temp;
                }

            } else {

                Node temp = minValueNode(root.right);

                root.data = temp.data;

                root.right = delete(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // LL
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // LR
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RR
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // RL
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Search
    boolean search(Node node, int value) {

        if (node == null)
            return false;

        if (node.data == value)
            return true;

        if (value < node.data)
            return search(node.left, value);

        return search(node.right, value);
    }

    // Display
    void display(Node node) {

        if (node != null) {

            display(node.left);

            System.out.print(node.data + " ");

            display(node.right);
        }
    }

    public static void main(String[] args) {

        MediFlowAVL tree = new MediFlowAVL();

        Scanner sc = new Scanner(System.in);

        int n;

        System.out.println("Enter number of Patient IDs:");
        n = sc.nextInt();

        System.out.println("Enter Patient IDs:");

        // User Input for Insertion
        for (int i = 0; i < n; i++) {

            int value = sc.nextInt();

            tree.root = tree.insert(tree.root, value);
        }

        System.out.println("\nAVL Tree after Insertion:");
        tree.display(tree.root);

        // Search
        System.out.println("\n\nEnter Patient ID to Search:");
        int searchValue = sc.nextInt();

        if (tree.search(tree.root, searchValue))
            System.out.println("Patient ID Found");
        else
            System.out.println("Patient ID Not Found");

        // Delete
        System.out.println("\nEnter Patient ID to Delete:");
        int deleteValue = sc.nextInt();

        tree.root = tree.delete(tree.root, deleteValue);

        System.out.println("\nAVL Tree after Deletion:");
        tree.display(tree.root);

        sc.close();
    }
}
