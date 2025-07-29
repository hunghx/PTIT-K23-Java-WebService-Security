package ra.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.security.dto.request.FormLogin;
import ra.security.dto.request.FormRegister;
import ra.security.entity.Account;
import ra.security.entity.Role;
import ra.security.entity.RoleName;
import ra.security.repository.IAccountRepository;
import ra.security.repository.IRoleRepository;
import ra.security.security.principal.UserDetailsCus;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    public Account login(FormLogin request){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );
        Authentication auth = authenticationManager.authenticate(authentication);
        String userDetails = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Authentication successful for user: {}", userDetails);
        Account account = accountRepository.loadUserByUsername(request.getUsername()).get();
        return account;
    }
    public Account register(FormRegister request){
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (accountRepository.existsByPhone(request.getPhone())) {
            throw new RuntimeException("Phone number already exists");
        }
        if (accountRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        Set<Role> set = new HashSet<>();
        set.add(roleRepository.findByRoleName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Role not found")));
        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setEmail(request.getEmail());
        account.setPhone(request.getPhone());
        account.setAddress(request.getAddress());
        account.setFullName(request.getFullName());
        account.setRoles(set);
        return accountRepository.save(account);

    }
}
