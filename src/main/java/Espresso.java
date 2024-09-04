import java.util.Scanner;
public class Espresso {
    private Task[] tasks ;
    private int count;

    public Espresso() {
        tasks = new Task[100];
        count = 0;
    }

    void addToList(String str) {
        Task task = null;
        try {
            if (count < tasks.length) {
                if (str.startsWith("todo")) {
                    String status = str.substring(4).trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of todo task is required");
                    task = new todoTask(status);
                } else if (str.startsWith("deadline")) {
                    String split[] = str.substring(8).split(" /by");
                    String status = split[0].trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of deadline task is required");
                    task = new deadlineTask(status, split[1].trim());
                } else if (str.startsWith("event")) {
                    String split1[] = str.substring(5).split(" /from ");
                    String status = split1[0].trim();
                    if (status.isEmpty())
                        throw new IllegalArgumentException("Description of event task is required");
                    String split2[] = split1[1].split(" /to ");
                    task = new eventTask(status, split2[0].trim(), split2[1].trim());
                }else {
                    throw new IllegalArgumentException("Random Input! I don't know what that means :(");
                }
            }
            else
                throw new IllegalArgumentException("Task list is full");
            tasks[count] = task;
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            if (count == 0)
                System.out.println("Now you have " + (count + 1) + " task in the list.");
            else
                System.out.println("Now you have " + (count + 1) + " tasks in the list.");
            System.out.println("____________________________________________________________");
            count++;
        }
        catch (IllegalArgumentException e) {
            System.out.println("____________________________________________________________");
            System.out.println("Oops! " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    void process() {
        System.out.println("_________________________________________");
        System.out.println("Hello! I'm Espresso");
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");
        Scanner sc = new Scanner(System.in);
        String str;
        while (true) {
            str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (str.startsWith("mark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                markTask(pos - 1);
            } else if (str.startsWith("unmark ")) {
                String extractNum = str.substring(str.indexOf(' ') + 1).trim();
                int pos = Integer.valueOf(extractNum);
                unmarkTask(pos - 1);
            }else if (str.equals("list")) {
                printList();
            }
            else {
                addToList(str);
            }
        }
        sc.close();
    }
    void markTask(int position) {
        System.out.println("_________________________________________");
        if (position >= 0 && position < count) {
            tasks[position].mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[position]);
            System.out.println("_________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(int position) {
        System.out.println("_________________________________________");
        if (position >= 0 && position < count) {
            tasks[position].unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[position]);
            System.out.println("_________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        Espresso esp = new Espresso();
        esp.process();
    }
}
