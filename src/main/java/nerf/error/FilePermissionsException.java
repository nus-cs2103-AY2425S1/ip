package nerf.error;

public class FilePermissionsException extends Exception{
    public FilePermissionsException(String errorMessage){
        super(errorMessage);
    }
}
