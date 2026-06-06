package com.j2htmx.auto.demo.pages;

import com.j2htmx.auto.annotations.Node;
import com.j2htmx.auto.base.Component;
import com.j2htmx.auto.components.Button;
import com.j2htmx.auto.components.Div;
import com.j2htmx.auto.demo.beans.StudentBean;

import java.util.List;


@Node
public class MarkAttendance extends Component {

    public MarkAttendance() {
        setTag("div");
        setContent(new Div(new Button("Go Back").clazz("secondary").target("wrapper").post("login-user")).styleRaw("text-align:right;").margin(2).below(2));
        addStudentTable();
    }

    private void addStudentTable() {
        for (StudentBean st : getStudentBeansFromDb()) {
            addContent(new Student(st.getRollNo(), st.getName()));
        }
    }


    private List<StudentBean> getStudentBeansFromDb() {
        return List.of(
                new StudentBean(1, "Aditya"),
                new StudentBean(2, "Aarav"),
                new StudentBean(3, "Arjun"),
                new StudentBean(4, "Vihaan"),
                new StudentBean(5, "Reyansh"),
                new StudentBean(6, "Krishna"),
                new StudentBean(7, "Ishaan"),
                new StudentBean(8, "Atharv"),
                new StudentBean(9, "Sai"),
                new StudentBean(10, "Rohan"),
                new StudentBean(11, "Karthik"),
                new StudentBean(12, "Nikhil"),
                new StudentBean(13, "Rahul"),
                new StudentBean(14, "Yash"),
                new StudentBean(15, "Varun"),
                new StudentBean(16, "Aman"),
                new StudentBean(17, "Siddharth"),
                new StudentBean(18, "Aniket"),
                new StudentBean(19, "Harsh"),
                new StudentBean(20, "Akash"),
                new StudentBean(21, "Pranav"),
                new StudentBean(22, "Ritvik"),
                new StudentBean(23, "Manav"),
                new StudentBean(24, "Shivam"),
                new StudentBean(25, "Deepak"),
                new StudentBean(26, "Abhinav"),
                new StudentBean(27, "Suraj"),
                new StudentBean(28, "Tarun"),
                new StudentBean(29, "Neeraj"),
                new StudentBean(30, "Raj"),
                new StudentBean(31, "Piyush"),
                new StudentBean(32, "Mohit")
        );
    }
}
