import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;
    public StudentManager() {
        students=new ArrayList<>();
    }
    public void addStudent(Student student){
        students.add(student);
    }

    public void deleteStudent(int index){
        if(index>=0&&index<students.size()){
            students.remove(index);
        }
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

    public double getAverageMarks(){
        if(students.isEmpty())
            return 0;

        double total=0;
        for(Student s : students){
            total +=s.getMarks();
        }
        return total/students.size();
    }

    public Student getHighestStudent(){
        if(students.isEmpty())
            return null;

        Student highest=students.get(0);

        for(Student s : students){

            if(s.getMarks()>highest.getMarks()){
                highest=s;
            }
        }

        return highest;
    }

    public Student getLowestStudent(){

        if(students.isEmpty())
            return null;

        Student lowest=students.get(0);

        for(Student s : students){
            if(s.getMarks()<lowest.getMarks()) {
                lowest=s;
            }
        }

        return lowest;
    }
}