import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MaFenetre fenetre = new MaFenetre();
        fenetre.setLocationRelativeTo(null);
        GamePanel g1 = new GamePanel();
        g1.startGameThread();
        fenetre.add(g1, BorderLayout.CENTER);
        g1.requestFocusInWindow();
        fenetre.pack();
        fenetre.setVisible(true);
    }
}
