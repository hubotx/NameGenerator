package pl.hubot.dev.nameGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class NameGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static Random random = new Random();

    void generate() {
        WordChecker wordChecker = new WordChecker();
        StringSimilarity stringSimilarity = new StringSimilarity();
        Dictionary dictionary = new Dictionary();
        final String filepath = "/usr/share/dict/american-english";
        dictionary.build(filepath);
        List<String> syllabes = combination(CHARACTERS, 3).parallelStream()
                .filter((x) -> wordChecker.countSyllables(x) > 1).collect(Collectors.toList());
        String word = "";
        while (stringSimilarity.similarity(word, dictionary.getRandomEntries(1)[0]) < 0.6) {
            word = syllabes.get(random.nextInt(syllabes.size())) + syllabes.get(random.nextInt(syllabes.size()));
        }
        System.out.println(word);
    }

    private Set<String> combination(String input, int n) {
        List<Character> letters = new ArrayList<Character>();
        for (int i = 0; i < input.length(); ++i)
            letters.add(input.charAt(i));
        Set<String> results = new HashSet<String>();
        combination("", letters, n, results);
        return results;
    }

    private void combination(String soFar, List<Character> letters, int n,
                            Set<String> results) {
        if (n == 0) {
            results.add(soFar);
            return;
        }

        int startIndex = soFar.length();
        if (startIndex >= letters.size())
            return;

        for (int i = startIndex; i < letters.size(); ++i) {
            // ch is the next candidate to add to the result that we're
            // building (soFar)
            char ch = letters.get(i);
            // Swap the characters at positions i and position startIndex.
            char temp = letters.get(startIndex);
            letters.set(i, temp);
            letters.set(startIndex, ch);

            // add ch to soFar, compute combinations of length n-1.
            // as startIndex is essentially soFar.length() this means that
            // the recursive call will only process the remainder of the list.
            combination(soFar + ch, letters, n - 1, results);

            // Swap the characters back - restore the original state.
            letters.set(i, ch);
            letters.set(startIndex, temp);
        }
    }
}
