import java.util.Scanner;

public class Talkie {
    public static void main(String[] args) {

        // Initialisation of Scanner
        Scanner scanner = new Scanner(System.in);

        String logo = """
                -----------------------------------------------\s
                Hello! I'm Talkie, your friendly ChatBot.\s
                What can I do for you?\s
                -----------------------------------------------\s
                """;

        String byeMessage = """ 
                -----------------------------------------------\s
                Bye. Hope to see you again soon!\s
                -----------------------------------------------\s
                """;

        System.out.println(logo);

        // Program runs until user inputs "bye"
        while (true) {
            String toDo = scanner.nextLine();

            if (toDo.equalsIgnoreCase("bye")) {
                System.out.println(byeMessage);
                break;
            }


            String toDoMessage = "-----------------------------------------------\n"
                    + toDo + "\n"
                    + "-----------------------------------------------\n";

            System.out.println(toDoMessage);
        }

        // Close the scanner when the program ends
        scanner.close();
    }
}
