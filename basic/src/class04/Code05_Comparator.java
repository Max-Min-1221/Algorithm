package class04;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Code05_Comparator {

    public static class Student{
        private String name;
        private int id;
        private int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    public static void printStudents(Student[] students){
        for (Student student: students){
            System.out.println(student);
        }
    }

    // 按照年龄升序进行排序
    public static class AgeAscendingComparator implements Comparator<Student>{

        // 返回负数的时候，第一个参数排在前面
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    // 按照年龄升序进行排序，当年龄一样时，按照id升序进行排序
    public static class AgeAscendIdAscend implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return (o1.age == o2.age) ? (o1.id - o2.id) : (o1.age - o2.age);
        }
    }


    public static void main(String[] args) {
        Student s1 = new Student("A", 2, 20);
        Student s2 = new Student("B", 3, 21);
        Student s3 = new Student("C", 1, 22);
        Student s4 = new Student("D", 2, 21);

        Student[] students = new Student[]{s1, s2, s3, s4};

        // 按照年龄进行排序
        Arrays.sort(students, new AgeAscendingComparator());
        printStudents(students);

        System.out.println("===================");

        // 按照年龄升序进行排序，当年龄一样时，按照id升序进行排序
        Arrays.sort(students, new AgeAscendIdAscend());
        printStudents(students);
    }
}
