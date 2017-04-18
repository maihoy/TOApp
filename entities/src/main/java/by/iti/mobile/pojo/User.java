package by.iti.mobile.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by j on 14.4.17.
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name="username", nullable=false)
    private String username;
    @Column(name="pass")
    private String pass;

    public User(Long id, String pass, String username) {
        super(id);
        this.pass = pass;
        this.username = username;
    }

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }

    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!getUsername().equals(user.getUsername())) return false;
        return getPass().equals(user.getPass());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPass().hashCode();
        return result;
    }
}
