import java.util.ArrayList;

public class Storage {
    String InputWord;
    ArrayList<String> UserInputs;
    
    public Storage() {
        this.UserInputs = new ArrayList<String>();
    }
    
    public void storeWord(String InputWord) {
        this.UserInputs.add(InputWord);
    }
    
    public String lastWord() {
        if (!this.UserInputs.isEmpty()) {
            if (this.UserInputs.get(this.UserInputs.size() - 1).equals("bye"))
                return "Bye. Hope to see you again soon!";
            else {
                return this.UserInputs.get(this.UserInputs.size() - 1);
            }
        }
        else {
            return "";
        }
    }
}
