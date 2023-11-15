package tokyo.ronin.tg.datebot.models;

public class UserData {
    private String name;
    private String about;
    private int age;
    private String gender;

    public UserData() {
    }

    public UserData(String name, String about, int age, String gender) {
        this.name = name;
        this.about = about;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserData{" + "name='" + name + '\'' + ", about='" + about + '\'' + ", age='" + age + '\'' + ", gender='"
                + gender + '\'' + '}';
    }
}