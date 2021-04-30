import models.SlangWord;
import models.SlangMaps;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static Scanner keyboard = new Scanner(System.in);
    public static void Pause(){
        System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
    public static void main(String[] args) {
         var  SlangMaps = new SlangMaps();
        SlangMaps.readFromFile();
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
                    String key = keyboard.nextLine();
                    SlangWord value = SlangMaps.searchBySlang(key);
                    if(!(value == null)){
//                        Append_to_file(key + "   :   " + value + "\n", "history.txt");
                        System.out.println(value);
                    }
                    else{
                        System.out.println("No definition founded !!");
                    }
                    break;
                case 2:
                    System.out.print("Your definition: ");
                    String def = keyboard.nextLine();
                    List<SlangWord> keySet = SlangMaps.searchByDefinition(def);
                    System.out.println("Slang word of " + def + " is: ");
                    for(SlangWord slang: keySet){
//                        Append_to_file(a + "   :   " + slangMap.get(a) + "\n", "history.txt");
                        System.out.println(slang);
                    }
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
}
