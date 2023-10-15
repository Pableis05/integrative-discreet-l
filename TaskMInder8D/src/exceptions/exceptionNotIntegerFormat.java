package exceptions;

/**
 * The `exceptionNotIntegerFormat` class is a custom exception class that is used to handle cases where a given string is not a valid integer format.
 */
public class exceptionNotIntegerFormat extends Exception{

    /**The `public exceptionNotIntegerFormat(String integer)` constructor is creating a new instance of the `exceptionNotIntegerFormat` class and initializing it with a message. The message is constructed by concatenating the string "The integer " with the value of the `integer` parameter and the string " is not a valid integer". This message will be used to provide information about the exception when it is thrown. */
    public exceptionNotIntegerFormat(String integer){
        super("The integer "+integer+" is not a valid integer");
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
