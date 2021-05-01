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
            File slangList = new File("src/data/slang.txt");
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
    public static void addToFile(String s, String url) {
        try {
            File file = new File(url);
            FileWriter fr = new FileWriter(file, true);

            try {
                fr.write(s);
            } catch (Exception e) {
                System.out.println("cant write file");
            }finally{
                fr.close();
            }
        }catch(Exception e){
            System.out.println("cant open file");
        }
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
    public  void truncateFile(String url) {
        File file = new File(url);
        try (PrintWriter pw = new PrintWriter(file)) {
            try {
                for(String a : slangMap.keySet()){
                    pw.println(a + "`" + slangMap.get(a));
                }
            } catch (Exception e) {
                System.out.println("Can't truncate to file");
            } finally{
                pw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void addSlandWord(String slang_key, String def_value) {
        Scanner keyboard = new Scanner(System.in);
        String choose;
        if(slangMap.containsKey(slang_key)){
            System.out.println("!!! Slang word duplicate !!!");
            System.out.println(">> Choose 1 to overwrite");
            System.out.println(">> Choose 2 to duplicate to new slang word");
            System.out.print("Your choose: ");
            choose = keyboard.nextLine();
            if(choose.equals("1")){
                slangMap.put(slang_key, def_value);
                truncateFile("src/data/slang.txt");
                System.out.println("!!! Overwrite successfully !!!");
            }else{
                slangMap.put(slang_key, slangMap.get(slang_key) + "| " + def_value );
                truncateFile("src/data/slang.txt");
                System.out.println("!!! Duplicate successfully !!!");
            }

        }else{
            slangMap.put(slang_key, def_value);
            addToFile(slang_key + "`" + def_value + "\n", "src/data/slang.txt");
            System.out.println("!!! Add new slangword successfully !!!");
        }
    }

}
