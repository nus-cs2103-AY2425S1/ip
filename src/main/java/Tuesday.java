public class Tuesday {
    public static String Msg_welcome() {
        return "_______________________________\n"
                + "Hello! I'm Tuesday, a randomly created bot.\n"
                + "What can I do for you?\n"
                + "_______________________________";
    }
    public static String Msg_bye() {
        return "Bye bye. Hope to see you again soon!\n"
                + "_______________________________";
    }
    public static String Msg_list() {
        return "list\n"
                + "_______________________________";
    }
    public static String Msg_blah() {
        return "blah\n"
                + "_______________________________";
    }

    public static void main(String[] args) {
        System.out.println(Msg_welcome());
        System.out.println(Msg_list());
        System.out.println(Msg_blah());
        System.out.println(Msg_bye());
    }
}
