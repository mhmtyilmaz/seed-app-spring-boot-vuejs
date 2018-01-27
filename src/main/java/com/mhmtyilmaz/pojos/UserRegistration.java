package com.mhmtyilmaz.pojos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistration {

    private String username;
    private String password;
    private String passwordConfiguration;
}
