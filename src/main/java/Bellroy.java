import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bellroy {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String message = "____________________________________________________________\n" +
                " Hello! I'm Bellroy\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String userInput = "";
        List<String> toDoList = new ArrayList<>();

        System.out.println(message);
        while (true) {

            userInput = scanner.nextLine();

//            if (userInput.equalsIgnoreCase("bye")) {
//                System.out.println("____________________________________________________________\n" +
//                        "     Bye. Hope to see you again soon!\n" +
//                        "    ____________________________________________________________\n");
//
//                break;
//            }
//
//            System.out.println("____________________________________________________________\n" +
//                    userInput + "\n" +
//                    "    ____________________________________________________________");

            switch (userInput.toLowerCase()) {
                case "bye":
                    System.out.println("____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                    scanner.close();
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    for(int i = 0; i < toDoList.size(); i++) {
                        System.out.println("     " + (i + 1) + ". " + toDoList.get(i));
                    }
                    System.out.println("____________________________________________________________\n");
                    break;

                default:
                    toDoList.add(userInput);
                    System.out.println("____________________________________________________________\n" +
                            "     added: " + userInput + "\n" +
                            "____________________________________________________________\n");
                    break;
            }
        }

    }
}
