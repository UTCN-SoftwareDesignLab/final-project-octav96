package bookStore.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Blob;

public class UserDTO {

    @NotNull
    @Size(min=2, max=30, message = "USERNAME must have between 2 and 30 characters")
    private String username;
    @NotNull
    @Size(min=8, message = "Password must have minimum 8 characters")
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "PASSWORD must contain at least: one lowercase letter, one uppercase letter, one digit, one special character")
    private String password;
    @NotNull
    @Min(18)
    private int age;
    @NotNull
    private String address;
    @NotNull(message = "ROLE should not be null")
    private String role;
    @NotNull
    private Boolean privacy;

    private Blob icon;

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
