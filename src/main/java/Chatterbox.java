import java.util.Scanner;

public class Chatterbox {
    public static void main(String[] args) {
        String welcomeMessage = """
                ____________________________________________________________
                 Hello! I'm Chatterbox
                 What can I do for you?
                ____________________________________________________________
                """;
        System.out.println(welcomeMessage);
        StoredList l1 = new StoredList();
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            try {
                String input = sc.nextLine();
                String[] command = processInput(input);
                switch (command[0]) {
                    case "0": //bye
                        String byeMessage = """
                                ____________________________________________________________
                                Bye. Hope to see you again soon!
                                ____________________________________________________________
                                """;
                        System.out.println(byeMessage);
                        done = true;
                        break;
                    case "1": //list
                        System.out.println("____________________________________________________________");
                        int len = l1.getSize();
                        for (int i = 1; i < len + 1; i++) {
                            System.out.println(i + ". " + l1.getItem(i - 1));
                        }
                        System.out.println("____________________________________________________________");
                        break;
                    case "2": //mark & unmark
                        int taskNum = Integer.parseInt(command[2]) - 1;
                        try {
                            l1.getItem(taskNum).setCompleted(command[1].equals("mark"));
                            break;
                        } catch (NullPointerException e) {
                            throw new ChatterBoxNullTaskError();
                        }
                    case "3": //todo
                        l1.addItem(new ToDos(command[1]));
                        break;
                    case "4": //deadline
                        l1.addItem(new Deadline(command[1], command[2]));
                        break;
                    case "5": //event
                        l1.addItem(new Event(command[1], command[2], command[3]));
                        break;
                    case "6": //delete
                        try {
                            l1.removeItem(Integer.parseInt(command[1]) - 1);
                            break;
                        } catch (IndexOutOfBoundsException e) {
                            throw new ChatterBoxNullTaskError();
                        }
                }
            }
            catch (ChatterBoxError e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static String[] processInput(String input) throws ChatterBoxError {
        try {
            if (input.equals("bye")) {
                return new String[] {"0", " "};
            } else {
                String[] command = input.split(" ", 2);
                switch (command[0]) {
                    case "list" -> {
                        return new String[]{"1", " "};
                    }
                    case "mark", "unmark" -> {
                        if (command.length == 2) {
                            try {
                                Integer.parseInt(command[1]);
                                return new String[]{"2", command[0], command[1]};
                            } catch (NumberFormatException e) {
                                throw new ChatterBoxMarkError();
                            }
                        } else {
                            throw new ChatterBoxMarkError();
                        }
                    }
                    case "todo" -> {
                        if (command.length == 2) {
                            return new String[]{"3", command[1]};
                        } else {
                            throw new ChatterBoxToDoError();
                        }
                    }
                    case "deadline" -> {
                        if (command.length == 2) {
                            try {
                                String[] details = command[1].split("/by ");
                                return new String[]{"4", details[0], details[1]};
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new ChatterBoxDeadlineError();
                            }
                        } else {
                            throw new ChatterBoxDeadlineError();
                        }
                    }
                    case "event" -> {
                        if (command.length == 2) {
                            try {
                                String[] details = command[1].split("/from ");
                                String[] times = details[1].split("/to ");
                                return new String[]{"5", details[0], times[0], times[1]};
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new ChatterBoxEventError();
                            }
                        } else {
                            throw new ChatterBoxEventError();
                        }
                    }
                    case "delete" -> {
                        if (command.length == 2) {
                            try {
                                Integer.parseInt(command[1]);
                                return new String[]{"6", command[1]};
                            } catch (NumberFormatException e) {
                                throw new ChatterBoxDeleteError();
                            }
                        } else {
                            throw new ChatterBoxDeleteError();
                        }
                    }
                    default -> throw new ChatterBoxError();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ChatterBoxError();
        }
    }
}