import java.util.Scanner;

class Task {
    private boolean mark;
    private String val;
    public Task(boolean mark, String val) {
        this.mark = mark;
        this.val = val;
    }

    public void markDone() {
        this.mark = true;
        return;
    }

    public void markUndone() {
        this.mark = false;
        return;
    }

    public String getStatusIcon() {
        return (this.mark ? "X" : " ");
    }

    public String value() {
        return this.val;
    }
}



public class Timo {
    public static void main(String[] args) {
        //greet
        System.out.println("----------------------------");
        System.out.println("Hello! I'm Timo\nWhat can I do for you?");
        System.out.println("----------------------------");

        //Scanner to receive input
        Scanner echo = new Scanner(System.in);

        //initialise array to store the values
        Task[] arr = new Task[100];
        int index = 1;


        String input = "";

        //boolean to know whether input = bye
        boolean is_bye = false;
        while (!is_bye) {
            input = echo.nextLine();
            //determine actions based on input
            switch (input) {
                case "bye":
                    System.out.println("----------------------------");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("----------------------------");
                    is_bye = true;
                    break;

                case "list":
                    System.out.println("----------------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < index; i++) {
                        Task chosen = arr[i - 1];
                        System.out.println(i + ". " + "[" + chosen.getStatusIcon() + "] " + chosen.value());
                    }
                    System.out.println("----------------------------");
                    break;

                default:
                    if (input.startsWith("mark")) {
                        //to mark
                        String num = String.valueOf(input.charAt(input.length() - 1));
                        int target = Integer.parseInt(num);
                        System.out.println(target);
                        Task chosen = arr[target - 1];
                        chosen.markDone();
                        System.out.println("----------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + "[" + chosen.getStatusIcon() + "] " + chosen.value());
                        System.out.println("----------------------------");
                    } else if (input.startsWith("unmark")) {
                        //to unmark
                        String num = String.valueOf(input.charAt(input.length() - 1));
                        int target = Integer.parseInt(num);
                        Task chosen = arr[target - 1];
                        chosen.markUndone();
                        System.out.println("----------------------------");
                        System.out.println("Nice! I've marked this task as not done yet:");
                        System.out.println("  " + "[" + chosen.getStatusIcon() + "] " + chosen.value());
                        System.out.println("----------------------------");
                    } else {
                        //to add to list
                        System.out.println("----------------------------");
                        System.out.println("added: " + input);
                        System.out.println("----------------------------");
                        arr[index - 1] = new Task(false, input);
                        index++;
                        break;
                    }
            }
        }



    }
}
