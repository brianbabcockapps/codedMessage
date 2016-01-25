
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
		
		//SortArray
		quickSort(letters);
		
		//print letters
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].getLetter() == '_') {
				break;
			}
			System.out.print(letters[i].getLetter());
		}
	}
	
	/**
	 * Takes in array and split it by pivot
	 * 
	 * @param letters
	 * @param l for left most of array
	 * @param r for right most of array
	 * @return
	 */
	public static int partition (LetterBean [] letters, int l, int r) {
		
		int left = l, right = r;
		int pivot = letters[(left + right)/2].getCount(); 
		
		while (left <= right) {
			while (letters[left].getCount() > pivot) {
				left++;
			}
			while (letters[right].getCount() < pivot) {
				right--;
			}
			
			if (left <= right) {
				LetterBean temp = letters[left];
				letters[left++] = letters[right];
				letters[right--] = temp;
			}
		}
		return left;
	}
	
	/**
	 * Initialize quicksort.  Set left and right and only sort the letters that matter.
	 * 
	 * @param letters
	 */
	public static void quickSort (LetterBean [] letters) {
		int left = 0, right = UNDERSCORE;
		
		LetterBean temp = letters[(left + right)/2];
		letters[(left + right)/2] = letters[right];
		letters[right] = temp;
		
		int index = partition (letters, left, right);
		
	    if (left < index - 1) {
	    	quickSort(letters, left, index - 1);
	    }
	}
	
	/**
	 * Main quickSort recursion function.  Keep partitioning and pivot.
	 * 
	 * @param letters
	 * @param left
	 * @param right
	 */
	public static void quickSort (LetterBean [] letters, int left, int right) {
		
		int index = partition (letters, left, right);
		
	    if (left < index - 1) {
	    	quickSort(letters, left, index - 1);
	    }
	    if (index < right) {
	    	quickSort(letters, index, right);
	    }
	}
}
