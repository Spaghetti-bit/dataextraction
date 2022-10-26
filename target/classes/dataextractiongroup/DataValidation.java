package dataextractiongroup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation implements RegexUtility {

    /*PRIVATE FIELDS*/
    private String regexPattern = "";
    private Pattern pattern;
    private Matcher matcher;
    @Override
    public boolean isValidHumanName(String name)
    {
        regexPattern = "(?:(?:[A-Z][a-z]{1,4}[\\.][\\s])|(?:[A-Z][a-z]{1,4}[\\s]))?(?:[A-Z][a-z]+[\\s])(?:(?:[A-Z][\\s])|(?:[A-Z][\\.][\\s])|(?:[A-Z][a-z]+[\\s]))?(?:(?:[A-Z][a-z]+)|(?:[A-Z][\\'][A-Z][a-z]+))";
        pattern = Pattern.compile(regexPattern);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    @Override
    public boolean isValidSSN(String ssn)
    {
        regexPattern = "\\d{3}-\\d{2}-\\d{4}";
        pattern = Pattern.compile(regexPattern);
        matcher = pattern.matcher(ssn);
        return matcher.matches();
    }
    @Override
    public boolean isValidPhoneNumber(String phone)
    {
        regexPattern = "(?:(?:\\d{1,2}-)(?:\\d{3}-\\d{3}-\\d{4})|^\\d{3}-\\d{3}-\\d{4})";
        pattern = Pattern.compile(regexPattern);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    @Override
    public boolean isValidEmailAddress(String email)
    {
        regexPattern = "[a-zA-Z]\\w+[@][a-zA-Z]\\w{3,12}[.][a-z]{1,3}";
        pattern = Pattern.compile(regexPattern);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    @Override
    public String[] getHTMLTagsContents(String html, String tagName)
    {
        return new String[1];
    }
    @Override
    public String[] getHTMLLinkURL(String html)
    {
        regexPattern = "href=[\\'\"]?([^\\'\">]+)";
        matcher = pattern.matcher(html);
        return new String[1];
    }
    @Override
    public boolean validatePasswordComplexity(String password, int minLength, int minUpper, int minLower,
            int minNumeric, int minSymbols) {
        if (password.length() < minLength) return false;
        int upper = 0;
        int lower = 0;
        int symbol = 0;
        int numeric = 0;
        for (Character c : password.toCharArray()) {
            if (c >= 'A' && c <= 'Z')
            {
                upper++;
            } else if (c >= 'a' && c <= 'z')
            {
                lower++;
            } else if (c >= '0' && c >= '9')
            {
                numeric++;
            } else
            {
                symbol++;
            }
        }
        return (upper >= minUpper && lower >= minLower && numeric >= minNumeric && symbol >= minSymbols);
    }

}
