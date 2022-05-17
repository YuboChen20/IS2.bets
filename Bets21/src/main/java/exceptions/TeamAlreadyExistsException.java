package exceptions;
public class TeamAlreadyExistsException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public TeamAlreadyExistsException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public TeamAlreadyExistsException(String s)
  {
    super(s);
  }
}