package main;

import models.School;
import models.Student;
import models.Teacher;
import models.Person;
import services.SchoolServices;
import tools.Actions;

import java.util.Scanner;

public class Hmi {
    private static SchoolServices schoolServices;
    private static Scanner sc = new Scanner(System.in);

    public static void start() {
        School school = Hmi.createSchool();
        System.out.println("Welcome to " + " " + school.getName() + " in " +  school.getAddress());

        String action;
        do {
            System.out.print("What do you want to do?");
            action = sc.next();

            switch (action) {
                case "addStudent":
                    Hmi.addStudent();
                    break;
                case "addTeacher":
                    Hmi.addTeacher();
                    break;
                case "printAllStudents":
                    Hmi.printAllStudents();
                    break;
                case "printAllTeachers":
                    Hmi.printAllTeachers();
                    break;
                case "findStudentByName":
                    Hmi.printDataPersonByName(Actions.STUDENT);
                    break;
                case "findTeacherByName":
                    Hmi.printDataPersonByName(Actions.TEACHER);
                    break;
                case "removeStudent":
                    Hmi.removePerson(Actions.STUDENT);
                    break;
                case "removeTeacher":
                    Hmi.removePerson(Actions.TEACHER);
                    break;
            }
        }while(!action.equals("exit"));
    }

    private static School createSchool() {
        School school = new School();
        school.setName("BarmajaAN");
        school.setAddress("Tanger,Maroc");
        school.setPhoneNumber("0500000011");
        Hmi.schoolServices = new  SchoolServices(school);
        return school;
    }

    private static void addStudent() {
        Student student = new Student();
        Hmi.setDataPerson(student);
        Hmi.schoolServices.addStudent(student);
    }

    private static void printAllStudents() {
        for(Student student : Hmi.schoolServices.getSchool().getStudents()) {
            System.out.println(student.getData());
        }
    }

    private static void addTeacher() {
        Teacher teacher = new Teacher();
        Hmi.setDataPerson(teacher);
        Hmi.schoolServices.addTeacher(teacher);
    }

    private static void printAllTeachers() {
        for(Teacher teacher : Hmi.schoolServices.getSchool().getTeachers()) {
            System.out.println(teacher.getData());
        }
    }

    private static void setDataPerson(Person p) {
        System.out.print("Plz enter the name :");
        p.setName(sc.next());
        System.out.print("Plz enter the age :");
        p.setAge(sc.nextInt());

        if(p instanceof Teacher) {
            System.out.print("Plz enter the salary:");
            ((Teacher) p).setSalary(sc.nextFloat());
        }else if(p instanceof Student) {
            System.out.print("Plz enter the parent phone number :");
            ((Student) p).setParentPhoneNumber(sc.next());
        }
    }

    private static void printDataPersonByName(Actions action) {
        Person p = Hmi.findPersonByName(action);
        Hmi.printDataPerson(p);
    }

    private static void printDataPerson(Person person) {
        if(person != null) {
            System.out.println(person.getData());
        }else{
            System.out.println("Name not found");
        }
    }

    private static Person findPersonByName(Actions action) {
        System.out.print("Plz enter the name :");
        String name = sc.next();
        Person p;
        if(action == Actions.STUDENT) {
            p = Hmi.schoolServices.getStudentByName(name);
        }else {
            p = Hmi.schoolServices.getTeacherByName(name);
        }
        return p;
    }

    private static void removePerson(Actions action) {
        Person p = Hmi.findPersonByName(action);
        if(p instanceof Student) {
            Hmi.schoolServices.removeStudent((Student) p);
        }else{
            Hmi.schoolServices.removeTeacher((Teacher) p);
        }
    }
}
