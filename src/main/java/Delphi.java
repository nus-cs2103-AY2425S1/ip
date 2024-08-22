import java.util.Scanner;

public class Delphi {
    public static void main(String[] args) {
        TaskList t = new TaskList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Delphi, the greatest oracle in all of the classical world.");
        System.out.println("To quote a very famous person who will arrive a bit later, ask and ye shall find");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (Parser.checkStringPrefix(input, 4, "mark")) {
                t.markTaskAsDone(Integer.parseInt(String.valueOf(input.charAt(5))));
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + t.getTask(Integer.parseInt(String.valueOf(input.charAt(5)))));
            } else if (Parser.checkStringPrefix(input, 6, "unmark")) {
                t.markTaskAsUndone(Integer.parseInt(String.valueOf(input.charAt(7))));
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + t.getTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
            } else if (Parser.checkStringPrefix(input, 6, "delete")) {
                System.out.println("    Noted. I've removed this task:");
                System.out.println("      " + t.removeTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
                System.out.println("    Now you have " + t.getSize() + " tasks in the list");
            }
            else {
                try {
                    t.addTask(input);
                } catch (DelphiException e){
                    System.out.println("    " + e.getMessage());
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
