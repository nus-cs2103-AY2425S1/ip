import java.util.Scanner;

public class YapperBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numTask = 0;

        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (true) {
            String userInput = scanner.nextLine();
            try {
                if (userInput.equals("bye")) {
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numTask; i++) {
                        System.out.println((i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("________________________________");
                } else if (userInput.startsWith("mark")) {
                    int targetTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[targetTask].mark();
                    System.out.println("________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + tasks[targetTask]);
                    System.out.println("________________________________");
                } else if (userInput.startsWith("unmark")) {
                    int targetTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks[targetTask].unmark();
                    System.out.println("________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + tasks[targetTask]);
                    System.out.println("________________________________");
                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() == 4) {
                        throw new YapperBotException("Umm, the description for a Todo task cannot be empty!!!");
                    }
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new YapperBotException("Umm, the description for a Todo task cannot be empty!!!");
                    }
                    tasks[numTask] = new Todo(description);
                    numTask++;
                    System.out.println("________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[numTask - 1]);
                    System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                    System.out.println("________________________________");
                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() == 8) {
                        throw new YapperBotException("Umm, the description for a deadline task cannot be empty!!!");
                    }
                    String[] input = userInput.substring(9).split(" /by ");
                    if (input.length < 2 || input[0].trim().isEmpty()) {
                        throw new YapperBotException("Umm, you need to provide a description with a deadline.\nEg. deadline play bball /by Monday");
                    }
                    String description = input[0];
                    String by = input[1];
                    tasks[numTask] = new Deadline(description, by);
                    numTask++;
                    System.out.println("________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[numTask - 1]);
                    System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                    System.out.println("________________________________");
                } else if (userInput.startsWith("event")) {
                    if (userInput.length() == 5) {
                        throw new YapperBotException("Umm, the description for a event task cannot be empty!!!");
                    }
                    String[] input = userInput.substring(6).split(" /from | /to ");
                    if (input.length < 3 || input[0].trim().isEmpty()) {
                        throw new YapperBotException("Umm, you need to provide a description with a time frame.\nEg. deadline play bball /from Monday 2pm /to Monday 4pm");
                    }
                    String description = input[0];
                    String from = input[1];
                    String to = input[2];
                    tasks[numTask] = new Event(description, from, to);
                    numTask++;
                    System.out.println("________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks[numTask - 1]);
                    System.out.println("Now you have " + numTask + (numTask == 1 ? " task" : " tasks") + " in the list.");
                    System.out.println("________________________________");
                } else {
                    throw new YapperBotException("IDK what you are yapping about!!");
                }
            } catch (YapperBotException e){
                System.out.println("________________________________");
                System.out.println(e.getMessage());
                System.out.println("________________________________");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("______________________________");
                System.out.println("Umm, the task number you entered is out of bounds! Please enter a number less than or equal to 100");
                System.out.println("______________________________");
        }
        }

        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }
}
