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

    public void test(){
        for (String key : wordMap.keySet()) {
            System.out.println(key+" appears in: "+wordMap.get(key));
        }
    }

    public static void main(String[] args) {
        WordsInFiles words = new WordsInFiles();
        words.test();
    }
}
