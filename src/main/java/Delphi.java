import java.util.Scanner;

public class Delphi {

    public static boolean checkStringPrefix(String original, int index, String comparison) {
        // Ensure the index is within the bounds of the original string
        if (index > original.length()) {
            index = original.length();
        }

        // Get the substring from the original string up to the specified index
        String prefix = original.substring(0, index);

        // Compare the prefix with the comparison string
        return prefix.equals(comparison);
    }
    public static void main(String[] args) {
        TaskList t = new TaskList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Delphi, the greatest oracle in all of the classical world.");
        System.out.println("To quote a very famous person who will arrive a bit later, ask and ye shall find");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) break;
            if (checkStringPrefix(input, 4, "mark")) {
                t.markTaskAsDone(Integer.parseInt(String.valueOf(input.charAt(5))));
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      [X] " + t.getTask(Integer.parseInt(String.valueOf(input.charAt(5)))));
            } else if (checkStringPrefix(input, 6, "unmark")) {
                t.markTaskAsUndone(Integer.parseInt(String.valueOf(input.charAt(7))));
                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      [ ] " + t.getTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
            }
            else {
                t.addTask(input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
