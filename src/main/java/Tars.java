import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

class WritingFile {
    public static void writeFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            fileWriter.write(task.toFileFormat() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public static ArrayList<Task> readFile(String filePath) throws IOException {
        File f = new File(filePath); // create a File for the given file path

        if(!f.exists()){
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> itemsList = new ArrayList<>();

        while (s.hasNext()) {
            String entry = s.nextLine(); //Task from each line
            String[] entryParts = entry.split(" ");

            if(entryParts[0].equals("T")) {
                StringBuilder strBuild = new StringBuilder();
                for (int i = 2; i < entryParts.length; i++) {
                    strBuild.append(entryParts[i]).append(" ");
                }
                ToDos todo = new ToDos(strBuild.toString().trim());
                if(entryParts[1].equals("1")){
                    todo.mark();
                }
                itemsList.add(todo);
            } else if (entryParts[0].equals("D")) {
                StringBuilder strBuild = new StringBuilder();
                StringBuilder dateStr = new StringBuilder();

                for (int i = 2; i < entryParts.length - 2; i++) {
                    if (i < entryParts.length - 3) {
                        strBuild.append(entryParts[i]).append(" ");
                    } else {
                        dateStr.append(entryParts[entryParts.length - 2]);
                    }
                }
                LocalDateTime deadlineDate = LocalDateTime.parse(dateStr.toString());
                Deadline deadlineTask = new Deadline(strBuild.toString().trim(), deadlineDate);
                if(entryParts[1].equals("1")) {
                    deadlineTask.mark();
                }
                itemsList.add(deadlineTask);
            }else if (entryParts[0].equals("E")) {
                StringBuilder strBuild = new StringBuilder();
                StringBuilder toStr = new StringBuilder();
                StringBuilder forStr = new StringBuilder();

                for (int i = 2; i < entryParts.length; i++) {
                    if (i < entryParts.length - 5) {
                        strBuild.append(entryParts[i]).append(" ");
                    } else if (i > entryParts.length - 5 && i < entryParts.length - 3) {
                        forStr.append(entryParts[i]).append(" ");
                    } else if (i > entryParts.length - 3 && i < entryParts.length - 1) {
                        toStr.append(entryParts[i]);
                    }
                }

                LocalDateTime fromDate = LocalDateTime.parse(forStr.toString().trim());
                LocalDateTime toDate = LocalDateTime.parse(toStr.toString());
                Event eventTask = new Event(strBuild.toString(), fromDate, toDate);
                if(entryParts[1].equals("1")) {
                    eventTask.mark();
                }
                itemsList.add(eventTask);
            }
        }
        return itemsList;
    }
}

/**
 * Class represents a generic class with a Description and isDone status
 * Serves as a base class for other Task types like (ToDos, Deadline, Event)
 *
 *
 * @author csk
 * @version 1
 */
class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for new Task with specified description
     * The task will be initialised with marked as not done
     *
     * @param description (description of Task)
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon depending on the isDone status of the Task
     *
     * @return "X" if task is done, " " otherwise returned
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the status and description of the task
     *
     * @return String Representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toFileFormat(){
        return "";
    }
}
/**
 * Class represents ToDos task, which only has a description
 * Extends from Task class
 *
 * @see Task
 */
class ToDos extends Task {
    /**
     * Constructs for new ToDos Task with description stated
     *
     * @param description (of specified ToDos task)
     */
    public ToDos(String description) {
        super(description);
    }
    /**
     * Returns a string representation of the status and description of the task with "[T]"
     *
     * @return String Representation of ToDos task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileFormat(){
        if(isDone) {
            return "T " + "1 " + description;
        }else{
            return "T " + "0 " + description;
        }
    }
}
/**
 * Class represents Deadline Task, which has a specific deadline
 * Extends from Task class and also has an extra field for deadline of task
 *
 * @see Task
 */
class Deadline extends Task {
    protected LocalDateTime by;
    /**
     * Constructs new Deadline Task with deadline stated
     *
     * @param description (of specified ToDos task)
     * @param by (deadline of Task)
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    /**
     * Returns a string representation of the description of the Deadline task with "[D]"
     *
     * @return String Representation of Deadline task
     */
    @Override
    public String toString() {
            return "[D] " + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat(){
        if(isDone) {
            return "D " + "1 " + description + " (by: " + by + " )";
        } else{
                return "D " + "0 " + description + " (by: " + by + " )";
        }
    }
}
/**
 * Class represents Event task, which has a description, and start and end time
 * Extends from Task class and has extra fields to store start and end time of event
 *
 * @see Task
 */
class Event extends Task {
    protected LocalDateTime from, to;
    /**
     * Constructs new Deadline Task with deadline stated
     *
     * @param description (of specified ToDos task)
     * @param from (start of Event)
     * @param to (end of Event)
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a string representation of the description of the Event task with "[E]"
     * String also includes information on Start and End time of Event
     *
     * @return String Representation of Event task
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                    + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileFormat(){
        if(isDone) {
            return "E " + "1 " + description + "(from: " + from + " to: " + to + " )";
        }else{
            return "E " + "0 " + description + "(from: " + from + " to: " + to + " )";
        }
    }
}

/**
 * This class is Chatbot application which handles Task Management, mainly ToDos, Deadline and Event tasks
 * Users can add the 3 different type of tasks and carry out other functions like mark, unmark and delete
 * Users can also view all the tasks added in a LIST
 * This application will exist till "bye" command entered by User
 *
 * @author csk
 * @version 1
 */
public class Tars {
    static String line = "    _____________________________________________";
    static String filePath = "./data/Tars.txt";

