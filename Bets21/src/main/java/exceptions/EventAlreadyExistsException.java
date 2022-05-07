package exceptions;
public class EventAlreadyExistsException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventAlreadyExistsException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventAlreadyExistsException(String s)
  {
    super(s);
  }
}