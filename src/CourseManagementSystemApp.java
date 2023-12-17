import java.util.ArrayList;
import java.util.List;

// codSoft: JAVA DEVELOPMENT by Kevin Bou Merheb
// TASK 4: STUDENT COURSE REGISTRATION SYSTEM

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int filledSlots;
    private String schedule;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.filledSlots = 0; 
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFilledSlots() {
        return filledSlots;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean addStudent() {
        if (filledSlots < capacity) {
            filledSlots++;
            return true;
        }
        return false;
    }

    public void removeStudent() {
        if (filledSlots > 0) {
            filledSlots--;
        }
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nFilled Slots: " + filledSlots + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.addStudent()) {
            registeredCourses.add(course);
            System.out.println(name + " registered for the course: " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.removeStudent();
            System.out.println(name + " dropped the course: " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size() + "\n";
    }
}

class CourseManagementSystem {
    private List<Course> courses;
    private List<Student> students;

    public CourseManagementSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
}

public class CourseManagementSystemApp {
    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();

        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Fundamentals of programming", 50, "Mon-Wed-Fri, 9:00 AM");
        Course c2 = new Course("MAT201", "Calculus I", "Introduction to differential and integral calculus", 40, "Tue-Thu, 11:00 AM");

        cms.addCourse(c1);
        cms.addCourse(c2);

        Student s1 = new Student(1001, "Alice");
        Student s2 = new Student(1002, "Bob");

        cms.addStudent(s1);
        cms.addStudent(s2);

        cms.displayCourses();

        s1.registerCourse(c1);
        s2.registerCourse(c1);

        s1.registerCourse(c2);

        s1.dropCourse(c1);

        System.out.println("\nCourses registered by " + s1.getName() + ":");
        for (Course course : s1.getRegisteredCourses()) {
            System.out.println(course.getTitle());
        }
        System.out.println("\nCourses registered by " + s2.getName() + ":");
        for (Course course : s2.getRegisteredCourses()) {
            System.out.println(course.getTitle());
        }
    }
}
