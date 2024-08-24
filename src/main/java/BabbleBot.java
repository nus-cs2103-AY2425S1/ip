import java.util.Scanner;

public class BabbleBot {
    public static void main(String[] args) {
        Boolean notBye = true;
        Task[] storedTasks = new Task[100];
        int counter = 0;
        Scanner in = new Scanner(System.in);
        String separator = "---------------------------------------------";
        System.out.println(separator);
        System.out.println("Hello! I'm BabbleBot!\nWhat can I do for you?");
        System.out.println(separator);

        while(notBye) {
            String userInp = in.nextLine();
            String[] userInpSplit = userInp.split(" ");
            if (userInpSplit[0].equals("bye")) {
                notBye = false;
            } else if (userInpSplit[0].equals("list")) {
                System.out.println(separator);
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ". " + "[" + storedTasks[i].getStatusIcon() + "] " + storedTasks[i].description);
                }
                System.out.println(separator);
            } else if (userInpSplit.length == 2) {
                if (userInpSplit[0].equals("mark")) {
                    //mark code'
                    int index = Integer.valueOf(userInpSplit[1]) - 1;
                    storedTasks[index].markAsDone();
                    System.out.println(separator);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("   " + "[" + storedTasks[index].getStatusIcon() + "] " + storedTasks[index].description);
                    System.out.println(separator);
                } else if (userInpSplit[0].equals("unmark")) {
                    //unmark code
                    int index = Integer.valueOf(userInpSplit[1]) - 1;
                    storedTasks[index].maskAsUndone();
                    System.out.println(separator);
                    System.out.println("OK, I've marked this task as not done yet: ");
                    System.out.println("   " + "[" + storedTasks[index].getStatusIcon() + "] " + storedTasks[index].description);
                    System.out.println(separator);
                }
            }
            else {
                System.out.println(separator);
                System.out.println("added: " + userInp);
                storedTasks[counter] = new Task(userInp);
                counter++;
                System.out.println(separator);
            }
        }
        System.out.println(separator);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
