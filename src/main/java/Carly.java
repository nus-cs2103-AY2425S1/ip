import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Carly {
    private ArrayList<Task> taskList;
    private String username;

    public Carly() {
        this.taskList = new ArrayList<Task>();
        this.username = "";
    }

    private void welcomeMsg() {
        System.out.println("Hey " + this.username + "! I'm Carly 👩🏼‍💼️.\nWhat can I do for you?");
    }

    private void byeMsg() {
        System.out.println("Bye " + this.username + ". I'll see you next time!");
    }

    private void mark(Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.markAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        String msg = "Okiee! I've marked this task as done:\n    " + t.toString();
        System.out.println(msg);
    }

    private void unmark(Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.unmarkAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        String msg = "Okiee! I've marked this task as not done yet:\n    " + t.toString();
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

    private void updateUsername(String username){
        this.username = username;
    }

    private void chat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your name?");
        String username = scan.nextLine();
        updateUsername(username);

        enum Msg {
            TODO, DEADLINE, EVENT, MARK, UNMARK, LIST, BYE
        }

        String input;
        int taskNum;
        String taskDescription;
        Msg command;

        welcomeMsg();

        while (true) {
            if (scan.hasNextLine()) {
                input = scan.nextLine();
            } else {
                System.out.println("No input detected. Exiting...");
                break;
            }

            int firstSpaceIndex = input.indexOf(" ");
            String[] parts = input.split(" ");
            String action = parts[0].toUpperCase();

            try {
                command = Msg.valueOf(action);
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!! I am not sure what this means. Please reenter.");
                continue;
            }

            switch(command){
                case BYE:
                    byeMsg();
                    return;
                case LIST:
                    printTaskList();
                    break;
                case TODO:
                    taskDescription = input.substring(firstSpaceIndex + 1);
                    addToDo(taskDescription);
                    break;
                case DEADLINE:
                    try {
                        String[] taskDueDate = input.substring(firstSpaceIndex + 1).split(" /by ");
                        taskDescription = taskDueDate[0];
                        String duedate = taskDueDate[1];
                        addDeadLine(taskDescription, duedate);
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case EVENT:
                    try {
                        String[] taskTimeParts = input.substring(firstSpaceIndex + 1).split(" /from ");
                        taskDescription = taskTimeParts[0];
                        String timeParts = taskTimeParts[1];
                        String[] startEndTimeParts = timeParts.split(" /to ");
                        String startTime = startEndTimeParts[0];
                        String endTime = startEndTimeParts[1];
                        addEvent(taskDescription, startTime, endTime);
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                case MARK:
                    taskNum = Integer.parseInt(parts[1]);
                    this.mark(taskNum);
                    break;
                case UNMARK:
                    try {
                        taskNum = Integer.parseInt(parts[1]);
                        this.unmark(taskNum);
                        break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
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
        Carly carly = new Carly();
        carly.chat();
    }


}
