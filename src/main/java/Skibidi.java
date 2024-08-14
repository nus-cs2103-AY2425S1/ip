import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Skibidi {
    static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        String filePath = "resources/skibidi-ascii.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }

        Skibidi.printSeparator();
        System.out.println("EXITING APPLICATION");
        Skibidi.printSeparator();
    }
}
