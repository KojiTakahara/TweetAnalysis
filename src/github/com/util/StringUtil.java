package github.com.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {

    public static final String SJIS = "Shift-JIS";
    public static final String UTF8 = "UTF-8";
    public static final String JIS = "ISO-2022-JP";
    public static final String EUC = "EUC-JP";

    public static String decode(String src, String enc)
            throws UnsupportedEncodingException {
        String[] strings = src.split("%");
        byte[] bytes = new byte[src.length()];
        int p = 0;
        if (strings[0].length() == src.length()) {
            return src;
        }
        if (src.indexOf("%") != 0) {
            for (int i = 0; i < strings[0].length(); i++) {
                bytes[p++] = (byte) strings[0].charAt(i);
            }
        }
        for (int j = 1; j < strings.length; j++) {
            String part = strings[j];
            bytes[p++] = (byte) Integer.parseInt(part.substring(0, 2), 16);
            for (int i = 2; i < part.length(); i++) {
                bytes[p++] = (byte) part.charAt(i);
            }
        }
        return new String(bytes, 0, p, enc);
    }
}
