import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Mentos
{
    private static final String FILE_PATH = System.getProperty("user.dir")+"/data/Mentos.txt";
    private final String MARKED = "mark";
    private final String UNMARKED = "unmark";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";
    private final String EVENT = "event";
    private final String DELETE = "delete";
    private ArrayList<Task> tasks = new ArrayList<>();


    public static void main(String[] args) {
        Mentos mentos = new Mentos();
        mentos.startConversation();
        mentos.loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.nextLine();
            if (input.equals("bye")){
                break;
            }
            mentos.taskHandler(input);
        }
        mentos.endConversation();
    }

    public void startConversation(){
        /*
         * Starts the conversation with the user by displaying a greeting message.
         * This method prints a formatted message to the console, including a
         * border and a welcome message indicating that the chatbot, Mentos, is
         * ready to assist.
         */
        System.out.println("____________________________");
        System.out.println("Hello! I'm Mentos\nWhat can I do to help you?");
        System.out.println("____________________________");
    }

    public void endConversation(){
        /*
         * Ends the conversation with the user by displaying a farewell message.
         * This method prints a formatted message to the console, including a
         * border and a closing statement with a fun reference to Mentos,
         * encouraging the user to "Pop a Mentos" and stay fresh.
         */
        System.out.println("____________________________");
        System.out.println("Pop a Mentos, stay fresh! See you next time!");
        System.out.println("____________________________");
    }



    public Matcher regexHandler(String input,String regex){
        /*
         * Handles regular expression matching on a given input string.
         * This method compiles the provided regular expression (regex) and
         * attempts to find a match within the input string. If a match is found,
         * it returns the `Matcher` object, allowing further operations like
         * extracting matched groups. If no match is found, it returns `null`.
         *
         * @param input the string to be matched against the regular expression.
         * @param regex the regular expression used for matching.
         * @return `Matcher` object if a match is found; `null` otherwise.
         */
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher;
        }
        return null;
    }



    public void taskHandler(String input) {
        /*
         * Handles user input commands related to task management.
         *
         * This method processes various task-related commands such as listing tasks,
         * marking tasks as done, unmarking tasks, deleting tasks, and adding new tasks
         * (todo, deadline, event). It uses regular expressions to parse and validate
         * the input commands, and throws custom exceptions (`MentosException`)
         * when inputs are invalid.
         *
         * The following commands are supported:
         *   list - Lists all the current tasks.
         *   mark X- Marks the task at index X as done.
         *   unmark X - Unmarks the task at index X (marks it as not done).
         *   delete X< - Deletes the task at index X.
         *   todo DESCRIPTION - Adds a new ToDo task with the given description.
         *   deadline DESCRIPTION /by DATETIME - Adds a new Deadline task with the given description and due date.
         *   event DESCRIPTION /from DATETIME /to DATETIME - Adds a new Event task with the given description and time range.
         *
         * If an unrecognized command is given, the method responds with a message indicating
         * that the command is not understood.
         *
         * @param input the user's input command as a string.
         * @throws MentosException if the input is invalid or if the specified task index does not exist.
         */
        try {
            if (input.equals("list")) {
                displayTasks();
            }  else if (input.startsWith(MARKED)) {
                Matcher match = regexHandler(input, "mark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > tasks.size() || index == 0) {
                    throw new MentosException("No Such Tasks!");
                }
                tasks.get(index-1).markAsDone();
                System.out.println("Nicely done! This task is marked as done!");
                System.out.println(tasks.get(index-1).toString());
                System.out.println("____________________________");

            } else if (input.startsWith(UNMARKED)) {
                Matcher match = regexHandler(input, "unmark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > tasks.size() || index == 0) {
                    throw new MentosException("No Such Tasks!");
                }
                tasks.get(index-1).markAsNotDone();
                System.out.println("Holdup this task is not done!");
                System.out.println(tasks.get(index-1).toString());
                System.out.println("____________________________");
            } else if (input.startsWith(DELETE)){
                Matcher match = regexHandler(input,"delete (\\d+)$");
                if (match == null){
                    throw new MentosException("Invalid Delete input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > tasks.size() || index ==0){
                    throw new MentosException("No Such Tasks!");
                }
                System.out.println("____________________________");
                System.out.printf("Alrights I have removed the following task!\n%s%n",tasks.get(index-1).toString());
                tasks.remove(index-1);
                System.out.printf("%d remaining tasks%n",tasks.size());
            }
            else if (input.startsWith(TODO)) {
                Matcher match = regexHandler(input, "todo (.+)");
                if (match == null) {
                    throw new MentosException("Todo cannot be empty!");
                }
                String extracted = match.group(1);

                Task newTodo = new ToDo(extracted);
                tasks.add(newTodo);
                print_event(TODO,newTodo);
            } else if (input.startsWith(DEADLINE)){
                Matcher match = regexHandler(input,"deadline (.+) \\/by (\\d{4}-\\d{2}-\\d{2} \\d{4})$");
                if (match == null){
                    throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime> in yyyy-mm-dd hhmm");
                }
                String deadline_desc = match.group(1);
                String by = match.group(2);
                Task newDeadline = new Deadline(deadline_desc,by);
                tasks.add(newDeadline);
                print_event(DEADLINE,newDeadline);
            } else if (input.startsWith(EVENT)){
                Matcher match = regexHandler(input,"event (.+) \\/from (\\d{4}-\\d{2}-\\d{2} \\d{4}) \\/to (\\d{4}-\\d{2}-\\d{2} \\d{4})$");
                if (match == null){
                    throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> yyyy-mm-dd hhmm /to <datetime> yyyy-mm-dd hhmm" );
                }

                String eventDesc = match.group(1);
                String from = match.group(2);
                String to = match.group(3);
                System.out.println(String.format("from: %s, to: %s",from,to));
                Task newEvent = new Event(eventDesc,from,to);
                tasks.add(newEvent);
                print_event(EVENT,newEvent);
            }

            else {
                System.out.println("____________________________");
                System.out.println("Sorry me no understand");
                System.out.println("____________________________");
            }
            saveTasksToFile();
        } catch (MentosException err){
            System.out.println(err);
        } catch (DateTimeException e){
            System.out.println("Invalid Datetime inputted");
        }
    }

    public void displayTasks(){
        /*
         * Displays the list of tasks currently stored.
         *
         * This method iterates through the list of tasks and prints each one,
         * preceded by its corresponding index in the list. The tasks are formatted
         * as a numbered list, and each task's string representation is output.
         * After displaying all tasks, a separator line is printed.
         */

        for (int i = 0; i < tasks.size(); i++) {
            String task_out = String.format("%d. %s",i+1,tasks.get(i).toString());
            System.out.println(task_out);
        }
        System.out.println("____________________________");
    }

    public void print_event(String event, Task task){
        /*
         * Prints a formatted message indicating that a task event has occurred.
         *
         * This method outputs a message to the console that includes the type of event
         * (such as "todo", "deadline", or "event"), the details of the task that was
         * added, and the number of remaining tasks. The message is enclosed within
         * a border for visual clarity.
         *
         * @param event the type of event that occurred (e.g., "todo", "deadline", "event").
         * @param task the task object that was added or modified, whose details will be printed.
         */

        System.out.println("____________________________");
        System.out.printf(event+" Added\n%s\n%d remaining tasks%n",task.toString(),tasks.size());
        System.out.println("____________________________");
    }

    public void saveTasksToFile() {
        /*
         * Saves the list of tasks to a file specified by {@code FILE_PATH}.
         * This method writes each task in the {@code tasks} list to the file, with each task on a new line.
         * If the file specified by {@code FILE_PATH} does not exist, an error message will be printed to the console.
         * Note: The method uses a hard-coded file path. Ensure that the directory structure exists before calling this method.
         *
         * @throws IOException if an I/O error occurs while writing to the file.
         */
        try{
            FileWriter file = new FileWriter(FILE_PATH,false);
            for (Task task: tasks){
                file.write(task.toString()+"\n");
            }
            file.close();
        } catch (IOException exception){
            System.out.println("Please create a /data/Mentos.txt file in "+ System.getProperty("user.dir"));
        }
    }

    public void loadTasksFromFile(){
        /*
         * Loads the list of tasks from a file specified by {@code FILE_PATH}.
         * This method reads each line from the file and attempts to parse it into a {@code Task} object,
         * depending on the task type identified in the line. The supported task types are:
         * To-Do (T)
         * Event (E)
         * Deadline (D)
         * If a line is not in the expected format, it will be skipped, and a warning message will be printed to the console.
         * If the file does not exist or an I/O error occurs, an error message will be printed instead.

         * Note: The method expects the file content to be in a specific format for parsing.
         *
         * @throws IOException if an I/O error occurs while reading the file.
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = regexHandler(line,"^\\[([T|E|D])\\] \\[(.)\\] (?:.*)$");
                if (matcher == null){
                    System.out.println("Content not in the right format! Skipping....");
                    continue;
                }
                String eventType = matcher.group(1);
                String isDone = matcher.group(2);
                if (eventType.equals("T")){
                    //todo
                    Matcher toDoMatcher = regexHandler(line,"^\\[(?:[T])\\] \\[(?:.)\\] (.*)$");
                    if (toDoMatcher == null){
                        System.out.println("Content not in the right format! Skipping....");
                        continue;
                    }
                    String desc = toDoMatcher.group(1);
                    Task toDoEvent = new ToDo(desc);
                    if (isDone.equals("X")){
                        toDoEvent.markAsDone();
                    }
                    tasks.add(toDoEvent);
                } else if (eventType.equals("E")) {
                    //event
                    Matcher eventMatcher = regexHandler(line,"^\\[(?:[E])\\] \\[(?:.)\\] (.*) \\(from: (.*) to: (.*)\\)$");
                    if (eventMatcher == null){
                        System.out.println("Content not in the right format! Skipping....");
                        continue;
                    }
                    String desc = eventMatcher.group(1);
                    String fromDate = eventMatcher.group(2);
                    String toDate = eventMatcher.group(3);
                    Task eventEvent = new Event(desc,changeFormat(fromDate),changeFormat(toDate));
                    if (isDone.equals("X")){
                        eventEvent.markAsDone();
                    }
                    tasks.add(eventEvent);
                } else if (eventType.equals("D")){
                    //deadline
                    Matcher deadlineMatcher = regexHandler(line,"^\\[(?:[D])\\] \\[(?:.)\\] (.*) \\(by: (.*)\\)$");
                    if (deadlineMatcher == null){
                        System.out.println("Content not in the right format! Skipping....");
                        continue;
                    }
                    String desc = deadlineMatcher.group(1);
                    String byDate = deadlineMatcher.group(2);
                    Task deadlineEvent = new Deadline(desc,changeFormat(byDate));
                    if (isDone.equals("X")){
                        deadlineEvent.markAsDone();
                    }
                    tasks.add(deadlineEvent);
                }
            }
        } catch (IOException exception){
            System.out.println("No Configuration found in "+ FILE_PATH);
        }
    }

    public String changeFormat(String dateTimeStr){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(outputFormatter);
    }



}

