import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Student {
    String name;
    Set<String> completedCourses;

    public Student(String name) {
        this.name = name;
        this.completedCourses = new HashSet<>();
    }

    public void enrollInCourse(String course, List<String> prerequisites, Course courseObj) throws CourseFullException, PrerequisiteNotMetException {
        if (courseObj.isFull()) {
            throw new CourseFullException("Course " + course + " is full, unable to enroll.");
        }

        for (String prerequisite : prerequisites) {
            if (!completedCourses.contains(prerequisite)) {
                throw new PrerequisiteNotMetException("Complete " + prerequisite + " before enrolling in " + course + ".");
            }
        }

        courseObj.addStudent(this);
        System.out.println("Successfully enrolled in " + course);
    }

    public void completeCourse(String course) {
        completedCourses.add(course);
        System.out.println("Completed course: " + course);
    }
}

class Course {
    String name;
    int maxEnrollment;
    List<Student> students;

    public Course(String name, int maxEnrollment) {
        this.name = name;
        this.maxEnrollment = maxEnrollment;
        this.students = new ArrayList<>();
    }

    public boolean isFull() {
        return students.size() >= maxEnrollment;
    }

    public void addStudent(Student student) {
        if (!isFull()) {
            students.add(student);
        }
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
       
        Course coreJava = new Course("Core Java", 3);
        Course advancedJava = new Course("Advanced Java", 2);

        
        Student student1 = new Student("Alice");
        student1.completeCourse("Core Java");  // Alice completes Core Java first

        Student student2 = new Student("Bob");

   
        try {
            student1.enrollInCourse("Advanced Java", Arrays.asList("Core Java"), advancedJava);  // Success case
            student2.enrollInCourse("Advanced Java", Arrays.asList("Core Java"), advancedJava);  // Will throw PrerequisiteNotMetException
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        }

       
        student2.completeCourse("Core Java");

        try {
            student2.enrollInCourse("Advanced Java", Arrays.asList("Core Java"), advancedJava);  // Success case
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
