package services;

import models.Grade;
import models.School;
import models.Student;
import models.Teacher;

public class SchoolServices {
    School school;

    public SchoolServices(School school) {
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public void addStudent(Student student) {
        this.school.getStudents().add(student);
    }

    public void addTeacher(Teacher teacher) {
        this.school.getTeachers().add(teacher);
    }

    public void addGrade(Grade grade) {
        this.school.getGrades().add(grade);
    }
}
