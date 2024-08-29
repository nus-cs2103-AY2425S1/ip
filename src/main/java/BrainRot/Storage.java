package BrainRot;

import java.io.*;
import java.util.ArrayList;

import BrainRot.exceptions.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> arr = new ArrayList<>();
        File myData = new File(filePath);

        try (BufferedReader dataR = new BufferedReader(new FileReader(myData))) {
            String line;
            while ((line = dataR.readLine()) != null) {
                String[] dataFromDisk = line.split("/", 4);
                char eventType = dataFromDisk[0].charAt(1);

                switch (eventType) {
                    case 'T':
                        ToDo T = new ToDo(dataFromDisk[1]);
                        if (dataFromDisk[1].equals("[X]")) {
                            T.isDone = true;
                        }
                        arr.add(T);
                        break;
                    case 'D':
                        Deadline D = new Deadline(dataFromDisk[1], dataFromDisk[2], true);
                        if (dataFromDisk[1].equals("[X]")) {
                            D.isDone = true;
                        }
                        arr.add(D);
                        break;
                    case 'E':
                        Event E = new Event(dataFromDisk[1], dataFromDisk[2], dataFromDisk[3]);
                        if (dataFromDisk[1].equals("[X]")) {
                            E.isDone = true;
                        }
                        arr.add(E);
                        break;
                }
            }
        } catch (IOException e) {
            throw new UnknownLoadingError(e);
        }

        return arr;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        try (FileWriter dataW = new FileWriter(filePath)) {
            for (Task task : tasks) {
                dataW.write(task.toFileString() + "\n");
            }
        }
    }
}
