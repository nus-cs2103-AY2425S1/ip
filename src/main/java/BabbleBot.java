import java.util.Scanner;

public class BabbleBot {
    public static void main(String[] args) {
        Boolean notBye = true;
        Task[] storedTasks = new Task[100];
        int counter = 0;
        Scanner in = new Scanner(System.in);
        String separator = "------------------------------------------------------------------";
        System.out.println(separator);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(separator);

        while(notBye && in.hasNextLine()) {
            String userInp = in.nextLine();
            String[] userInpSplit = userInp.split(" ");
            if (userInpSplit[0].equals("bye")) {
                notBye = false;
            } else if (userInpSplit[0].equals("list")) {
                System.out.println(separator);
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + storedTasks[i].toString());
                }
                System.out.println(separator);
            } else if (userInpSplit[0].equals("todo")) {
                storedTasks[counter] = new Todo(userInp.split("todo ")[1]);
                System.out.println(separator);
                System.out.println("Got it. I've added this task:");
                System.out.println("   " + storedTasks[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(separator);
            } else if (userInpSplit[0].equals("deadline")) {
                String content = userInp.split("deadline ")[1].split(" /by")[0];
                String by = userInp.split("/by ")[1];
                storedTasks[counter] = new Deadline(content, by);
                System.out.println(separator);
                System.out.println("Got it. I've added this task:");
                System.out.println("   " + storedTasks[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(separator);
            } else if (userInpSplit[0].equals("event")) {
                String content = userInp.split("event ")[1].split(" /from")[0];
                String splitDates = userInp.split("/from ")[1];
                String[] parsedDates = splitDates.split(" /to ");
                storedTasks[counter] = new Event(content, parsedDates[0], parsedDates[1]);
                System.out.println(separator);
                System.out.println("Got it. I've added this task:");
                System.out.println("   " + storedTasks[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(separator);
            } else if (userInpSplit.length == 2) {
                if (userInpSplit[0].equals("mark")) {
                    //mark code'
                    int index = Integer.valueOf(userInpSplit[1]) - 1;
                    storedTasks[index].markAsDone();
                    System.out.println(separator);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("   " + storedTasks[index].toString());
                    System.out.println(separator);
                } else if (userInpSplit[0].equals("unmark")) {
                    //unmark code
                    int index = Integer.valueOf(userInpSplit[1]) - 1;
                    storedTasks[index].maskAsUndone();
                    System.out.println(separator);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("   " + storedTasks[index].toString());
                    System.out.println(separator);
                }
            }
            else {
                System.out.println(separator);
                System.out.println("Whoopsie daisy! Looks like I got all tangled up in my words there!\n" +
                        "Let's try that again in a way that might make a bit more sense.\n" +
                        "What do you need help with?");
                System.out.println(separator);
            }
        }
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
