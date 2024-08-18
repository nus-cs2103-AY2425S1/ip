import java.util.Scanner;

public class Hoshi {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        String logo = "\n" +
                " __    __    ______        _______. __    __   __  \n" +
                "|  |  |  |  /  __  \\      /       ||  |  |  | |  | \n" +
                "|  |__|  | |  |  |  |    |   (----`|  |__|  | |  | \n" +
                "|   __   | |  |  |  |     \\   \\    |   __   | |  | \n" +
                "|  |  |  | |  `--'  | .----)   |   |  |  |  | |  | \n" +
                "|__|  |__|  \\______/  |_______/    |__|  |__| |__| \n" +
                "                                                   \n";

        System.out.println(logo);

        System.out.println("____________________________________________________________\n" +
                "Hello! Im Hoshi!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");


        Task[] array = new Task[100];
        int indexCounter = -1;

        while (true) {


            System.out.println("Enter your request below ");

            // take in input
            String input = scanner.nextLine();

            // bye
            if (input.equals("bye")) {
                break;

                // list
            } else if (input.equals("list")) {

                // for loop for listing
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == null) {
                        break;
                    }

                    System.out.println(i+1 + ". "+ "[" + array[i].getStatusIcon() + "] " + array[i].getDesc() + "\n");
                }

                // mark
            } else if (input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("mark")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                // set isDone to true
                array[markIndex].setIsDone(true);

                System.out.println("Nice! I've marked this task as done: \n");
                System.out.println("[" + array[markIndex].getStatusIcon() + "] " + array[markIndex].getDesc() + "\n");

                // unmark
            } else if (input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("unmark")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                // set isDone to false
                array[markIndex].setIsDone(false);

                System.out.println("OK, I've marked this task as not done yet: \n");
                System.out.println("[" + array[markIndex].getStatusIcon() + "] " + array[markIndex].getDesc() + "\n");

                // add
            } else {

                indexCounter++;

                // init new Task object
                Task newTask = new Task(input);

                // add new task to array
                array[indexCounter] = newTask;
                System.out.println("added: " + input);

                System.out.println("____________________________________________________________");
            }



            // echo
            //System.out.println(input);


        }

        bye();




    }

    /**
     * Prints bye message when user terminates the program
     *
     */
    static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

}
