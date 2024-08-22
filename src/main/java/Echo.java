import java.util.ArrayList;
import java.util.List;

public class Echo {
    private String word;
    private List<Task> list;

    public Echo() {
        this.list = new ArrayList<>();
    }

    public void setWord(String word) {
        this.word = word;
    }
    //update the todo, deadline and event
    public void echoOut() {

            String description = word;
            String[] parts = description.split(" ", 2);
            String command = parts[0];

        switch (command) {
            case "list":
                if (list.isEmpty()) {
                    System.out.println("No items in the list yet!\n");
                }
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    String response = String.format("%d. %s", i + 1, task.getDescription());
                    System.out.println(response);
                }
                break;
            case "mark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to mark \n"
                                +  "____________________________________________________________\n");
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        if (index >= 0 && index < list.size()) {
                            Task t = list.get(index);
                            t.markAsDone();
                            String response = String.format("%s", t.getDescription());
                            System.out.println("Nice I've marked this task as done: \n"
                                    +  "____________________________________________________________\n"
                                    + response);
                        } else {
                            System.out.println("Invalid Task Number.\n");
                        }

                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                break;

            case "unmark":
                if (parts.length < 2) {
                    System.out.println("Please specify the task number to unmark \n"
                            +  "____________________________________________________________\n");
                } else {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        if (index >= 0 && index < list.size()) {
                            Task t = list.get(index);
                            t.unMark();
                            String response = String.format("%s", t.getDescription());
                            System.out.println("OK, I've marked this task as not done yet: \n"
                                    +  "____________________________________________________________\n"
                                    + response);
                        } else {
                            System.out.println("Invalid Task Number.\n");
                        }

                    } catch(NumberFormatException e) {
                        System.out.println("Please enter a valid task number\n");
                    }
                }
                break;

            case "todo":
                try {
                    Task toDoTask = new ToDoTask(parts[1]);
                    list.add(toDoTask);
                    System.out.print("Got it. I've added this task: \n" + toDoTask.getDescription() +
                            String.format("Now you have %d tasks in the list\n", list.size()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.print("Oh no! Please input a ToDo description!");
                }
                break;

            case "deadline":
                int byIndex = parts[1].indexOf("/by");
                if (byIndex == -1) {
                    System.out.println("Incorrect format of adding deadline tasks. " +
                            "Use '/by to specify the deadline after the task description");
                }
                String desc = parts[1].substring(0, byIndex);
                String deadline = parts[1].substring(byIndex + 3);
                Task deadlineTask = new DeadlineTask(desc, deadline);
                list.add(deadlineTask);
                System.out.print("Got it. I've added this task: \n" + deadlineTask.getDescription() +
                        String.format("Now you have %d tasks in the list\n", list.size()));

                break;
            case "event":
                int fromIndex = parts[1].indexOf("/from");
                int toIndex = parts[1].indexOf("/to");
                if (fromIndex == -1 || toIndex == -1) {
                    System.out.println("Incorrect format of adding event tasks. " +
                            "Use '/from to specify the start after the task description " +
                            "and /to to specify deadline after start time");
                }
                String details = parts[1].substring(0, fromIndex);
                String start = parts[1].substring(fromIndex + 5, toIndex);
                String end = parts[1].substring(toIndex + 3);
                Task eventTask = new EventTask(details, start, end);
                list.add(eventTask);
                System.out.print("Got it. I've added this task: \n" + eventTask.getDescription() + "\n" +
                                String.format("Now you have %d tasks in the list\n", list.size()));
                break;
            case "delete":
                int index = Integer.parseInt(parts[1]) - 1;
                if (index >= 0 && index < list.size()) {
                    Task deletedTask = list.get(index);
                    list.remove(index);
                    System.out.print("Noted. I've removed this task:\n" +
                            deletedTask.getDescription() +
                            String.format("Now you have %d tasks in the list.\n", list.size()));
                } else {
                    System.out.print("There is no such task number!");
                }
                break;

            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n"
                                +  "____________________________________________________________\n");
                break;
            default:
                System.out.println("Oh no! You have input an unknown command!");
                break;
        }
    }
    public void greet() {
        System.out.print("____________________________________________________________\n"
                + "Hello! I'm Meeks! Your friendly chatbot!\n"
                + "What can I do for you?\n");
    }
}
