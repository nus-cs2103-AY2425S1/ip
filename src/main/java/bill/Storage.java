package bill;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Storage class stores the relevant file and directories paths, and loads
 * and writes to the persistent data of tasks.
 */
public class Storage {
    private Path pathStorageDirectory;
    private Path pathStorageFile;

    /**
     * Initializes Storage, reading and writing from persistent data to save and update list of task state.
     */
    public Storage() {
        // set directory for output, and output file,
        // in this case <>\Desktop\CS2103T_IP\data, where <> is the autodetected home directory
        String home = System.getProperty("user.home");
        pathStorageDirectory = Paths.get(home, "Desktop", "CS2103T_IP", "data");
        pathStorageFile = Paths.get(home, "Desktop", "CS2103T_IP", "data", "bill.txt");
    }

    /**
     * Saves updated list to bill.txt.
     *
     * @param userList Current accessible state of mutable list.
     * @throws IOException If there is an error writing to the bill.txt file.
     */
    public void saveList(ArrayList<Task> userList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathStorageFile)));
        for (int i = 0; i < userList.size(); i++) {
            writer.write((i + 1) + "." + userList.get(i));
            writer.newLine();
        }
        writer.close();
    }

    /**
     * Loads bill.txt tasks into a mutable userlist.
     *
     * @param ui User Interface access to leverage in built methods to handle parsing of tasks
     *          from bill.txt to mutable userlist.
     * @param userList Current accessible state of mutable list
     * @param tasks All helper methods associated with userList, such as getters.
     * @throws IOException If there is an error reading from the bill.txt file.
     * @throws BillException If there is an unrecognisable format in the bill.txt file.
     */
    public void loadStorage(Ui ui, ArrayList<Task> userList, TaskList tasks) throws IOException, BillException {
        boolean directoryExists = Files.exists(pathStorageDirectory);
        boolean fileExists = Files.exists(pathStorageFile);

        // if directory doesn't exist
        if (!directoryExists) {
            // make the directory and the file
            Files.createDirectory(pathStorageDirectory);
        }

        // if directory exists but file doesn't
        if (!fileExists) {
            // make the file
            Files.createFile(pathStorageFile);
        }

        File file = new File(String.valueOf(pathStorageFile));

        // if text file empty return early to main function
        if (file.length() == 0) {
            return;
        }

        // while loop until finish reading bill.txt or error
        BufferedReader lineReader = new BufferedReader(new FileReader(String.valueOf(pathStorageFile)));

        String line;
        while ((line = lineReader.readLine()) != null) {
            // get 4 char the route, note index from 0
            char route = line.charAt(3);

            // get 7 char the mark
            char mark = line.charAt(6);
            boolean isMarked = mark == 'X';

            // get index of first char after marking [ ] or [X] and the space after
            int firstSpace = Math.max(line.indexOf("[ ]"), line.indexOf("[X]")) + 4;

            // get index
            int index = Integer.parseInt(String.valueOf(line.charAt(0)));

            // load storage into list var
            switch (route) {
            case 'T':
                //System.out.println("todo " + line.substring(firstSpace));
                ui.handleToDo("todo " + line.substring(firstSpace), userList, this, tasks);
                break;
            case 'D':
                ui.handleDeadline("deadline "
                            + line.substring(firstSpace).replace("(", "")
                            .replace(")", "")
                            .replace("by:", "/by"), userList, this, tasks);
                break;
            case 'E':
                ui.handleEvent("event "
                            + line.substring(firstSpace).replace("(", "")
                            .replace(")", "").replace("from:", "/from")
                            .replace("to:", "/to"), userList, this, tasks);
                break;
            default:
                throw new BillException("Not a recognised command in bill.txt,"
                            + " please ensure that all lines in bill.txt have the template of the expected output"
                            + " based on user commands");
            }

            if (isMarked) {
                ui.handleMarkOfTask(new String[]{"mark", String.valueOf(index)}, userList, tasks, this);
            }
        }
    }
}
