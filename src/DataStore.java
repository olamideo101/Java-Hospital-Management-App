import java.io.*;
import java.util.*;

public class DataStore {

    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading: " + filePath);
        }
        return lines;
    }

    public static void writeLine(String filePath, String content) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(content);
            bw.newLine();
        } catch (Exception e) {
            System.out.println("Error writing: " + filePath);
        }
    }
}
