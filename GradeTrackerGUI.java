import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GradeTrackerGUI extends JFrame {
    private JTextField nameField;
    private JTextField marksField;
    private JTable table;
    private DefaultTableModel model;
    private StudentManager manager;
    public GradeTrackerGUI(){
        manager=new StudentManager();

        setTitle("Student Grade Tracker");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel inputPanel=new JPanel(new GridLayout(3,2,10,10));

        inputPanel.add(new JLabel("Student Name"));
        nameField=new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Marks"));
        marksField = new JTextField();
        inputPanel.add(marksField);
        JButton addButton = new JButton("Add Student");
        JButton deleteButton = new JButton("Delete Student");
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Marks");
        model.addColumn("Grade");

        table = new JTable(model);
        JButton reportButton=new JButton("Generate Report");
        JTextArea reportArea=new JTextArea();
        reportArea.setEditable(false);
        add(inputPanel,BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(reportButton, BorderLayout.NORTH);
        southPanel.add(new JScrollPane(reportArea),BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e ->{
            try{
                String name=nameField.getText();
                double marks=Double.parseDouble(marksField.getText());
                Student student=new Student(name, marks);
                manager.addStudent(student);
                model.addRow(new Object[]{
                        student.getName(),
                        student.getMarks(),
                        student.getGrade()
                });
                nameField.setText("");
                marksField.setText("");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!"
                );
            }
        });
        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row!=-1){
                manager.deleteStudent(row);
                model.removeRow(row);
            }
        });
        reportButton.addActionListener(e -> {
            StringBuilder report=new StringBuilder();
            report.append("===== REPORT =====\n\n");
            report.append("Total Students : ")
                    .append(manager.getStudents().size())
                    .append("\n");
            report.append("Average Marks : ")
                    .append(String.format("%.2f",
                            manager.getAverageMarks()))
                    .append("\n");
            Student highest=manager.getHighestStudent();
            Student lowest=manager.getLowestStudent();
            if(highest!=null){
                report.append("\nHighest Score\n");
                report.append(highest.getName())
                        .append(" : ")
                        .append(highest.getMarks())
                        .append("\n");
            }
            if(lowest!=null){
                report.append("\nLowest Score\n");
                report.append(lowest.getName())
                        .append(" : ")
                        .append(lowest.getMarks())
                        .append("\n");
            }
            reportArea.setText(report.toString());
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {new GradeTrackerGUI().setVisible(true);
        });
    }
}