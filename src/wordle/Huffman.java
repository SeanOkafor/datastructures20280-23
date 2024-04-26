package wordle;

import project20280.interfaces.BinaryTree;
import project20280.interfaces.Position;
import project20280.priorityqueue.HeapPriorityQueue;
import project20280.interfaces.Entry;
import project20280.tree.LinkedBinaryTree;

import java.util.HashMap;
import java.util.Map;

class MyHuffman {
    private HeapPriorityQueue<Integer, LinkedBinaryTree> myQueue;

    public MyHuffman() {
        myQueue = new HeapPriorityQueue<>();
    }

    public LinkedBinaryTree<Character> buildHuffmanTree(String input) {

        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            LinkedBinaryTree<Character> leafTree = new LinkedBinaryTree<>();
            leafTree.addRoot(entry.getKey());
            myQueue.insert(entry.getValue(), leafTree);
        }

        while (myQueue.size() > 1) {
            Entry<Integer, LinkedBinaryTree> tree1 = myQueue.removeMin();
            Entry<Integer, LinkedBinaryTree> tree2 = myQueue.removeMin();

            // Combine two smallest nodes into a new node and insert it back into the queue
            Integer combinedFreq = tree1.getKey() + tree2.getKey();
            LinkedBinaryTree<Character> leftChild = tree1.getValue();
            LinkedBinaryTree<Character> rightChild = tree2.getValue();

            LinkedBinaryTree<Character> parentNode = new LinkedBinaryTree<>();
            parentNode.addRoot(null);
            parentNode.attach(parentNode.root(), leftChild, rightChild);

            myQueue.insert(combinedFreq, parentNode);
        }
        Entry<Integer, LinkedBinaryTree> rootEntry = myQueue.removeMin();
        return rootEntry.getValue();
    }

    public static void main(String[] args) {
        MyHuffman myHuffman = new MyHuffman();
        LinkedBinaryTree<Character> huffmanTree = myHuffman.buildHuffmanTree("ABCDE");
        System.out.println(huffmanTree.toBinaryTreeString());
    }
}