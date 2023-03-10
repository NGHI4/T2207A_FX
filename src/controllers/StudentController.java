package controllers;

import database.Database;
import entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StudentController implements Initializable {


    public TableView<Student> tbStudents;
    public TableColumn<Student, Integer> cId;
    public TableColumn<Student, String> cName;
    public TableColumn<Student, String> cEmail;
    public TableColumn<Student, Date> cBirthday;
    public TableColumn<Student, String> cGender;
    public TableColumn<Student, Integer> cClass_id;

    public void goToHome(ActionEvent actionEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../resources/home.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 600, 400));
    }

    public void goToCreate(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/students/create.fxml"));
        HomeController.rootStage.setScene(new Scene(root, 600, 400));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        cGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        cClass_id.setCellValueFactory(new PropertyValueFactory<>("class_id"));
        ObservableList<Student> list = FXCollections.observableArrayList();
        try {
            Database db = Database.getInstance();
            Statement stt = db.getStatement();
            String sql = "select * from sinhvien";
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date birthday = rs.getDate("birthday");
                String gender = rs.getString("gender");
                Integer classId = rs.getInt("class_id");
                Student s = new Student(id,name,email,birthday,gender,classId);
                list.add(s);
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        tbStudents.setItems(list);
    }
}