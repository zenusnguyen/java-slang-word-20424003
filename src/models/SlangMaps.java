package models;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class SlangMaps {
    protected Map<String, String> slangMap = new HashMap<String, String>();

    public SlangMaps(){}

    public Map<String, String> getSlangMap() {
        return this.slangMap;
    }

    public void setSlangMap(Map<String, String> slangMap) {
        this.slangMap = slangMap;
    }

    public void readFromFile() {
        try {
            File slangList = new File("data/slang.txt");
            Scanner scanner = new Scanner(slangList);
            while (scanner.hasNextLine()) {
                String slangStr = scanner.nextLine();
                SlangWord slang = new SlangWord(slangStr);
                this.slangMap.put(slang.slang, slang.definition);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SlangWord searchBySlang(String slang) {
        String definition = this.slangMap.get(slang);
        return new SlangWord(slang, definition);
    }

    public List<SlangWord> searchByDefinition(String keyword) {
        List<SlangWord> result = new ArrayList<SlangWord>();
        for (Map.Entry<String, String> entry : slangMap.entrySet()) {
            boolean match = Pattern.matches(".*" + keyword + ".*", entry.getValue());
            if (match) {
                result.add(new SlangWord(entry.getKey(), entry.getValue()));
            }
        }
        return result;
    }
}
