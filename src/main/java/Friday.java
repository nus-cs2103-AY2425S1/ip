import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Scanner;

public class Friday {
    enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE;

        public static CommandType commandController(String inputCommand) throws InvalidFridayCommand {
            switch (inputCommand.toLowerCase()) {
                case "todo": return TODO;
                case "deadline": return DEADLINE;
                case "event": return EVENT;
                case "mark": return MARK;
                case "unmark": return UNMARK;
                case "delete": return DELETE;
                case "list": return LIST;
                case "bye": return BYE;
                default: throw new InvalidFridayCommand(inputCommand);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        boolean endScanner = false;
        List<Task> toDoList = new ArrayList<>();
        System.out.println("___________________________________________________________");
        System.out.println(" Hello! I'm Friday");
        System.out.println(" What can I do for you?");
        System.out.println("___________________________________________________________");

        while (!endScanner) {
            try {
                String toDo = scannerObj.nextLine();
                String command = toDo.split(" ")[0];
                CommandType commandType = CommandType.commandController(command);

                switch (commandType) {
                    case BYE:
                        endScanner = true;
                        break;

                    case LIST:
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < toDoList.size(); i++) {
                            System.out.println("     " + (i + 1) + "." + toDoList.get(i));
                        }
                        System.out.println("    _______________________________________________________");
                        break;

                    case TODO:
                        if (toDo.split(" ").length == 1) {
                            throw new InvalidTodoArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        Todo newTodo = new Todo(toDo.substring(5));
                        toDoList.add(newTodo);
                        System.out.println("       " + newTodo);
                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                        System.out.println("    _______________________________________________________");
                        break;

                    case DEADLINE:
                        if (toDo.split(" ").length <= 3 || !toDo.contains("/by")) {
                            throw new InvalidDeadlineArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        Deadline newDeadline = new Deadline(toDo.substring(9));
                        toDoList.add(newDeadline);
                        System.out.println("       " + newDeadline);
                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                        System.out.println("    _______________________________________________________");
                        break;

                    case EVENT:
                        if (toDo.split(" ").length <= 5 || !toDo.contains("/from") || !toDo.contains("/to")) {
                            throw new InvalidEventArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        Event newEvent = new Event(toDo.substring(6));
                        toDoList.add(newEvent);
                        System.out.println("       " + newEvent);
                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                        System.out.println("    _______________________________________________________");
                        break;

                    case MARK:
                        if (toDo.split(" ").length == 1 || Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
                            throw new InvalidMarkArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        int markTaskNumber = Integer.parseInt(toDo.split(" ")[1]);
                        toDoList.get(markTaskNumber - 1).markAsCompleted();
                        System.out.println("       " + toDoList.get(markTaskNumber - 1));
                        System.out.println("    _______________________________________________________");
                        break;

                    case UNMARK:
                        if (toDo.split(" ").length == 1 || Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
                            throw new InvalidUnmarkArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        int unmarkTaskNumber = Integer.parseInt(toDo.split(" ")[1]);
                        toDoList.get(unmarkTaskNumber - 1).markAsUncompleted();
                        System.out.println("       " + toDoList.get(unmarkTaskNumber - 1));
                        System.out.println("    _______________________________________________________");
                        break;

                    case DELETE:
                        if (toDo.split(" ").length == 1 || Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
                            throw new InvalidDeleteArgument();
                        }
                        System.out.println("    _______________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        int deleteTaskNumber = Integer.parseInt(toDo.split(" ")[1]);
                        System.out.println("       " + toDoList.get(deleteTaskNumber - 1));
                        toDoList.remove(deleteTaskNumber - 1);
                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
                        System.out.println("    _______________________________________________________");
                        break;

                    default:
                        throw new InvalidFridayCommand(toDo);
                }
            } catch (FridayException e) {
                System.out.println("    _______________________________________________________");
                System.out.println("     " + e.getMessage());
                System.out.println("    _______________________________________________________");
            }
        }

        System.out.println("___________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________");
        scannerObj.close();
    }

//        public static void main(String[] args) {
//            Scanner scannerObj = new Scanner(System.in);
//            boolean endScanner = false;
//            List<Task> toDoList = new ArrayList<>();
//            System.out.println("___________________________________________________________");
//            System.out.println(" Hello! I'm Friday");
//            System.out.println(" What can I do for you?");
//            System.out.println("___________________________________________________________");
//            while (!endScanner) {
//                try {
//                    String toDo = scannerObj.nextLine();
//                    if (Objects.equals(toDo, "bye")) {
//                        endScanner = true;
//                    } else if(Objects.equals(toDo, "list")) {
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Here are the tasks in your list:");
//                        for (int i = 0; i < toDoList.size(); i++) {
//                            System.out.println("     " + (i+1) + "." + toDoList.get(i));
//                        }
//                        System.out.println("    _______________________________________________________");
//                    } else if(Objects.equals(toDo.split(" ")[0], "todo")) {
//                        if (toDo.split(" ").length == 1) {
//                            throw new InvalidTodoArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Got it. I've added this task:");
//                        Todo newTodo = new Todo(toDo.substring(5));
//                        toDoList.add(newTodo);
//                        System.out.println("       " + newTodo);
//                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
//                        System.out.println("    _______________________________________________________");
//                    } else if(Objects.equals(toDo.split(" ")[0], "deadline")) {
//                        if (toDo.split(" ").length <= 3 || !toDo.contains("/by")) {
//                            throw new InvalidDeadlineArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Got it. I've added this task:");
//                        Deadline newDeadline = new Deadline(toDo.substring(9));
//                        toDoList.add(newDeadline);
//                        System.out.println("       " + newDeadline);
//                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
//                        System.out.println("    _______________________________________________________");
//                    } else if(Objects.equals(toDo.split(" ")[0], "event")) {
//                        if (toDo.split(" ").length <= 5 ||
//                                !toDo.contains("/from") || !toDo.contains("/to")) {
//                            throw new InvalidEventArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Got it. I've added this task:");
//                        Event newEvent = new Event(toDo.substring(6));
//                        toDoList.add(newEvent);
//                        System.out.println("       " + newEvent);
//                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
//                        System.out.println("    _______________________________________________________");
//                    } else if(Objects.equals(toDo.split(" ")[0], "mark")) {
//                        if (toDo.split(" ").length == 1 ||
//                                Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
//                            throw new InvalidMarkArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Nice! I've marked this task as done:");
//                        int taskNumber = Integer.parseInt(toDo.split(" ")[1]);
//                        toDoList.get(taskNumber-1).markAsCompleted();
//                        System.out.println("       " + toDoList.get(taskNumber-1));
//                        System.out.println("    _______________________________________________________");
//
//                    } else if (Objects.equals(toDo.split(" ")[0], "unmark")) {
//                        if (toDo.split(" ").length == 1 ||
//                                Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
//                            throw new InvalidUnmarkArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     OK, I've marked this task as not done yet:");
//                        int taskNumber = Integer.parseInt(toDo.split(" ")[1]);
//                        toDoList.get(taskNumber-1).markAsUncompleted();
//                        System.out.println("      " + toDoList.get(taskNumber-1));
//                        System.out.println("    _______________________________________________________");
//                    } else if (Objects.equals(toDo.split(" ")[0], "delete")) {
//                        if (toDo.split(" ").length == 1 ||
//                                Integer.parseInt(toDo.split(" ")[1]) > toDoList.size()) {
//                            throw new InvalidDeleteArgument();
//                        }
//                        System.out.println("    _______________________________________________________");
//                        System.out.println("     Noted. I've removed this task:");
//                        int taskNumber = Integer.parseInt(toDo.split(" ")[1]);
//                        System.out.println("       " + toDoList.get(taskNumber-1));
//                        toDoList.remove(taskNumber-1);
//                        System.out.println("     Now you have " + toDoList.size() + " tasks in the list.");
//                        System.out.println("    _______________________________________________________");
//                    } else {
////                        Task newTask = new Task(toDo);
////                        String returnString = "added: " + toDo;
////                        toDoList.add(newTask);
////                        System.out.println("    _______________________________________________________");
////                        System.out.println("     " + returnString);
////                        System.out.println("    _______________________________________________________");
//                        throw new InvalidFridayCommand(toDo);
//                    }
//                } catch (FridayException e) {
//                    System.out.println("    _______________________________________________________");
//                    System.out.println("     " + e.getMessage());
//                    System.out.println("    _______________________________________________________");
//                }
//            }
//            System.out.println("___________________________________________________________");
//            System.out.println(" Bye. Hope to see you again soon!");
//            System.out.println("___________________________________________________________");
//            scannerObj.close();
//        }


}
