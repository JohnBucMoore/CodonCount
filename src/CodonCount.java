import java.util.HashMap;
import edu.duke.FileResource;

public class CodonCount {
    private HashMap<String, Integer> codonCount;

    public CodonCount() {
        codonCount = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codonCount.clear();
        String currCodon;
        for (int i = start; i < dna.length(); i+=3) {
            int end = i+3;
            if (end < dna.length()) {
                currCodon = dna.substring(i, end);
            } else {
                break;
            }
            if (! codonCount.containsKey(currCodon)) {
                codonCount.put(currCodon, 1);
            } else {
                codonCount.put(currCodon, codonCount.get(currCodon) + 1);
            }
        }
    }

    public String getMostCommonCodon() {
        int occurences = 0;
        String k = "";
        for (String key : codonCount.keySet()) {
            if (codonCount.get(key) > occurences) {
                k = key;
                occurences = codonCount.get(key);
            }
        }
        return k;
    }

    public void printCodonCounts(int start, int end) {
        for (String key : codonCount.keySet()) {
            if (codonCount.get(key) >= start && codonCount.get(key) <= end) {
                System.out.println(key+"\t"+codonCount.get(key));
            }
        }
    }

    public void test() {
        FileResource fr = new FileResource();
        String s = fr.asString().toUpperCase();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, s);
            System.out.println("Number of unique codons: "+codonCount.size());
            System.out.println("Most common codon: "+getMostCommonCodon()+"\t"+codonCount.get(getMostCommonCodon()));
            printCodonCounts(1,5);
        }
    }

    public static void main(String[] args) {
        CodonCount c = new CodonCount();
        c.test();
    }
}
