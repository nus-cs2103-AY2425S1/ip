package rainy.database;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;

import rainy.tasks.TaskTracker;

public class Storage {
    public Storage() {

    }

    public TaskTracker copyPreviousFiles(File newFile) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        TaskTracker newTask;
        UI ui = new UI();
        try {
            newTask = new TaskTracker();
            Scanner sc = new Scanner(newFile);
            int trace = 0;
            while (sc.hasNext()) {
                String oldData = sc.nextLine();
                if (trace > 0){
                    if (oldData.charAt(8) == 'T') {
                        newTask.updateListToDo(oldData.substring(11));
                    } else if (oldData.charAt(8) == 'D') {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] deadlineSplit = updatedOldData.split(" \\(");
                        newTask.updateListDeadline(deadlineSplit[0] + " ", deadlineSplit[1]);
                    } else {
                        String updatedOldData = oldData.substring(11, oldData.length() - 1);
                        String[] eventSplit = updatedOldData.split(" \\(");
                        String newDate = eventSplit[1].split(" from ")[0];
                        String newTime = eventSplit[1].split(" from ")[1];
                        newTask.updateListEvent(eventSplit[0] + " ", newDate, newTime);
                    }
                    if (oldData.charAt(4) == 'X') {
                        newTask.markDone(trace - 1);
                    }
                }
                trace++;
            }
            ui.startTracking();
        } catch(FileNotFoundException e) {
            ui.startTracking();
            newTask = new TaskTracker();
        }
        newTask.toggleReceivedInputs();
        return newTask;
    }

    public void writeOverFile(File filename, TaskTracker tm) throws IOException {
        try {
            filename.createNewFile();
            FileWriter fw = new FileWriter(filename);
            fw.write(tm.getList());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}