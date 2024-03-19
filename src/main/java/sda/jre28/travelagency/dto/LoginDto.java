package sda.jre28.travelagency.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
