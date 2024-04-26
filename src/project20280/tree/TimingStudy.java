package project20280.tree;

import project20280.tree.AVLTreeMap;
import project20280.tree.SplayTreeMap;

import java.util.Random;

public class TimingStudy {

    public static void main(String[] args) {
        // Create instances of AVL tree and Splay tree
        AVLTreeMap<Integer, Integer> avlTree = new AVLTreeMap<>();
        SplayTreeMap<Integer, Integer> splayTree = new SplayTreeMap<>();

        // Generate a set of random numbers
        Random random = new Random();
        int[] randomNumbers = new int[1000]; // Adjust the size as needed
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt(1000); // Generate random numbers between 0 and 999
        }

        // Insert random numbers into AVL tree and measure time
        long startTimeAVLInsert = System.nanoTime();
        for (int num : randomNumbers) {
            avlTree.put(num, num);
        }
        long endTimeAVLInsert = System.nanoTime();
        long durationAVLInsert = endTimeAVLInsert - startTimeAVLInsert;
        System.out.println("Time taken to insert random numbers into AVL tree: " + durationAVLInsert + " nanoseconds");

        // Insert random numbers into Splay tree and measure time
        long startTimeSplayInsert = System.nanoTime();
        for (int num : randomNumbers) {
            splayTree.put(num, num);
        }
        long endTimeSplayInsert = System.nanoTime();
        long durationSplayInsert = endTimeSplayInsert - startTimeSplayInsert;
        System.out.println("Time taken to insert random numbers into Splay tree: " + durationSplayInsert + " nanoseconds");

        // Perform insertions and deletions on trees of size N and measure time
        int N = 100; // Adjust the size as needed
        for (int i = 0; i < N; i++) {
            // Perform insertions and deletions on AVL tree
            long startTimeAVLInsertion = System.nanoTime();
            // Insertion operation on AVL tree
            long endTimeAVLInsertion = System.nanoTime();
            long durationAVLInsertion = endTimeAVLInsertion - startTimeAVLInsertion;
            System.out.println("Time taken for AVL insertion operation " + i + ": " + durationAVLInsertion + " nanoseconds");

            long startTimeAVLDeletion = System.nanoTime();
            // Deletion operation on AVL tree
            long endTimeAVLDeletion = System.nanoTime();
            long durationAVLDeletion = endTimeAVLDeletion - startTimeAVLDeletion;
            System.out.println("Time taken for AVL deletion operation " + i + ": " + durationAVLDeletion + " nanoseconds");

            // Perform insertions and deletions on Splay tree
            long startTimeSplayInsertion = System.nanoTime();
            // Insertion operation on Splay tree
            long endTimeSplayInsertion = System.nanoTime();
            long durationSplayInsertion = endTimeSplayInsertion - startTimeSplayInsertion;
            System.out.println("Time taken for Splay insertion operation " + i + ": " + durationSplayInsertion + " nanoseconds");

            long startTimeSplayDeletion = System.nanoTime();
            // Deletion operation on Splay tree
            long endTimeSplayDeletion = System.nanoTime();
            long durationSplayDeletion = endTimeSplayDeletion - startTimeSplayDeletion;
            System.out.println("Time taken for Splay deletion operation " + i + ": " + durationSplayDeletion + " nanoseconds");
        }
    }
}