package com.epam;

import java.util.List;

public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.addWord("abc");
        trie.addWord("abd");
        trie.addWord("abde");
        trie.addWord("ac");

        boolean isWordPresent = trie.findWord("abc");
        printStatus("abc", isWordPresent);

        isWordPresent = trie.findWord("abd");
        printStatus("abd", isWordPresent);

        isWordPresent = trie.findWord("ac");
        printStatus("ac", isWordPresent);

        isWordPresent = trie.findWord("abcd");
        printStatus("abcd", isWordPresent);

        List<String> wordsWithPrefix = trie.findWordWithPrefix("ab");

        System.out.println("Words with prefix ab are " + wordsWithPrefix);
    }

    private static void printStatus(String word, boolean isWordPresent){
        System.out.println("Does word " + word + " is present in Trie ? " + isWordPresent);
    }
}
