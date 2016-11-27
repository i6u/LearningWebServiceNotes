package zyr.learn.model;

/**
 * Created by zhouweitao on 2016/11/26.
 */
public class Classroom {
    private Integer cid;
    private String name;
    private int grade;

    public Classroom() {
    }

    public Classroom(Integer cid, String name, int grade) {
        this.cid = cid;
        this.name = name;
        this.grade = grade;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "cid=" + cid +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
