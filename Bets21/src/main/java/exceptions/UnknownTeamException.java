package exceptions;
public class UnknownTeamException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public UnknownTeamException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public UnknownTeamException(String s)
  {
    super(s);
  }
}