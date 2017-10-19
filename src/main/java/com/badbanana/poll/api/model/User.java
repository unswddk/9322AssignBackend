package com.badbanana.poll.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    @JsonIgnore
    private Long id;

    @NotNull
    @Size(min = 4, max = 30)
//    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    @Setter
    @Getter
    private String username;

    @NotNull
    @Column(name = "password", length = 70)
    @Setter
    @Getter
    @JsonIgnore
    private String password;



    @Setter
    @Getter
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notice> notices = new ArrayList<>();

    @NotNull
    @Size(min = 4, max = 30)
    @Setter
    @Getter
    private String nickname;

    @Setter
    @Getter
    private String avatar;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String userRoles = this.getRole();
        if (userRoles != null) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRoles);
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


    //    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(nickname = "user_roles",
//            joinColumns        = {@JoinColumn(nickname = "user_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(nickname = "role_id",  referencedColumnName = "id")}
//    )
    @Setter
    @Getter
    private String role;

    @Override
    @JsonIgnore
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;

        if (o instanceof User) {
            final User other = (User) o;
            return Objects.equal(getId(), other.getId())
                    && Objects.equal(getUsername(), other.getUsername())
                    && Objects.equal(getPassword(), other.getPassword())
                    && Objects.equal(getRole(), other.getRole())
                    && Objects.equal(isEnabled(), other.isEnabled());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUsername(), getPassword(), getRole(), isEnabled());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}