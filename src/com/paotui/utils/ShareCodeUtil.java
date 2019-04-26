package com.paotui.utils;

import java.util.HashMap;
import java.util.Map;

public class ShareCodeUtil {
	 private static final char[] allChar = new char[]{'D', 'H', 'K', 'A', '4', 'S', 'G', '8', 'B', 'J', 'C', 'L', 'X', '2', 'P', 'E', '6', '5', '7', '9', 'M', '3', 'Y', 'R', 'F', 'T', 'U', 'V', 'W', 'N', 'Q', 'Z'};

	    private static final Map<String, Integer> charAndNum = new HashMap<String, Integer>();

	    private static final int codeSize = 6;


	    public static String getCodeByUid(int num) {
	        char[] buf = new char[32];
	        int charPos = 32;
	        while ((num / allChar.length) > 0) {
	            buf[--charPos] = allChar[(num % allChar.length)];
	            num /= allChar.length;
	        }
	        buf[--charPos] = allChar[(num % allChar.length)];
	        String str = new String(buf, charPos, (32 - charPos));
	        if (str.length() < codeSize) {
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < codeSize - str.length(); i++) {
	                sb.append(allChar[0]);
	            }
	            sb.append(str);
	            str = sb.toString();
	        }
	        return str;
	    }


	    public static int getUidByCode(String code) {
	        if (code == null) {
	            return 0;
	        }
	        char[] codet = code.toCharArray();
	        for (char a : codet) {
	            if (!(a >= '1' && a < '9' || a >= 'A' && a <= 'Z' || a >= 'a' && a <= 'z')) {
	                return 0;
	            }
	        }
	        code = code.toUpperCase();
	        char[] codes = code.toCharArray();
	        if (codes.length != codeSize) {
	            return 0;
	        }
	        int uid = 0;
	        for (int i = 0; i < codes.length; i++) {
	            uid += charAndNum.get(String.valueOf(codes[i])) * Math.pow(allChar.length, codes.length - i - 1);
	        }
	        return uid;
	    }

	    public static void main(String[] args) {
	        if (charAndNum.size() == 0) {
	            for (int i = 0; i < allChar.length; i++) {
	                charAndNum.put(String.valueOf(allChar[i]), i);
	            }
	        }
	        int uid = getUidByCode("ddddda");
	        System.out.println(uid);
	        System.out.println(getCodeByUid(10019391)); 
	    }
 
}
