import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Talkie {

    private static List<String> taskList = new ArrayList<>();

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

            if (toDo.equalsIgnoreCase("list")) {

                String listMessage = "";

                for (int i=0; i<taskList.size(); i++) {
                    String description = (i + 1) + ". " + taskList.get(i) + "\n";
                    listMessage += description;
                }

                String finalListMessage = "-----------------------------------------------\n"
                        +  listMessage
                        + "-----------------------------------------------\n";
                System.out.println(finalListMessage);
                continue;
            }


            // Initialise a new Item Object
            Task t = new Task(toDo);
            taskList.add(t.toString());

            String toDoMessage = "-----------------------------------------------\n"
                    + "Added: " + t + "\n"
                    + "-----------------------------------------------\n";

            System.out.println(toDoMessage);
        }

        // Close the scanner when the program ends
        scanner.close();
    }
}
