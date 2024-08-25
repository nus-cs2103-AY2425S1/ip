import java.util.Scanner;  // Import the Scanner class
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;


public class Atlas {
    private static final String BOT_INDENT = "\t";
    private static final String SEP = BOT_INDENT + "___________________________________________";
    private static final String EXIT = "bye";
    private static final Scanner scanner = new Scanner(System.in);  // Create a Scanner object

    private static final ArrayList<Task> tasks = new ArrayList<>(); 

    public static void main(String[] args) {
        introduction();
        String input = null;
        do {
            parse(input);
            input = listen();
        } while (!input.equals(EXIT));
        exit();
        scanner.close();
    }

    private static void parse(String input) {
        if (input == null) {
            return;
        }
        try {
            String[] text = input.split(" ");
            if (text.length == 0) {
                return;
            }
            String command = text[0];
            boolean status = false;
            switch (command) {
                case "bye": // do nothing: handled in main loop
                    break;
                case "list":
                    listShow();
                    break;
                case "mark":
                    status = true;
                case "unmark":
                    if (text.length < 2) {
                        throwMissingTask();
                    }
                    int index = Integer.parseInt(text[1]) - 1;
                    taskMark(index, status);
                    taskMarkLog(index, status);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    if (text.length < 2) {
                        throwMissingDescription();
                    }
                    switch (command) {
                        case "todo":
                            addToDo(text);
                            break;
                        case "deadline":
                            addDeadline(text);
                            break;
                        case "event":
                            addEvent(text);
                            break;
                        default:
                    }
                    addTaskLog();
                    break;
                default:
                    throwUnknownCommand();
            }
        } catch(IllegalArgumentException e) {
            botMessage(e.getMessage());
            separate();
        } catch (ArrayIndexOutOfBoundsException e) {
            botMessage(e.getMessage());
            separate();
        }
    }

    private static void addToDo(String[] text) throws IllegalArgumentException {
        String description = arrayJoin(text, 1, text.length);
        if (description.isBlank()) {
            throwMissingDescription();
        }
        tasks.add(new ToDo(description));
    }

    private static void addDeadline(String[] text) throws IllegalArgumentException {
        int idx = Arrays.asList(text).indexOf("/by");
        if (idx == -1) {
            throwMissingDeadline();
        }
        String description = arrayJoin(text, 1, idx);
        if (description.isBlank()) {
            throwMissingDescription();
        }
        String by = arrayJoin(text, idx + 1, text.length);
        if (by.isBlank()) {
            throwMissingDeadline();
        }
        tasks.add(new Deadline(description, by));
    }

    private static void addEvent(String[] text) throws IllegalArgumentException{
        int idx = Arrays.asList(text).indexOf("/from");
        if (idx == -1) {
            throwMissingFrom();
        }
        int idx2 = Arrays.asList(text).indexOf("/to");
        if (idx2 == -1) {
            throwMissingTo();
        }
        String description = arrayJoin(text, 1, Math.min(idx, idx2));
        if (description.isBlank()) {
            throwMissingDescription();
        }
        String from = arrayJoin(text, idx + 1, idx2 > idx ? idx2 : text.length);
        if (from.isBlank()) {
            throwMissingFrom();
        }
        botMessage("from: " + from);
        String to = arrayJoin(text, idx2 + 1, idx2 > idx ? text.length : idx);
        if (to.isBlank()) {
            throwMissingTo();
        }
        botMessage("to: " + to);
        tasks.add(new Event(description, from, to));
    }

    private static void addTaskLog() {
        botMessage("Got it. I've added this task:");
        botMessage("  " + tasks.get(tasks.size() - 1));
        botMessage("Now you have " + tasks.size() + " task(s) in the list.");
        separate();
    }

