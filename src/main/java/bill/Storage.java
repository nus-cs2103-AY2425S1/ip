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
        String home = System.getProperty("user.home");
        pathStorageDirectory = Paths.get("./data");
        pathStorageFile = Paths.get("./data", "bill.txt");
    }

    private void writeToBillTxt(ArrayList<Task> userList, BufferedWriter writer) throws IOException {
        for (int i = 0; i < userList.size(); i++) {
            writer.write((i + 1) + "." + userList.get(i));
            writer.newLine();
        }
    }

    /**
     * Saves updated list to bill.txt.
     *
     * @param userList Current accessible state of mutable list.
     * @throws IOException If there is an error writing to the bill.txt file.
     */
    public void saveList(ArrayList<Task> userList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathStorageFile)));
        writeToBillTxt(userList, writer);
        writer.close();
    }

    private File makeDirectoryAndFile() throws IOException {
        boolean directoryExists = Files.exists(pathStorageDirectory);
        boolean fileExists = Files.exists(pathStorageFile);

        if (!directoryExists) {
            Files.createDirectory(pathStorageDirectory);
        }

        if (!fileExists) {
            Files.createFile(pathStorageFile);
        }

        File file = new File(String.valueOf(pathStorageFile));

        assert Files.exists(pathStorageDirectory) : "Ensure the data directory exists";
        assert Files.exists(pathStorageFile) : "Ensure the file bill.txt exists";

        return file;
    }

    private void loadData(Ui ui, ArrayList<Task> userList, TaskList tasks) throws IOException, BillException {
        BufferedReader lineReader = new BufferedReader(new FileReader(String.valueOf(pathStorageFile)));
        readLine(ui, userList, tasks, lineReader);
    }

    private void handleLine(Ui ui, ArrayList<Task> userList, TaskList tasks, char route, String line, int firstSpace)
                throws BillException, IOException {
        switch (route) {
        case 'T':
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
    }

    private void readLine(Ui ui, ArrayList<Task> userList, TaskList tasks, BufferedReader lineReader)
                throws IOException, BillException {
        String line;
        while ((line = lineReader.readLine()) != null) {
            char route = line.charAt(3);
            char mark = line.charAt(6);
            boolean isMarked = mark == 'X';
            int firstSpace = Math.max(line.indexOf("[ ]"), line.indexOf("[X]")) + 4;
            int index = Integer.parseInt(String.valueOf(line.charAt(0)));

            handleLine(ui, userList, tasks, route, line, firstSpace);

            if (isMarked) {
                ui.handleMarkOfTask(new String[]{"mark", String.valueOf(index)}, userList, tasks, this);
            }
        }
    }

    private boolean checkEmptyFile(File file) {
        return file.length() == 0;
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
        File file = makeDirectoryAndFile();

        if (checkEmptyFile(file)) {
            return;
        }

        loadData(ui, userList, tasks);
    }
}
