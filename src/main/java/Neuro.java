import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Neuro {
    private static Task getTask(String input) {
        // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
        // Array copyOfRange inspired by https://www.geeksforgeeks.org/java-util-arrays-copyofrange-java/
        String[] inputComponents = input.split("[\s]");
        String taskType = inputComponents[0];
        Task task = null;

        if (taskType.equals("todo")) {
            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, inputComponents.length));
            task = new Todo(description);
        } else if (taskType.equals("deadline")) {
            int byIndex = -1;
            for (int i = 1; i < inputComponents.length; i++) {
                if (inputComponents[i].equals("/by")) {
                    byIndex = i;
                    break;
                }
            }
            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, byIndex));
            String deadline = String.join(" ",
                    Arrays.copyOfRange(inputComponents, byIndex + 1, inputComponents.length));

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

            String description = String.join(" ",
                    Arrays.copyOfRange(inputComponents, 1, fromIndex));
            String from = String.join(" ",
                    Arrays.copyOfRange(inputComponents, fromIndex + 1, toIndex));
            String to = String.join(" ",
                    Arrays.copyOfRange(inputComponents, toIndex + 1, inputComponents.length));

            task = new Event(description, from, to);
        }

        return task;
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        // Scanner creation format inspired by https://www.w3schools.com/java/java_user_input.asp
        Scanner scanner = new Scanner(System.in);
        System.out.println("    ___________________________________________________");
        System.out.println("    Hii, I'm Neuro, your chatbot assistant!");
        System.out.println("    How can I help you?");
        System.out.println("    ___________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    ___________________________________________________");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + taskList.get(i));
                }
                System.out.println("    ___________________________________________________");
            } else if (input.startsWith("mark")) {
                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
                String[] inputComponents = input.split("[\s]");
                int taskIndex = Integer.valueOf(inputComponents[1]);
                Task task = taskList.get(taskIndex - 1);
                task.markDone();

                System.out.println("    ___________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    " + task);
                System.out.println("    ___________________________________________________");
            } else if (input.startsWith("unmark")) {
                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
                String[] inputComponents = input.split("[\s]");
                int taskIndex = Integer.valueOf(inputComponents[1]);
                Task task = taskList.get(taskIndex - 1);
                task.markUndone();

                System.out.println("    ___________________________________________________");
                System.out.println("    Ok, I've marked this task as not done:");
                System.out.println("    " + task);
                System.out.println("    ___________________________________________________");
            } else {
                Task task = getTask(input);
                taskList.add(task);
                
                System.out.println("    ___________________________________________________");
                System.out.println("    Ok, I've added this task:");
                System.out.println("        " + task);
                System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
                System.out.println("    ___________________________________________________");
            }
        }

        System.out.println("    ___________________________________________________");
        System.out.println("    Goodbyeee!");
        System.out.println("""


                                                                   @@=+==%@               \s
                                                                    @%==#@                \s
                                                                      @++@                \s
                                                      @@@@@@@%%%%%@@@@@@#@                \s
                                                 @@%*+=======+++++====++#@@               \s
                                             @@#+==+=++=================+==+*@@           \s
                                     @@@%%@@*+=+===+=#*+=====================++*@@        \s
                       @    @ @@%####@*#@+==+====++@+===========================++@@      \s
                  @%@ %-=@@*-*%#####*@#========+=@++=+====++======================+=#@    \s
                 @*--%*--*@---@*###%#=========+%+==+#@#++#@%========================+=*@  \s
                @@%---#--=*---@*##@++=+======#*=++@+=+====+===========================+=#@\s
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
