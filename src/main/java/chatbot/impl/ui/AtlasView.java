package chatbot.impl.ui;

import chatbot.impl.Command;
import chatbot.interfaces.MessageView;


import java.util.Scanner;

public class AtlasView implements MessageView<Command> {
    private static final String BOT_INDENT = "\t";
    private static final String SEP = "__________________________________________________________";

    private Scanner scanner;

    public Command parseInput(String input) throws IllegalArgumentException {
        String[] arr = input.split(" ");
        String command = arr[0];
        return switch (command) {
            case "bye" -> Command.Exit;
            case "list" -> Command.List;
            case "mark" -> Command.Mark;
            case "unmark" -> Command.Unmark;
            case "delete" -> Command.Delete;
            case "find" -> Command.Find;
            case "todo" -> Command.ToDo;
            case "deadline" -> Command.Deadline;
            case "event" -> Command.Event;
            default -> Command.Unknown;
        };
    }

    public String[] splitInput(String input) {
        return input.split(" ");
    }

    public void bind(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * <p>
     *     Reads the user input from the console
     * </p>
     * @return the user input
     */
    public String listen() {
        System.out.print("Me > ");
        String text = scanner.nextLine();  // Read user input
        endMessage();
        return text;
    }

    /**
     * <p> Sends a response to the user </p>
     * @param response
     */
    public void send(String response) {
        String[] lines = response.split("\n");
        for (String line : lines) {
            System.out.println(BOT_INDENT + line);
        }

    }

    /**
     * <p> Finishes the message </p>
     * <p> Adds a line separator </p>
     */
    public void endMessage() {
        System.out.println(BOT_INDENT + SEP);
        System.out.println();
    }

    /**
     * <p> Introduces the chatbot </p>
     */
    public void introduce() {
        send("""
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
        send("Hello! I'm Atlas.");
        send("I am here to share your burdens of remembering tasks");
        send("What can I do for you today?");
        endMessage();
    }

    /**
     * <p> Exits the chatbot </p>
     */
    public void exit() {
        send("Goodbye! Have a great day ahead!");
        endMessage();
    }
}
