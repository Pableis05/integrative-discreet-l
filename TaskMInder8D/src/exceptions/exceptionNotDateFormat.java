package exceptions;

/**
 * The `exceptionNotDateFormat` class is a custom exception that is thrown when a given date is not in a valid format.
 */
public class exceptionNotDateFormat extends Exception {

    /**The code is defining a constructor for the `exceptionNotDateFormat` class. This constructor takes a `String` parameter called `date`. */
    public exceptionNotDateFormat(String date){
        super("The date "+date+" is not a valid date");
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
