import java.util.ArrayList;
import java.util.Arrays;
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
        List<Task> toDoList = new ArrayList<>();

        System.out.println(message);
        while (true) {

            userInput = scanner.nextLine();
            String[] input = userInput.split(" /", 2);
            String type = input[0].split(" ")[0].toLowerCase();
            String description = input[0].substring(type.length());

            switch (type) {
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

                case "mark":
                    int position = Integer.parseInt(userInput.split(" ")[1]);
                    toDoList.get(position - 1).markDone();
                    System.out.println("    ____________________________________________________________\n" +
                            "     Nice! I've marked this task as done:\n" +
                            "     " + toDoList.get(position-1).toString() + "\n" +
                            "    ____________________________________________________________");
                    break;

                case "unmark":
                    int pos = Integer.parseInt(userInput.split(" ")[1]);
                    toDoList.get(pos - 1).undo();
                    System.out.println("    ____________________________________________________________\n" +
                            "     OK, I've marked this task as not done yet:\n" +
                            "     " + toDoList.get(pos-1).toString() + "\n" +
                            "    ____________________________________________________________");
                    break;

                case "todo":
                    Task todo = new Todo(description);
                    toDoList.add(todo);
                    String response1 = String.format("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + todo + "\n" +
                            "     Now you have %d tasks in the list.\n" +
                            "    ____________________________________________________________", toDoList.size());
                    System.out.println(response1);
                    break;

                case "deadline":
                    String dueDate = input[1].split(" ", 2)[1].trim();
                    Task deadline = new deadline(description, dueDate);
                    toDoList.add(deadline);
                    String response2 = String.format("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + deadline + "\n" +
                            "     Now you have %d tasks in the list.\n" +
                            "    ____________________________________________________________", toDoList.size());
                    System.out.println(response2);
                    break;

                case "event":
                    String startTime = input[1].split(" /", 2)[0].split(" ", 2)[1].trim();
                    String endTime = input[1].split(" /", 2)[1].split(" ", 2)[1].trim();
                    Task event = new Event(description, startTime, endTime);
                    toDoList.add(event);
                    String response3 = String.format("    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "       " + event + "\n" +
                            "     Now you have %d tasks in the list.\n" +
                            "    ____________________________________________________________", toDoList.size());
                    System.out.println(response3);
                    break;


//                default:
//                    Task temp = new Task(userInput);
//                    toDoList.add(temp);
//                    System.out.println("____________________________________________________________\n" +
//                            "     added: " + userInput + "\n" +
//                            "____________________________________________________________\n");
//                    break;
            }
        }

    }
}
