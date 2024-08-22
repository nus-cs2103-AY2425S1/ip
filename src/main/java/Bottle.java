import java.util.Scanner;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";
    static class Task {
        static class Todo extends Task {
            public Todo(String desc) {
                super(desc);
            }
            @Override
            public String toString() {
                return "[T]" + super.toString();
            }
        }
        static class Deadline extends Task {
            protected String by;

            public Deadline(String description, String by) {
                super(description);
                this.by = by;
            }

            @Override
            public String toString() {
                return "[D]" + super.toString() + " (by: " + by + ")";
            }
        }
        static class Event extends Task {
            protected String from;
            protected String to;
            public Event(String desc, String from, String to) {
                super(desc);
                this.from = from;
                this.to = to;
            }
            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
            }
        }
        private boolean isChecked;
        private String taskDesc;

        public Task(String taskDesc) {
            this.taskDesc = taskDesc;
            this.isChecked = false;
        }
        public void mark() {
            this.isChecked = true;
        }

        public void unMark() {
            this.isChecked = false;
        }

        @Override
        public String toString() {
            String box = isChecked ? "[X] " : "[ ] ";
            return box + taskDesc;
        }
    }
    public static void printwithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int taskCount = 0;
        String welcomeMsg =
                " Hello! I'm Bottle\n" +
                " What can I do for you?";

        String byeMsg =" Bye. Hope to see you again soon!";
        printwithBreak(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printwithBreak(byeMsg);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(lineBreak);
                for (int i = 0; i < taskCount; i++) {
                    int idx = i + 1;
                    System.out.println(idx + ". " + taskList[i]);
                }
                System.out.println(lineBreak);
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = taskList[taskIndex];
                    task.mark();
                    String msg = "Nice! I've marked this task as done:\n";
                    printwithBreak(msg + task);
                }
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    Task task = taskList[taskIndex];
                    task.unMark();
                    String msg = "OK, I've marked this task as not done yet:\n";
                    printwithBreak(msg + task);
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                taskList[taskCount] = new Task.Todo(description);
                taskCount++;
                System.out.print(lineBreak);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + taskList[taskCount - 1].toString());
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println(lineBreak);
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                String description = parts[0];
                String by = parts[1];
                taskList[taskCount] = new Task.Deadline(description, by);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + taskList[taskCount - 1].toString());
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                String description = parts[0];
                String from = parts[1];
                String to = parts[2];
                taskList[taskCount] = new Task.Event(description, from, to);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + taskList[taskCount - 1].toString());
                System.out.println(" Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                taskList[taskCount] = new Task(input);
                taskCount++;
                printwithBreak("added " + input);
            }
        }
    }
}
