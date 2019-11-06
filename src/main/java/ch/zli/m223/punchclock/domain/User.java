package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    private String userName;
    private String passWord;

    @OneToMany
    private List<Entry> entries;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
