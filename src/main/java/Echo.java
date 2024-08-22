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
    public void echoOut() {
        String description = word.toLowerCase();
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
                    String response = String.format("%d. [%s] %s", i + 1, task.getStatusIcon(), task.getDescription());
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
                            String response = String.format("[%s] %s", t.getStatusIcon(), t.getDescription());
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
                            String response = String.format("[%s] %s", t.getStatusIcon(), t.getDescription());
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
            case "bye":
                System.out.println("Bye. Hope to see you again soon!\n"
                                +  "____________________________________________________________\n");
                break;
            default:
                list.add(new Task(word));
                System.out.println("\n____________________________________________________________\n"
                        + "added: " + word + "\n____________________________________________________________\n");
                break;
        }
    }
    public void greet() {
        System.out.print("____________________________________________________________\n"
                + "Hello! I'm Meeks! Your friendly chatbot!\n"
                + "What can I do for you?\n");
    }
}
