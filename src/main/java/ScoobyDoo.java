public class ScoobyDoo {
    public static final String name = "Scooby-Doo";
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", name);
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 60; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }
}

