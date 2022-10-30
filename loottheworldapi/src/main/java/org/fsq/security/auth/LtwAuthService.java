package org.fsq.security.auth;

import fsq.core.data.repository.user.UserRepository;
import fsq.core.entity.user.User;
import org.fsq.security.user.GoogleAuthUser;
import org.fsq.security.user.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class LtwAuthService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) throws Exception {
        GoogleAuthUser googleAuthUser = new GoogleAuthUser(oAuth2User.getAttributes());
        if(StringUtils.isEmpty(googleAuthUser.getEmail())) {
            throw new Exception("Email not found from OAuth2 provider");
        }

        Optional<User> mUser = userRepository.findByEmail(googleAuthUser.getEmail());
        User user;
        if(mUser.isPresent()) {
            user = mUser.get();
            user = updateExistingUser(user, googleAuthUser);
        } else {
            user = registerNewUser(oAuth2UserRequest, googleAuthUser);
        }

        return UserPrinciple.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, GoogleAuthUser oAuth2UserInfo) {
        User user = new User();

        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, GoogleAuthUser oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }
}
