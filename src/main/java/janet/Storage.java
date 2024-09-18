package janet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the way Janet saves/read tasks into/from a text file.
 */
public class Storage {
    private final String filePath;  // "janet.txt"

    /**
     * @param filePath A file path thar represents the location of janet.txt
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the elements in Janet's listOfTasks into a text file (janet.txt).
     * Each Task object is saved in the format,
     * symbol | isDone | description | startDate (if deadline/event)-endDate (if event)
     *
     * @param listOfTasks the list of tasks (ArrayList<janet.Task>) that Janet has.
     */
    public void saveToJanetTextFile(ArrayList<Task> listOfTasks) throws JanetException {
        // using FileWriter to write text into a text file (janet.txt).
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : listOfTasks) {
                String marked = (task.isTaskMarked()) ? "1" : "0";
                String entry = task.getSymbol() + " | " + marked + " | " + task.getDescription();
                if (task instanceof Deadline) {
                    entry = entry + " | " + ((Deadline) task).getDueDate();
                } else if (task instanceof Event) {
                    entry = entry + " | " + ((Event) task).getStartDate() + "-" + ((Event) task).getEndDate();
                }
                fileWriter.write(entry + "\n");     // write the entry into the text file and save it
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new JanetException("");
        }
    }


    /**
     * Returns an ArrayList<janet.Task> listOfTasks,
     * each Task object is created based on each line read from janet.txt.
     *
     * @return an ArrayList<janet.Task> listOfTasks, that contains janet.Task objects
     */
    public ArrayList<Task> textFileToArrayList() throws IOException {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        FileReader fileReader = new FileReader(this.filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            Task task = null;
            if (line.isEmpty()) {
                continue;
            }
            char taskSymbol = line.charAt(0);   // get task symbol
            String done = String.valueOf(line.charAt(4));   // get mark/unmark value
            boolean isDone = (done.equals("1"));

            switch (taskSymbol) {
            case 'T' :
                // todo object
                task = new ToDo(line.split("\\|"));
                break;
            case 'D' :
                // deadline object
                task = new Deadline(line.split("\\|"));
                break;
            case 'E' :
                // event object
                task = new Event(line.split("\\|"));
                break;
            default:
                // invalid
                continue;
            }
            task.setMark(isDone);
            listOfTasks.add(task);
        }
        fileReader.close();
        bufferedReader.close();
        return listOfTasks;
    }
}
