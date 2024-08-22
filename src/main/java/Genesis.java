import java.util.Scanner;
import java.util.ArrayList;

class Task {
    protected String description;
    protected boolean isComplete;

    public Task (String description) {
        this.description = description;
        this.isComplete = false;
    }

    @Override
    public String toString() {
        String marked;
        if (isComplete) {
            marked = "[X] ";
        } else {
            marked = "[ ] ";
        }
        return marked + this.description;
    }

    public void mark() {
        this.isComplete = true;
    }
    public void unmark() {
        this.isComplete = false;
    }
}
public class Genesis {


    public static void main(String[] args) {

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        System.out.println("Hello! I'm Genesis!\n"
                + "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(i + 1 + ". " + items.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= items.size()) {
                    System.out.println("No such task exists!");
                    continue;
                }
                Task current = items.get(index);
                current.mark();
                System.out.println("Nice! I've marked this as done:\n" +
                                    current.description);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= items.size()) {
                    System.out.println("No such task exists!");
                    continue;
                }
                Task current = items.get(index);
                current.unmark();
                System.out.println("Ok. I've marked this task as not done yet:\n" +
                        current.description);
            } else {
                Task current = new Task(input);
                items.add(current);
                System.out.println("added: " + current.description);
            }
        }

    }
}
