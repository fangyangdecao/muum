package com.yhy.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestJavaFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
/*        Button btn = new Button();
        btn.setText("你好啊，世界");
        btn.setOnAction(event -> {
            System.out.println("你好，世界!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);*/
//网格布局
        /*GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        //网格垂直间距
        grid.setHgap(10);
        //网格水平间距
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //新建场景
        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        //添加标题
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        //添加标签及文本框
        Label userName = new Label("用户名:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        //添加标签及密码框
        Label pw = new Label("密码:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        //添加提交按钮
        Button btn = new Button("登录");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        //提交文本提示
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(event -> {
            actiontarget.setFill(Color.FIREBRICK);
            actiontarget.setText("已经登录");
        });

        primaryStage.setTitle("JavaFX Welcome");*/
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 900, 755);
        primaryStage.setTitle("使用FXML");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
