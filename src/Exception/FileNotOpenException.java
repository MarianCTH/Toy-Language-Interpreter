package Exception;

public class FileNotOpenException extends RuntimeException
{
    public FileNotOpenException(String message) {
        super(message);
    }
}
