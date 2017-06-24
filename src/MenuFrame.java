import javax.swing.*;

/**
 * Created by nikolai on 18.06.17.
 */
public class MenuFrame extends JFrame {
    private MenuPanel panel;

    public MenuFrame() {
        super("Huffman Algorithm");
        initComponents();
    }

    private void initComponents() {
        panel = new MenuPanel();
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(600, 600);
    }
}
