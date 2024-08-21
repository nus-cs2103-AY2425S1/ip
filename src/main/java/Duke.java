public class Duke {
    private static String NAME = "Genji";
    public static String bye() {
        return "Bye. Hope to see you again soon!";
    }
    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?");
        System.out.println(Duke.bye());
    }
}
