import java.io.File;

public class FileTest {
    public static void main(String[] args) {
        File file = new File("./test.txt");
        try {
            file.createNewFile();
        } catch (Exception e) {
            System.out.println("Error creating file");
        }
        System.out.println(file.exists());
    }
}
