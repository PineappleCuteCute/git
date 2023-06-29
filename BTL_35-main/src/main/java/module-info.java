module com.example.btl_35 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.sql;
    requires java.naming;
    requires java.desktop;
    requires javafx.media;
    requires javafx.web;

    opens com.example.btl_35 to javafx.fxml;
    opens com.example.btl_35.entity;
    opens com.example.btl_35.viewController to javafx.fxml;

    exports com.example.btl_35;
    exports com.example.btl_35.viewController to javafx.fxml;

}