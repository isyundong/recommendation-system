package com.isyundong.recommendation.toolbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil{

    private static final Pattern upperCaseLetterPattern = Pattern.compile("[A-Z]");
    private static final Pattern lowerCaseLetterPattern = Pattern.compile("_[a-z]");

    public static String initialLetterToLowerCase(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }


    public static String camelCaseToSnakeCase(String str) {
        Matcher matcher = upperCaseLetterPattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, STR."_\{matcher.group(0).toLowerCase()}");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String snakeCaseToCamelCase(String str) {
        str = str.toLowerCase();
        Matcher matcher = lowerCaseLetterPattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(0).toUpperCase().replace("_", ""));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


}