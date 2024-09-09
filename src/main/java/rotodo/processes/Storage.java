package rotodo.processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

import rotodo.commands.AddCommand;
import rotodo.exception.InvalidInputException;
import rotodo.tasklist.TaskList;

/**
 * This class loads and stores the task list in a datafile.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Storage {
    private final String datafile;

    public Storage(String file) {
        datafile = file;
    }

    /**
     * Saves tasks from provided TaskList object to a datafile.
     *
     * @param tl TaskList object to save from
     * @throws IOException
     */
    public void saveList(TaskList tl) throws IOException {
        File f = new File(datafile);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileWriter fw = new FileWriter(datafile);
        fw.write(tl.saveList());
        fw.close();
    }

    /**
     * Loads tasks from datafile into TaskList object.
     *
     * @param tasks TaskList object to load data into
     */
    public void loadList(TaskList tasks) {
        try {
            FileReader fr = new FileReader(datafile);
            BufferedReader br = new BufferedReader(fr);
            Stream<String> sr = br.lines();

            sr.forEach(x -> {
                String[] token = x.split(" \\| ", 3);
                AddCommand.TaskType type = token[0].equals("T") ? AddCommand.TaskType.TODO
                    : token[0].equals("D") ? AddCommand.TaskType.DEADLINE : AddCommand.TaskType.EVENT;
                boolean isDone = token[1].equals("1");
                try {
                    AddCommand add = new AddCommand(type, token[2].split(" \\| "));
                    add.setStatus(isDone);
                    add.execute(tasks, null, null);
                } catch (InvalidInputException e) {
                    // do nothing
                }
            });
        } catch (FileNotFoundException e) {
            // Do nothing
        }
    }
}
