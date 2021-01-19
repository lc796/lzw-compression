package me.lukecs.lzw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Luke Collishaw
 * @version v1.0
 * @since 2021-01-19
 */
public class Encoder {

    private final Map<String, Integer> dictionary = new HashMap<>();

    public Encoder() {
        for (int i = 0; i<256; i++) {
            dictionary.put(Character.toString((char) i), i);
        }
    }

    /**
     * This is the main public method used for encoding. It tries to encode a file after you specify a path to a file,
     * as well as a desired name for the file after it has been encoded.
     *
     * @param path The absolute (not relative) path or location of the file.
     * @param encodedFileName The desired name to store the encoded file as, without file extension.
     */
    public void encodeFile(String path, String encodedFileName) {


        String decodedFileAsString = FileUtilities.fileToString(path);
        String encodedFileAsString = encodeString(decodedFileAsString);

        if (encodedFileAsString == null) {
            System.out.println("[ENCODER] Cannot compress an empty file!");
            return;
        }

        if (encodedFileName.equals("")) {
            encodedFileName = "compressed";
        }

        try (PrintStream out = new PrintStream(new FileOutputStream(encodedFileName + ".lzw"))) {
            out.print(encodedFileAsString);
            System.out.println("[ENCODER] Successfully compressed file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible for main encoding logic. It will take a plaintext string, in format such as "Hello",
     * and build the dictionary dynamically to convert the plaintext string into it's encoded form (i.e. "72 101 108 108 111").
     *
     * @param fileAsString The plaintext string to encode.
     * @return The encoded string in encoded string format.
     */
    private String encodeString(String fileAsString) {
        String s = "";
        String encodedFile = "";

        //dictionary.put("a", 0);
        //dictionary.put("b", 1);

        char[] fileAsCharArray = fileAsString.toCharArray();

        if (fileAsCharArray.length == 0) {
            return null;
        }

        for (char c : fileAsCharArray) {
            //System.out.println(s);
            String oldS = s;
            s = s + c;

            if (!dictionary.containsKey(s)) {
                dictionary.put(s, dictionary.size());
                if (encodedFile.isEmpty()) {
                    encodedFile += dictionary.get(oldS);
                } else {
                    encodedFile += " " + dictionary.get(oldS);
                }
                //s = s.replaceFirst(oldS, "");
                s = s.substring(oldS.length());
            }
        }

        if (encodedFile.isEmpty()) {
            encodedFile += dictionary.get(s);
        } else {
            encodedFile += " " + dictionary.get(s);
        }

        //System.out.println(dictionary);
        return encodedFile;
    }

}
