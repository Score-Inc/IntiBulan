package emu.lunarcore.util;

import java.io.File;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public static final Object EMPTY_OBJECT = new Object();
    public static final int[] EMPTY_ARRAY = new int[0];
    public static final String EMPTY_STRING = "";

    public static String bytesToHex(byte[] bytes) {
        if (bytes == null) return "";
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String capitalize(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static String lowerCaseFirstChar(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * Creates a string with the path to a file.
     * @param path The path to the file.
     * @return A path using the operating system's file separator.
     */
    public static String toFilePath(String path) {
        return path.replace("/", File.separator);
    }

    /**
     * Checks if a file exists on the file system.
     * @param path The path to the file.
     * @return True if the file exists, false otherwise.
     */
    public static boolean fileExists(String path) {
        return new File(path).exists();
    }

    /**
     * Creates a folder on the file system.
     * @param path The path to the folder.
     * @return True if the folder was created, false otherwise.
     */
    public static boolean createFolder(String path) {
        return new File(path).mkdirs();
    }

    public static long getCurrentSeconds() {
        return Math.floorDiv(System.currentTimeMillis(), 1000);
    }

    public static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    public static String generateRandomString(int length) {
        return bytesToHex(generateRandomBytes(length));
    }

    public static int parseSafeInt(String s) {
        if (s == null) {
            return 0;
        }
        
        int i = 0;

        try {
            i = Integer.parseInt(s);
        } catch (Exception e) {
            i = 0;
        }

        return i;
    }

    public static long parseSafeLong(String s) {
        if (s == null) {
            return 0;
        }
        
        long i = 0;

        try {
            i = Long.parseLong(s);
        } catch (Exception e) {
            i = 0;
        }

        return i;
    }

    public static double generateRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public static int randomRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static int randomElement(int[] array) {
        return array[ThreadLocalRandom.current().nextInt(0, array.length)];
    }

    public static <T> T randomElement(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

    /**
     * Base64 encodes a given byte array.
     * @param toEncode An array of bytes.
     * @return A base64 encoded string.
     */
    public static String base64Encode(byte[] toEncode) {
        return Base64.getEncoder().encodeToString(toEncode);
    }

    /**
     * Base64 decodes a given string.
     * @param toDecode A base64 encoded string.
     * @return An array of bytes.
     */
    public static byte[] base64Decode(String toDecode) {
        return Base64.getDecoder().decode(toDecode);
    }
}
