package learn.openidconnect.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @GetMapping
    public OAuth2User getUser(@AuthenticationPrincipal OAuth2User principal) {
        // It's not a great idea to return a whole OAuth2User in an endpoint
        // since it might contain information you would rather not reveal to a browser client.
        return principal;
    }

    @GetMapping("/name")
    public Map<String, Object> getUserName(@AuthenticationPrincipal OAuth2User principal) {
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }

    // Alternative approach to getting the principal user that uses the security context...

//    @GetMapping("/context")
//    public ResponseEntity<?> getPrincipalFromSecurityContext() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.getPrincipal() instanceof OAuth2User) {
//            OAuth2User principal = (OAuth2User)authentication.getPrincipal();
//            return new ResponseEntity<>(principal, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
