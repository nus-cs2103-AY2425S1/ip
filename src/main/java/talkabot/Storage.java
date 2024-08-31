package talkabot;

import talkabot.exceptions.UnknownTimeException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage Class handles the file containing the task list
 * to be read and saved onto the hard drive.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath path of input file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns task list based on contents of file if it already exists.
     * If not, returns empty task list.
     *
     * @return Task list.
     * @throws IOException When an issue is encountered
     * while trying to create or read file.
     */
    public TaskList load() throws IOException {
        File file = new File(this.filePath);

        file.getParentFile().mkdirs();

        TaskList taskList = new TaskList();

        if (!file.createNewFile()) {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] keywords = line.split(" \\| ");
                Task curr = null;
                switch (keywords.length) {
                    case 2:
                        curr = new ToDo(keywords[1]);
                        break;
                    case 3:
                        curr = new Deadline(new String[] {keywords[1], keywords[2]});
                        break;
                    case 4:
                        curr = new Event(new String[] {keywords[1], keywords[2], keywords[3]});
                        break;
                    default:
                        System.out.println("error");
                        break;
                }
                if (keywords[0].equals("1")) {
                    curr.markAsDone();
                }
                taskList.add(curr);
            }
            sc.close();
        }

        return taskList;
    }

    /**
     * Saves file based on current task list.
     *
     * @throws IOException When an issue is encountered
     * while trying to write or save file.
     */
    public void save(TaskList taskList) throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(this.filePath));
        String tasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            tasks += taskList.get(i).fileString() + "\n";
        }
        taskWriter.write(tasks);
        taskWriter.close();
    }
}
