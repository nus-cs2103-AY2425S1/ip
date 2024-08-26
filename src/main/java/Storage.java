import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    String path;
    public Storage(String path) {
        this.path = path;
    }
    public ArrayList<Task> loadList() {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
                return tempList;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        Parser tempParser = new Parser(); //check if this is okay
                        LocalDateTime LDT = tempParser.parseDate(parts[3]);
                        if (LDT != null) {
                            task = new Deadline(parts[2], LDT);
                        } else {
                            task = new Deadline(parts[2], parts[3]);
                        }
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3]);
                        break;
                    default:
                        throw new MaxException("Unknown task type found in file.");
                }
                if (parts[1].equals("1")) {
                    task.markDone();
                }
                tempList.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An IO Exception has occured. " + e.getMessage());
        } catch (MaxException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tempList;

    }

    public void saveTasks(ArrayList<Task> storedTasks) throws MaxException {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (Task task : storedTasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new MaxException("An IOException occurred.");
        }
    }

}
