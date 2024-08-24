import java.util.Scanner;

public class TextUI {
    //TODO: add verbose toggle

    public static void run(){
        Scanner scanner = new Scanner(System.in);
        AVA ava = new AVA(); //default AVA object

        // Greet the user
        System.out.println("""
                        Hiii,  I'm AVA (Assimilated Virtual Assistant). 🎀
                       I am a virtual personal assistant created by Nikhil.
                                   Its sooo nice to meet you 🌸.
                                   
                        I am currently a toddler and can't do much 🙁 but
                          don't worry I should soon be very capable 💖.
                
                ----------------------------------------------------------------
                             I am here to help you and hope that'll
                               we'll have lots of fun together 🎀.
                               
                        If you get lost just say help and I'll help you out
                           but if you wanna leave you can just say bye. 🙂
                ----------------------------------------------------------------
                """);
        ava.tellAva(scanner.nextLine());
        // Process user input until user says bye
        while(ava.isRunning()){
            ava.respond(System.out);
            ava.tellAva(scanner.nextLine());
        }
        //Exit
        System.out.println("""
                                         Byeee!! It was really nice talking to you.💖
                                                 Hope to see you again soon.
                              """);

    }
}
