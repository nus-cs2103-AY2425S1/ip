package janet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the way Janet saves/read tasks into/from a text file.
 */
public class Storage {
    private final String filePath;  // "./janet.txt"

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the elements in Janet's listOfTasks into a text file (janet.txt).
     * Each janet.Task object is saved in the format,
     * symbol | isDone | description | startDate (if deadline/event)-endDate (if event)
     *
     * @param listOfTasks the list of tasks (ArrayList<janet.Task>) that Janet has.
     */
    public void saveToJanetTextFile(ArrayList<Task> listOfTasks) throws JanetException {
        // using FileWriter to write text into a text file (janet.txt).
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : listOfTasks) {
                String marked = (task.isDone()) ? "1" : "0";
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
     * each janet.Task object is created based on each line read from janet.txt.
     *
     * @return an ArrayList<janet.Task> listOfTasks, that contains janet.Task objects
     */
    public ArrayList<Task> textFileToArrayList() throws JanetException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Task task = null;
                char taskSymbol = line.charAt(0);   // get task symbol
                String done = String.valueOf(line.charAt(4));   // get mark/unmark value
                boolean isDone = (done.equals("1"));

                switch (taskSymbol) {
                case 'T' :
                    // todo object
                    task = createToDoFromJanetTextFile(line, isDone);
                    break;
                case 'D' :
                    // deadline object
                    task = createDeadlineFromJanetTextFile(line, isDone);
                    break;
                case 'E' :
                    // event object
                    task = createEventFromJanetTextFile(line, isDone);
                    break;
                }
                listOfTasks.add(task);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            // exception will be thrown when fileReader cannot find the file.
            throw new JanetException("");
        }
        return listOfTasks;
    }


    /**
     * Returns a janet.ToDo object,
     * based on a line, containing information about a janet.ToDo, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file.
     * @param isDone isDone = true if task has been marked else false.
     * @return a new janet.ToDo object created using the parameters.
     */
    public ToDo createToDoFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String toDoDescription = line.substring(8);
        ToDo task = new ToDo(toDoDescription, "T");
        task.setDone(isDone);
        return task;
    }


    /**
     * Returns a janet.Deadline object,
     * based on a line, containing information about a janet.Deadline, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file.
     * @param isDone isDone = true if task has been marked else false.
     * @return a new janet.Deadline object created using the parameters.
     */
    public Deadline createDeadlineFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String deadlineDescription = line.substring(8, line.indexOf("|", 8) - 1);

        // get the due date and convert into LocalDateTime
        String dueDateAndTime = line.substring(line.indexOf("|", 8) + 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");   // format String dueDateAndTime is in
        LocalDateTime dueDate = LocalDateTime.parse(dueDateAndTime, formatter);

        Deadline deadline = new Deadline(deadlineDescription, "D", dueDate);
        deadline.setDone(isDone);
        return deadline;
    }


    /**
     * Returns an janet.Event object,
     * based on a line, containing information about an janet.Event, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file
     * @param isDone isDone = true if task has been marked else false.
     * @return a new janet.Event object created using the parameters.
     */
    public Event createEventFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String eventDescription = line.substring(8, line.indexOf("|", 8) - 1);

        // get startDate and convert into LocalDateTime
        String startDateAndTime = line.substring(line.indexOf("|", 8) + 2, line.indexOf("-", line.indexOf("|", 8)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm a");   // format String dueDateAndTime is in
        LocalDateTime startDate = LocalDateTime.parse(startDateAndTime, formatter);

        // get the endDate and convert into LocalDateTime
        String endDateAndTime = line.substring(line.indexOf("-", line.indexOf("|", 8)) + 1);
        LocalDateTime endDate = LocalDateTime.parse(endDateAndTime, formatter);

        Event event = new Event(eventDescription, "E", startDate, endDate);
        event.setDone(isDone);
        return event;
    }

}
