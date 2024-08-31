package talkabot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
