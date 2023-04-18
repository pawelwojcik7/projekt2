package is.components;

import javax.swing.*;

public class CommunicateTextArea extends JTextArea {


    public void setCommunicate(String message){
        this.setText(message);
        clearErrorField();
    }

    private void clearErrorField() {
        Timer timer = new Timer(5000, e -> {
            this.setText("");
            this.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

}
