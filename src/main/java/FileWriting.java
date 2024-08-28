import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void main(String[] args) {
        String file2 = "BuddyBot.txt";
        try {
            writeToFile(file2, "first" + System.lineSeparator() + "second");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
