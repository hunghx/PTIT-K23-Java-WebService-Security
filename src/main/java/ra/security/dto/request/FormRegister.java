package ra.security.dto.request;

import lombok.Data;

@Data
public class FormRegister {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String fullName;
}
