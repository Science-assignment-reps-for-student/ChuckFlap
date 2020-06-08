package kr.hs.dsm_scarfs.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoder {

    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean checkPassword(String dbPassword, String password) {
        return new BCryptPasswordEncoder().matches(password, dbPassword);
    }
}
