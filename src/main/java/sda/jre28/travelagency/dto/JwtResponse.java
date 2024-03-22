package sda.jre28.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    private Long id;
    private String username;
    private String role;
    private String email;
    private String name;
    public JwtResponse(String token) {
        this.token = token;
    }

}
