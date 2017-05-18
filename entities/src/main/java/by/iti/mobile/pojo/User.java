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


    public User(Long id, String username, String pass) {
        super(id);
        this.username = username;
        this.pass = pass;

    }

    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;

    }

    public User() {
        super();
    }
    public User(User user){
        this(user.getUsername(),user.getPass());
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

        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        return getPass() != null ? getPass().equals(user.getPass()) : user.getPass() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPass() != null ? getPass().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
