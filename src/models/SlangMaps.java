package models;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import configs.config;
public class SlangMaps {
    protected Map<String, String> slangMap = new HashMap<String, String>();
    public SlangMaps(){}
    public static Scanner keyboard = new Scanner(System.in);
    private static String slangPath = config.getSlangPath();
    private static String slangOriginPath = config.getSlangOriginPath();
    public void readFromFile(String url) {
        try {
            File slangList = new File(url);
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
                System.out.println("Cant write file");
            }finally{
                fr.close();
            }
        }catch(Exception e){
            System.out.println("Cant open file");
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

    public  void addSlandWord(String key, String def) {
        String choose;
        if(slangMap.containsKey(key)){
            System.out.println("Slang word duplicate !!!");
            System.out.println("Choose 1 to overwrite");
            System.out.println("Choose 2 to duplicate to new slang word");
            System.out.print("Your choose: ");
            choose = keyboard.nextLine();
            if(choose.equals("1")){
                slangMap.put(key, def);
                truncateFile(slangPath);
                System.out.println("Successfully !");
            }else{
                slangMap.put(key, slangMap.get(key) + "| " + def );
                truncateFile(slangPath);
                System.out.println("Duplicate successfully !");
            }

        }else{
            slangMap.put(key, def);
            addToFile(key + "`" + def + "\n", slangPath);
            System.out.println("Successfully !");
        }
    }
    public  void editSlangWord(String key, String def) {
        if(slangMap.containsKey(key)){
            slangMap.put(key, def);
            truncateFile(slangPath);
            System.out.println("Edit completed !");
        }else{
            System.out.println("Slang word not found !");
        }
    }
    public  void deleteSlangWord(String slangKey) {

        if(slangMap.containsKey(slangKey)){
            System.out.println("Are you sure? (y/n)");
            System.out.print("Your choose: ");
            String choose = keyboard.nextLine();
            if (choose.equals("y")){
                slangMap.remove(slangKey);
                truncateFile(slangPath);
                System.out.println("Delete successfully !");
            }
        }
        else{
            System.out.println("Slang word not found !");
        }
    }
    public void backUp() {
        System.out.println("\n1. Set this slang word list as origin.");
        System.out.println("2. Reset to origin.");
        System.out.print("Your choose: ");
        String choose = keyboard.nextLine();
        if(choose.equals("1")){
            truncateFile(slangOriginPath);
            System.out.println("Successfully !");
        }else{
            slangMap.clear();
            readFromFile(slangPath);
            truncateFile(slangPath);
            System.out.println("Successfully !");
        }
    }
    public  ArrayList<String> findWitdValue(String value) {
        ArrayList<String> keySet = new ArrayList<String>();
        for(String a : slangMap.keySet())
            if(slangMap.get(a).contains(value)){
                keySet.add(a);
            }
        return keySet;
    }
    public  String randomSlangWord() {
        Random generator = new Random();
        Object[] values = slangMap.values().toArray();
        Object randomValue = values[generator.nextInt(values.length)];
        String key = findWitdValue(String.valueOf(randomValue)).get(0);
        return key;
    }
    public String getByKey(String key){
        return slangMap.get(key);
    }
    public  int randomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public  void slangGame() {
        ArrayList<String> ansList = new ArrayList<String>();
        String tmp = randomSlangWord();
        for(int i = 0; i < 4; i++){
            while(ansList.contains(tmp))
                tmp = randomSlangWord();
            ansList.add(tmp);
        }
        int true_key_index = randomNumber(0, 3);
        System.out.println("\nThe mysterious slang word is: " + ansList.get(true_key_index));
        System.out.println("Answer: ");
        System.out.println("1. " + slangMap.get(ansList.get(0)));
        System.out.println("2. " + slangMap.get(ansList.get(1)));
        System.out.println("3. " + slangMap.get(ansList.get(2)));
        System.out.println("4. " + slangMap.get(ansList.get(3)));
        System.out.print("\nYour choose is: ");
        int choose = keyboard.nextInt();
        if (choose - 1 == true_key_index){
            System.out.println("Correct answer !");
        }else{
            System.out.println("Wrong answer !");
        }
    }
    public  void slangGameDefinition() {
        ArrayList<String> ansList = new ArrayList<String>();
        String tmp = randomSlangWord();
        for(int i = 0; i < 4; i++){
            while(ansList.contains(tmp))
                tmp = randomSlangWord();
            ansList.add(tmp);
        }
        int true_key_index = randomNumber(0, 3);
        System.out.println("The mysterious slang word is: " + slangMap.get(ansList.get(true_key_index)));
        System.out.println("Answer: ");
        System.out.println("1. " + ansList.get(0));
        System.out.println("2. " + ansList.get(1));
        System.out.println("3. " + ansList.get(2));
        System.out.println("4. " + ansList.get(3));
        System.out.print("\nYour choose is: ");
        int choose = keyboard.nextInt();

        if (choose - 1 == true_key_index){
            System.out.println("Correct answer !");
        }else{
            System.out.println("Wrong answer !");
        }
    }

}
