import java.util.ArrayList;
import java.util.Scanner;
public class Patrick {
    public static void main(String[] args) {
        String horizontalLine = "-----------------------------------------------------------------------\n";
        String greetingMsg = horizontalLine + "Hello! I'm Patrick, Spongebob bestie\nHow can I help you?\n" + horizontalLine;
        String exitMsg = horizontalLine + "Bye. Hope to see you again soon!\n" + horizontalLine;
        String input;
        ArrayList list = new ArrayList();
        Task task;

        Scanner inputMsg = new Scanner(System.in);
        System.out.println(greetingMsg);
        do {
            input = inputMsg.nextLine();
            if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task curr = (Task) list.get(i - 1);
                    System.out.println(i + ". " + curr.toString());
                }
            }
            else if (input.equals("bye")) {
                break;
            }
            else if (input.startsWith("mark")) {
                String taskNo = input.replace("mark", "").trim();
                int num = Integer.parseInt(taskNo);
                Task curr = (Task) list.get(num - 1);
                curr.markAsDone();

                System.out.println(horizontalLine + "Nice! I've marked this task as done:\n  "
                + curr.toString() + "\n" + horizontalLine);
            }
            else if (input.startsWith("unmark")) {
                String taskNo = input.replace("unmark", "").trim();
                int num = Integer.parseInt(taskNo);
                Task curr = (Task) list.get(num - 1);
                curr.markAsUndone();

                System.out.println(horizontalLine + "Nice! I've marked this task as not done yet:\n  "
                        + curr.toString() + "\n" + horizontalLine);
            }
            else {
                System.out.println("added: " + input + "\n");
                task = new Task(input);
                list.add(task);
            }
        } while (true);
        System.out.println(exitMsg);
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsUndone() {
            this.isDone = false;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + this.description;
        }
    }
}
