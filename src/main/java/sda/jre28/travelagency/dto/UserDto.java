package sda.jre28.travelagency.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String name;
    private String role;

    // Standard getters and setters
}

