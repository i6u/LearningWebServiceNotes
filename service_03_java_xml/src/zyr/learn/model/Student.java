package zyr.learn.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by zhouweitao on 2016/11/26.
 */

@XmlRootElement
public class Student {

    private Integer sid;
    private String username;
    private int age;
    private Classroom classroom;

    public Student() {
    }

    public Student(Integer sid, String username, int age, Classroom classroom) {
        this.sid = sid;
        this.username = username;
        this.age = age;
        this.classroom = classroom;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", classroom=" + classroom +
                '}';
    }
}
