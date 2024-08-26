import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File f;
    private String filePath;

    public Storage(String filePath) {
        //ensures file is always created (if it didn't exist initially)
        try {
            this.filePath = filePath;
            this.f = new File(filePath);
            if (this.f.createNewFile()) {
                FileWriter fw = new FileWriter(filePath);
                fw.write("type,is_checked,task_name,time1,time2\n");
                fw.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Database file was unable to be generated!");
        }
    }

    public ArrayList<Task> getFileContents() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(this.f);
        s.nextLine(); //skips headers

        while (s.hasNext()) {
            String[] array = s.nextLine().split(",");
            String taskType = array[0];
            int isChecked = Integer.parseInt(array[1]);
            String taskName = array[2];

            if (taskType.equals("T")) {
                Todo t = new Todo(taskName, isChecked == 1);
                tasks.add(t);
            } else if (taskType.equals("D")) {
                Deadline d = new Deadline(taskName, isChecked == 1, array[3]);
                tasks.add(d);
            } else if (taskType.equals("E")) {
                Event e = new Event(taskName, isChecked == 1, array[3], array[4]);
                tasks.add(e);
            }
        }
        return tasks;
    }

    public void updateFileContents(ArrayList<Task> database) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder allData = new StringBuilder("type,is_checked,task_name,time1,time2\n");
        for (int i = 0; i < database.size(); i++) {
            Task t = database.get(i);
            String isChecked = String.valueOf(t.isDone ? 1 : 0);
            if (t.type.equals("T")) {
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s\n", t.type, isChecked, t.name));
                allData.append(data);
            } else if (t.type.equals("D")) {
                Deadline d = (Deadline) t;
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s,%s\n", t.type, isChecked, t.name, d.getDeadline()));
                allData.append(data);
            } else if (t.type.equals("E")) {
                Event e = (Event) t;
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", t.type, isChecked, t.name, e.getStart(), e.getEnd()));
                allData.append(data);
            }
        }
        System.out.println("CHECK");
        fw.write(allData.toString());
        fw.close();
    }
}
