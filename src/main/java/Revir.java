public class Revir {
    public static void main(String[] args) {
        String name = "Revir";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        // Echo user input until user types "bye"
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }
            // Differentiate the print from the user input
            System.out.println("You said: ");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
