package exceptions;

public class exceptionNotIntegerFormat extends Exception{

    public exceptionNotIntegerFormat(String integer){
        super("The integer "+integer+" is not a valid integer");
    }

    public String getMessage(){
        return super.getMessage();
    }

}
