package pl.hubot.dev.nameGenerator;

import java.util.ArrayList;
import java.util.List;

public class WordChecker {
    int countSyllables(String word) {
        int count = 0;
        word = word.toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '\"' || word.charAt(i) == '\'' || word.charAt(i) == '-' || word.charAt(i) == ',' || word.charAt(i) == ')' || word.charAt(i) == '(') {
                word = word.substring(0,i)+word.substring(i+1, word.length());
            }
        }
        boolean isPrevVowel = false;
        for (int j = 0; j < word.length(); j++) {
            if (word.contains("a") || word.contains("e") || word.contains("i") || word.contains("o") || word.contains("u")) {
                if (isVowel(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length()-1))) {
                    if (!isPrevVowel) {
                        count++;
                        isPrevVowel = true;
                    }
                } else {
                    isPrevVowel = false;
                }
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    boolean checkIfAreOnlyVowels(String input) {
        return checkForCharacters(input, "aeiouy");
    }

    boolean checkIfAreOnlyConsonants(String input) {
        return checkForCharacters(input, "bcdfghjklmnpqrstvwxz");
    }

    boolean checkForCharacters(String input, String chars) {
        List<Boolean> logicalArray = new ArrayList<Boolean>();

        for (int i = 0; i < input.length(); i++) {
            boolean isChar = false;
            for (int j = 0; j < chars.toCharArray().length; j++) {
                if (input.charAt(i) == chars.toCharArray()[j]) isChar = true;
            }
            logicalArray.add(isChar);
        }

        boolean areGivenChars = false;
        if (logicalArray.size() == input.length()) {
            for (boolean logicalValue : logicalArray) {
                if (logicalValue) areGivenChars = true;
                else return false;
            }
        }

        return areGivenChars;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
