package exceptions;
public class LessThanMinimumTeamException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public LessThanMinimumTeamException()
  {
    super();
  }
  /**This exception is triggered if the event has already finished
  *@param s String of the exception
  */
  public LessThanMinimumTeamException(String s)
  {
    super(s);
  }
}