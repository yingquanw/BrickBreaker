package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer extends JLabel {

    private int elapsedTime = 0;
    private int ten_milliseconds = 0;
    private int seconds = 0;
    private int minutes = 0;
    private String ten_milliseconds_string;
    private String seconds_string;
    private String minutes_string;

    //Timer updates per 10 milliseconds
    private Timer timer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            elapsedTime = elapsedTime + 1;
            minutes = (elapsedTime/100) / 60;
            seconds = (elapsedTime/100) % 60;
            ten_milliseconds = elapsedTime % 100;
            ten_milliseconds_string = String.format("%02d", ten_milliseconds);
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            setText(minutes_string + ":" + seconds_string + ":" + ten_milliseconds_string);
        }
    });


    public GameTimer() {
        setText("00:00:00");
        setBounds(0, 0, 100, 80);
        setForeground(Color.green);
        setFont(new Font("Verdana", Font.PLAIN, 30));
        setOpaque(false);
    }

    public void startTimer() {
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }


}
