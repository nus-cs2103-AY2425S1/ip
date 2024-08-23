import java.util.Scanner;
import java.util.ArrayList;

enum Commands {BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, OTHERS}

public class YapperBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("________________________________");
        System.out.println("Hello! I'm YapperBot");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (true) {

            String userInput = scanner.nextLine();
            Commands userCommand = YapperBot.getCommand(userInput);

            try {
                switch (userCommand) {

                    case BYE:
                        System.out.println("________________________________");
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println("________________________________");
                        return;

                    case LIST:
                        System.out.println("________________________________");
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.println("________________________________");
                        break;

                    case MARK:
                        if (userInput.length() == 4) {
                            throw new YapperBotException("Umm, you need to provide a task number!");
                        }
                        int markTaskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        tasks.get(markTaskIndex).mark();
                        System.out.println("________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(markTaskIndex));
                        System.out.println("________________________________");
                        break;

                    case UNMARK:
                        if (userInput.length() == 6) {
                            throw new YapperBotException("Umm, you need to provide a task number!");
                        }
                        int unmarkTargetTask = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        tasks.get(unmarkTargetTask).unmark();
                        System.out.println("________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(unmarkTargetTask));
                        System.out.println("________________________________");
                        break;

                    case TODO:
                        if (userInput.length() == 4) {
                            throw new YapperBotException("Umm, the description for a Todo task cannot be empty!!!");
                        }
                        String todoDescription = userInput.substring(5).trim();
                        if (todoDescription.isEmpty()) {
                            throw new YapperBotException("Umm, the description for a Todo task cannot be empty!!!");
                        }
                        tasks.add(new Todo(todoDescription));
                        System.out.println("________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                        System.out.println("________________________________");
                        break;

                    case DEADLINE:
                        if (userInput.length() == 8) {
                            throw new YapperBotException("Umm, the description for a deadline task cannot be empty!!!");
                        }
                        String[] deadlineInput = userInput.substring(9).split(" /by ");
                        if (deadlineInput.length < 2 || deadlineInput[0].trim().isEmpty()) {
                            throw new YapperBotException("Umm, you need to provide a description with a deadline.\nEg. deadline play bball /by Monday");
                        }
                        String deadlineDescription = deadlineInput[0];
                        String by = deadlineInput[1];
                        tasks.add(new Deadline(deadlineDescription, by));
                        System.out.println("________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                        System.out.println("________________________________");
                        break;

                    case EVENT:
                        if (userInput.length() == 5) {
                            throw new YapperBotException("Umm, the description for a event task cannot be empty!!!");
                        }
                        String[] eventInput = userInput.substring(6).split(" /from | /to ");
                        if (eventInput.length < 3 || eventInput[0].trim().isEmpty()) {
                            throw new YapperBotException("Umm, you need to provide a description with a time frame.\nEg. deadline play bball /from Monday 2pm /to Monday 4pm");
                        }
                        String eventDescription = eventInput[0];
                        String from = eventInput[1];
                        String to = eventInput[2];
                        tasks.add(new Event(eventDescription, from, to));
                        System.out.println("________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                        System.out.println("________________________________");
                        break;

                    case DELETE:
                        if (tasks.isEmpty()) {
                            throw new YapperBotException("Umm, there are no tasks currently. Please use todo/ deadline/ event commands to add respective tasks!:):)");
                        } else if (userInput.length() == 6) {
                            throw new YapperBotException("Umm, you need to provide a task number!");
                        }
                        int DeleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                        Task deleteTargetTask = tasks.get(DeleteTaskNumber);
                        tasks.remove(deleteTargetTask);
                        System.out.println("________________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + deleteTargetTask);
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
                        System.out.println("________________________________");
                        break;

                    case OTHERS:
                        throw new YapperBotException("IDK what you are yapping about!!");

                }
            } catch(YapperBotException e){
                System.out.println("________________________________");
                System.out.println(e.getMessage());
                System.out.println("________________________________");
            } catch(IndexOutOfBoundsException e) {
                System.out.println("______________________________");
                System.out.println("Invalid task number! Please enter a number greater than 0 and less than " + (tasks.size() + 1) + "!");
                System.out.println("______________________________");
            }
        }
    }
    private static Commands getCommand(String userInput) {
        if (userInput.equals("bye")) {
            return Commands.BYE;
        } else if (userInput.equals("list")) {
            return Commands.LIST;
        } else if (userInput.startsWith("mark")) {
            return Commands.MARK;
        } else if (userInput.startsWith("unmark")) {
            return Commands.UNMARK;
        } else if (userInput.startsWith("todo")) {
            return Commands.TODO;
        } else if (userInput.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return Commands.EVENT;
        } else if (userInput.startsWith("delete")) {
            return Commands.DELETE;
        } else {
            return Commands.OTHERS;
        }
    }
}
