package wordle;

import project20280.interfaces.Entry;
import project20280.priorityqueue.HeapPriorityQueue;
import project20280.tree.LinkedBinaryTree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wordle {

    String fileName = "wordle/resources/dictionary.txt";
    //String fileName = "wordle/resources/extended-dictionary.txt";
    List<String> dictionary = null;
    final int num_guesses = 5;
    final long seed = 42;
    //Random rand = new Random(seed);
    Random rand = new Random();

    static final String winMessage = "CONGRATULATIONS! YOU WON! :)";
    static final String lostMessage = "YOU LOST :( THE WORD CHOSEN BY THE GAME IS: ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";

    Wordle() {
        Map<String, String> huffmanTree = null;

        this.dictionary = readDictionary(fileName);

        System.out.println("dict length: " + this.dictionary.size());
        System.out.println("dict: " + dictionary);

        buildHuffmanTree(dictionary);

        int asciiBits = calculateAsciiBits(dictionary);
        int huffmanBits = calculateHuffmanBits(dictionary);
        double compressionRatio = (double) huffmanBits / asciiBits;

        System.out.println("Number of bits required for ASCII coding: " + asciiBits);
        System.out.println("Number of bits required for Huffman coding: " + huffmanBits);
        System.out.println("Compression ratio: " + compressionRatio);

    }

    public int calculateAsciiBits(List<String> wordList) {
        int totalBits = 0;
        for (String word : wordList) {
            totalBits += word.length() * 8; // Assuming 8 bits per character for ASCII
        }
        return totalBits;
    }
    public int calculateHuffmanBits(List<String> wordList) {
        HeapPriorityQueue<Integer, LinkedBinaryTree> myQueue = new HeapPriorityQueue<>();

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            LinkedBinaryTree<Character> leafTree = new LinkedBinaryTree<>();
            leafTree.addRoot(entry.getKey());
            myQueue.insert(entry.getValue(), leafTree);
        }

        while (myQueue.size() > 1) {
            Entry<Integer, LinkedBinaryTree> tree1 = myQueue.removeMin();
            Entry<Integer, LinkedBinaryTree> tree2 = myQueue.removeMin();

            Integer combinedFreq = tree1.getKey() + tree2.getKey();
            LinkedBinaryTree<Character> leftChild = tree1.getValue();
            LinkedBinaryTree<Character> rightChild = tree2.getValue();

            LinkedBinaryTree<Character> parentNode = new LinkedBinaryTree<>();
            parentNode.addRoot(null);
            parentNode.attach(parentNode.root(), leftChild, rightChild);

            myQueue.insert(combinedFreq, parentNode);
        }
        Entry<Integer, LinkedBinaryTree> rootEntry = myQueue.removeMin();
        LinkedBinaryTree<Character> huffmanTree = rootEntry.getValue();

        int totalBits = 0;
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                totalBits += getHuffmanBitLength(huffmanTree, c);
            }
        }
        return totalBits;
    }

    public int getHuffmanBitLength(LinkedBinaryTree<Character> huffmanTree, char c) {
        if (huffmanTree == null || huffmanTree.root() == null) {
            return -1; // Invalid input or empty tree
        }
        // Assuming getNode() is a method that returns the Node for a given Position
        LinkedBinaryTree.Node<Character> rootNode = huffmanTree.getNode(huffmanTree.root());
        return findBitLength(huffmanTree, rootNode, c, 0);
    }

    private int findBitLength(LinkedBinaryTree<Character> huffmanTree, LinkedBinaryTree.Node<Character> node, char c, int depth) {
        if (node == null) {
            return -1; // Character not found
        }
        if (node.getLeft() == null && node.getRight() == null && node.getElement() == c) {
            return depth;
        }
        int leftDepth = findBitLength(huffmanTree, node.getLeft(), c, depth + 1);
        if (leftDepth != -1) {
            return leftDepth;
        }
        return findBitLength(huffmanTree, node.getRight(), c, depth + 1);
    }


    public void buildHuffmanTree(List<String> wordList) {
        HeapPriorityQueue<Integer, LinkedBinaryTree> myQueue = new HeapPriorityQueue<>();

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            LinkedBinaryTree<Character> leafTree = new LinkedBinaryTree<>();
            leafTree.addRoot(entry.getKey());
            myQueue.insert(entry.getValue(), leafTree);
        }

        while (myQueue.size() > 1) {
            Entry<Integer, LinkedBinaryTree> tree1 = myQueue.removeMin();
            Entry<Integer, LinkedBinaryTree> tree2 = myQueue.removeMin();

            Integer combinedFreq = tree1.getKey() + tree2.getKey();
            LinkedBinaryTree<Character> leftChild = tree1.getValue();
            LinkedBinaryTree<Character> rightChild = tree2.getValue();

            LinkedBinaryTree<Character> parentNode = new LinkedBinaryTree<>();
            parentNode.addRoot(null);
            parentNode.attach(parentNode.root(), leftChild, rightChild);

            myQueue.insert(combinedFreq, parentNode);
        }
        Entry<Integer, LinkedBinaryTree> rootEntry = myQueue.removeMin();
        LinkedBinaryTree<Character> huffmanTree = rootEntry.getValue();
        System.out.println("Huffman Tree: " + huffmanTree.toBinaryTreeString());
    }


    public static void main(String[] args) {
        Wordle game = new Wordle();

        String target = game.getRandomTargetWord();

        //System.out.println("target: " + target);

        game.play(target);

    }

    public void play(String target) {
        // TODO
        // TODO: You have to fill in the code
        for(int i = 0; i < num_guesses; ++i) {
            String guess = getGuess();

            if(guess == target) { // you won!
                win(target);
                return;
            }

            // the hint is a string where green="+", yellow="o", grey="_"
            // didn't win ;(
            String [] hint = {"_", "_", "_", "_", "_"};

            for (int k = 0; k < 5; k++) {
                if(guess.charAt(k) == target.charAt(k)) {
                    hint[k] = "+"; // green
                }
            }
            for (int k = 0; k < 5; k++) {
                if(hint[k] == "_") {
                    if(target.contains(String.valueOf(guess.charAt(k)))) {
                        hint[k] = "o"; // yellow
                    } else {
                        hint[k] = "-"; // grey
                    }
                }
            }

            // after setting the yellow and green positions, the remaining hint positions must be "not present" or "_"
            System.out.println("hint: " + Arrays.toString(hint));

            // check for a win
            int num_green = 0;
            for(int k = 0; k < 5; ++k) {
                if(hint[k] == "+") {
                    num_green += 1;
                }
            }
            if(num_green == 5) {
                win(target);
                return;
            }
        }

        lost(target);
    }

    public void lost(String target) {
        System.out.println();
        System.out.println(lostMessage + target.toUpperCase() + ".");
        System.out.println();

    }
    public void win(String target) {
        System.out.println(ANSI_GREEN_BACKGROUND + target.toUpperCase() + ANSI_RESET);
        System.out.println();
        System.out.println(winMessage);
        System.out.println();
    }

    public String getGuess() {
        Scanner myScanner = new Scanner(System.in, StandardCharsets.UTF_8.displayName());  // Create a Scanner object
        System.out.println("Guess:");

        String userWord = myScanner.nextLine();  // Read user input
        userWord = userWord.toLowerCase(); // covert to lowercase

        // check the length of the word and if it exists
        while ((userWord.length() != 5) || !(dictionary.contains(userWord))) {
            if ((userWord.length() != 5)) {
                System.out.println("The word " + userWord + " does not have 5 letters.");
            } else {
                System.out.println("The word " + userWord + " is not in the word list.");
            }
            // Ask for a new word
            System.out.println("Please enter a new 5-letter word.");
            userWord = myScanner.nextLine();
        }
        return userWord;
    }

    public String getRandomTargetWord() {
        // generate random values from 0 to dictionary size
        return dictionary.get(rand.nextInt(dictionary.size()));
    }
    public List<String> readDictionary(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line
            while ((strLine = reader.readLine()) != null) {
                wordList.add(strLine.toLowerCase());
            }
            //Close the input stream
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return wordList;
    }
}
