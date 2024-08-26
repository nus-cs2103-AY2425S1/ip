package rotodo.processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.stream.Stream;

import rotodo.tasklist.TaskList;

public class Storage {
    private final String datafile;
    private static Storage storage;

    private Storage(String file) {
        datafile = file;
    }

    public static Storage of(String file) {
        if (storage != null) {
            return storage;
        }
        storage = new Storage(file);
        return storage;
    }

    public static Storage of() {
        return storage;
    }

    public void saveList() throws IOException {
        TaskList tl = TaskList.of();
        File f = new File(datafile);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileWriter fw = new FileWriter(datafile);
        fw.write(tl.saveList());
        fw.close();
    }

    public void loadList() {
        TaskList tl = TaskList.of();

        try { 
            FileReader fr = new FileReader(datafile);
            BufferedReader br = new BufferedReader(fr);
            Stream<String> sr = br.lines();

            sr.forEach(x -> {
                String[] token = x.split(" \\| ");
                tl.addTask(Arrays.copyOfRange(token, 1, token.length));
            }); 
        } catch (FileNotFoundException e) {
            // Do nothing
        }
    }
}
