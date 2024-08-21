import java.util.Arrays;
import java.util.Scanner;

public class BMO {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println("Hello! I'm BMO!\nWhat can I do for you?\n" + line);

        //Initialise storage and scanner
        Storage storage = new Storage();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //Loop to read user inputted commands
        while (!command.equals("bye")) {
            //Stores the input line as an array of words
            String[] words = command.split(" ");

            //Extracts the first word as the command word
            String commandWord = words[0];
            //Extracts the rest of the words as the description
            String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

            //Switch statement to handle different commands
            switch (commandWord) {
                case "list":
                    System.out.println("\nHere are the tasks in your list:\n" + storage.getTasks() + line);
                    break;
                case "mark":
                    int index = Integer.parseInt(words[1]) - 1;
                    Task taskToMark = storage.getTask(index);
                    taskToMark.mark();
                    System.out.println("\nNice! I've marked this task as done:\n" + taskToMark.toString() + "\n" + line);
                    break;
                case "unmark":
                    int index2 = Integer.parseInt(words[1]) - 1;
                    Task taskToUnmark = storage.getTask(index2);
                    taskToUnmark.unmark();
                    System.out.println("\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString() + "\n" + line);
                    break;
                case "todo":
                    Task todo = new ToDo(description);
                    storage.addTask(todo);
                    System.out.println("\nGot it. I've added this task:\n" + todo.toString() + "\n" + storage.getNumOfTasks() + "\n" + line);
                    break;
                case "deadline":
                    String[] deadlineDetails = description.split(" /by ");
                    Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    storage.addTask(deadline);
                    System.out.println("\nGot it. I've added this task:\n" + deadline.toString() + "\n" + storage.getNumOfTasks() + "\n" + line);
                    break;
                case "event":
                    String[] eventDetails = description.split(" /from ");
                    String[] eventTimings = eventDetails[1].split(" /to ");
                    Task event = new Event(eventDetails[0], eventTimings[0], eventTimings[1]);
                    storage.addTask(event);
                    System.out.println("\nGot it. I've added this task:\n" + event.toString() + "\n" + storage.getNumOfTasks() + "\n" + line);
                    break;
                default:
                    break;
            }
            command = sc.nextLine();
        }
        sc.close();
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
