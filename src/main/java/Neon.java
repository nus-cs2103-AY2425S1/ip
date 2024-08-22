import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";
    private static Task[] list = new Task[100];
    private static int lastListIndex = 1;
    private static void greetingLine() {
        System.out.println(DASH_BREAK);
        String greeting = "hello I'm " + NAME + "!\n"
                + "what can I help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closingLine() {
        System.out.println(DASH_BREAK);
        String closing = "byeee! nice to meet you :)\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    private static void mainChat(String answer) {
        System.out.println(DASH_BREAK);

        Scanner ansObj = new Scanner(answer);
        String first = ansObj.next();

        switch (first) {
            case "todo":
                String taskTodo = removeSpace(answer.replace("todo ", ""));
                Todo newTodo = new Todo(taskTodo, false, lastListIndex);
                list[lastListIndex] = newTodo;
                lastListIndex++;
                System.out.println("adding todo to list : " + taskTodo);
                break;
            case "deadline":
                String taskDeadline = answer.replace("deadline ", "");
                String[] partsDeadline = taskDeadline.split("\\s*/by\\s*");
                Deadline newDeadline = new Deadline(partsDeadline[0],
                        false, lastListIndex,
                        removeSpace(partsDeadline[1]));
                list[lastListIndex] = newDeadline;
                lastListIndex++;
                System.out.println("adding deadline to list : " + newDeadline.getName());
                break;
            case "event":
                String taskEvent = answer.replace("event ", "");
                String[] partsEvent = taskEvent.split("\\s*/from\\s*|\\s*/to\\s*");

                Event newEvent = new Event(partsEvent[0], false, lastListIndex,
                        partsEvent[1], removeSpace(partsEvent[2]));
                list[lastListIndex] = newEvent;
                lastListIndex++;
                System.out.println("adding event to list : " + newEvent.getName());
                break;
            default:
                System.out.println("cannot read : " + first);
                break;
        }

        System.out.println(DASH_BREAK);
    }

    private static String removeSpace(String line) {
        return line.substring(0, line.length() - 1);
    }
    private static void printList() {
        System.out.println(DASH_BREAK);

        String message = "";
        if (lastListIndex == 1) {
            message = "nothing in the list!";
        } else {
            message = "printing list:";
        }

        System.out.println(message);

        for(int i = 1; i < lastListIndex; i++) {
            System.out.println(list[i].toString());
        }
        System.out.println(DASH_BREAK);
    }

    private static void markItem(int taskNumber) {
        Task currTask = list[taskNumber];
        currTask.check();

        System.out.println(DASH_BREAK);
        System.out.println("checking task number : " + taskNumber);
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

        Scanner ansObj = new Scanner(System.in);
        String allInput = ansObj.useDelimiter("\\Z").next(); // Reads until end of input (CTRL+D or an equivalent)
        String[] lines = allInput.split("\\r?\\n");
        for (String line : lines) {
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
