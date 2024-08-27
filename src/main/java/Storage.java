import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void restoreTasks(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File("data/list.txt");
        if (!file.exists()) {
            throw new FileNotFoundException("Save file not found");
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String taskLine = s.next();
            String input = s.nextLine();
            boolean isDone = input.strip().startsWith("[X]");
            switch (taskLine.charAt(1)) {
            case 'T':
                Task t = new ToDo(input.substring(4).strip());
                TaskList.addToTaskList(tasks, t);

                if (isDone) {
                    t.markAsDone();
                }

                break;
            case 'D':
                String[] deadlineName = input.substring(4).strip().split(" \\(by: ");
                Task dl = new Deadline(deadlineName[0].stripIndent(), deadlineName[1].substring(0, deadlineName[1].length() - 1));
                TaskList.addToTaskList(tasks, dl);

                if (isDone) {
                    dl.markAsDone();
                }

                break;
            case 'E':
                String[] eventName = input.substring(4).strip().split("\\(");
                String[] interval = eventName[1].split(" - ");
                Event e = new Event(eventName[0].stripIndent(), interval[0], interval[1].substring(0, interval[1].length()- 1));
                TaskList.addToTaskList(tasks, e);

                if (isDone) {
                    e.markAsDone();
                }

                break;
            }
        }
    }

    public static void writeToFile(String pathName, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(pathName);

        for (Task t : tasks) {
            fw.write(String.format("%s\n", t.toString()));
        }

        fw.close();
    }
}
