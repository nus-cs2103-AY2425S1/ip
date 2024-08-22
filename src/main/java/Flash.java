import java.util.Scanner;

public class Flash {
    public static void main(String[] args) {
        /* create an instance of scanner class */
        Scanner scanner = new Scanner(System.in);
        /* create an instance for Store list*/
        StoreList storeList = new StoreList();

        //Horizontal line for better readability
        String line = "    __________________________________________";

        System.out.println(line);
        System.out.println("    Hey! I'm Flash");
        System.out.println("    What can I do for ya?");
        System.out.println(line);

        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {

            // Split the string by spaces
            String[] words = userInput.split(" ");

            //if user inputs mark, mark task
            if (words[0].equals("mark")) {
                System.out.println(line);
                storeList.markItem(Integer.parseInt(words[1]));
                System.out.println(line);
                userInput = scanner.nextLine();
            }

            //if user inputs unmark, unmark task
            else if (words[0].equals("unmark")) {
                System.out.println(line);
                storeList.UnmarkItem(Integer.parseInt(words[1]));
                System.out.println(line);
                userInput = scanner.nextLine();
            }

            //if user inputs list, display tasks
            else if (userInput.equals("list")) {
                System.out.println(line);
                storeList.displayItems();
                System.out.println(line);
                userInput = scanner.nextLine();

            } else {
                //If user types anything other than Bye, repeat it
                System.out.println(line);
                storeList.addItem(userInput);
                System.out.println(line);
                userInput = scanner.nextLine();
            }
        }

        //is user inputs bye, exit
        System.out.println(line);
        System.out.println("    Bye. Hope to see ya again soon!");
        System.out.println(line);
        scanner.close();
    }
}

