package exceptions;

/**
 * The class exceptionObjectAlredyExistWithThatKey is a custom exception that is thrown when an object with a specific key already exists.
 */
public class exceptionObjectAlredyExistWithThatKey extends Exception{
    /** The `public exceptionObjectAlredyExistWithThatKey(String ObjectValue, String key)` constructor is creating a new instance of the `exceptionObjectAlredyExistWithThatKey` exception class. It takes two parameters: `ObjectValue` and `key`. */
    public exceptionObjectAlredyExistWithThatKey(String ObjectValue, String key){
        super("The identity "+ObjectValue+" already exist with the key "+key);
    }
    /**
     * The function returns the message associated with an exception.
     * 
     * @return The getMessage() method is returning the message from the superclass.
     */
    public String getMessage(){
        return super.getMessage();
    }
}