    private static void taskMark(int index, boolean status) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throwIllegalIndex(index);
        }
        tasks.get(index).setDone(status);
    }

    private static void taskMarkLog(int index, boolean status) {
        String message = status ? "Nice! I've marked this task as done:"
            : "OK! I've marked this task as not done yet:";

        botMessage(message);
        botMessage("  " + tasks.get(index));
        separate();
    }

    private static void listShow() {
        
        botMessage("Here are the items in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            botMessage((i + 1) + "." + tasks.get(i));
        }
        if (tasks.size() == 0) {
            botMessage("<EMPTY>");
            botMessage("\nThere is nothing for you to do! Yay!");
        }
        separate();
    }

    private static void introduction() {
        botMessage("""
            ....   .                              ....        .                        ....            .     
            ....        .   ......         .     ....             .....                ....            ......
          ...               ......           . ....              .......            .... .             ......
         ...   .    .        ....             ...                 .....        .   ....        .    .   .... 
         ..                   .              ...                          .       ...                        
                       . .                                             .        .         . .                
                                                                                                             
                           .                      ..:-=+++++++==-:...          .                             
          .                           .      ..-=#@@@@%%@@@#%%%%@@@@#=-..                                    
                  ..   .        .         .:=@@@@#+: .. =@@.   ...#@@@@@%=:. .                  .            
            .                    ...    .=@@@#:@@-      #@*.-%%@@@@%. .:#@@@=.       .                      .
          .....                ....   .#@@#:.. %%#*%@#..+@@@@%*++=-..... .:#@@#.     .....                ...
         ......              ....   .*@@+:     :=+*=#@%. .==:.     ..:****#*-+@@*.  ......              .... 
          .....   .        ....   .=@@*:       ......@@-  .     .....%@%#**%@@%@@@=. .....   .        ....   
                          ...    .*@%-       ..     =@%.        ....#@#.    .=**=%@*.       .        ...     
              .             .   .#@#..    .  -@%*++*@@=         . :*@%.   . .    .%@#.             .     .   
               .           .   .#@%.. ......:#@%#%%#+.        ....@@#.            .%@#.    .                 
                 .             *@@=   =@@@@@@@#.            .=%@@@@%               .%@*  .               .   
                              -@@@@. +@@=--::..   .    .    .%@*::..         .      :@@-                     
                             .%@#@@.-@@:    .               .*@@##%%##*.            .+@%.                    
         .    ...            -@@:@@::@@:            ..       :=+*+==+#@%..    .      .@@-..              .   
            ....            .+%*.+@@=@@-         .....     .....::---.#@#             #@+..        .   ..... 
          ....              .*#=..-@@@%..       ....      .#@@@@@@###%%@+       .:--..+@#             .......
         ...              ...*@=.  :@@@@@#-.......       .%@#.  . ...-#@: .    .#@@@@++@%.             ..... 
         .      .   .        #@+    *@#:+@@@@%=:         +@%   .           ....:@@:.@@@@#                    
                .            *@#   -@@%  ..:=%@*         %@=    .         =@@@@%#@@:.#@@*         .          
          .    .  .         .-@@.:%@@-.      +@#        -@@@@%#=.         *@@=-#@%-. .@@-    .               
                             .%@+ *@%..      +@@@%*  .   ..:==#@@*.       .=%@%+.    +@%.                    
                   .          =@@..#@#.       .:=@@.    .:==:. :*@%:        .-#@*  ..@@=           .         
               . .          :-+@@#..#@@=.      :=@@- .-#@@@@@@#-...        .-*@@=  .#@@+-:                   
               .          .*@@%*%@*..:@@+    .*@@#:..%@%=.  .=%@%.   .....+@@%+.. .*@%*%@@*.   .          ...
         ......           =@@. ..%@#. =@@. .:@@#....%@*.      .*@%. .....#@#..   .#@@....@@=      ..  .   ...
         ......          .@@=.-%@@@@@..%@+ =@@-....=@%.        .%@+.... :@@:    .@@@@%#..=@@.      .     ....
         ......          =@@..=@%::=@@*@@- #@*.....*@*          *@*... +%@%.. .+@@=.:##-..@@=          ....  
                    .    %@=..=@@   :#@@@.:@@:   .*@@@=.    .  +%@@*. .=#-. .+@@#:.  @@=  +@%.       ....    
                      . -@@.  -@@.   .-#@@*@@.  .+@%-%@+.    .*@%-%@+.  . :+@@#-.   .@@-  .@@-               
                        #@*   -@@.      :*@@@=-. +@@-:%@@@@@@@@%:.@@+ .-=%@%*:.     .@@-   *@#.      .       
                       :@@:   :@@::--:::-%@@@@@@%#@%. .-=:::-+-.  #@##@@@@@@%-:::--::@@:   :@@:              
                      .+@%.   :@@@@@@@@@@%-...:#%@@#.           .:@@@%#-...-%@@@@@@@@@@:  . %@+              
                       %@+    #@#......:...  .  ..#@@           =@@=....    ..:......#@#    +@%   .      .   
         .     . .     @@-    ...                 .@@%##=    *##%@#                  ...  . -@@              
           .....       @@*-. ....    .            ..=+*%@#**#@@**=....                 ....-*@@         .... 
           ....        **%@@*=:...              .....  .=****=.  .......     .        .:=*@@%**        ......
         ....    . .     ..+*##%##+======+*+. -#+:..  .  .::.    ....+#- .*++======+#%@@%#+..          ......
         ..  .           .   ..:=*#%%%%%%#%@#..*@@%+=====+@@+=====+#@@#..*@%#%%%%%%#*=:..  .             .   
                                          .@@=  .-#@@@@@@@@@@@@@@@@#-.  =@@.        .                        
                       .                   :-.    ........::........    .-:                              .   
                             .                           .                             .       . .           
                       .              ...           . .  .                            .                      
                                                                                          .                 .
                                 ..                     .              .                    .     .        ..
          ....      . .        ....           . ....          .     .....   .        ....                 ...
         ......              ....              .....               ....             ......              .... 
         ...... .           .....              .....      .      .....       .      ......             ......
         ****************************************************************************************************
         *++++++*+++++*++=+*********************************************************=+++++++++**+++++++++++**
         ****************************************************************************************************
                """);
        botMessage("Hello! I'm Atlas.");
        botMessage("I am here to share your burdens of remembering tasks");
        botMessage("What can I do for you today?");
        separate();
    }

    private static void exit() {

        botMessage("Goodbye! Have a great day ahead!");
        separate();
    }

    private static String listen() {
        System.out.println("");
        String text = scanner.nextLine();  // Read user input
        separate();
        return text;
    }

    private static void echo(String input) {
        if (input == null) {
            return;
        }
        botMessage(input);
        separate();
    }

    /* --------- HELPER FUNCTIONS --------- */

    private static void separate() {
        System.out.println(SEP);
    }

    private static void botMessage(String message) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            System.out.println(BOT_INDENT + line);
        }
    }

    private static String arrayJoin(String[] arr, int start, int end) {
        if (start >= end) {
            return "";
        }
        return String.join(" ", Arrays.copyOfRange(arr, start, end));
    }

    /* --------- EXCEPTIONS --------- */

    private static void throwMissingDescription() throws IllegalArgumentException{
        throw new IllegalArgumentException("Oopsie! The description of a task cannot be empty.");
    }

    private static void throwMissingDeadline() throws IllegalArgumentException{
        throw new IllegalArgumentException("Oh no!! The deadline of a task cannot be empty.\nIndicate the deadline by using \"/by <deadline>\"");
    }

    private static void throwMissingFrom() throws IllegalArgumentException{
        throw new IllegalArgumentException("Uh oh!! The event details cannot be empty.\nIndicate when the event starts by using \"/from <start>\"");
    }

    private static void throwMissingTo() throws IllegalArgumentException{
        throw new IllegalArgumentException("Oh no!! The event details cannot be empty.\nIndicate when the event starts by using \"/to <end>\"");
    }

    private static void throwMissingTask() throws IllegalArgumentException{
        throw new IllegalArgumentException("Golly!! The task number is missing.\nIndicate the task number to (un)mark by using \"(un)mark <task number>\"");
    }

    private static void throwIllegalIndex(int index) throws ArrayIndexOutOfBoundsException{
        throw new ArrayIndexOutOfBoundsException("Oopsie! The task number " + (index + 1) + " does not exist");
    }

    private static void throwUnknownCommand() throws IllegalArgumentException{
        throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
    }
}
