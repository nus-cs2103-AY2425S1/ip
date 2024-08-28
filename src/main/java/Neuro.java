import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

public class Neuro {
    private static Task getTask(String input) throws IllegalArgumentException {
        // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
        // Array copyOfRange inspired by https://www.geeksforgeeks.org/java-util-arrays-copyofrange-java/
        String[] inputComponents = input.split(" ");
        String taskType = inputComponents[0];
        Task task = null;

        if (taskType.equals("todo")) {
            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, inputComponents.length));

            if (description.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The description of a 'todo' cannot be empty! Try adding" +
                        " a description like 'todo read book'.");
            }

            task = new Todo(description);
        } else if (taskType.equals("deadline")) {
            int byIndex = -1;
            for (int i = 1; i < inputComponents.length; i++) {
                if (inputComponents[i].equals("/by")) {
                    byIndex = i;
                    break;
                }
            }

            // Missing /by
            if (byIndex < 0) {
                throw new IllegalArgumentException("UH OH! The command given is missing the '/by' input for deadline." +
                        "Try updating the command like 'deadline finish homework /by Mon 2359'.");
            }

            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, byIndex));
            String deadline = String.join(" ",
                    Arrays.copyOfRange(inputComponents, byIndex + 1, inputComponents.length));

            if (description.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The description of a 'deadline' cannot be empty! Try adding" +
                        " a description like 'deadline finish homework /by Mon 2359'.");
            } else if (deadline.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The deadline time/date of a 'deadline' cannot be empty! Try adding" +
                        " a valid deadline like 'deadline finish homework /by Mon 2359'.");
            }

            task = new Deadline(description, deadline);
        } else if (taskType.equals("event")) {
            int fromIndex = -1;
            int toIndex = -1;

            for (int i = 1; i < inputComponents.length; i++) {
                if (inputComponents[i].equals("/from")) {
                    fromIndex = i;
                } else if (inputComponents[i].equals("/to")) {
                    toIndex = i;
                    break;
                }
            }

            // Missing /from
            if (fromIndex < 0) {
                throw new IllegalArgumentException("UH OH! The command given is missing the '/from' input for event." +
                        "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
            }

            // Missing /to
            if (toIndex < 0) {
                throw new IllegalArgumentException("UH OH! The command given is missing the '/to' input for event." +
                        "Try updating the command like 'event project meeting /from Mon 2pm /to 5pm'.");
            }

            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, fromIndex));
            String from = String.join(" ",
                    Arrays.copyOfRange(inputComponents, fromIndex + 1, toIndex));
            String to = String.join(" ",
                    Arrays.copyOfRange(inputComponents, toIndex + 1, inputComponents.length));

            if (description.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The description of an 'event' cannot be empty! Try adding" +
                        " a description like 'event project meeting /from Mon 2pm /to 5pm'.");
            } else if (from.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The from time/date of an 'event' cannot be empty! Try adding" +
                        " a valid from time/date like 'event project meeting /from Mon 2pm /to 5pm'.");
            } else if (to.isEmpty()) {
                throw new IllegalArgumentException("UH OH! The to time/date of an 'event' cannot be empty! Try adding" +
                        " a valid to time/date like 'event project meeting /from Mon 2pm /to 5pm'.");
            }

            task = new Event(description, from, to);
        }

        if (task == null) {
            throw new IllegalArgumentException("UH OH! I'm sorry but I don't recognise that command. Try the following:" +
                    " 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'delete', 'bye'.");
        }
        return task;
    }

    private static Task getTaskToAdd(String taskType, String[] taskComponents, String taskIsDone) {
        Task taskToAdd = null;

        switch (taskType) {
            case ("T"):
                taskToAdd = new Todo(taskComponents[2]);
                break;
            case ("D"):
                taskToAdd = new Deadline(taskComponents[2], taskComponents[3]);
                break;
            case ("E"):
                taskToAdd = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                break;
            default:

                break;
        }

        if (taskIsDone.equals("1") && taskToAdd != null) {
            taskToAdd.markDone();
        }
        return taskToAdd;
    }

    private static ArrayList<Task> loadOrCreateTaskFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }

        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskComponents = nextLine.split(" \\| ");
            String taskType = taskComponents[0];
            String taskIsDone = taskComponents[1];
            Task taskToAdd = getTaskToAdd(taskType, taskComponents, taskIsDone);

            taskList.add(taskToAdd);
        }

        return taskList;
    }

    private static void updateTaskFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        for (Task task : taskList) {
            fileWriter.write(task.toSaveData() + System.lineSeparator());
        }

        fileWriter.close();
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList;
        try {
            taskList = loadOrCreateTaskFile("./data/Neuro.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No save file found");
            System.out.println("Error encountered: " + e);
            return;
        }

        // Scanner creation format inspired by https://www.w3schools.com/java/java_user_input.asp
        Scanner scanner = new Scanner(System.in);
        System.out.println("    ___________________________________________________");
        System.out.println("    Hii, I'm Neuro, your chatbot assistant!");
        System.out.println("    How can I help you?");
        System.out.println("    ___________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                try {
                    updateTaskFile("./data/Neuro.txt", taskList);
                } catch (IOException e) {
                    System.out.println("Error encountered: " + e);
                }
                break;
            } else if (input.equals("list")) {
                System.out.println("    ___________________________________________________");

                if (taskList.isEmpty()) {
                    System.out.println("    You currently have no tasks.");
                } else {
                    System.out.println("    Here is a list of all your tasks:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("    " + (i + 1) + ". " + taskList.get(i));
                    }
                }

                System.out.println("    ___________________________________________________");
            } else if (input.startsWith("mark")) {
                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
                String[] inputComponents = input.split(" ");

                System.out.println("    ___________________________________________________");
                try {
                    int taskIndex = Integer.valueOf(inputComponents[1]);
                    Task task = taskList.get(taskIndex - 1);
                    task.markDone();

                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    " + task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Missing index for 'mark' command! Add a valid index for a task" +
                            " to mark, like 'mark 2'.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
                            " index of the desired task.");
                }
                System.out.println("    ___________________________________________________");

            } else if (input.startsWith("unmark")) {
                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
                String[] inputComponents = input.split("[\s]");

                System.out.println("    ___________________________________________________");
                try {
                    int taskIndex = Integer.valueOf(inputComponents[1]);
                    Task task = taskList.get(taskIndex - 1);
                    task.markUndone();

                    System.out.println("    Ok, I've marked this task as not done:");
                    System.out.println("    " + task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Missing index for 'unmark' command! Add a valid index for a task" +
                            " to unmark, like 'unmark 2'.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
                            " index of the desired task.");
                }
                System.out.println("    ___________________________________________________");
            } else if (input.startsWith("delete")) {
                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
                String[] inputComponents = input.split("[\s]");

                System.out.println("    ___________________________________________________");
                try {
                    int taskIndex = Integer.valueOf(inputComponents[1]);
                    Task task = taskList.remove(taskIndex - 1);

                    System.out.println("    Ok, I've removed this task:");
                    System.out.println("    " + task);
                    System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Missing index for 'delete' command! Add a valid index for a task" +
                            " to delete, like 'delete 2'.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
                            " index of the desired task.");
                }
                System.out.println("    ___________________________________________________");
            } else {
                try {
                    Task task = getTask(input);
                    taskList.add(task);

                    System.out.println("    ___________________________________________________");
                    System.out.println("    Ok, I've added this task:");
                    System.out.println("        " + task);
                    System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("    ___________________________________________________");
                } catch (IllegalArgumentException e) {
                    System.out.println("    ___________________________________________________");
                    System.out.println("    " + e.getMessage());
                    System.out.println("    ___________________________________________________");
                }
            }
        }

        System.out.println("    ___________________________________________________");
        System.out.println("    Goodbyeee!");
        System.out.println("""


                                                                   @@=+==%@
                                                                    @%==#@
                                                                      @++@
                                                      @@@@@@@%%%%%@@@@@@#@
                                                 @@%*+=======+++++====++#@@
                                             @@#+==+=++=================+==+*@@
                                     @@@%%@@*+=+===+=#*+=====================++*@@
                       @    @ @@%####@*#@+==+====++@+===========================++@@
                  @%@ %-=@@*-*%#####*@#========+=@++=+====++======================+=#@
                 @*--%*--*@---@*###%#=========+%+==+#@#++#@%========================+=*@
                @@%---#--=*---@*##@++=+======#*=++@+=+====+===========================+=#@
                +-*#----------##%@#%++=====+%+==+@*+========================+============+@
                @=-----=====---==--*#====++%===**%=====++=+=============+*%@%*=+==++=====++
                 @*---======------%+=====+#=+=%=%+====+@+=#++=============+==+@#==+#=======
                @*#+------------=%*%*===*#==#*=+#====#*@==@++=+================#++=@=======
                ==#@#+======+#%%#===+#+##=##=--%=++=@=-@++@++====++====++========+=@++=====
                #=======--===========%+###=---#++=%+=--#++@+========+==%@========+=@*+=====
                %-===================*%=-----=#=#*=----+*+#*=======+@=#+@=========+%*+=====
                %-===================++------%#%=-------#**#=++===+@#**=%=========*##+=====
                @=====================#------*=---------#**%=====+##+#-=#=========%##======
                @+====================@@@@@@*-=#+-------+%##*+=++@=%%=-+*=========@#*======
                @#====================@@@@@@@@%=-%-------%%+#===%=+@=--*+========+*#+======
                @%====================%*%@@@@@@@%=-------+@=#=+#*-*%---%=+=======#-%=======
                @@====================#+====*@@@@@*-------@=+#=%=-#*--=%=======++#=#=======
                %*+===================**======+@@#-----------%+#------=#@+=++=+=%=#++======
                +=#====================#========%=-----------=@+---==+*#@+=#%++@==@========
                +=%===================-#========-----------------#@@@@@@@%*=--===*+========
                =+%+==================-#========--=+=----------+@@@@@@@@@@@@@@*-=%=========
                =+**===================#+=====----%**#%%*=-----+*+===+*%@@@@@@@@%++========
                ===#-==================**=-------##*******%#------========*@@@@@*+=========
                =+*@====================#--------%**********-----============*@*=========+*
                =+@@*==================-%--------%********@=----=============%+=========+=%
                +@ @%===================*--------**======#=-----===========*%============%*
                @   @===================+@=-------+====+%=-------=======+%#*@=+========++%*
                    @*===================@-+@*=-------------------=========@++========++@**
                   @%@===================#==+*-+%#+=-----------------=====%=+==========%***
                   @*%+===================*==#*==+*=+###*==----------=*#%%============##***
                  @#**#-=================-%===#*=-%#%*=*#===++++#@#****#*=+==========*#****
                 @#***%+==================+*=*++*-*==*#====-=##+*#****%+=+%%+=======*%*****
                @%*****%-=================-#-%+*%%+#%=-=#%#+=====+%*%@@%**%=======+##******
                %******##==================+%@++=%@@@#%%%*=========%*****%======+=@********

                """);
        System.out.println("    ___________________________________________________");
    }
}
