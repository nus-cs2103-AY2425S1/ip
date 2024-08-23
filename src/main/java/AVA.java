import java.util.Scanner;

public class AVA {

    /**
     * Current user input being processed by AVA.
     * Note this requires there be only 1 AVA instance
     */
    public String currentInput;

    /**
     * Main function which decides if AVA is running or not
     * //TODO: switch to a state based system like Operating System Threads
     * @return <span color="green">true</span> if AVA is running <span color="red">false</span> otherwise
     */
    public final boolean isRunning(){
        return !currentInput.equals("bye");
    }

    /**
     * Main Driver method running AVA
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AVA ava = new AVA(); //default AVA object

        // Greet the user
        System.out.println("""
                          Hi,  I'm AVA (Artificial Virtual Assistant).
                       I am a virtual personal assistant created by Nikhil.
                        I am currently an infant and can't do much 🙁 but
                          don't worry I should soon be very capable 😉.
                
                ----------------------------------------------------------------
                       I'll add what you say to a list and show it to you when
                                          you say list,
                          but if you want to leave you can just say bye .🙂
                            
                ----------------------------------------------------------------
                """);
        currentInput = scanner.nextLine();
        // Process user input until user says bye
        while(ava.isRunning()){
            System.out.println("----------------------------------------------------------------");
            System.out.println("You said "+currentInput);
            System.out.println("----------------------------------------------------------------");
            currentInput = scanner.nextLine();
        }
        //Exit
        System.out.println("Byee!! It was nice talking to you. Hope to see you again soon.");
    }
}

