import java.util.Objects;
import java.util.Scanner;


public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";
    private static Task[] list = new Task[100];
    private static int lastListIndex = 0;
    private static void greetingLine() {
        System.out.println(DASH_BREAK);
        String greeting = "Hello I'm " + NAME + "!\n"
                + "What can I help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closingLine() {
        System.out.println(DASH_BREAK);
        String closing = "Byeee! Nice to meet you :)\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    private static void mainChat(String answer) {
        System.out.println(DASH_BREAK);
        System.out.println("adding to list : " + answer);
        System.out.println(DASH_BREAK);

        Task newTask = new Task(answer, false, lastListIndex);
        list[lastListIndex] = newTask;
        lastListIndex++;
    }

    private static void printList() {
        System.out.println(DASH_BREAK);
        for(int i = 0; i < lastListIndex; i++) {
            System.out.println(list[i].toString());
        }
        System.out.println(DASH_BREAK);
    }

    private static void markItem(int taskNumber) {
        Task currTask = list[taskNumber];
        currTask.check();

        System.out.println(DASH_BREAK);
        System.out.println("checking task number :" + taskNumber);
        System.out.println(DASH_BREAK);
    }

    private static void unmarkItem(int taskNumber) {
        Task currTask = list[taskNumber];
        currTask.uncheck();

        System.out.println(DASH_BREAK);
        System.out.println("checking task number : " + taskNumber);
        System.out.println(DASH_BREAK);
    }

    public static void main(String[] args) {
        boolean chatting = true;
        greetingLine();
        StringBuilder answer = new StringBuilder();
        int number = -1;

        while (chatting) {
            Scanner ansObj = new Scanner(System.in);
            String line = ansObj.nextLine();

            Scanner lineSc = new Scanner(line);
            while (lineSc.hasNext()) {
                if (lineSc.hasNextInt()) {
                    number = lineSc.nextInt();
                    break;
                } else {
                    answer.append(lineSc.next()).append(" ");
                }
            }

            switch (answer.toString().substring(0, answer.toString().length() - 1)) {
                case "bye":
                    chatting = false;
                    break;
                case "list":
                    printList();
                    break;
                case "mark":
                    markItem(number);
                    break;
                case "unmark":
                    unmarkItem(number);
                    break;
                default:
                    mainChat(String.valueOf(answer));
                    break;
            }
            answer = new StringBuilder();
        }
        closingLine();
    }
}
