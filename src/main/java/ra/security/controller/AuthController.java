package ra.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.security.dto.request.FormLogin;
import ra.security.dto.request.FormRegister;
import ra.security.dto.response.JwtResponse;
import ra.security.entity.Account;
import ra.security.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> login(@RequestBody FormLogin request){
        return new ResponseEntity<>(authenticationService.login(request), HttpStatus.OK);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<Account> register(@RequestBody FormRegister request) {
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }
}
