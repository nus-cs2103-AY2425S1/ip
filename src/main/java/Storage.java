import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filepath;
    private List<String> data;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() throws SpongebobException{
        this.data = new ArrayList<>();
        List<Task> res = new ArrayList<>();
        List<String> args;
        Task newTask;

        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                this.data.add(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }


        for (String line : this.data) {

            try {
                args = Arrays.asList(line.split("\\|"));

                switch (args.get(0)) {
                    case "TODO":
                        newTask = new Todo(args.get(2));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;

                    case "DEADLINE":
                        newTask = new Deadline(args.get(2), args.get(3));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;

                    case "EVENT":
                        newTask = new Event(args.get(2), args.get(3), args.get(4));
                        if (args.get(1).equals("true")) {
                            newTask.markAsDone();
                        }
                        res.add(newTask);
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Some parts are corrupted!");
            }
        }

        return res;
    }

    public void add(Task task) {
        this.data.add(task.save());
        this.write();
    }

    public void delete(Task task) {
        this.data.remove(task.save());
        this.write();
    }

    public void update(int index, Task task) {
        this.data.set(index, task.save());
        this.write();
    }

    private void write() {
        try {
            FileWriter fw = new FileWriter(this.filepath);
            StringBuilder res = new StringBuilder();

            for (String line : this.data) {
                res.append(line + System.lineSeparator());
            }
            fw.write(res.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
