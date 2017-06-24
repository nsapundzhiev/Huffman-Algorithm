import javax.swing.JFrame;

/**
 * Author Nikolai.
 */
public class MenuFrame extends JFrame {
    /**
     * MenuPanel instance.
     */
    private MenuPanel panel;

    /**
     * Constructor.
     */
    public MenuFrame() {
        super("Huffman Algorithm");
        initComponents();
    }

    /**
     * Init components and add them in frame.
     */
    private void initComponents() {
        panel = new MenuPanel();
        this.add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(600, 600);
    }
}
