package com.minghao.wzlg.domain;

/**
 * 学生对象
 * @author chunyou
 * @date 2020-09-02
 */
public class Student {
    // 学号
    private String studentNumber;
    // 密码
    private String password;
    // 姓名
    private String name;
    // 班级
    private String aClass;
    // 手机号
    private String phone;
    // 课程表
    private CourseTable courseTable;
    // 成绩
    private SchoolReport schoolReport;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CourseTable getCourseTable() {
        return courseTable;
    }

    public void setCourseTable(CourseTable courseTable) {
        this.courseTable = courseTable;
    }

    public SchoolReport getSchoolReport() {
        if (this.schoolReport == null) {
            this.schoolReport = new SchoolReport();
        }
        return schoolReport;
    }

    public void setSchoolReport(SchoolReport schoolReport) {
        this.schoolReport = schoolReport;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", aClass='" + aClass + '\'' +
                ", phone='" + phone + '\'' +
                ", courseTable=" + courseTable +
                ", schoolReport=" + schoolReport +
                '}';
    }
}
