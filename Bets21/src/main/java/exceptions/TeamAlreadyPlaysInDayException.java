package exceptions;
public class TeamAlreadyPlaysInDayException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public TeamAlreadyPlaysInDayException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public TeamAlreadyPlaysInDayException(String s)
  {
    super(s);
  }
}