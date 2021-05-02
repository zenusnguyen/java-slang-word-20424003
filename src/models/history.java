package models;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
public class history {
    public static void ShowHistory() {
        try{
            File file = new File("data/history.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String tmp = "error";
            int inc = 0;
            System.out.println("!!!History!!!");

            try {
                tmp = reader.readLine();
                while (tmp != null) {
                    System.out.println( ++inc + ". " + tmp);
                    tmp = reader.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(history.class.getName())
                        .log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(history.class.getName())
                        .log(Level.SEVERE, null, ex);
            } finally {
                try {
                    reader.close();
                    // file.close();
                } catch (IOException ex) {
                    Logger.getLogger(history.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        } catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public static void addHistory(String s, String url) {
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
}
