package org.fsq.controller.user;

import fsq.core.data.repository.user.UserRepository;
import fsq.core.entity.user.User;
import org.fsq.security.ex.ResourceNotFoundException;
import org.fsq.security.user.CurrentUser;
import org.fsq.security.user.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/current")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrinciple userPrinciple) throws Exception {
        if (userPrinciple == null) {
            throw new ResourceNotFoundException("User");
        } else {
            return userRepository.findById(userPrinciple.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrinciple.getId()));
        }
    }
}
