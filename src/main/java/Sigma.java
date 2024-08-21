import java.util.ArrayList;
import java.util.Scanner;

public class Sigma {
    public static void main(String[] args) {
        String name = "SIGMA";
        Scanner input = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();

        print("Hello! I'm " + name + "\nLooking forward to slaying with you!\nWhat do you need today?" +
                "\nYou can add tasks with \"todo\", \"deadline\", \"event\" or view tasks with \"list\"." +
                "\nYou can also mark tasks as done with \"mark\" or \"unmark\" them." +
                "\nIf you're done, just type \"bye\" to exit.");

        String userPrompt = input.nextLine();
        while (!userPrompt.isEmpty()) {
            try {
                String[] split = userPrompt.split(" ", 2);
                String command = split[0];
                switch (command.toLowerCase()) {
                    case "bye":
                        exit();
                        System.exit(0);
                        break;
                    case "list":
                        StringBuilder s = new StringBuilder();
                        for (Task item : items) {
                            s.append(item.getId() + ". " + item.toString() + "\n");
                        }
                        if (items.size() == 0) {
                            print("What the sigma? You have no tasks!");
                        } else {
                            print("You want a list? You got it!\n" + s.toString());
                        }
                        break;
                    case "mark":
                        if (split.length > 1) {
                            int index = Integer.parseInt(split[1]);
                            if (index > 0 && index <= items.size()) {
                                Task item = items.get(index - 1);
                                if (item.getStatusString() == "X") {
                                    throw new SigmaException("What the sigma? Task already marked!");
                                }
                                item.setStatus(true);
                                print(String.format("SLAYYY! I'm going to mark this done for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                            } else {
                                throw new SigmaException("Invalid task number!");
                            }
                        } else {
                            throw new SigmaException("Bro's dreaming. Add a number bozo!");
                        }
                        break;
                    case "unmark":
                        if (split.length > 1) {
                            int index = Integer.parseInt(split[1]);
                            if (index > 0 && index <= items.size()) {
                                Task item = items.get(index - 1);
                                if (item.getStatusString() == " ") {
                                    throw new SigmaException("What the sigma? Task already unmarked!");
                                }
                                item.setStatus(false);
                                print(String.format("Dang, I'm going to unmark this for you:\n [%s] %s", item.getStatusString(), item.getDesc()));
                            } else {
                                throw new SigmaException("What the skibidi? Invalid task number!");
                            }
                        } else {
                            throw new SigmaException("Bro's dreaming. Add a number bozo!");
                        }
                        break;
                    case "todo":
                        if (split.length < 2) {
                            throw new SigmaException("???? You're missing the task! Write \"todo <task>\"!");
                        }
                        ToDoTask toDoTask = new ToDoTask(split[1]);
                        items.add(toDoTask);
                        print("Productive! Added: \n" + toDoTask.toString()
                                + "\nNow you have " + items.size() + " tasks in the list!");
                        break;

                    case "deadline":
                        if (split.length < 2) {
                            throw new SigmaException("What the sigma? You're missing the task! " +
                                    "Write \"deadline <task> /by <deadline>\"!");
                        }
                        String[] deadlineSplit = split[1].split(" /by ");
                        if (deadlineSplit.length < 2) {
                            throw new SigmaException("What the sigma? You're missing the deadline! " +
                                    "Write \"deadline <task> /by <deadline>\"!");
                        }
                        DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], deadlineSplit[1]);
                        items.add(deadlineTask);
                        print("Wow! Keeping yourself busy! Added: \n" + deadlineTask.toString()
                                + "\nNow you have " + items.size() + " tasks in the list!");
                        break;
                    case "event":
                        if (split.length < 2) {
                            throw new SigmaException("What the sigma? You're missing the task! " +
                                    "Write \"event <task> /from <start time> /to <end time>\"!");
                        }
                        String[] eventSplit = split[1].split(" /from ");
                        if (eventSplit.length < 2) {
                            throw new SigmaException("What the sigma? You're missing the timing!");
                        }
                        String[] timing = eventSplit[1].split(" /to ");
                        if (timing.length < 2) {
                            throw new SigmaException("What the sigma? You're missing the end timing!");
                        }
                        EventTask eventTask = new EventTask(eventSplit[0], timing[0], timing[1]);
                        items.add(eventTask);
                        print("You're a busy bee! Added: \n" + eventTask.toString()
                                + "\nNow you have " + items.size() + " tasks in the list!");
                        break;
                    default:
                        throw new SigmaException("That's crazy - I don't understand! Try again! Enter " +
                                "\"todo\", \"deadline\", \"event\", \"list\", \"mark\", \"unmark\" or \"bye\"!");
                }
            } catch (SigmaException e) {
                print(e.getMessage());
            } catch (NumberFormatException e) {
                print("What the sigma? I need a number!");
            } finally {
                // Do nothing
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
