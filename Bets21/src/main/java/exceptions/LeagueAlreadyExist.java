package exceptions;
public class LeagueAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public LeagueAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public LeagueAlreadyExist(String s)
  {
    super(s);
  }
}