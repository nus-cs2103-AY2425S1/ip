import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String DATA_ADDRESS;

    public Storage(String address) {
        DATA_ADDRESS = address;
    }

    public String[] load() {
        ArrayList<String> out = new ArrayList<>();
        try {
            File f = new File(DATA_ADDRESS);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                out.add(s.nextLine());
            }
            return out.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ");
            return new String[0];
        }
    }

    public void save(String[] arr) {
        try {
            FileWriter fw = new FileWriter(DATA_ADDRESS);

            for (String s : arr) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to save data");
        }
    }
}
