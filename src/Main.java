import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static HashMap<String, String> slangMap = new HashMap<String, String>();
    public static ArrayList<String> history = new ArrayList<String>();
    public static Scanner keyboard = new Scanner(System.in);
    public static void Pause(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    public static int randomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static HashMap<String, String> SlandMap(String url) {

        try{
            File file = new File(url);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String tmp = "error";
            String[] strSplit;

            try {
                tmp = reader.readLine();
                while (tmp != null) {
                    strSplit = tmp.split("`");
                    System.out.println(strSplit);
                    slangMap.put(strSplit[0], strSplit[1]);
                    tmp = reader.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, null, ex);
            } finally {
                try {
                    reader.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }

            return slangMap;
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
        return slangMap;
    }

    public static String findSlang(String keyWord) {
        return slangMap.get(keyWord);
    }


    public static void Menu(){
        String out = "";
        while(!"e".equals(out)){
            System.out.println("1. Find with slang word");
            System.out.println("2. Find with definition");
            System.out.println("3. Show history");
            System.out.println("4. Add new slang word");
            System.out.println("5. Edit slang word");
            System.out.println("6. Delete slang word");
            System.out.println("7. Back up origin");
            System.out.println("8. Random slang word");
            System.out.println("9. Game of slang word");
            System.out.println("10. Game of definition");
            System.out.println("11. Exit");
            System.out.print("Enter your choose: ");
            int choose = keyboard.nextInt();
            String c = keyboard.nextLine();
            switch (choose) {
                case 1:
                    System.out.print("Your slang word: ");

                    break;
                case 2:
                    System.out.print("Your definition: ");

                    break;
                case 3:
                    System.out.print("Your history: ");
                    break;
                case 4:
                    System.out.print("Enter slang word: ");
                    String slang_key = keyboard.nextLine();
                    System.out.print("Enter definition: ");

                    break;
                case 5:
                    System.out.print("Enter slang word you want to edit: ");

                    break;
                case 6:
                    System.out.print("Slang word you want to delete: ");
                    String delete_slang = keyboard.nextLine();

                    break;
                case 7:

                    break;
                case 8:
                    System.out.println("Your slang word today is: ");

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:
                    out = "e";
                    break;
            }
            Pause();
        }
    }
    public static void main(String[] args) {
//       Menu();
        SlandMap("src/slang.txt");
    }
}
