import java.util.Scanner;
import java.util.ArrayList;


public class Matcha {
    public static void main(String[] args) {

        //scanner for user input
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        Matcha.greet();

        while (true) {

            //read user input
            String input = scanner.nextLine();

            //if user exits program
            if (input.equals("bye")) {
                String exit = "____________________________________________________________\n" +
                        " Bye. Hope to see you again!\n" +
                        "____________________________________________________________\n";
                System.out.println(exit);
                break;
            }

            //split user input into keyword and action description
            String[] inputWords = input.split(" ", 2);

            //get first word of user input
            String keyword = inputWords[0];

            switch (keyword) {
                case "list":
                    printLine();
                    System.out.println("Here are your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        String task = (i + 1) + ". " + tasks.get(i);
                        System.out.println(task);
                    }
                    printLine();
                    break;

                case "mark":
                    //get the tasks from the list of tasks based on given task number
                    Task markTask = tasks.get(Integer.parseInt(input.split(" ")[1])- 1);
                    markTask.markDone();
                    printLine();
                    System.out.println("I have successfully marked this task as done:");
                    System.out.println(markTask.toString());
                    printLine();
                    break;

                case "unmark":
                    //get the tasks from the list of tasks based on given task number
                    Task unmarkTask = tasks.get(Integer.parseInt(input.split(" ")[1])- 1);
                    unmarkTask.markNotDone();
                    printLine();
                    System.out.println("Alright, I have marked this task as not done yet:");
                    System.out.println(unmarkTask.toString());
                    printLine();
                    break;

                case "todo":
                    printLine();
                    System.out.println("Alright, I have added this task:");
                    Todo todo = new Todo(inputWords[1]);
                    tasks.add(todo);
                    System.out.println(todo);
                    Matcha.countTasks(tasks.size());
                    printLine();
                    break;

                case "deadline":

                    String[] deadlineInfo = inputWords[1].split(" /by ", 2);
                    String deadlineDesc = deadlineInfo[0].strip();
                    String by = deadlineInfo[1].strip();

                    printLine();
                    System.out.println("Alright, I have added this task:");
                    Deadline deadline = new Deadline(deadlineDesc, by);
                    tasks.add(deadline);
                    System.out.println(deadline);
                    Matcha.countTasks(tasks.size());
                    printLine();
                    break;

                case "event":
                    String eventDesc = inputWords[1].split(" /from")[0];
                    String from = inputWords[1].split(" /from ")[1].split(" /to ")[0];
                    String to = inputWords[1].split(" /to ")[1];
                    printLine();
                    System.out.println("Alright, I have added this task:");
                    Event event = new Event(eventDesc, from, to);
                    tasks.add(event);
                    System.out.println(event);
                    Matcha.countTasks(tasks.size());
                    printLine();
                    break;

                default:
                    break;
            }

        }
        //once user has exited program, close scanner
        scanner.close();
    }

    public static void greet() {
        printLine();
        System.out.println(" Hi there! I am Matcha, your personal chatbot.");
        System.out.println(" How can I help you today?");
        printLine();
    }

    public static void countTasks(int numOfTasks) {
        String task = numOfTasks == 1 ? "task" : "tasks";
        System.out.println("You have " + numOfTasks + " " + task + " in the list.");
    }

    public static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }
}


