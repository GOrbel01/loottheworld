package org.fsq.security.util;

import org.fsq.security.user.UserPrinciple;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserPrinciple getCurrentAuthenticatedUser() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
