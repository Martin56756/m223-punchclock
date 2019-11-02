package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    private String userName;
    private String passWord;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;

    @OneToMany
    private List<Entry> entries;

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
}
