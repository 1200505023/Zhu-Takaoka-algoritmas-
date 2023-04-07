import java.io.;
import java.util.;

public class WordCounter {
    public static void main(String[] args) throws IOException {
        File file = new File("alice_in_wonderland.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Kelime öbekleri
        String[] wordsToFind = {"upon", "sigh", "Dormouse", "jury-box", "swim"};

        // Hash tablosu oluşturulması
        Map<Integer, String> hashToWordMap = new HashMap<>();
        for (String word : wordsToFind) {
            int hash = calculateHash(word);
            hashToWordMap.put(hash, word);
        }

        while (line != null) {
            // Satırdaki kelimelerin hash değerlerinin hesaplanması
            String[] words = line.split("\W+");
            for (String word : words) {
                int hash = calculateHash(word);
                String wordToFind = hashToWordMap.get(hash);
                if (wordToFind != null && wordToFind.equals(word)) {
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
            line = reader.readLine();
        }
        reader.close();

        // Sonuçların yazdırılması
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Zhu-Takaoka algoritması ile hash değerinin hesaplanması
    private static int calculateHash(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash = 31 * hash + c;
        }
        return hash;
    }
}


