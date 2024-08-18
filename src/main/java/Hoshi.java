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


        String[] array = new String[100];
        int indexCounter = -1;

        while (true) {

            indexCounter++;

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
                    System.out.println(i+1 + ". " + array[i] + "\n");
                }

                // add
            } else {
                // add
                array[indexCounter] = input;
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
