package com.belhard.utils;

public class StringUtils {

	private StringUtils() {
	}

	public static boolean isContainsWrongStr(String str) {
		if(str.contains(Constants.DANGER_STR)){
			return true;
		}
        return false;
	}
	
	public static boolean isNotContainsWrongStr(String str) {
        return !isContainsWrongStr(str);
	}
	
	public static boolean isEmpty(String str) {
        if (str != null) {
            return Constants.EMPTY_STR.equals(str);
        }

        return true;
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static boolean isBlank(String str) {
        if (isNotEmpty(str)) {
            return Constants.EMPTY_STR.equals(str.trim());
        }

        return true;
	}
	
	public static boolean isNotBlank(String str) {
        return !isBlank(str);
	}
	
	public static String implodeArray(String[] inputArray) {
		StringBuilder builder = new StringBuilder();
	    
		for(int i=0;i<inputArray.length;i++){
		if(i != (inputArray.length - 1) ){
			builder.append(inputArray[i]+":");	
		}else{
			builder.append(inputArray[i]);
		}	
		}

	    return new String(builder);
	}

	public static boolean isStrPassed(String str) {
		if (isNotBlank(str)) {
            return isNotContainsWrongStr(str);
        }

        return false;
	}
	
	public static boolean checked(String str) {
		if (isStrPassed(str)) {
            return true;
        }

        return false;
	}
	
	public static void getSafe (String str) {
		if(str == null || str.contains(Constants.DANGER_STR)){
			str = Constants.EMPTY_STR;
		}
	}
}