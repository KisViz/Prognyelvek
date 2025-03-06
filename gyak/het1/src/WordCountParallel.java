import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class WordCountParallel {

    public static void main(String[] args) throws InterruptedException, IOException {
//        System.out.println(Runtime.getRuntime().availableProcessors());

        int numberOfThreads = 1;

        String fileName = "C:\\Users\\Albert\\IdeaProjects\\Prognyelvek\\gyak\\het1\\src\\bible.txt";

        HashMap<String, Integer> totalWordCount = new HashMap<>();
        List<String> lines = new ArrayList<>();
        List<WordCounterThread> threads = new ArrayList<>();

        long startTime = System.nanoTime(); // időmérés kezdete

        // sorok beolvasása
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        // szálak létrehozása
        int chunkSize = lines.size() / numberOfThreads;
        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numberOfThreads - 1) ? lines.size() : (i + 1) * chunkSize;

            List<String> part = lines.subList(start, end);
            WordCounterThread thread = new WordCounterThread(part, new HashMap<>());
            thread.start();
            threads.add(thread);
        }

        // a szálak eredményeinek rögzítése
        for (WordCounterThread thread : threads) {
            thread.join();
            for (Map.Entry<String, Integer> entry : thread.wordCount.entrySet()) {
                totalWordCount.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        long endTime = System.nanoTime(); // időmérés vége

        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(totalWordCount.entrySet());
        sortedWords.sort(
                (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())
        );

        sortedWords.stream()
                .limit(10)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        System.out.println("Runtime: " + (endTime - startTime) / 1_000_000 + " ms");
    }
}

class WordCounterThread extends Thread {
    List<String> lines;
    HashMap<String, Integer> wordCount;

    public WordCounterThread(List<String> lines, HashMap<String, Integer> wordCount) {
        this.lines = lines;
        this.wordCount = wordCount;
    }

    @Override
    public void run() {
        for (String line : lines) {
            String[] words = line.toLowerCase().split("[^a-z]+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }
    }
}
