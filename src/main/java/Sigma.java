import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    public static void main(String[] args) {
        String name = "SIGMA";
        Scanner input = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();

        print("Hello! I'm " + name + "\nLooking forward to slaying with you!\nWhat do you need today?");
        String userPrompt = input.nextLine();
        while (!userPrompt.isEmpty()) {
            String[] split = userPrompt.split(" ", 2);
            String command = split[0];
            switch (command) {
                case "bye":
                    exit();
                    System.exit(0);
                    break;
                case "list":
                    StringBuilder s = new StringBuilder();
                    for (Task item : items) {
                        s.append(item.getId() + ". " + item.toString() + "\n");
                    }
                    print("You want a list? You got it!\n" + s.toString());
                    break;
                case "mark":
                    if (split.length > 1) {
                        int index = Integer.parseInt(split[1]);
                        if (index > 0 && index <= items.size()) {
                            Task item = items.get(index - 1);
                            if (item.getStatusString() == "X") {
                                print("What the sigma? Task already marked!");
                                break;
                            }
                            item.setStatus(true);
                            print(String.format("SLAYYY! I'm going to mark this done for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                        } else {
                            print("What the skibidi? Invalid task number!");
                        }
                    } else {
                        print("Bro's dreaming. Add a number bozo!");
                    }
                    break;
                case "unmark":
                    if (split.length > 1) {
                        int index = Integer.parseInt(split[1]);
                        if (index > 0 && index <= items.size()) {
                            Task item = items.get(index - 1);
                            if (item.getStatusString() == " ") {
                                print("What the sigma? Task already unmarked!");
                                break;
                            }
                            item.setStatus(false);
                            print(String.format("Dang, I'm going to unmark this for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                        } else {
                            print("What the skibidi? Invalid task number!");
                        }
                    } else {
                        print("Bro's dreaming. Add a number bozo!");
                    }
                    break;
                case "todo":
                    ToDoTask toDoTask = new ToDoTask(split[1]);
                    items.add(toDoTask);
                    print("Productive! Added: \n" + toDoTask.toString()
                    + "\nNow you have " + items.size() + " tasks in the list!");
                    break;
                case "deadline":
                    String[] deadlineSplit = split[1].split(" /by ");
                    if (deadlineSplit.length < 2) {
                        print("What the sigma? You're missing the deadline!");
                        break;
                    }
                    DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], deadlineSplit[1]);
                    items.add(deadlineTask);
                    print("Wow! Keeping yourself busy! Added: \n" + deadlineTask.toString()
                    + "\nNow you have " + items.size() + " tasks in the list!");
                    break;
                case "event":
                    String[] eventSplit = split[1].split(" /from ");
                    if (eventSplit.length < 2) {
                        print("What the sigma? You're missing the timing!");
                        break;
                    }
                    String[] timing = eventSplit[1].split(" /to ");
                    if (timing.length < 2) {
                        print("What the sigma? You're missing the end timing!");
                        break;
                    }
                    EventTask eventTask = new EventTask(eventSplit[0], timing[0], timing[1]);
                    items.add(eventTask);
                    print("You're a busy bee! Added: \n" + eventTask.toString()
                    + "\nNow you have " + items.size() + " tasks in the list!");
                    break;
                default:
                    print("That's crazy - I don't understand! Try again! Enter " +
                            "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\" or \"bye\"!");
                    break;
            }
            userPrompt = input.nextLine();
        }
        exit();
    }

    private static void exit() {
        print("What the sigma? You're leaving so soon? Bye chat, see you again!");
    }

    private static void print(String message) {
        line();
        System.out.println(message);
        line();
    }
    private static void line() {
        String line = "----------------------------------------------------------------------------------------------";
        System.out.println(line);
    }
}
