public class Spongebob {
    public static void main(String[] args) {
        final String LINE = "____________________________________________________________\n";
        final String logo = "▕╮╭┻┻╮╭┻┻╮╭▕╮╲\n" +
                            "▕╯┃╭╮┃┃╭╮┃╰▕╯╭▏\n" +
                            "▕╭┻┻┻┛┗┻┻┛ ╰▏\n" +
                            "▕╰━━━┓┈┈┈╭╮▕╭╮▏\n" +
                            "▕╭╮╰┳┳┳┳╯╰╯▕╰╯▏\n" +
                            "▕╰╯┈┗┛┗┛┈╭╮▕╮┈▏";

        String greetings = "Hello! I'm Spongebob! \nWhat can I do for you?\n";
        String goodbye =  "Bye. Hope to see you again soon!\n";



        System.out.println(LINE + logo + "\n" + greetings + LINE + goodbye);
    }
}
