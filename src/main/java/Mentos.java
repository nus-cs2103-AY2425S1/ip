import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Mentos
{
    private String MARKED = "mark";
    private String UNMARKED = "unmark";
    private String TODO = "todo";
    private String DEADLINE = "deadline";
    private String EVENT = "event";
    private String DELETE = "delete";
    private ArrayList<Task> tasks = new ArrayList<>();
    private int noTasks = 0;

    public static void main(String[] args) {
        Mentos mentos = new Mentos();
        mentos.startConversation();
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
            } else if (input.startsWith(MARKED)) {
                Matcher match = regexHandler(input, "mark (\\d+)");
                if (match == null) {
                    throw new MentosException("Invalid input!");
                }
                String extracted = match.group(1);
                int index = Integer.parseInt(extracted);
                if (index > noTasks || index == 0) {
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
                if (index > noTasks || index == 0) {
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
                if (index > noTasks || index ==0){
                    throw new MentosException("No Such Tasks!");
                }
                System.out.println("____________________________");
                System.out.printf("Alrights I have removed the following task!\n%s%n",tasks.get(index-1).toString());
                tasks.remove(index-1);
                noTasks--;
                System.out.printf("%d remaining tasks%n",noTasks);
            }
            else if (input.startsWith(TODO)) {
                Matcher match = regexHandler(input, "todo (.+)");
                if (match == null) {
                    throw new MentosException("Todo cannot be empty!");
                }
                String extracted = match.group(1);
                noTasks++;
                Task newTodo = new ToDo(extracted);
                tasks.add(newTodo);
                print_event(TODO,newTodo);
            } else if (input.startsWith(DEADLINE)){
                Matcher match = regexHandler(input,"deadline (.+) \\/by (.+)$");
                if (match == null){
                    throw new MentosException("Invalid deadline input! usage:deadline <desc> /by <datetime>");
                }
                noTasks++;
                String deadline_desc = match.group(1);
                String by = match.group(2);
                Task newDeadline = new Deadline(deadline_desc,by);
                tasks.add(newDeadline);
                print_event(DEADLINE,newDeadline);
            } else if (input.startsWith(EVENT)){
                Matcher match = regexHandler(input,"event (.+) \\/from (.+) \\/to (.+)$");
                if (match == null){
                    throw new MentosException("Invalid Event input! usage:event <desc> /from <datetime> /to <datetime>" );
                }
                noTasks++;
                String eventDesc = match.group(1);
                String from = match.group(2);
                String to = match.group(3);
                Task newEvent = new Event(eventDesc,from,to);
                tasks.add(newEvent);
                print_event(EVENT,newEvent);
            }

            else {
                System.out.println("____________________________");
                System.out.println("Sorry me no understand");
                System.out.println("____________________________");
            }
        } catch (MentosException err){
            System.out.println(err);
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

        for (int i = 0; i < noTasks; i++) {
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
        System.out.printf(event+" Added\n%s\n%d remaining tasks%n",task.toString(),noTasks);
        System.out.println("____________________________");
    }

}

