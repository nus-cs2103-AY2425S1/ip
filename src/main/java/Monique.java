<<<<<<< HEAD
<<<<<<< HEAD
//imports for user input
import java.io.*;

public class Monique {
    public static void main(String[] args) throws IOException{
=======
public class Duke {
    public static void main(String[] args) {
>>>>>>> 3d8de7a8dc3c1fd17ce7b9ea8507221017ee77e8
=======
public class Duke {
    public static void main(String[] args) {
>>>>>>> 3d8de7a8dc3c1fd17ce7b9ea8507221017ee77e8
        String HORIZONTAL_LINE = "_____________________________________________";
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("Hello, I am Monique,\nWhat can I do for you today?");
        System.out.println(HORIZONTAL_LINE + "\n");
<<<<<<< HEAD
<<<<<<< HEAD
        boolean active = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (active) {
            System.out.println("user: ");
            String userInput = br.readLine();
            if (userInput.equalsIgnoreCase("bye")){
                active = false;
                System.out.println("Monique: Goodbye! Have a great day!");
            } else {
                System.out.println(userInput);
            }
        }

=======
        System.out.println("Ciao!");
>>>>>>> 3d8de7a8dc3c1fd17ce7b9ea8507221017ee77e8
=======
        System.out.println("Ciao!");
>>>>>>> 3d8de7a8dc3c1fd17ce7b9ea8507221017ee77e8
    }
}
