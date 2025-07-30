package ra.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ra.security.security.principal.UserDetailsCus;

@RestController
@RequestMapping("/api/v1/manager")
public class HomeController {
    @GetMapping("/statics")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public String home(@AuthenticationPrincipal UserDetailsCus userDetailsCus) {
        return "Welcome to the Admin Home Page!";
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        // Logic to delete user by id
        // For demonstration, we will just return a success message
        return "User with ID " + id + " has been deleted successfully.";
    }
}
