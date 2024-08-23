import java.util.*;

public class Rainy {
    public static void main(String[] args) throws InvalidIndexException, InvalidMarkAndUnmarkException {
        System.out.println("Hello! I am RAINY - Responsive, Automated, Intelligence Network for You.");
        System.out.println("I am a digital assistant designed to help you keep track of your day.");
        System.out.println("So, what can I do for you today?");
        TaskTracker tm = new TaskTracker();
        Scanner sc = new Scanner(System.in);
        String messages = sc.nextLine();
        String[] input = messages.split(" ");
        String[] splitByTask = messages.split("/");
        String message = input[0];
        int count;
        if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
            try {
                count = Integer.parseInt(input[1]);
            } catch (Exception e) {
                count = -1;
            }
        } else {
            count = -1;
        }
        Instructions instruction;
        try {
            instruction = Instructions.valueOf(message.toUpperCase());
        } catch(Exception e) {
            instruction = Instructions.INVALID;
        }


        while(instruction != Instructions.BYE) {
            switch (instruction) {
                case LIST:
                    tm.getList();
                    break;
                case MARK:
                    if (count != -1) {
                        tm.markDone(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case UNMARK:
                    if (count != -1) {
                        tm.unmarkDone(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case DELETE:
                    if (count != -1) {
                        tm.delete(count - 1);
                    } else {
                        System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    }
                    break;
                case TODO:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your ToDo task, please try again!");
                    } else {
                        tm.updateListToDo(splitByTask[0].substring(5));
                    }
                    break;
                case DEADLINE:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your Deadline, please try again!");
                    } else if (splitByTask.length == 1) {
                        System.out.println("Please provide an end date for your Deadline!");
                    } else {
                        tm.updateListDeadline(splitByTask[0].substring(9), splitByTask[1]);
                    }
                    break;
                case EVENT:
                    if (input.length == 1) {
                        System.out.println("You need to provide a description of your Event, please try again!");
                    } else if (splitByTask.length < 3) {
                        System.out.println("Please provide a proper start time and end time for your Event!");
                    } else {
                        tm.updateListEvent(splitByTask[0].substring(6), splitByTask[1], splitByTask[2]);
                    }
                    break;
                case INVALID:
                    System.out.println("Please indicate the category of your task (ToDo, Deadline, or Event) before providing a description of it.");
                    break;
            }
            messages = sc.nextLine();
            input = messages.split(" ");
            splitByTask = messages.split("/");
            message = input[0];
            if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
                try {
                    count = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    count = -1;
                }
            } else {
                count = -1;
            }
            try {
                instruction = Instructions.valueOf(message.toUpperCase());
            } catch(Exception e) {
                instruction = Instructions.INVALID;
            }
        }
        System.out.println("Goodbye! Have a nice day ahead!!");
    }
}
