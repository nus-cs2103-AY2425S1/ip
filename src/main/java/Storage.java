import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String PATH;

    public Storage(String path) {
        this.PATH = path;
    }

    public ArrayList<Task> loadTask() throws IOException, LamaException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line =  scanner.nextLine();
                System.out.println(line);
                String[] words = line.split(" \\| ");
                switch (words[0]) {
                case "T":
                    Todo todo = new Todo(words[2]);
                    if (words[1].equals("1")) {
                        todo.markAsDone();
                    }
                    list.add(todo);
                    break;

                case "D":
                    Deadline deadline = new Deadline(words[2], words[3]);
                    if (words[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    list.add(deadline);
                    break;

                case "E":
                    Event event = new Event(words[2], words[3], words[4]);
                    if (words[1].equals("1")) {
                        event.markAsDone();
                    }
                    list.add(event);
                    break;

                default:
                    throw new LamaException("Invalid data format in file");
                }
            }
            scanner.close();
        }

        return list;
    }

    public void saveTask(ArrayList<Task> tasks) throws IOException{
        FileWriter fileWriter = new FileWriter(PATH);

        for (Task task: tasks) {
            fileWriter.write(task.toFile() + "\n");
        }

        fileWriter.close();
    }
}
