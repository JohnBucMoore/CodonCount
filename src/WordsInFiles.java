import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;

    public WordsInFiles() {
        wordMap = new HashMap<>();
    }

    private void addWordsFromFile(File f) {
        String name = f.getName();
        FileResource fr = new FileResource(f)
;        for (String word : fr.words()) {
            if (! wordMap.containsKey(word)) {
                ArrayList<String> fn = new ArrayList<>();
                fn.add(name);
                wordMap.put(word, fn);
            } else {
                if (! wordMap.get(word).contains(name)) {
                    wordMap.get(word).add(name);
                }
            }
        }
    }

    public void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public int maxNumber() {
        int max = 0;
        for (String key : wordMap.keySet()) {
            if (wordMap.get(key).size() > max) {
                max = wordMap.get(key).size();
            }
        }
        return max;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();
        for (String word : wordMap.keySet()) {
            if (wordMap.get(word).size() == number) {
                words.add(word);
            }
        }
        return words;
    }

    public void test(){
        buildWordFileMap();
        for (String key : wordMap.keySet()) {
            System.out.println(key+" appears in: "+wordMap.get(key));
        }
        System.out.println("The most common word occurs in "+maxNumber()+" files");
        System.out.println("Words that occur "+maxNumber()+" times are: "+wordsInNumFiles(maxNumber()));
    }

    public static void main(String[] args) {
        WordsInFiles words = new WordsInFiles();
        words.test();
    }
}
