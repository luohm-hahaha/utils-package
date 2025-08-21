package com.lhm.utils.password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: luohm
 * @Date: 2025/8/21 - 16:35
 * @Description: com.lhm.utils.password
 * @version: 1.0
 * 校验密码是否强度足够
 * 密码强度要求：
 * 1. 密码长度至少为 8 位
 * 2. 密码包含至少一个大写字母、一个小写字母、一个数字和一个特殊字符
 * 3. 密码不能包含常见的英文单词以及易猜测的字母数字组合
 */
public class StrongPasswordUtils {

    /**
     * 检查密码强度
     * @param password 密码
     * @return 如果密码强度足够返回 true，否则返回 false
     */
    private static boolean isStrongPassword(String password) {
        // 检查密码长度是否至少为 8 位
        if (password.length() < 8) {
            return false;
        }

        // 检查密码是否包含数字、字母和特殊字符
        boolean hasDigit = false;
        boolean hasLetter = false;
        boolean hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            } else {
                hasSpecial = true;
            }
        }
        if (!hasDigit || !hasLetter || !hasSpecial) {
            return false;
        }

        // 检查密码是否包含常见英文单词以及易猜测的字母数字组合
        String[] commonPatterns = {"qaz", "wsx", "asd", "zxc", "qwe", "edc", "12", "123", "1234", "12345", "123456", "1234567", "12345678", "123456789", "1234567890", "666", "888", "111", "000"};
        String lowerCasePassword = password.toLowerCase();
        for (String pattern : commonPatterns) {
            if (lowerCasePassword.contains(pattern)) {
                return false;
            }
        }

        return true;
    }

    // 生成随机密码的方法
    private static String generateRandomPassword() {
        final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        final String DIGITS = "0123456789";
        final String SPECIAL_CHARS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

        final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();

        // 确保包含至少一个大写字母、一个小写字母、一个数字和一个特殊字符
        passwordChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        passwordChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        passwordChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        passwordChars.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // 生成剩余的字符
        while (passwordChars.size() < 8) {
            passwordChars.add(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // 打乱字符顺序
        java.util.Collections.shuffle(passwordChars, random);

        // 将字符列表转换为字符串
        return passwordChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {

        String password = "163@qx1";
        boolean isStrong = isStrongPassword(password);
        System.out.println("Is the password strong? " + isStrong);

        String s = generateRandomPassword();
        System.out.println("随机密码："+s);

    }
}
