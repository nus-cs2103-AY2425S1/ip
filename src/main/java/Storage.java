import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private final String filePath;  // "./janet.txt"

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the elements in Janet's listOfTasks into a text file (janet.txt).
     * Each Task object is saved in the format,
     * symbol | isDone | description | startDate (if deadline/event)-endDate (if event)
     *
     * @param listOfTasks the list of tasks (ArrayList<Task>) that Janet has.
     * @throws IOException
     */
    public void saveToJanetTextFile(ArrayList<Task> listOfTasks) throws IOException {
        // using FileWriter to write text into a text file (janet.txt).
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
    }


    /**
     * Returns an ArrayList<Task> listOfTasks,
     * each Task object is created based on each line read from janet.txt.
     *
     * reads the content in janet.txt and loads them into an ArrayList<Task>, returns this ArrayList<Task>
     * @return an ArrayList<Task> listOfTasks, that contains Task objects
     */
    public ArrayList<Task> textFileToArrayList() {
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
                    case 'T' -> {
                        // todo object
                        task = createToDoFromJanetTextFile(line, isDone);
                    }
                    case 'D' -> {
                        // deadline object
                        task = createDeadlineFromJanetTextFile(line, isDone);
                    }
                    case 'E' -> {
                        // event object
                        task = createEventFromJanetTextFile(line, isDone);
                    }
                }
                listOfTasks.add(task);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listOfTasks;
    }


    /**
     * Returns a ToDo object,
     * based on a line, containing information about a ToDo, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file.
     * @param isDone isDone = true if task has been marked else false.
     * @return a new ToDo object created using the parameters.
     */
    public ToDo createToDoFromJanetTextFile(String line, boolean isDone) {
        // get description from text file
        String toDoDescription = line.substring(8);
        ToDo task = new ToDo(toDoDescription, "T");
        task.setDone(isDone);
        return task;
    }


    /**
     * Returns a Deadline object,
     * based on a line, containing information about a Deadline, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file.
     * @param isDone isDone = true if task has been marked else false.
     * @return a new Deadline object created using the parameters.
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
     * Returns an Event object,
     * based on a line, containing information about an Event, read from janet.txt.
     *
     * @param line a line of text from the janet.txt file
     * @param isDone isDone = true if task has been marked else false.
     * @return a new Event object created using the parameters.
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
