package entity.parallelTest;

public class Patent {
    private String ansId;
    private String patentName;
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String ansId) {
        this.ansId = ansId;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    @Override
    public String toString() {
        return "Patent{" +
                "ansId='" + ansId + '\'' +
                ", patentName='" + patentName + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
