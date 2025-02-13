public class Student extends Person {
    private String grade;
    private String major;
    private int examScore;

    public Student(String name, int age, String grade, String major, int examScore) {
        super(name, age);
        this.grade = grade;
        this.major = major;
        this.examScore = examScore;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", examScore=" + examScore +
                '}';
    }
}
