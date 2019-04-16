import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Andmevood {
    private static int pikkus(String s) {
        return Integer.parseInt(s.split(" ")[1]);
    }

    public static void main(String[] arg) throws Exception {

        String input = "sisend.txt";
        String output = "valjund.txt";
        ArrayList<String> words = new ArrayList<>();
        Map<String, Long> occurrence;
        ArrayList<String> wordsWithOccurrence = new ArrayList<>();

        // Read the file

        File file = new File(input);
        Scanner scan = new Scanner(file);

        while (scan.hasNext()) {
            words.add(scan.next()
                          .replaceAll("[^a-zA-Z]", "")
                          .toLowerCase()); // lowercase and remove punctuation
        }

        // Group by occurrence

        occurrence = words.stream()
                          .collect(groupingBy(Function.identity(), counting()));

        // Merge keys and values

        for (Map.Entry<String, Long> entry : occurrence.entrySet()) {
            wordsWithOccurrence.add(entry.getKey() + " " + entry.getValue());
        }

        // Sort and print word with occurrence

        PrintWriter pw = new PrintWriter(new FileWriter(output));
        wordsWithOccurrence
                .stream()
                .sorted((s1, s2) -> pikkus(s2) - pikkus(s1))
                .collect(Collectors.toList())
                .forEach(pw::println);
        pw.close();
    }
}
