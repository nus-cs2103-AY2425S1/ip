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


            System.out.println("Add ToDo/Deadline/Event OR List(Lowercase): ");

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

                    System.out.println(i+1 + ". "+ array[i].toString() + "\n");
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
                System.out.println(array[markIndex].toString() + "\n");

                // unmark
            } else if (input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("unmark")) {

                // split string
                String[] splitInput = input.split(" ");

                // get only the number from the 2nd half of the splitInput
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                // set isDone to false
                array[markIndex].setIsDone(false);

                System.out.println("OK, I've marked this task as not done yet: \n");
                System.out.println(array[markIndex].toString() + "\n");

                // add a Task
            } else {

                switch (input) {
                    case "todo" -> {

                        System.out.println("Understood! What is your ToDo? ");
                        String desc = scanner.nextLine();


                        try {
                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }

                            Todo newToDo = new Todo(desc);
                            indexCounter++;
                            array[indexCounter] = newToDo;
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case "deadline" -> {

                        System.out.println("Understood! What is your Deadline? ");
                        String desc = scanner.nextLine();

                        try {
                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }


                            System.out.println("When would you like your Deadline to be due by? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Deadline newDeadline = new Deadline(desc, endTime);
                            indexCounter++;
                            array[indexCounter] = newDeadline;
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }

                        
                    }
                    case "event" -> {

                        System.out.println("Understood! What is your Event? ");

                        String desc = scanner.nextLine();

                        try {
                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi dosen't understand! Is input empty?");
                            }

                            System.out.println("When would you like your Event to start? ");

                            // take in input
                            String startTime = scanner.nextLine();

                            System.out.println("When would you like your Event to end? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Event newEvent = new Event(desc, startTime, endTime);
                            indexCounter++;
                            array[indexCounter] = newEvent;
                            System.out.println("added: " + input);


                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                    default ->

                        // in event of invalid input
                            System.out.println("Hoshi dosen't understand! Please try again with the above keywords(in lowercase)");
                }

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
