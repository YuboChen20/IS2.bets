package exceptions;
public class MaximumNumberOfTeamsReached extends Exception {
 private static final long serialVersionUID = 1L;
 
 public MaximumNumberOfTeamsReached()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public MaximumNumberOfTeamsReached(String s)
  {
    super(s);
  }
}