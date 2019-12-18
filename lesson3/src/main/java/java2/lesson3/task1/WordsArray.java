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
    int arraySize;
    public WordsArray(String[] words) {
        this.arraySize = words.length;
        this.words = new String[arraySize];
        System.arraycopy(words, 0, this.words, 0, arraySize);
    }
    public HashMap<String, Integer> getUniqElements(String[] words) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for(String word : words) {
            if (hashMap.get(word) == null) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, hashMap.get(word) + 1);
            }
        }
        return hashMap;
    }
}
