import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws BarcusException {
        File file = new File(this.filePath);
        String[] temp = this.filePath.split("/");
        File dir = new File(String.join("/",
                Arrays.copyOfRange(temp, 0, temp.length - 1)));

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new BarcusException("error in creating new file");
            }
        }

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
//                String line = s.next();
                String[] lineSplit = s.nextLine().split(" \\| ");

                if (lineSplit[0].equals("T")) {
                    tasks.add(new Todo(lineSplit[2], lineSplit[1].equals("1")));
                } else if (lineSplit[0].equals("D")) {
                    tasks.add(new Deadline(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3]
                    ));
                } else if (lineSplit[0].equals("E")) {
                    tasks.add(new Event(
                            lineSplit[2],
                            lineSplit[1].equals("1"),
                            lineSplit[3],
                            lineSplit[4]
                    ));
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new BarcusException("error in finding file");
        }

        return tasks;
    }

    public void upload(ArrayList<Task> tasks) throws BarcusException {
        try (FileWriter writer = new FileWriter(this.filePath);
             BufferedWriter bfWriter = new BufferedWriter(writer)) {
            for (Task task: tasks) {
                bfWriter.write(task.convertToSavedString() + "\n");
            }
        } catch (IOException e) {
            throw new BarcusException("error updating txt save file");
        }
    }
}
