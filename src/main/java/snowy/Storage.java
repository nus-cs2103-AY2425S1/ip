package snowy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }
    
    public ArrayList<String> load() throws SnowyException{
        ArrayList<String> lines = new ArrayList<>();
        try {
            file.createNewFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                lines.add(nextLine);
            }


        } catch (IOException e) {
            throw new SnowyException();
        }
        return lines;
    }

    public void save(String saveLines) throws SnowyException{
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(saveLines);
            writer.close();
        } catch (IOException e) {
            throw new SnowyException();
        }
    }

}
