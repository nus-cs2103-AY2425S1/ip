import java.util.ArrayList;
import java.util.Scanner;

public class SlothingWaffler {
    public static void main(String[] args) {

        greet();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {
                String input = scanner.nextLine();
                String[] split = input.split(" ", 2);

                if (split[0].strip().equals("bye")) {
                    exit();
                    break;
                }
                switch (split[0].strip()) {
                    case "list" -> displayTaskList(tasks);
                    case "mark" -> markTask(tasks, Integer.parseInt(split[1]) - 1);
                    case "delete" -> deleteTask(tasks, Integer.parseInt(split[1]) - 1);
                    case "todo" -> addTodoTask(split, tasks);
                    case "deadline" -> addDeadlineTask(split, tasks);
                    case "event" -> addEventTask(split, tasks);
                    default -> throw new SlothingWafflerException("STOP SLOTHING! Please give me instructions that the Slothing Waffler can understand :(");
                }
            } catch (SlothingWafflerException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("YUM. The Waffler is ready for your next command!");
            }
        }
        scanner.close();
    }

    private static void displayTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    private static void markTask(ArrayList<Task> tasks, int taskNum) {
        tasks.get(taskNum).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNum).toString());
    }

    private static void exit() {
        System.out.println("See you next time! Remember to get a waffle!");
    }

    private static void greet() {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");
    }

    private static void addTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addTodoTask(String[] split, ArrayList<Task> tasks) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Todo Task cannot be empty!");
        }
        tasks.add(new Todo(split[1]));
        addTaskMessage(tasks);
    }

    private static void addDeadlineTask(String[] split, ArrayList<Task> tasks) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of a Deadline Task cannot be empty.");
        }
        String[] desc = split[1].split(" /by ", 2);
        if (desc.length < 2) {
            throw new SlothingWafflerException("HEY!! The Deadline Task must have a description AND a due date.");
        }
        tasks.add(new Deadline(desc[0], desc[1]));
        addTaskMessage(tasks);
    }

    private static void addEventTask(String[] split, ArrayList<Task> tasks) throws SlothingWafflerException {
        if (split.length < 2 || split[1].isBlank()) {
            throw new SlothingWafflerException("HEY!! The description of an Event Task cannot be empty.");
        }
        String[] desc = split[1].split(" /from | /to ");
        if (desc.length < 3) {
            throw new SlothingWafflerException("HEY!! An event must have a description, start time, and end time.");
        }
        tasks.add(new Event(desc[0], desc[1], desc[2]));
        addTaskMessage(tasks);
    }

    private static void deleteTask(ArrayList<Task> tasks, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskNum));
        tasks.remove(taskNum);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
