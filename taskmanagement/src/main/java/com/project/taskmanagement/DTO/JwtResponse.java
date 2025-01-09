package com.project.taskmanagement.DTO;

import lombok.*;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
public class JwtResponse {

    private String jwtToken;
    private UserDTO user;

    public JwtResponse(String jwtToken, UserDTO user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public JwtResponse( ) {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    //create builder class for JwtResponse
    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    public static class JwtResponseBuilder {
        private String jwtToken;
        private UserDTO user;

        private JwtResponseBuilder() {
        }

        public JwtResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public JwtResponseBuilder user(UserDTO user) {
            this.user = user;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(jwtToken, user);
        }
    }
}

