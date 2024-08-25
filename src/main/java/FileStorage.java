import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class FileStorage {
    private final File file;

    FileStorage(String filePath) {
        this.file = new File(filePath);
        String[] parsedFilePath = filePath.split("/");
        String dirPath = "";
        for (int i = 0; i < parsedFilePath.length - 1; i++) {
            dirPath += (i > 0 ? "/" : "") + parsedFilePath[i];
            File directory = new File(dirPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error has occurred");
            }
        }
    }

    ArrayList<Task> load() {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> taskList = new ArrayList<>();
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] parsedInput = input.split("/");
                switch (parsedInput[0]) {
                    case "T":
                        Todo todo = new Todo(parsedInput[2],
                                parsedInput[1].equals("1"));
                        taskList.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parsedInput[2],
                                LocalDate.parse(parsedInput[3]),
                                parsedInput[1].equals("1"));
                        taskList.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parsedInput[2],
                                LocalDate.parse(parsedInput[3]),
                                LocalDate.parse(parsedInput[4]),
                                parsedInput[1].equals("1"));
                        taskList.add(event);
                        break;
                }
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    void save(ArrayList<Task> taskList) {
        try {
            String saveData = "";
            FileWriter fileWriter = new FileWriter(this.file);
            for (Task task : taskList) {
                saveData += task.toSaveState();
            }
            fileWriter.write(saveData);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred");
        }
    }
}
