import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Genji {
    private static String NAME = "Genji";
    private static String LINE = "________________________________________________________________";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> list = new ArrayList();
    private static String SAVELIST_PATH = "./data/Genji.txt";

    private static void saveList(){
        try {
            FileWriter fw = new FileWriter(SAVELIST_PATH);
            for (Task t : list) {
                fw.write(t.toListString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadList() {
            try {
                File f = new File(SAVELIST_PATH);
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String input = s.nextLine();
                    if (input.startsWith("T")) {
                        list.add(new ToDo(input.substring(8)));
                        if (input.substring(4).startsWith("1")) {
                            list.get(list.size()- 1).mark();
                        }
                    } else if (input.startsWith("D")) {
                        String name = input.substring(8, input.lastIndexOf(" |"));
                        LocalDateTime from = LocalDateTime.parse(input.substring(input.lastIndexOf(" |") + 3),
                                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                        list.add(new Deadline(name, from));
                        if (input.substring(4).startsWith("1")) {
                            list.get(list.size() - 1).mark();
                        }
                    } else {
                        String name = input.substring(8, input.lastIndexOf(" |"));
                        LocalDateTime from = LocalDateTime.parse(input.substring(input.lastIndexOf(" |") + 3,
                                input.lastIndexOf(" to")), DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                        LocalDateTime to = LocalDateTime.parse(input.substring(input.lastIndexOf(" to") + 4),
                                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                        list.add(new Event(name, from, to));
                        if (input.substring(4).startsWith("1")) {
                            list.get(list.size()- 1).mark();
                        }
                    }
                    System.out.println(input);
                }
            } catch (FileNotFoundException e) {
                File directory = new File("./data");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File f = new File(SAVELIST_PATH);
                System.out.println("No task in list");
            }
    }


    public static void addList(Task t) {
        list.add(t);
    }

    public static void delete(int i) {
        list.remove(i);
    }

    public static void showList() {
        int index = 1;
        for(Task task : list) {
            System.out.println(String.format("%d. ", index)+ task);
            index++;
        }
    }

    public static void checkDate(String date) {
        for (Task t : list) {
            if (t instanceof Deadline) {
                Deadline temp  = (Deadline) t;
                if (temp.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date)) {
                    System.out.println(t);
                }
            } else if (t instanceof Event) {
                Event temp = (Event) t;
                if (temp.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(date)) {
                    System.out.println(t);
                }
            }
        }
    }

    public static void echo(String echo) {
        System.out.println(echo);
    }

    public static void run() {
        System.out.println(LINE);
        System.out.println("Hello! I'm " + NAME + "\nWhat can I do for you?  O.o?");
        loadList();
        System.out.println(LINE);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (input.length() > 4) {
                    System.out.println("Please don't add things after \"list\"");
                } else {
                    showList();
                }
            } else if (input.startsWith("mark")) {
                if (input.length() < 6) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        list.get(index).mark();
                        saveList();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index));
                    } catch (NumberFormatException n) {
                        System.out.println("Please input a integer after \"mark\"");
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Please input a integer smaller than the number of tasks");
                    }
                }
            } else if (input.startsWith("unmark")) {
                if (input.length() < 8) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        list.get(index).unmark();
                        saveList();
                        System.out.println("OK! I've marked this task as not done yet:");
                        System.out.println(list.get(index));
                    } catch (NumberFormatException n) {
                        System.out.println("Please input a integer after \"unmark\"");
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Please input a integer smaller than the number of tasks");
                    }
                }
            } else if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    ToDo td = new ToDo(input.substring(5));
                    addList(td);
                    saveList();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(td);
                    System.out.println("Now you have " + list.size() + " tasks in the list");
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() < 10) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        String name = input.substring(9, input.lastIndexOf(" /"));
                        LocalDateTime time = LocalDateTime.parse(input.substring(input.lastIndexOf(" /") + 5));
                        Deadline ddl = new Deadline(name, time);
                        addList(ddl);
                        saveList();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(ddl);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (StringIndexOutOfBoundsException s) {
                        System.out.println("Due date not provided or not in the proper form");
                    } catch (DateTimeParseException d) {
                        System.out.println("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
                    }
                }
            } else if (input.startsWith("event")) {
                if (input.length() < 7) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        String name = input.substring(6, input.lastIndexOf(" /from"));
                        LocalDateTime from = LocalDateTime.parse(input.substring(input.lastIndexOf(" /from") + 7,
                                input.lastIndexOf(" /to")));
                        LocalDateTime to = LocalDateTime.parse(input.substring(input.lastIndexOf(" /to") + 5));
                        Event evt = new Event(name, from, to);
                        addList(evt);
                        saveList();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(evt);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (StringIndexOutOfBoundsException s) {
                        System.out.println("Time period not provided or not in the proper form");
                    } catch (DateTimeParseException d) {
                        System.out.println("Please provide date time in this format\"yyyy-mm-ddThh:mm\"");
                    }
                }
            } else if (input.startsWith("delete")) {
                if (input.length() < 8) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        int index = Integer.parseInt(input.substring(7)) - 1;
                        Task temp = list.get(index);
                        Genji.delete(index);
                        saveList();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(temp);
                        System.out.println("Now you have " + list.size() + " tasks in the list");
                    } catch (NumberFormatException n) {
                        System.out.println("Please input a integer after \"delete\"");
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Please input a integer smaller than the number of tasks");
                    }
                }
            } else if (input.startsWith("date")) {
                if (input.length() < 6) {
                    System.out.println("No descriptions detected, try again");
                } else {
                    try {
                        checkDate(input.substring(5));
                    } catch (DateTimeParseException d) {
                        System.out.println("Please provide date time in this format\"yyyy-mm-dd\"");
                    }
                }
            } else {
                System.out.println("Invalid command, try to start with \"todo\" \"deadline\"" +
                        " \"event\", type \"list\" \"date\", or type \"bye\" to end");
            }
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
