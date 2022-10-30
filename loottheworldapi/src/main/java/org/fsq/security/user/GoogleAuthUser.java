package org.fsq.security.user;

import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class GoogleAuthUser {
    protected Map<String, Object> attributes;

    public GoogleAuthUser(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return (String) attributes.get("sub");
    }

    public String getName() {
        return (String) attributes.get("name");
    }

    public String getEmail() {
        return (String) attributes.get("email");
    }

    public String getImageUrl() {
        return (String) attributes.get("picture");
    }
}
