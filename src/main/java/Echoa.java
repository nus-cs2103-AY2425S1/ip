import java.util.ArrayList;
import java.util.Scanner;

/**
 * Echoa is a class that demonstrates a simple console-based interaction.
 */

public class Echoa {
    /**
     * The main method is the entry point to the application.
     * It greets the user with a series of messages and allows input.
     * The user is able to access and modify their list of task using a series of instructions,
     * todo, deadline, event, mark, unmark, delete, list.
     * They can also end the chat using "bye".
     * @param args
     */
    public static void main(String[] args) throws InvalidInstructionException, InvalidTaskContentException {

        String[] instructionList = {"todo", "deadline", "event", "mark", "unmark", "delete", "list", "bye"};

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasklist = new ArrayList<>();

        int taskcount = 0;

        System.out.println("Hello! I'm Echoa.");
        System.out.println("What can I do for you?\n");

        try {
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();

                if (command.isBlank()) {
                    throw new InvalidInstructionException("Blank");
                } else if (command.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                } else if (command.equals("list")) {
                    System.out.println("My Task List");
                    if (taskcount == 0) {
                        System.out.println("No tasks yet :o");
                    } else {
                        for (int i = 0; i < taskcount; i++) {
                            int index = i + 1;
                            System.out.println(index + ". " + tasklist.get(i).toString());
                        }
                    }
                    System.out.println();
                } else if (command.startsWith("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasklist.get(index).markAsDone();
                    System.out.println("Task marked :)");
                    System.out.println(tasklist.get(index).toString() + "\n");
                } else if (command.startsWith("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasklist.get(index).unmarkAsUndone();
                    System.out.println("Task unmarked :(");
                    System.out.println(tasklist.get(index).toString() + "\n");
                } else if (command.startsWith("delete")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    tasklist.remove(index);
                    taskcount--;
                    System.out.println("Task deleted :/");
                    System.out.println(tasklist.get(index).toString() + "\n");
                    System.out.println("Now you have " + (taskcount) + " task(s).\n");
                } else {
                    String[] commandArr = command.split(" ", 2);

                    if (commandArr.length != 2) {
                        throw new InvalidTaskContentException();
                    }

                    String type = commandArr[0];
                    String task = commandArr[1];

                    if (type.equals("todo")) {
                        tasklist.add(new ToDo(task));
                    } else if (type.equals("deadline")) {
                        String[] taskArray = task.split(" /", 2);
                        if (taskArray.length != 2) {
                            throw new InvalidDeadlineContentException();
                        }
                        String taskDescription = taskArray[0];
                        String taskDate = taskArray[1];
                        tasklist.add(new Deadline(taskDescription, taskDate));
                    } else if (type.equals("event")) {
                        String[] taskArray = task.split(" /", 3);
                        if (taskArray.length != 3) {
                            throw new InvalidEventContentException();
                        }
                        String taskDescription = taskArray[0];
                        String taskStart = taskArray[1];
                        taskStart = taskStart.split(" ", 2)[1];
                        String taskEnd = taskArray[2];
                        taskEnd = taskEnd.split(" ", 2)[1];
                        tasklist.add(new Event(taskDescription, taskStart, taskEnd));
                    } else {
                        throw new InvalidInstructionException(type);
                    }

                    tasklist.get(taskcount).unmarkAsUndone();
                    System.out.println("Task added!");
                    System.out.println(tasklist.get(taskcount).toString());
                    System.out.println("Now you have " + (taskcount + 1) + " task(s).\n");
                    taskcount++;
                }
            }
        } catch (InvalidInstructionException e) {
            System.out.println(e.toString());
            System.out.println("Here are the valid instructions: ");
            for (String i : instructionList) {
                System.out.print("- ");
                System.out.println(i);
            }
            System.out.println("Please try again.");
        } catch (InvalidTaskContentException e) {
            System.out.println(e.toString());
            System.out.println("Please try again.");
        }
    }
}
