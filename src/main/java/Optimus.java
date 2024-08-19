public class Optimus {
    String linebreak = "____________________________";
    private void greet() {
        System.out.println("Hello! I'm Optimus\nWhat can I do for you?");
        System.out.println(linebreak);
    }

    private void leave() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(linebreak);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Optimus optimus = new Optimus();
        optimus.greet();
        optimus.leave();
    }
}
