package com.home.users.util;

import com.home.users.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserParser {
    public static User parse(String line) {

        Pattern userPattern = Pattern.compile("Id: (-?[0-9]+), Firstname: ([A-Za-z0-9]+)");

        Matcher userMatcher = userPattern.matcher(line);
        if (!userMatcher.find()) {
            throw new RuntimeException("Invalid line");
        }
        long a = Long.parseLong(userMatcher.group(1));
        return new User(a, userMatcher.group(2));
    }
}
