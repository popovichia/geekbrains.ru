/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.lesson3.task1;

import java.util.HashMap;

/**
 *
 * @author igor
 */
public class WordsArray {
    String[] words;
    public WordsArray(String[] words) {
        this.words = new String[words.length];
        System.arraycopy(words, 0, this.words, 0, words.length);
    }
    public String[] getWords() {
        String[] wordsCopy = new String[this.words.length];
        System.arraycopy(this.words, 0, wordsCopy, 0, this.words.length);
        return wordsCopy;
    }
    public HashMap<String, Integer> getUniqElements(String[] words) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String word : words) {
            hashMap.containsKey(word) == null ? hashMap.put(word, 1) : hashMap.put(word, Integer.MIN_VALUE);
        }
        return hashMap;
    }
}
