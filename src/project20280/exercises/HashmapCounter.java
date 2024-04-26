package project20280.exercises;
import java.util.*;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;

import java.io.File;
import java.io.FileNotFoundException;

public class HashmapCounter {
    public static void main(String[] args) {
        ChainHashMap<String, Integer> wordFrequencyMap = new ChainHashMap<>();
        try {
            File inputFile = new File("C:\\Users\\seano\\OneDrive\\Documents\\datastructures20280-23\\src\\project20280\\exercises\\sample_text.txt");
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNext()) {
                String word = fileScanner.next().replaceAll("[^a-zA-Z]", "");
                word = word.chars().mapToObj(c -> Character.toLowerCase((char) c))
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
                if (!word.isEmpty()) {
                    Integer count = wordFrequencyMap.get(word);
                    if (count == null) {
                        count = 0;
                    }
                    count++; // increment the count
                    wordFrequencyMap.put(word, count); // update the count
                }
            }
            fileScanner.close();
            List<Entry<String, Integer>> sortedWordList = sortEntriesByValue(wordFrequencyMap);
            for (Entry<String, Integer> entry : sortedWordList) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + ex.getMessage());
        }

        System.out.println("Unique word counts: " + wordFrequencyMap.size());
    }

    public static List<Entry<String, Integer>> sortEntriesByValue(ChainHashMap<String, Integer> inputMap) {
        List<Entry<String, Integer>> entries = new ArrayList<>();
        for (Entry<String, Integer> entry : inputMap.entrySet()) {
            entries.add(entry);
        }

        // Sort using Collections.sort() method
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                return entry1.getValue().compareTo(entry2.getValue());
            }
        });

        return entries;
    }
}
