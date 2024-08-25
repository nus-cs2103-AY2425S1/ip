import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Lutchat {
    ArrayList<Task> taskList = new ArrayList<>();
    String userInput;

    //We are now in branch-Level-7

    void greet() {
        System.out.print("______________________________________________\n");
        System.out.print("Hello! I'm Lutchat!\n");
        System.out.print("What can I do for you?\n");
        System.out.print("______________________________________________\n");
    }

    void exit() {
        System.out.print("Bye! Hope to see you again soon!\n");
        System.out.print("______________________________________________\n");
    }

    boolean invalidInputResponse(String message) {
        System.out.print("OOPS!!! " + message +"\n");
        System.out.print("______________________________________________\n");
        return true;
    }

    boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.valueOf(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    boolean conversation(String userInput) {
        String[] userInputArr = userInput.split(" ");
        String keyword = userInputArr[0];


        switch (keyword) {
            case "bye":
                return false;
            case "list":
                System.out.print("Here are the task(s) in your list:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print( (i+1) + "." + taskList.get(i).toString() + "\n");
                }
                System.out.print("______________________________________________\n");
                return true;
            case "mark":
                if (userInputArr.length < 2) {
                    return invalidInputResponse("Please indicate which task you would like to mark.");
                } else if (userInputArr.length > 2 || !isInteger(userInputArr[1])) {
                    return invalidInputResponse("Please indicate an integer for which task you would like to mark.");
                } else if (Integer.valueOf(userInputArr[1]) - 1 < 0
                        || Integer.valueOf(userInputArr[1]) - 1 > taskList.size()) {
                    return invalidInputResponse("Task does not exist, please try again!");
                }
                int itemNum1 = Integer.valueOf(userInputArr[1]) - 1;
                taskList.get(itemNum1).markAsDone();
                System.out.print("Nice! I've marked this task as done:\n");
                System.out.print(taskList.get(itemNum1).toString() + "\n");
                System.out.print("______________________________________________\n");
                return true;
            case "unmark":
                if (userInputArr.length < 2) {
                    return invalidInputResponse("Please indicate which task you would like to unmark.");
                } else if (userInputArr.length > 2 || !isInteger(userInputArr[1])) {
                    return invalidInputResponse("Please indicate an integer for which task you would like to unmark.");
                } else if (Integer.valueOf(userInputArr[1]) - 1 < 0
                        || Integer.valueOf(userInputArr[1]) - 1 > taskList.size()) {
                    return invalidInputResponse("Task does not exist, please try again!");
                }
                int itemNum2 = Integer.valueOf(userInputArr[1]) - 1;
                taskList.get(itemNum2).markAsUndone();
                System.out.print("OK, I've marked this task as not done yet:\n");
                System.out.print(taskList.get(itemNum2).toString() + "\n");
                System.out.print("______________________________________________\n");
                return true;
            case "delete":
                if (userInputArr.length < 2) {
                    return invalidInputResponse("Please indicate which task you would like to delete.");
                } else if (userInputArr.length > 2 || !isInteger(userInputArr[1])) {
                    return invalidInputResponse("Please indicate an integer for which task you would like to delete.");
                } else if (Integer.valueOf(userInputArr[1]) - 1 < 0
                        || Integer.valueOf(userInputArr[1]) - 1 > taskList.size()) {
                    return invalidInputResponse("Task does not exist, please try again!");
                }
                int itemNum3 = Integer.valueOf(userInputArr[1]) - 1;
                System.out.print("Noted. I've removed this task:\n");
                System.out.print(taskList.get(itemNum3).toString() + "\n");
                taskList.remove(itemNum3);
                System.out.print("Now you have " + taskList.size() + " task(s) in the list.\n");
                System.out.print("______________________________________________\n");
                return true;
            case "todo":
                String desc = "";
                for (int i = 1; i < userInputArr.length; i++) {
                    desc += userInputArr[i] + " ";
                }

                desc = desc.substring(0, desc.equals("") ? 0 : desc.length()-1);

                if (desc.length() == 0) {
                    return invalidInputResponse("Todo 'description' is missing...");
                }

                Todo todo = new Todo(desc);
                taskList.add(todo);

                System.out.print("Got it. I've added this task:\n");
                System.out.print(todo + "\n");
                System.out.print("Now you have " + taskList.size() + " task(s) in the list.\n");
                System.out.print("______________________________________________\n");
                return true;
            case "deadline":
                String desc1 = "";
                String by = "";
                for (int i = 1; i < userInputArr.length; i++) {
                    if (userInputArr[i].equals("/by")) {
                        for (int j = i + 1; j < userInputArr.length; j++) {
                            by += userInputArr[j] + " ";
                        }
                        by = by.substring(0, by.equals("") ? 0 : by.length()-1);
                        break;
                    }
                    desc1 += userInputArr[i] + " ";
                }
                desc1 = desc1.substring(0, desc1.equals("") ? 0 : desc1.length()-1);

                if (desc1.length() == 0 || by.length() == 0) {
                    return invalidInputResponse("Deadline 'description' or 'by' input(s) is/are missing...");
                }

                Deadline deadline = new Deadline(desc1, by);
                taskList.add(deadline);

                System.out.print("Got it. I've added this task:\n");
                System.out.print(deadline + "\n");
                System.out.print("Now you have " + taskList.size() + " task(s) in the list.\n");
                System.out.print("______________________________________________\n");
                return true;
            case "event":
                String desc2 = "";
                String from = "";
                String to = "";
                for (int i = 1; i < userInputArr.length; i++) {
                    if (userInputArr[i].equals("/from")) {
                        for (int j = i + 1; j < userInputArr.length; j++) {
                            if (userInputArr[j].equals("/to")) {
                                for (int k = j + 1; k < userInputArr.length; k++) {
                                    to += userInputArr[k] + " ";
                                }
                                to = to.substring(0, to.equals("") ? 0 : to.length()-1);
                                break;
                            }
                            from += userInputArr[j] + " ";
                        }
                        from = from.substring(0, from.equals("") ? 0 : from.length()-1);
                        break;
                    }
                    desc2 += userInputArr[i] + " ";
                }
                desc2 = desc2.substring(0, desc2.equals("") ? 0 : desc2.length()-1);

                if (desc2.length() == 0 || from.length() == 0 || to.length() == 0) {
                    return invalidInputResponse("Event 'description', 'from' or 'to' input(s) is/are missing...");
                }

                Event event = new Event(desc2, from, to);
                taskList.add(event);

                System.out.print("Got it. I've added this task:\n");
                System.out.print(event + "\n");
                System.out.print("Now you have " + taskList.size() + " task(s) in the list.\n");
                System.out.print("______________________________________________\n");
                return true;
            default:
                return invalidInputResponse("I don't know what that means... :(");
        }
    }

    public static void main(String[] args) {
        Lutchat lutchat = new Lutchat();
        Scanner sc = new Scanner(System.in);

        lutchat.greet();

        lutchat.userInput = sc.nextLine();

        while (lutchat.conversation(lutchat.userInput)) {
            lutchat.userInput = sc.nextLine();
        }

        lutchat.exit();
    }
}
