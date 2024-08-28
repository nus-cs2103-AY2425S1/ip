package talkie.components;

import talkie.exception.TalkieNoTaskFoundException;
import talkie.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws TalkieNoTaskFoundException {

        File database = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                taskList.add(readEntry(entry));
            }
        } catch (FileNotFoundException e) {
            this.createDatabase();

        }

        return taskList;
    }

    private Task readEntry(String entry) throws TalkieNoTaskFoundException {
        String[] fields = entry.split(" \\| ");
        Task taskToBeAdded;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        switch(fields[0]) {
            case "T":
                taskToBeAdded = new ToDo(fields[2]);
                break;

            case "E":
                taskToBeAdded = new Event(fields[2],
                        LocalDateTime.parse(fields[3], formatter),
                        LocalDateTime.parse(fields[4], formatter));
                break;

            case "D":
                taskToBeAdded = new Deadline(fields[2],
                        LocalDateTime.parse(fields[3], formatter));
                break;

            default:
                throw new TalkieNoTaskFoundException();
        }

        if (Integer.parseInt(fields[1]) == 1) {
            taskToBeAdded.markAsDone();
        }

        return taskToBeAdded;
    }

    private void createDatabase() {
        File db = new File(this.filePath);
        File dir = new File(db.getParent());
        dir.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong when creating the database!");
        }
    }

    public void saveData(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(this.filePath, false);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for (int i = 1; i <= taskList.size(); i++) {
            bufferedWriter.write(taskList.getTask(i).stringifyTask());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
        writer.close();
    }






}
