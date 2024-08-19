public class Cook {
    public static void main(String[] args) {
        String logo = "_________                __    \n"
                + "\\_   ___ \\  ____   ____ |  | __\n"
                + "/    \\  \\/ /  _ \\ /  _ \\|  |/ /\n"
                + "\\     \\___(  <_> |  <_> )    < \n"
                + " \\______  /\\____/ \\____/|__|_ \\\n"
                + "        \\/                   \\/\n";
        System.out.println(logo);
        start();
        exit();
    }

    public static void start() {
        System.out.println("""
                Hello, I'm Cook!
                What can I do for you?
                """);
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
