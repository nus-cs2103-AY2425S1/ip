import java.io.IOException;

public interface Bot {
    void introduction();
    void acceptCommands();
    void returnList();

    void writeToFile(String textToAdd) throws IOException;


}
