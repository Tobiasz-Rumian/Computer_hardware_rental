package view.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by zekori on 30.04.17.
 */
public class TabPanel extends JPanel {
    private JLabel labelLoggin = new JLabel("Login");
    private JLabel labelPassword = new JLabel("Hasło");
    private JTextField loggin = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton button = new JButton("Zaloguj");
    public TabPanel() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("images/middle.gif");

        JComponent panelLogging = new JPanel(false);
        panelLogging.setLayout(new GridLayout(0,2));
        panelLogging.add(labelLoggin);
        panelLogging.add(loggin);
        loggin.setMaximumSize(new Dimension(50,30));
        loggin.setPreferredSize(new Dimension(50,30));
        loggin.setMinimumSize(new Dimension(50,30));
        panelLogging.add(labelPassword);
        panelLogging.add(password);
        panelLogging.add(button);
        tabbedPane.addTab("Logowanie", icon, panelLogging, "Panel logowania");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
