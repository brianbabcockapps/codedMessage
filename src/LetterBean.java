/**
 * A place to store the letters and count.
 * @author Brian Babcock
 *
 */
public class LetterBean 
{
	private char letter;
	private int count = 0;
	
	LetterBean (char letter)
	{
		this.letter = letter;
	}
	
	void addOne () 
	{
		count++;
	}
	
	int getCount ()
	{
		return count;
	}
	
	char getLetter ()
	{
		return letter;
	}
	
}
