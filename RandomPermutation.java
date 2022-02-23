/*
 * Activity 3.6.4
 */

class RandomPermutation
{
  public static String[] next(String[] originalArray) {
    String[] newArray = new String[originalArray.length];
    int validDigits = originalArray.length;
    int indexSelection;
    double random;
    String selection;

    for (int i = 0; i < originalArray.length; i++)
    {
        random = Math.random();
        // System.out.println(random); debugging
        indexSelection = (int)(random * validDigits);
        // System.out.println(indexSelection); debugging
        selection = originalArray[indexSelection];
        newArray[i] = selection;
        originalArray[indexSelection] = originalArray[validDigits-1];
        originalArray[validDigits-1] = selection;
        validDigits--;
    }
    return newArray;
  }
}
