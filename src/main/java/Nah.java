public class Nah {
    static String greetLine = "____________________________________________________________ \n"
            + " Hello! I'm NAH \n"
            + " What can I do for you? \n"
            + "____________________________________________________________";
    static String byeLine = " Bye. Hope to see you again soon!";
    void greet() {
        System.out.println(greetLine);
    }

    void exit() {
        System.out.println(byeLine);
    }
    public static void main(String[] args) {
        String logo = "| \\   | |      /\\      | |  | | \n"
                + "| |\\  | |     / /\\     | |==| | \n"
                + "| | \\ | |    / /__\\    | |  | | \n"
                + "| |  \\  |   / /    \\   | |  | | \n";


        System.out.println("Hello from\n" + logo);

        Nah nah = new Nah();

        nah.greet();

        nah.exit();
        System.out.println("____________________________________________________________ \n");
    }
}
