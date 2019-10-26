package kportufe;
import java.util.*;
import java.io.*;

public class Main
{
    public static String normalizeText(String text)
    {
        String textTemp= "";
        textTemp = text.replaceAll("[\\s.,:;'!?()\"]", "").toUpperCase();
        return textTemp;
    }

    public static String caesarify(String text , int value)
    {
        String textTemp = shiftAlphabet(value);

        textTemp = text.replace(text, textTemp);

        return textTemp;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    public static String groupify(String text, int letterBroken)
    {
        String textTemp = "";
        int count = 0;
        for (int loopingThroughTheText = 0; loopingThroughTheText < text.length(); loopingThroughTheText++)
        {
            if (loopingThroughTheText % letterBroken == 0 && loopingThroughTheText != 0) {
                textTemp = textTemp + " ";
            }
            textTemp = textTemp + text.charAt(loopingThroughTheText);
        }

            for (int loopingThroughTheText = textTemp.lastIndexOf(" ") + 1; loopingThroughTheText < textTemp.length(); loopingThroughTheText++)
            {
                ++count;
            }

            for (int loopingThroughTheText = letterBroken - count; loopingThroughTheText > 0; loopingThroughTheText--)
            {
                textTemp += "x";
            }

            return textTemp;
    }

    public static String encryptString (String text, int value, int letterBroken)
    {
        String textTemp = "";
        textTemp = normalizeText(text);
        textTemp = caesarify(text, value);
        textTemp = groupify(text, letterBroken);

        return textTemp;
    }

    public static String ungroupify(String groupedString)
    {
        String textTemp = groupedString.replaceAll("[\\sx]", "");
        return textTemp;
    }

    public static String decryptString(String text, int value)
    {
        return ungroupify(text);
    }

    public static void main (String [] args)
    {
        Scanner x = new Scanner(System.in);
        System.out.print("Please enter the text to be decrypted: ");
        String text = x.nextLine();
        System.out.println(text = normalizeText(text));

        System.out.print("Please enter the shift value: ");
        int value = x.nextInt();
        System.out.println(caesarify(text, value));

        System.out.print("Please enter how many groups do you want to break the encryption: ");
        int groupSize = x.nextInt();
        String codeGroupedString = groupify(text, groupSize);

        System.out.println("Encryption String .....");
        String cypherText = encryptString(text, value, groupSize);
        System.out.println(cypherText);
        System.out.println("--------------------------");

        ungroupify(codeGroupedString);
        System.out.println("Decrypting string...");
        String plainText = decryptString(cypherText,value);
        System.out.println(plainText);
    }
}