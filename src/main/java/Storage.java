import java.util.*;
import java.io.*;

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
                        newTask.updateListEvent(eventSplit[0] + " ", eventSplit[1].split(" from ")[0], eventSplit[1].split(" from ")[1]);
                    }
                    if (oldData.charAt(4) == 'X') {
                        newTask.markDone(trace - 1);
                    }
                }
                trace++;
            }
            ui.startTracking();
        } catch (FileNotFoundException e) {
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