import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Victor {

    public static class Task {
        protected final String name;
        protected boolean done;

        public Task(String name) {
            this.name = name;
            this.done = false;
        }

        public String getName() {
            return this.name;
        }
        public void markDone() {
            this.done = true;
        }

        public void markUndone() {
            this.done = false;
        }

        @Override
        public String toString() {
            String out;
            if (this.done) {
                out = "{X} " + this.name;
            } else {
                out = "{ } " + this.name;
            }
            return out;
        }
    }

    public static void main(String[] args) {
        String logo = ",---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.\n"
+ "|   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\    \n"
+ "|  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |\n"
+ "|  | _ |  |  `-'`\"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /\n"
+ "|  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __\n"
+ "\\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |\n"
+ " \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `\"/  \\  ) / |  | \\ `'   /\n"
+ "  \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``\".'  |  |  \\    /\n"
+ "   `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'\n";

        Scanner inp = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<Task>();

        System.out.println(logo);
        System.out.println("Hello! My name is Victor!");
        System.out.println("What can I do for you?");
        System.out.println("============================================================");
        String userInput = inp.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("============================================================");
            if (userInput.trim().isEmpty()) {
                // User input is empty - ask again for input
                System.out.println("  ~  What can I do for you?");
            } else if (userInput.toLowerCase().contains("list")) {
                if (inputs.isEmpty()) {
                    System.out.println("  ~  Nothing has been input so far - nothing to list! What can I do for you?");
                } else {
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println("  ~  " + (i + 1) + ". " + inputs.get(i));
                    }
                }
            } else if (userInput.trim().toLowerCase().startsWith("mark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markDone();
                    System.out.println("  ~  You finished a task! Well done! I marked this task as done:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark as done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (userInput.trim().toLowerCase().startsWith("unmark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markUndone();
                    System.out.println("  ~  Oops, I guess you didn't finish the task! I marked this task as undone:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark as not done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else {
                inputs.add(new Task(userInput));
                System.out.println("  ~  added: " + userInput);
            }
            System.out.println("============================================================");
            userInput = inp.nextLine();
        }
        System.out.println("============================================================");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("============================================================");
    }
}
