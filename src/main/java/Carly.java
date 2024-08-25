import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.text.MessageFormat;

public class Carly {
    private ArrayList<Task> taskList;
    private String username;

    public Carly(String username) {
        this.taskList = new ArrayList<Task>();
        this.username = username;
    }

    private void welcomeMsg() {
        System.out.println("Hey " + this.username + "! I'm Carly 👩🏼‍💼️. \nWhat can I do for you?\n");
    }

    private void byeMsg() {
        System.out.println("Bye " + this.username + ". I'll see you next time!");
    }

    private void mark(Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.markAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        String msg = "Okiee! I've marked this task as done: \n    " + t.toString();
        System.out.println(msg);
    }

    private void unmark(Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.unmarkAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        String msg = "Okiee! I've marked this task as not done yet: \n    " + t.toString();
        System.out.println(msg);
    }

    private void addToDo(String taskDescription){
        Todo t = new Todo(taskDescription);
        this.taskList.add(t);
        System.out.println("Got it. I've added this task:\n     " + t.toString());
        taskListSize();
    }

    private void addDeadLine(String taskDescription, String duedate){
        Deadline t = new Deadline(taskDescription, duedate);
        this.taskList.add(t);
        System.out.println("Got it. I've added this task:\n     " + t.toString());
        taskListSize();
    }

    private void addEvent(String taskDescription, String startTime, String endTime){
        Event t = new Event(taskDescription, startTime, endTime);
        this.taskList.add(t);
        System.out.println("Got it. I've added this task:\n     " + t.toString());
        taskListSize();
    }

    private void taskListSize(){
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    private String extractTaskDescription(String input) {
        int firstSpaceIndex = input.indexOf(" ");
        String[] descriptionParts = input.substring(firstSpaceIndex + 1).split(" /from ");
        return descriptionParts[0];
    }

    private void chat() {
        welcomeMsg();
        Scanner scan = new Scanner(System.in);
        String input;


        while (true) {
            input = scan.nextLine();
            String[] parts = input.split(" ");
            String firstPart = parts[0];

            if (firstPart.equals("bye")) {
                byeMsg();
                break;
            } else if (firstPart.equals("list")) {
                printTaskList();
            } else if (firstPart.equals("mark")) {
                int taskNum = Integer.parseInt(parts[1]);
                this.mark(taskNum);
            } else if (firstPart.equals("unmark")) {
                int taskNum = Integer.parseInt(parts[1]);
                this.unmark(taskNum);
            } else if (firstPart.equals("todo")) {
                String taskDescription = parts[1];
                addToDo(taskDescription);
            } else if (firstPart.equals("deadline")) {
                int firstSpaceIndex = input.indexOf(" ");
                String[] taskDueDate = input.substring(firstSpaceIndex + 1).split(" /by ");
                String taskDescription = taskDueDate[0];
                String duedate = taskDueDate[1];
                addDeadLine(taskDescription, duedate);
            } else if (firstPart.equals("event")) {
                int firstSpaceIndex = input.indexOf(" ");
                String[] taskTimeParts = input.substring(firstSpaceIndex + 1).split(" /from ");
                String taskDescription = taskTimeParts[0];
                String timeParts = taskTimeParts[1];
                String[] startEndTimeParts = timeParts.split(" /to ");
                String startTime = startEndTimeParts[0];
                String endTime = startEndTimeParts[1];
                addEvent(taskDescription, startTime, endTime);
            }
        }
    }

    private void printTaskList(){
        if(this.taskList.isEmpty()){
            System.out.println("There's nothing in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            IntStream.range(0, taskList.size())
                    .forEach(i -> System.out.println(
                            MessageFormat.format("{0}.{1}", i + 1, taskList.get(i).toString())));
        }
    }

    public static void main(String[] args) {
        String logo = " ,-----.              ,--.          \n"
                + "'  .--./,--,--.,--.--.|  |,--. ,--. \n"
                + "|  |   ' ,-.  ||  .--'|  | \\  '  / \n"
                + "'  '--'\\ '-'  ||  |   |  |  \\   ' \n"
                + " `-----'`--`--'`--'   `--'.-'  /    \n"
                + "                          `---'  ";

        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner scan = new Scanner(System.in);
        String username = scan.nextLine();
        Carly carly = new Carly(username);
        carly.chat();
    }


}
