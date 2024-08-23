import java.util.Scanner;

public class AVA {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String currentInput="";
        // Greet the user
        System.out.println("""
                          Hi,  I'm AVA (Artificial Virtual Assistant).
                       I am a virtual personal assistant created by Nikhil.
                        I am currently an infant and can't do much 🙁 but  
                          don't worry I should soon be very capable 😉.
                
                ----------------------------------------------------------------
                       I'll repeat what you say, but if you want to leave
                                     you can just say Bye .🙂
                            
                ----------------------------------------------------------------                 
                """);
        currentInput = scanner.nextLine();
        // Process user input until user says bye
        while(!currentInput.equals("bye")){
            System.out.println("----------------------------------------------------------------");
            System.out.println("You said "+currentInput);
            System.out.println("----------------------------------------------------------------");
            currentInput = scanner.nextLine();
        }
        //Exit
        System.out.println("Byee!! It was nice talking to you. Hope to see you again soon.");
    }
}