    /**
     * Runs the Tars application, where it takes in users inputs until "bye" is entered by user
     *
     * @param args (command line arguments given by User)
     */
    public static void main(String[] args) throws IOException {
        //welcome/introduction message
        System.out.println(line + "\n" + "    Hello! I'm Tars\n" + "    What can I do for you" + "\n" + line);

        Scanner scanner = new Scanner(System.in); //initalising input scanner
        ArrayList<Task> itemsList = WritingFile.readFile(filePath); //reads from text file

        //while loop to ensure termination of programme only when "bye" input
        while (true) {
            String entry = scanner.nextLine();
            String[] entryParts = entry.split(" ");

            //String task = itemsList.size() > 1 ? "tasks" : "task";

            try {
                if(entryParts.length < 2){
                    if(entry.equals("todo") || entry.equals("deadline") || entry.equals("event")){
                        throw new TarsException(line + "\n" + "    OOPS! Describe the task/event/deadline/todo or list"
                                + "\n" + line);
                    } else if (entryParts[0].equals("list")) {
                        System.out.println(line + "\n" + "    Here are the tasks in your list:");
                        for (int i = 0; i < itemsList.size(); i++) {
                            System.out.println("    " + (i + 1) + ". " + itemsList.get(i));
                        }
                        System.out.println(line);
                    }  else if(entry.equals("bye")){
                        break;
                    } else{
                        throw new TarsException(line + "\n" + "    OOPS! Only accept a task/event/deadline/todo as input"
                                + "\n" + line);
                    }
                } else if (entryParts[0].equals("mark")) {
                    Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //to convert string format of number to Integer
                    itemsList.get(index - 1).mark(); //marking TASK as done

                    System.out.println(line);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("        " + itemsList.get(index - 1) + "\n" + line);
                } else if (entryParts[0].equals("unmark")) {
                    Integer index = Integer.parseInt(entryParts[entryParts.length - 1]); //convert str format of number to Integer
                    itemsList.get(index - 1).unmark(); //unmarking TASK as not done

                    System.out.println(line);
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("        " + itemsList.get(index - 1) + "\n" + line);
                } else if (entryParts[0].equals("todo")) {
                    StringBuilder strBuild = new StringBuilder();

                    for (int i = 1; i < entryParts.length; i++) {
                        strBuild.append(entryParts[i]).append(" ");
                    }

                    ToDos todo = new ToDos(strBuild.toString().trim());
                    itemsList.add(todo);

                    System.out.println(line);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + todo);
                    System.out.println("    Now you have " + itemsList.size() + " tasks in the list");
                    System.out.println(line);
                } else if (entryParts[0].equals("deadline")) {
                    StringBuilder strBuild = new StringBuilder();
                    StringBuilder dateStr = new StringBuilder();

                    int next = 2; //index to check for "/by" keyword
                    for (int i = 1; i < entryParts.length; i++) {
                        if(entryParts[i].equals("/by")){
                            next = i;
                        } else if (i < next) {
                            strBuild.append(entryParts[i]).append(" ");
                            next++;
                        } else{
                            dateStr.append(entryParts[i]).append(" ");
                        }
                    }

                    Deadline deadlineTask = null;

                    if (dateStr.toString().length() == 17) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateStr.toString().trim(), format);
                        deadlineTask = new Deadline(strBuild.toString().trim(), dateTime);
                        itemsList.add(deadlineTask);
                        System.out.println(line);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + deadlineTask);
                        System.out.println("    Now you have " + itemsList.size() + " tasks in the list" + "\n" + line);
                    } else {
                        System.out.println(line + "\n" + "  Please state date and time of deadline" + "\n" + line);
                    }
                } else if (entryParts[0].equals("event")) {
                    StringBuilder strBuild = new StringBuilder();
                    StringBuilder toStr = new StringBuilder();
                    StringBuilder fromStr = new StringBuilder();

                    int next = 2;
                    int toNext = 3;
                    for (int i = 1; i < entryParts.length; i++) {
                        if (entryParts[i].equals("/from")) {
                            next = i;
                            toNext++;
                        } else if (entryParts[i].equals("/to")) {
                            toNext = i;
                        } else if (i < next) {
                            strBuild.append(entryParts[i]).append(" ");
                            next++;
                            toNext++;
                        } else if (i < toNext) {
                            fromStr.append(entryParts[i]).append(" ");
                            toNext++;
                        } else {
                            toStr.append(entryParts[i]).append(" ");
                        }
                    }

                    System.out.println(fromStr.toString().length());
                    System.out.println(toStr.toString().length());

                    if (fromStr.toString().length() == 17 && toStr.toString().length() == 17) {
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(fromStr.toString().trim(), format);
                        LocalDateTime toTime = LocalDateTime.parse(toStr.toString().trim(), format);

                        Event eventTask = new Event(strBuild.toString(), dateTime, toTime);
                        itemsList.add(eventTask);

                        System.out.println(line);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        " + eventTask);
                        System.out.println("    Now you have " + itemsList.size() + " tasks in the list" + "\n" + line);
                    } else {
                        System.out.println(line + "\n" + "  Please state date and time of from and to of event"
                                + "\n" + line);
                    }
                }else if(entryParts[0].equals("delete")){
                    Integer index = Integer.parseInt(entryParts[entryParts.length - 1]);
                    Task temp = itemsList.get(index - 1);
                    itemsList.remove(itemsList.get(index - 1));

                    System.out.println(line);
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("        " + temp);
                    System.out.println("    Now you have " + itemsList.size() + " tasks in the list" + "\n" + line);
                } else{
                    Task t = new Task(entry);
                    itemsList.add(t); //adding a Task to list

                    System.out.println(line);
                    System.out.println("    added: " + entry + "\n" + line);
                }
            } catch (TarsException e) {
                System.out.println(e.getMessage());
            }
        }

        WritingFile.writeFile("./data/Tars.txt", itemsList);

        //exit message when given input "bye"
        System.out.println(line + "\n" + "    Bye. Hope to see you again soon!" + "\n" + line);
    }
}
