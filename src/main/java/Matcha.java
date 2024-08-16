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

            //get first word of user input
            String firstWord = input.split(" ")[0];

            switch (firstWord) {
                case "list":
                    printLine();
                    System.out.println("Here are your tasks:\n");
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
                    System.out.println("I have successfully marked this task as done: ");
                    System.out.println(markTask.toString());
                    printLine();
                    break;
                case "unmark":
                    Task unmarkTask = tasks.get(Integer.parseInt(input.split(" ")[1])- 1);
                    unmarkTask.markNotDone();
                    printLine();
                    System.out.println("Alright, I have marked this task as not done yet: ");
                    System.out.println(unmarkTask.toString());
                    printLine();
                    break;
                default:
                    printLine();
                    System.out.println("added: " + input);
                    printLine();
                    tasks.add(new Task(input));
                    break;
            }

        }
        //once user has exited program, close scanner
        scanner.close();
    }

    public static void greet() {
        printLine();
        System.out.println(" Hi there! I am Matcha, your personal chatbot.\n");
        System.out.println(" How can I help you today?\n");
        printLine();
    }

    public static void printLine() {
        String line = "____________________________________________________________\n";
        System.out.println(line);
    }
}


