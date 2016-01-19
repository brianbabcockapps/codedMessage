
/**
 * Takes in coded message through args, counts the number of each letter,
 * then sorts the letters into a coded message.
 * @author Brian Babcock
 *
 */

public class Decoder {
	
	public static final int UNDERSCORE = 30;
	public static final int ARRAY_LENGTH = 31;
	public static final int ASSCI_OFFSET = 65;

	public static void main(String[] args) {
		LetterBean [] letters = new LetterBean[ARRAY_LENGTH];
		
		//Create array of letters
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			letters[i] = new LetterBean((char) (i + ASSCI_OFFSET));
		}
		
		//Get coded message in args and count each letter
		for (int i = 0; i < args.length; i++) {
			for (int j = 0; j < args[i].length(); j++) {
				letters [(int)args[i].toUpperCase().charAt(j) - ASSCI_OFFSET].addOne();
			}
		}
		
		//reduce the array to make sorting faster
		letters = reduce(letters);
		
		letters = sort(letters);
		
		//print letters
		for (int i = 0; i < letters.length; i++) {
			System.out.print(letters[i].getLetter());
		}
	}
	
	/**
	 * Reduce the array to only letters to sort in order to make sorting faster
	 * @param letters
	 * @return
	 */
	public static LetterBean[] reduce (LetterBean [] letters)
	{
		LetterBean [] temp;
		int count  = 0;
		int j = 0;
		
		//Get size of new array
		for (int i = 0; i < letters.length; i++){
			if (letters[i].getCount() > letters[UNDERSCORE].getCount()) {
				count++;
			}
		}
		
		//Make the smaller array.
		temp = new LetterBean[count];
		
		for (int i = 0; i < count; i++){
			while (letters[j].getCount() < letters[UNDERSCORE].getCount())  {
				j++;
			}
			temp[i] = letters[j++];
		}
		
		return temp;
	}

	/**
	 * This function sets up the merge sort recusive function
	 * @param letters
	 * @return
	 */
	public static LetterBean [] sort (LetterBean [] letters)
	{
		int arrayLength = letters.length - 1;
		int middle = arrayLength/2;
	    mergeSort(letters, 0, middle);
	    mergeSort(letters, middle + 1, arrayLength);
	    merge(letters, 0, middle, arrayLength);
		
		return letters;
	}
	
	/**
	 * This recursive function splits up the array into smaller parts
	 * @param letters
	 * @param begin
	 * @param end
	 * @return
	 */
	public static LetterBean [] mergeSort(LetterBean [] letters, int begin, int end)
	{
		if (begin < end) {
			int middle = begin + (end - begin)/2;
			mergeSort(letters, begin, middle);
			mergeSort(letters, middle + 1, end);
			merge(letters, begin, middle, end);
		}
		return letters;
	}
	
	/**
	 * Merges and sorts the divided array into 
	 * @param letters
	 * @param begin
	 * @param middle
	 * @param end
	 * @return
	 */
	public static LetterBean [] merge (LetterBean [] letters, int begin, int middle, int end)
	{
		LetterBean temp [] = new LetterBean [end - begin + 1];
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		while ((i <= middle - begin) && (j < end - middle)) {
			if (letters[begin + i].getCount() > letters[middle + 1 + j].getCount()) {
				temp[k] = letters[begin + i];
				k++;
				i++;
			}
			else
			{
				temp[k] = letters[middle + 1 + j];
				k++;
				j++;
			}
		}
		
		while (i <= middle - begin) {
			temp[k] = letters[begin + i];
			k++;
			i++;
		}
		
		while (j < end - middle) {
			temp[k] = letters[middle + 1 + j];
			k++;
			j++;
		}
		
		k = 0;
		for (i = begin; i <= end; i ++) {
			letters[i] = temp[k++];
		}
		
		return letters;
	}
}
