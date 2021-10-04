package ankypas.miniblog.Dao;

public class UserOverview {
    private String name;
    private String sex;
    private int age;
    private String profession;
    private String personalProfile;

    public UserOverview(String name, String sex, int age, String profession, String personalProfile) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.profession = profession;
        this.personalProfile = personalProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(String personalProfile) {
        this.personalProfile = personalProfile;
    }
}
