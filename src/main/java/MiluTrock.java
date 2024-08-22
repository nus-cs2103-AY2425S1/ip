public class MiluTrock {
    public static void main(String[] args) {
        String name = "MiluTrock";
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        
        // Echo user input and exit if "bye" is entered
        while (true) {
            String input = System.console().readLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
