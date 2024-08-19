import java.util.Scanner;
import java.util.ArrayList;

public class Rizzler {
    public static void main(String[] args) {
        String separator = "_______________________________________________________\n";
        String greeting = separator
                + "Hello! I'm the Rizzler.\n"
                + "What can I do for you today?\n"
                + separator;
        String goodbye = separator
                + "Bye! Rizz you later!\n"
                + separator;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(greeting);
        while(true) {
            String input = sc.nextLine();
            String[] parsedInput = input.split(" ");
            switch (parsedInput[0]) {
                case "bye":
                    System.out.println(goodbye);
                    sc.close();
                    System.exit(0);
                case "list":
                    if (taskList.isEmpty()) {
                        System.out.println(separator
                                + "No tasks here yet\n"
                                + separator);
                    } else {
                        System.out.println(separator
                                + "Here are the tasks in your list");
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(String.valueOf(i + 1) + ". " + taskList.get(i));
                        }
                        System.out.println(separator);
                    }
                    break;
                case "mark":
                    if (taskList.isEmpty()) {
                        System.out.println(separator
                                + "Put tasks in the list first!\n"
                                + separator);
                        break;
                    }
                    try {
                        int index = Integer.parseInt(parsedInput[1]) - 1;
                        taskList.get(index).markAsDone();
                        System.out.println(separator
                                + "Hell yeah! You finished your task:\n"
                                + taskList.get(index) + "\n"
                                + separator);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(separator
                                + "Write a valid number for your second argument!\n"
                                + separator);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(separator
                                + "Write a task number that's actually in the list!\n"
                                + separator);
                        break;
                    }
                case "unmark":
                    if (taskList.isEmpty()) {
                        System.out.println(separator
                                + "Put tasks in the list first!\n"
                                + separator);
                        break;
                    }
                    try {
                        int index2 = Integer.parseInt(parsedInput[1]) - 1;
                        taskList.get(index2).unmark();
                        System.out.println(separator
                                + "Womp womp. Better do it later:\n"
                                + taskList.get(index2) + "\n"
                                + separator);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(separator
                                + "Write a valid number for your second argument!\n"
                                + separator);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(separator
                                + "Write a task number that's actually in the list!\n"
                                + separator);
                        break;
                    }
                case "todo":
                    if (parsedInput.length == 1) {
                        System.out.println(separator
                                + "Please write down your todo ya knobhead!\n"
                                + separator);
                        break;
                    }
                    String taskName = parsedInput[1];
                    for (int i = 2; i < parsedInput.length; i++) {
                        taskName += " " + parsedInput[i];
                    }
                    Todo newTask = new Todo(taskName);
                    taskList.add(newTask);
                    System.out.println(separator
                            + "Gotcha! I've added the new task for you:\n"
                            + newTask + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list.\n"
                            + separator);
                    break;
                case "deadline":
                    int indexOfBy = 0;
                    boolean byInInput = false;
                    for (int i = 1; i < parsedInput.length; i++) {
                        if (parsedInput[i].equals("/by")) {
                            indexOfBy = i;
                            byInInput = true;
                            break;
                        }
                    }
                    if ((!byInInput) || (indexOfBy == parsedInput.length - 1)) {
                        System.out.println(separator
                                + "Please set time to complete by ya knobhead!\n"
                                + separator);
                        break;
                    } else if (indexOfBy == 1) {
                        System.out.println(separator
                                + "Please write your deadline ya knobhead!\n"
                                + separator);
                        break;
                    }
                    String deadline = parsedInput[1];
                    for (int i = 2; i < indexOfBy; i++) {
                        deadline += " " + parsedInput[i];
                    }
                    String byTime = parsedInput[indexOfBy + 1];
                    for (int i = indexOfBy + 2; i < parsedInput.length; i++) {
                        byTime += " " + parsedInput[i];
                    }
                    Deadline newDeadline = new Deadline(deadline, byTime);
                    taskList.add(newDeadline);
                    System.out.println(separator
                            + "Gotcha! I've added the new task for you:\n"
                            + newDeadline + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list.\n"
                            + separator);
                    break;
                case "event":
                    int indexOfFrom = 0;
                    int indexOfTo = 0;
                    boolean hasFrom = false;
                    boolean hasTo = false;
                    for (int i = 1; i < parsedInput.length; i++) {
                        if (!hasFrom && parsedInput[i].equals("/from")) {
                            indexOfFrom = i;
                            hasFrom = true;
                        }
                        if (!hasTo && parsedInput[i].equals("/to")) {
                            indexOfTo = i;
                            hasTo = true;
                        }
                    }
                    if (!hasFrom || (indexOfTo - indexOfFrom == 1)) {
                        System.out.println(separator
                                + "Please write the event start timing ya knobhead!\n"
                                + separator);
                        break;
                    } else if (!hasTo || (indexOfTo == parsedInput.length - 1)) {
                        System.out.println(separator
                                + "Please write the event end timing ya knobhead!\n"
                                + separator);
                        break;
                    } else if (indexOfFrom == 1) {
                        System.out.println(separator
                                + "Please write your event ya knobhead!\n"
                                + separator);
                        break;
                    }
                    String event = parsedInput[1];
                    for (int i = 2; i < indexOfFrom; i++) {
                        event += " " + parsedInput[i];
                    }
                    String fromTime = parsedInput[indexOfFrom + 1];
                    for (int i = indexOfFrom + 2; i < indexOfTo; i++) {
                        fromTime += " " + parsedInput[i];
                    }
                    String toTime = parsedInput[indexOfTo + 1];
                    for (int i = indexOfTo + 2; i < parsedInput.length; i++) {
                        toTime += " " + parsedInput[i];
                    }
                    Event newEvent = new Event(event, fromTime, toTime);
                    taskList.add(newEvent);
                    System.out.println(separator
                            + "Gotcha! I've added the new task for you:\n"
                            + newEvent + "\n"
                            + "Now you have " + taskList.size() + " tasks in the list.\n"
                            + separator);
                    break;
                case "delete":
                    if (taskList.isEmpty()) {
                        System.out.println(separator
                                + "Put tasks in the list first!\n"
                                + separator);
                        break;
                    }
                    try {
                        int deleteIndex = Integer.parseInt(parsedInput[1]) - 1;
                        System.out.println(separator
                                + "I have removed this task for you:\n"
                                + taskList.remove(deleteIndex) + "\n"
                                + "Now you have " + taskList.size() + " tasks in the list.\n"
                                + separator);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(separator
                                + "Write a valid number for your second argument!\n"
                                + separator);
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(separator
                                + "Write a task number that's actually in the list!\n"
                                + separator);
                        break;
                    }
                default:
                    System.out.println(separator
                            + "I've gyatt no idea what you're saying!\n"
                            + separator);
            }
        }
    }
}
