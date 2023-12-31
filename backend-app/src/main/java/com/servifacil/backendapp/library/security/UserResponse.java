package com.servifacil.backendapp.library.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int id;
    private String full_name;
    private String email;
    private int id_user_status;
    private int id_user_role;

    public static UserResponse fromUser(User user){
        return new UserResponse(
                user.getId(), user.getFull_name(), user.getEmail(), user.getId_user_status(), user.getId_user_role()
        );
    }

}
