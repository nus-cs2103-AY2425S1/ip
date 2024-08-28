package main.java;

import exception.CasperBotIOException;
import exception.CasperBotOutOfBoundsException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class Storage {
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void openFile(TaskList taskList) throws CasperBotIOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {  // Read each line until end of file
                String[] values = line.split("\\|");
                boolean isDone = Boolean.parseBoolean(values[1]);
                String description = values[2];
                switch (values[0]) {
                case "T":
                    taskList.addTask(new ToDo(description, isDone));
                    break;
                case "D":
                    LocalDate deadline = LocalDate.parse(values[3]);
                    taskList.addTask(new Deadline(description, isDone, deadline));
                    break;
                case "E":
                    LocalDate start = LocalDate.parse(values[3]);
                    LocalDate end = LocalDate.parse(values[4]);
                    taskList.addTask(new Event(description, isDone, start, end));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                File file = new File(filePath);
                file.createNewFile();
            } catch (IOException ioException) {
                throw new CasperBotIOException();
            }
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    public void writeToFile(Task task) throws CasperBotIOException {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            boolean isDone = task.getStatusIcon().equals("X");
            String description = task.getDescription();
            String taskString = isDone + "|" + description;
            switch (task.getTaskType()) {
            case "T":
                bufferedWriter.write("T|" + taskString);
                break;
            case "D":
                Deadline deadline = (Deadline) task;
                bufferedWriter.write("D|" + taskString + "|" + deadline.getDeadline());
                break;
            case "E":
                Event event = (Event) task;
                bufferedWriter.write("D|" + taskString + "|" + event.getStart() + "|" + event.getEnd());
                break;
            }
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    public void deleteFromFile(int line) throws CasperBotIOException, CasperBotOutOfBoundsException {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            if (line >= 0 && line < lines.size()) {
                lines.remove(line);
            } else {
                throw new CasperBotOutOfBoundsException();
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }

    public void updateDoneStatus(int line, boolean isDone) throws CasperBotIOException, CasperBotOutOfBoundsException {
        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            if (line >= 0 && line < lines.size()) {
                // Retrieve the line and update the done status
                String previous = lines.remove(line);
                String[] previousParts = previous.split("\\|");
                previousParts[1] = String.valueOf(isDone);
                String updated = String.join("|", previousParts);
                lines.add(line, updated);
            } else {
                throw new CasperBotOutOfBoundsException();
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new CasperBotIOException();
        }
    }
}
