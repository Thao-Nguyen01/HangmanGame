import javax.swing.*;
import java.awt.*;

public class HangmanDrawing extends JPanel {
    private int wrongGuesses = 0;

    public void setWrongGuesses(int wrongGuesses) {
        this.wrongGuesses = wrongGuesses;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (wrongGuesses > 0) g.drawLine(50, 250, 150, 250);
        if (wrongGuesses > 1) g.drawLine(100, 250, 100, 50);
        if (wrongGuesses > 2) g.drawLine(100, 50, 200, 50);
        if (wrongGuesses > 3) g.drawLine(200, 50, 200, 80);
        if (wrongGuesses > 4) g.drawOval(180, 80, 40, 40);
        if (wrongGuesses > 5) g.drawLine(200, 120, 200, 180);
        if (wrongGuesses > 6) g.drawLine(200, 140, 170, 160);
        if (wrongGuesses > 7) g.drawLine(200, 140, 230, 160);
        if (wrongGuesses > 8) g.drawLine(200, 180, 170, 210);
        if (wrongGuesses > 9) g.drawLine(200, 180, 230, 210);
    }
}