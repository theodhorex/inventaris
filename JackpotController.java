package com.ukdw.prplbo.jackpot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.Random;

public class JackpotController {
    @FXML
    private Label random1;

    @FXML
    private Label random2;

    @FXML
    private Label random3;

    @FXML
    private Button spinBtn;

    @FXML
    private Label statusLabel;

    public void setUser(User user) {
       //digunakan untuk kirim data user dari form login ke form ini
    }

    @FXML
    public void handleSpin() {
        //=======================================================
        //BAGIAN INI ADALAH JIKA ANDA MENGINGINKAN BONUS ANIMASI, KODE INI DAPAT
        //MENJADI INSPIRASI.  NAMUN JIKA TIDAK BISA, LEBIH BAIK GUNAKAN RANDOM BIASA
        //jika anda ingin menggunakan animasi huruf bergerak random bisa gunakan Timeline

//        Timeline animation = new Timeline();
//
//        animation.getKeyFrames().add(new KeyFrame(Duration.millis(100), e -> {
////            masukan disini 3 label yang menampilkan random huruf
//            random1.setVisible(false);
//        }));

        //animation.setCycleCount(10); // 10 * 100ms = 1 second
        //animation.setOnFinished(event -> {
            //jika sudah finish animasinya bisa dilakukan semuanya di bagian ini

        //});
        //animation.play();
        //=======================================================
        //Jika anda tidak bisa membuat animasi biarkan bagian ini dikomen saja
        Game game = new Game();
        game.spin();
        random1.setText(String.valueOf(game.getA()));
        random2.setText(String.valueOf(game.getB()));
        random3.setText(String.valueOf(game.getC()));

        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setAttempt(user.increaseAttempts());
        user.setStatus(game.evaluateResult());

        DatabaseManager.saveUser(user);

        statusLabel.setText(game.evaluateResult());
    }
}

