package sda.jre28.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }

}
