package exceptions;

public class exceptionObjectAlredyExistWithThatKey extends Exception{
    public exceptionObjectAlredyExistWithThatKey(String ObjectValue, String key){
        super("The identity "+ObjectValue+" already exist with the key "+key);
    }
    public String getMessage(){
        return super.getMessage();
    }
}
