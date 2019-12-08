package task15;

import java.util.Date;

public class User {
    private String name;
    private Date birthday;
    private long loginId;
    private String city;
    private String email;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long loginId) {
        this.loginId = loginId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "loginid:" + loginId + ", " +
                name + ", " +
                "родился:" + (birthday != null ? birthday : "не известно когда") + ", " +
                "город " + (city != null ? city : "не известен") + ", " +
                "почта: " + (email != null ? email : "отсутствует") + ", " +
                (description != null ? "примечание" + description : "примечаний нет");
    }
}
