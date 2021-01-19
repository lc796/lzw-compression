package me.lukecs.lzw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Luke Collishaw
 * @version v1.0
 * @since 2021-01-19
 */
public class Decoder {

    private final Map<Integer, String> dictionary = new HashMap<>();

    /**
     * Constructor that builds our initial dictionary of unicode characters, representing from 0-255.
     */
    public Decoder() {
        for (int i = 0; i<256; i++) {
            dictionary.put(i, Character.toString((char) i));
        }
    }

    /**
     * This is the main public method used for decoding. It tries to decode a file after you specify a path to a file,
     * as well as a desired name for the file after it has been decoded.
     *
     * @param path The absolute (not relative) path or location of the file.
     * @param decodedFileName The desired name to store the decoded file as, without file extension.
     */
    public void decodeFile(String path, String decodedFileName) {

        String encodedFileAsString = FileUtilities.fileToString(path);
        String decodedFileAsString = decodeString(encodedFileAsString);

        if (encodedFileAsString.equals("")) {
            System.out.println("[DECODER] Cannot convert file to string! Perhaps file cannot be found?");
            return;
        }

        if (decodedFileAsString == null) {
            System.out.println("[DECODER] Cannot decompress file!");
            return;
        }

        if (decodedFileName.isEmpty()) {
            decodedFileName = "decompressed";
        }

        try (PrintStream out = new PrintStream(new FileOutputStream(decodedFileName + ".txt"))) {
            out.print(decodedFileAsString);
            System.out.println("[DECODER] Successfully decompressed file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible for main decoding logic. It will take an encoded string, in format such as "72 101 108 108 111",
     * and rebuild the dictionary to convert an encoded string back into it's decoded form.
     *
     * @param encodedString The encoded string.
     * @return The decoded string in plaintext.
     */
    private String decodeString(String encodedString) {
        String s = "";
        String decodedFile = "";

        //dictionary.put(0, "a");
        //dictionary.put(1, "b");

        List<Integer> encodedValues = convertStringToIntArray(encodedString);

        if (encodedValues == null) {
            return null;
        }

        for (int k : encodedValues) {
            //int k = Character.getNumericValue(c);
            if (k > dictionary.size()) {
                // Cannot decode since encoding should be incremental...
                return "";
            } else if (k == dictionary.size()) {
                String newDictionaryEntry = s + s.toCharArray()[0];
                dictionary.put(dictionary.size(), newDictionaryEntry);
            } else if (!s.isEmpty()) {
                String newDictionaryEntry = s + dictionary.get(k).toCharArray()[0];
                dictionary.put(dictionary.size(), newDictionaryEntry);
            }

            decodedFile += dictionary.get(k);
            s = dictionary.get(k);
        }

        //System.out.println(dictionary);
        return decodedFile;
    }

    /**
     * Takes a string input in the form "72 101 108 108 111", and converts it into a list of Integers by splitting
     * on a space character (" ").
     *
     * @param stringInput The string in encoded style to convert.
     * @return The converted array list if converted successfully, otherwise null.
     */
    private List<Integer> convertStringToIntArray(String stringInput) {
        String[] splitStringInput = stringInput.split(" ");
        List<Integer> convertedList = new ArrayList<>();
        for (String item : splitStringInput) {
            try {
                int i = Integer.parseInt(item);
                convertedList.add(i);
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return convertedList;
    }

}
