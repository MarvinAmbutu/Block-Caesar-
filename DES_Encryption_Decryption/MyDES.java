package DES_Encryption_Decryption;
/**
 * COMP 307 - Web Development 
 * Assignment #2 
 * STUDENT NAME : Marvin Ambutu 
 * STUDENT ID :260724599
 */

import java.util.Scanner;

/**
 * <h1>MyDES</h1>
 * @author Marvin Ambutu
 *
 */

public class MyDES {

  public static void main(String[] args) throws Exception {

    @SuppressWarnings("resource")
    Scanner messInput = new Scanner(System.in);
    int[] keys = new int[3];

    System.out.println("Welcome to myDES");
    System.out.println("Please enter your key(3 numbers with a space after each)");
    enterKeys(keys);
    System.out.println("Please enter your message:");
    String message = messInput.nextLine();
    isMessageValid(message);
    System.out.println("Encrypted message:" + encrypt(message, keys));
    System.out.println("Decrypted message:" + decrypt(encrypt(message, keys), keys));
    System.out.println("End of myDES.");
  }

  /*
   * <h1>encrypt method</h1>
   * 
   * This method encrypts a message using block Caesar cipher
   * 
   * @param message - The String that is encrypted
   * 
   * @param keys - Array of keys used in encryption
   * 
   */

  public static String encrypt(String message, int keys[]) {

    char[] array = message.toCharArray();
    for (int i = 1; i < 4; i++) {
      blockCipher(array, keys, 1);
      if (i < 3)
        rotateKey(keys, "F");
    }

    return charToString(array);
  }

  /*
   * <h1>encrypt method</h1> This method encrypts a message using block Caesar cipher
   * 
   * @param message - The String that is encrypted
   * 
   * @param keys - Array of keys used in encryption
   */
  public static String decrypt(String message, int keys[]) {

    char[] array = message.toCharArray();
    for (int i = 1; i < 4; i++) {
      blockCipher(array, keys, -1);
      if (i < 3)
        rotateKey(keys, "B");
    }

    return charToString(array);
  }

  /*
   * <h1>blockCipher method</h1> This method blocks Caesar cipher a char [] array
   * 
   * @param array - A char array whose characters are to be ciphered
   * 
   * @param keys - An int array that contains either encryption or decryption keys
   * 
   * @param sign - if -ve decryption, if +ve encryption block Caesar cipher
   */

  public static void blockCipher(char[] array, int keys[], int sign) {

    int i = 0;
    int j = 0;
    while (i < array.length) {
      array[i] = (char) ((int) array[i] + (keys[j] * sign));

      if (j >= 2)
        j = 0;
      else
        j++;
      i++;
    }
  }
  /*
   * <h1>rotateKey Method</h1> This method rotates the key to the left or to the right by one index depending ont the input of the string
   * 
   * @param keys- An array of keys to be rotated
   * 
   * @param direction - A string specifying the direction of rotation
   */

  public static void rotateKey(int keys[], String direction) {

    switch (direction) {
      case "F":
        int initial = keys.length - 1;
        int temp = keys[initial];

        for (; initial > 0; initial--)
          keys[initial] = keys[initial - 1];

        keys[initial] = temp;
        break;
      case "B":
        int start = 0;
        int stm = keys[start];

        for (; start < keys.length - 1; start++)
          keys[start] = keys[start + 1];

        keys[start] = stm;
        break;
    }
  }

  /*
   * <h1>isMessageValid method</h1> This method checks that the message is not empty nor null else
   * throws an exception
   * 
   * @param array - The array to be converted to a string
   */

  public static String charToString(char[] array) {

    String message = Character.toString(array[0]);
    for (int i = 1; i < array.length; i++)
      message += array[i];

    return message;
  }

  /*
   * <h1>isMessageValid method</h1> This method checks that the message is not empty nor null else
   * throws an exception
   * 
   * @param message - The string whose validity is checked
   */

  public static void isMessageValid(String message) throws Exception {

    if (!message.isEmpty() || message.length() != 0)
      System.out.println("Your message is valid");
    else
      throw new Exception("You input an empty string!");
  }

  /*
   * <h1>enterKeys method</h1> This method prompts user to enter keys for ciphering
   * 
   * @param keys - An array that stores the valid user input
   */

  public static void enterKeys(int keys[]) throws Exception {

    @SuppressWarnings("resource")
    Scanner input = new Scanner(System.in);
    int i = 0;

    while (i < 3) {

      try {
        keys[i] = input.nextInt();
      } catch (Exception e) {
      }

      if (keys[i] > 9 && keys[i] < 100) {
        i++;
      } else {
        throw new Exception(
            "You did not input 3 positive integer numbers each strictly >9 and <100");
      }

    }
    System.out.println("Your Key is valid");
  }

}
