package ra.security.dto.response;

import lombok.Getter;
import lombok.Setter;
import ra.security.entity.Account;
@Getter
@Setter
public class JwtResponse {
    private final String type = "Bearer Token";
    private String accessToken;
    private Account account;
}
