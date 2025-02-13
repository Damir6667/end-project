public class Teacher extends Person {
    private String subject;
    private int experience; // Experience attribute

    public Teacher(String name, int age, String subject, int experience) {
        super(name, age);
        this.subject = subject;
        this.experience = experience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return super.toString() + ", Subject: " + subject + ", Experience: " + experience + " years";
    }
}

