package exceptions;
public class EventFinishedException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public EventFinishedException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public EventFinishedException(String s)
  {
    super(s);
  }
}