public class Denim {
    public static void main(String[] args) {

        String horizontalLine = "____________________________________________________________";
        String chatBotName = "Denim";
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n"
                        + "Bye. Hope to see you again soon! %n%s",
                horizontalLine, chatBotName, horizontalLine, horizontalLine);

        System.out.println(greetingMessage);
    }
}


