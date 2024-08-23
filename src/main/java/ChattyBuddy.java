import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class ChattyBuddy {
    public static void main(String[] args) {
        ArrayList<Task> inputList = new ArrayList<>();
        String breakLine = "——————————————————————————";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(breakLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(breakLine);
        Scanner userInput = new Scanner(System.in);

        // user inputs something
        String response = userInput.nextLine();
        String[] slicedStr = response.split(" ");

        // checking against all possible outcomes
        while (!response.equals("bye")) {

            // when user enters "list"
            if (response.equals("list")) {
                System.out.println(breakLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.printf("%d.[%s][%s] %s%n", i + 1,inputList.get(i).type,inputList.get(i).getStatusIcon(), inputList.get(i).description);
                }
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            // when user enters "mark xxx"
            } else if (slicedStr.length == 2 && slicedStr[0].equals("mark")) {
                if (Integer.parseInt(slicedStr[1]) <= inputList.size() && Integer.parseInt(slicedStr[1]) > 0) {
                    Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                    target.setMarkStatus(true);
                    System.out.println(breakLine);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target.description);
                    System.out.println(breakLine);
                } else {
                    System.out.println(breakLine);
                    System.out.println("Oh no! No such task is found!");
                    System.out.println(breakLine);
                }
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            // when user enters "unmark xxx"
            } else if (slicedStr.length == 2 && slicedStr[0].equals("unmark")) {
                if (Integer.parseInt(slicedStr[1]) <= inputList.size() && Integer.parseInt(slicedStr[1]) > 0) {
                    Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                    target.setMarkStatus(false);
                    System.out.println(breakLine);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target.description);
                    System.out.println(breakLine);
                } else {
                    System.out.println(breakLine);
                    System.out.println("Oh no! No such task is found!");
                    System.out.println(breakLine);
                }
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            // users enters "todo xxx"
            } else if (slicedStr[0].equals("todo")) {
                String taskDetail = response.substring(5);
                Task newTask = new Task(taskDetail);
                newTask.setTypeStatus("T");
                inputList.add(newTask);
                System.out.println(breakLine);
                System.out.println("Got it. I've added this task:");
                System.out.printf("  [%s][%s] %s%n", newTask.type, newTask.getStatusIcon(), newTask.description);
                System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            // users enters "deadline xxx"
            } else if (slicedStr[0].equals("deadline")) {
                String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
                String taskDetail = String.join(" ", task);
                String[] deadlineTask = taskDetail.split("/");
                String[] taskDate = deadlineTask[1].split(" ");
                String taskDescription = deadlineTask[0] + "(by: " + taskDate[1] + ")";
                Task newTask = new Task(taskDescription);
                newTask.setTypeStatus("D");
                inputList.add(newTask);
                System.out.println(breakLine);
                System.out.println("Got it. I've added this task:");
                System.out.printf("  [%s][%s] %s%n", newTask.type, newTask.getStatusIcon(), newTask.description);
                System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            // user enters "event xxx"
            } else if (slicedStr[0].equals("event")) {
                String[] task = Arrays.copyOfRange(slicedStr, 1, slicedStr.length);
                String taskDetail = String.join(" ", task);
                String[] eventTask = taskDetail.split("/");
                String startTime = eventTask[1].substring(5);
                String endTime = eventTask[2].substring(3);
                String taskDescription = eventTask[0] + "(from: " + startTime + "to: " + endTime + ")";
                Task newTask = new Task(taskDescription);
                newTask.setTypeStatus("E");
                inputList.add(newTask);
                System.out.println(breakLine);
                System.out.println("Got it. I've added this task:");
                System.out.printf("  [%s][%s] %s%n", newTask.type, newTask.getStatusIcon(), newTask.description);
                System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");

            } else {
                inputList.add(inputList.size(), new Task(response));
                System.out.println(breakLine);
                System.out.printf("added: %s%n", response);
                System.out.println(breakLine);
                response = userInput.nextLine();
                slicedStr = response.split(" ");
            }
        }
        System.out.println(breakLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(breakLine);
    }
}
