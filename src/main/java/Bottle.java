import java.util.Scanner;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";
    static class Task {
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
            } else {
                taskList[taskCount] = new Task(input);
                taskCount++;
                printwithBreak("added " + input);
            }
        }
    }
}
