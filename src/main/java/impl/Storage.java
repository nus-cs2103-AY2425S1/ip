package impl;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    /**
     * Stores and Loads Tasks into a text file
     * <p>
     * Tasks stored in this syntax:
     * type Description /By/From /To | Done
     *
     */
    File file;
    String filePath;
    Danny danny;

    public Storage(String path, Danny bot){
        file = new File(path);
        filePath = path;
        danny = bot;
    }

    public void loadTask() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String line;
        while(s.hasNext()){
            line = s.nextLine();
            if(!line.isEmpty()){
                danny.parseString(line.split(" \\| ")[0]);
                if (Objects.equals(line.split(" \\| ")[1], "true")) {
                    danny.setLastDone();
                }
            }
        }
    }

    public void saveTask() throws IOException {
        String write = danny.saveTasks();
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(String.valueOf(write));
        fw.close();
    }

}
