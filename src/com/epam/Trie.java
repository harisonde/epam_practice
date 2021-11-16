package com.epam;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private boolean endOfWord;
    private String word;
    private final Trie[] tries = new Trie[26];


    public void addWord(String word) {
        if (word != null && !word.isEmpty()) {
            addWordUtil(word);
        }
    }

    public boolean findWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        return findWordUtil(word);
    }

    public List<String> findWordWithPrefix(String prefix) {
        List<String> wordsWithPrefix = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()) {
            return wordsWithPrefix;
        }

        Trie parent = findParentOfWordsWithPrefix(prefix);

        if (parent != null) {
            findWordsWithPrefixUtil(parent, wordsWithPrefix);
        }

        return wordsWithPrefix;
    }

    private void addWordUtil(String word) {
        StringBuilder wordToStore = new StringBuilder();

        Trie parent = this;
        for (int index = 0; index < word.length(); index++) {
            char currentChar = word.charAt(index);
            wordToStore.append(currentChar);

            Trie trie = getTrieForCurrentChar(parent, currentChar, wordToStore.toString());

            if (index == word.length() - 1) {
                trie.endOfWord = true;
            }

            parent = trie;
        }
    }

    private boolean findWordUtil(String word) {
        Trie parent = this;

        for (int index = 0; index < word.length(); index++) {
            int charIndex = getCharIndex(word.charAt(index));
            parent = parent.tries[charIndex];

            if (parent == null) {
                return false;
            }

            if (index == word.length() - 1 && parent.endOfWord) {
                return true;
            }
        }
        return false;
    }

    private void findWordsWithPrefixUtil(Trie parent, List<String> wordsWithPrefix) {
        for (int index = 0; index < parent.tries.length; index++) {
            Trie trie = parent.tries[index];

            if (trie != null) {
                if (trie.endOfWord) {
                    wordsWithPrefix.add(trie.word);
                }
                findWordsWithPrefixUtil(trie, wordsWithPrefix);
            }
        }
    }

    private Trie findParentOfWordsWithPrefix(String prefix) {
        Trie parent = this;
        for (int index = 0; index < prefix.length(); index++) {
            int charIndex = getCharIndex(prefix.charAt(index));
            parent = parent.tries[charIndex];

            if (parent == null) {
                return null;
            }
        }
        return parent;
    }

    private int getCharIndex(char character) {
        return character - 'a';
    }

    private Trie getTrieForCurrentChar(Trie parent, char character, String wordToStore) {
        int charIndex = getCharIndex(character);
        Trie trie = parent.tries[charIndex];

        if (trie == null) {
            trie = new Trie();
            trie.word = wordToStore;
            parent.tries[charIndex] = trie;
        }

        return trie;
    }
}
