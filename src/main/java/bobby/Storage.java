package bobby;

import bobby.exception.BobbyException;
import bobby.exception.EmptyDescriptionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(ArrayList<Task> listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);

        for (Task task : listOfTasks) {
            fw.write(task.taskInFile() + "\n");
        }
        fw.close();
    }

    public void processFileLines(ArrayList<Task> listOfTasks, String line) throws EmptyDescriptionException {
        String[] lineParts = line.split(" \\| ");
        switch (lineParts[0]) {
        case "T":
            Task t = Todo.createTodo("todo" + lineParts[2]);
            if (lineParts[1].equals("X")) {
                t.indIncomplete();
            }
            listOfTasks.add(t);
            break;
        case "D":
            Task d = Deadline.createDeadline("deadline" + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                d.indIncomplete();
            }
            listOfTasks.add(d);
            break;
        case "E":
            Task e = Event.createEvent("event " + lineParts[2] + " " + lineParts[3]);
            if (lineParts[1].equals("X")) {
                e.indIncomplete();
            }
            listOfTasks.add(e);
            break;
        }
    }

    public void loadData(ArrayList<Task> listOfTasks) throws BobbyException {
        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                processFileLines(listOfTasks, s.nextLine());
            }
        } catch (FileNotFoundException e) {
           throw new BobbyException("The file cannot be found, please check again.");
        }
    }

    public ArrayList<Task> returnTaskList() throws IOException, BobbyException {
        File f = new File(this.filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (f.createNewFile()) {
            System.out.println(filePath + " not found, allow me to create it for you");
            return tasks;
        } else {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) {
                processFileLines(tasks, s.nextLine());
            }
            return tasks;
        }
    }


}
