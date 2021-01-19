package me.lukecs.lzw;

/**
 * @author Luke Collishaw
 * @version v1.0
 * @since 2021-01-19
 */
public class LZW {

	/**
	 * Main method that drives program.
	 *
	 * @param args Arguments that should be in the form: [compress/decompress] [absolute path] [optional:file name without extension]
	 */
    public static void main(String[] args) {
    	final String ERROR = "Error: cannot understand command! Arg must be in the form \"[compress/decompress] [absolute path] [optional:file name wihout extension]\"";

    	if (args.length < 2 || args.length > 3) {
    		System.out.println(ERROR);
		}

		if (args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("compress")) {
			Encoder encoder = new Encoder();
			String path = args[1];
			String encodedFileName = "";
			if (args.length == 3) {
				encodedFileName = args[2];
			}
			encoder.encodeFile(path, encodedFileName);
		} else if (args[0].equalsIgnoreCase("d") || args[0].equalsIgnoreCase("decompress")) {
			Decoder decoder = new Decoder();
			String path = args[1];
			String decodedFileName = "";
			if (args.length == 3) {
				decodedFileName = args[2];
			}
			decoder.decodeFile(path, decodedFileName);
		} else {
			System.out.println(ERROR);
		}
    }

}
