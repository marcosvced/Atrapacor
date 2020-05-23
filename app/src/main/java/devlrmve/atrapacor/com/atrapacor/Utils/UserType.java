package devlrmve.atrapacor.com.atrapacor.Utils;

/**
 * Created by marcos_vicente on 22/01/16.
 */
public class UserType {
    public static String getUserType(String email) {
        String s = email;
        String[] parts = s.split("@"); //returns an array with the 2 parts
        String secondPart = parts[1]; //14.015
        String[] new_parts = secondPart.split(".com");
        String type_user = new_parts[0].toLowerCase();
        switch (type_user) {
            case "gmail":
                type_user = "Google";
                break;
            case "live":
                type_user = "Live";
                break;
            case "outlook":
                type_user = "Outlook.com";
                break;
            case "yahoo":
                type_user = "Yahoo";
                break;
        }
        return type_user;
    }
}
