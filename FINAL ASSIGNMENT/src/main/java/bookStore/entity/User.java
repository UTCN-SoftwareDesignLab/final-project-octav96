package bookStore.entity;

import org.hibernate.engine.jdbc.BlobProxy;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class User {
    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private int age;
    private String address;
    private String role;
    private Boolean privacy;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Blob icon;

    public User(){

    }

    public User(String username, String password, int age, String address, String role, Boolean privacy, Blob icon) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.address = address;
        this.role = role;
        this.privacy = privacy;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Blob getIcon() {
        return icon;
    }

    public void setIcon(Blob icon) {
        this.icon = icon;
    }
}
