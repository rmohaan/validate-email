package org.mohaan.validateEmail.validator;

import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Component
public class DomainValidator {

    public static boolean isValidDomain(String domain) {
        return hasMXRecord(domain);
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

