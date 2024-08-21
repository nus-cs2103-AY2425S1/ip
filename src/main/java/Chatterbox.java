import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;


public class Chatterbox {

    private static int extractNum(String input) {
        int length = input.length();
        StringBuilder numberBuild = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                numberBuild.insert(0, currentChar);
            } else {
                break;
            }
        }
        return Integer.parseInt(numberBuild.toString());
    }


    private static class Task {
        private Boolean status;
        private String desc;

        public Task(String desc) {
            this.status = false;
            this.desc = desc;
        }

        public String getDescription() {
            return this.desc;
        }
        public Boolean getStatus() {
            return this.status;
        }

        public void setStatus(Boolean stat) {
            this.status = stat;
        }
    }
    final static String botName = "Chatterbox";
    final static String lineSeperator = "____________________________________________________________";
    public static String greeting() {
        return String.format("""
 ____________________________________________________________
 Hello! I'm %s
 What can I do for you?
____________________________________________________________
""", Chatterbox.botName);
    }

    public static String goodBye() {
        return """
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
                """;
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(greeting());
        Task[] userList = new Task[100];
        int current  = 0;

        while (true) {
            String response = scanner.nextLine();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                System.out.println(Chatterbox.lineSeperator);
                System.out.println("Current Tasks in List: ");
                for (int i = 0; i < current; i++) {
                    System.out.println(String.format(i + 1 + ". " + "[%s] %s", userList[i].getStatus() ? "X" : " ", userList[i].getDescription()));
                }
                System.out.println(Chatterbox.lineSeperator);

            } else if (response.startsWith("mark")){
                response = response.trim();
                int index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                userList[index].setStatus(true);
                System.out.println(Chatterbox.lineSeperator);
                System.out.println("Marked task as done");
                System.out.println(String.format("[X] %s", userList[index].getDescription()));
                System.out.println(Chatterbox.lineSeperator);


            } else if (response.startsWith("unmark")) {
                response = response.trim();
                int index = Chatterbox.extractNum(response) - 1; // -1 as the display  start from 1
                userList[index].setStatus(false);
                System.out.println(Chatterbox.lineSeperator);
                System.out.println("Marked task as undone");
                System.out.println(String.format("[ ] %s", userList[index].getDescription()));
                System.out.println(Chatterbox.lineSeperator);
            }else {
                userList[current] = new Task(response);
                current++;
                System.out.println("added: " + response);
                System.out.println(Chatterbox.lineSeperator);
            }
        }
        System.out.println(goodBye());


        scanner.close();

    }
}
