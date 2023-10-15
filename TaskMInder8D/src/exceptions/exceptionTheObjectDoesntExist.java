package exceptions;

/**
 * The `exceptionTheObjectDoesntExist` class is a custom exception that is thrown when an object with a given key doesn't exist.
 */
public class exceptionTheObjectDoesntExist extends Exception{
    /**The `public exceptionTheObjectDoesntExist(String key)` constructor is creating a new instance of the `exceptionTheObjectDoesntExist` class. It takes a `String` parameter `key` and calls the constructor of the `Exception` superclass with a message that includes the value of `key`. This message indicates that the object with the given `key` doesn't exist. */
    public exceptionTheObjectDoesntExist(String key){
        super("The identity with "+key+" doesn't exist");
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
