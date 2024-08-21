public class Chatterbox {
    final static String botName = "Chatterbox";
    public static String greeting() {
        return String.format("""
 ____________________________________________________________
 Hello! I'm %s
 What can I do for you?
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
""", Chatterbox.botName);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(greeting());
    }
}
