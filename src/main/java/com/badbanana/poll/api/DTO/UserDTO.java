package com.badbanana.poll.api.DTO;

import com.badbanana.poll.api.model.Notice;
import com.badbanana.poll.api.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public final class UserDTO {

    private final String role; // "ROLE_USER" "ROLE_ADMIN"

    private final String email;
    @Size(min = 3, max = 100)
    private final String password;
    private final String name;
    private final String avatar;
    private final List<Notice> notices;



    public UserDTO(@JsonProperty("username") String email,
                   @JsonProperty("password") String password,
                   @JsonProperty("nickname") String name,
                   @JsonProperty("role") String role,
                   @JsonProperty("avatar") String avatar,
                   @JsonProperty("notice") List<Notice> notices
                   ) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.avatar = avatar;
        this.notices =notices;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getEncodedPassword() {
        return Optional.ofNullable(password).map(p -> new BCryptPasswordEncoder().encode(p));
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public String getRole() {
        return role;
    }

    public List<Notice> getNotices() { return notices; }

    public User toUser() {
        User user = new User();
        user.setUsername(email);
        user.setRole("");
        if (this.avatar != null) {
            user.setAvatar(this.avatar);
        } else {
            user.setAvatar("");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setNickname(name);
        user.setNotices(notices);
        return user;
    }

    public UsernamePasswordAuthenticationToken toAuthenticationToken() {

        return new UsernamePasswordAuthenticationToken(email, password, getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(() -> role);
    } // TODO login set role

}