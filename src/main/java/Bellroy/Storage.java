package Bellroy;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                char taskType = line.charAt(1);
                boolean isDone = line.charAt(4) == 'X';
                String remainder = line.substring(7).trim();

                switch (taskType) {
                    case ('T'):
                        Task tempToDo = new Todo(remainder);
                        if (isDone) {
                            tempToDo.markDone();
                        }
                        taskList.add(tempToDo);
                        break;
                    case ('D'):
                        String[] deadlineParts = remainder.split(" \\(by: ");
                        String deadlineDescription = deadlineParts[0].trim();
                        String by = deadlineParts[1].replace(")", "").trim();
                        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
                        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(by, inputFormat);
                        String dueDate = dateTime.format(outputFormat);
                        Task tempDeadline = new deadline(deadlineDescription, dueDate);
                        if (isDone) {
                            tempDeadline.markDone();
                        }
                        taskList.add(tempDeadline);
                        break;
                    case ('E'):
                        String[] eventParts = remainder.split(" \\(from: | to: ");
                        String eventDescription = eventParts[0].trim();
                        String from = eventParts[1].trim();
                        String to = eventParts[2].replace(")", "").trim();
                        Task tempEvent = new Event(eventDescription, from, to);
                        if (isDone) {
                            tempEvent.markDone();
                        }
                        taskList.add(tempEvent);
                        break;
                }

                line = reader.readLine();

            }
            reader.close();
        }
        return taskList;
    }

    public void save(TaskList taskList) throws IOException{
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task t: taskList.getTaskList()) {
                writer.write(t.toString());
                writer.write("\n");
            }
        }
    }
}
