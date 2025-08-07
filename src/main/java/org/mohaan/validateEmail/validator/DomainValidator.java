package org.mohaan.validateEmail.validator;

import java.util.Hashtable;
import java.util.regex.*;

import javax.naming.directory.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class DomainValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", Pattern.CASE_INSENSITIVE);

    public static boolean isValidDomain(String email) {
        if (email == null || !EmailValidator.getInstance().isValid(email) || !EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        String domain = getDomain(email);
        return hasMXRecord(domain);
    }

    private static String getDomain(String email) {
        return email.substring(email.indexOf("@") + 1);
    }

    private static boolean hasMXRecord(String domain) {
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes(domain, new String[]{"MX"});
            return attrs != null && attrs.get("MX") != null;
        } catch (Exception e) {
            return false;
        }
    }
}

