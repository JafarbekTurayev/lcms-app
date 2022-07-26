package com.example.lcmsapp.entity;

import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.entity.template.AbsEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class User extends AbsEntity implements UserDetails {

    //bot foydalanuvchi
    private String chatId;

    private String state;
    private Double lat;
    private Double lon;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

//    @Enumerated
//    @ElementCollection
//    private Set<AuthRole> authRole;

    private String fullName, phone;

    @Column(unique = true)
    private String email;
    private String code;
    private Double salary;

    private boolean active = true;

    @ManyToOne
    private Filial filial;

    @Enumerated
    private PositionType positionType;

    private String password;
    private boolean accountNonExpired = true; //accountni vaqti o'tmaganmi?
    private boolean accountNonLocked = true; //bloklanmaganmi?
    private boolean credentialsNonExpired = true; //parol o'znikimi
    private boolean enabled = true; //tizimga kimdir kirganda undan foydalanish huquqi

    //bu tizimdan foydalanuvchini yo permissionlari bo'ladi yoki rollari ro'yxati
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        for (Role role : this.roles) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    //ixtiyoriy uniq bo'ladigan ustunni berish kk login
    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String email, Set<Role> roles, String fullName, String phone, String password, boolean enabled) {
        this.email = email;
        this.roles = roles;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.enabled = enabled;
    }
}
