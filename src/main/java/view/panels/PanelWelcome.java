package view.panels;

import settings.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class PanelWelcome extends JPanel {
    private JTextArea textArea = new JTextArea("Witaj w wypożyczalni\nZaloguj się!");
    private JTextField login = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton buttonAccept = new JButton("Zaloguj");
    private boolean visibility = false;
    {
        this.add(textArea);
        this.add(login);
        this.add(passwordField);
        this.add(buttonAccept);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAccept.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public PanelWelcome() {
        refresh();
    }

    public void changeVisibility() {
        this.setVisible(!visibility);
        visibility=!visibility;
    }
    public void refresh(){
        login.setMaximumSize(new Dimension(Settings.windowSize.width,30));
        login.setMinimumSize(new Dimension(Settings.windowSize.width,30));
        passwordField.setMaximumSize(new Dimension(Settings.windowSize.width,30));
        passwordField.setMinimumSize(new Dimension(Settings.windowSize.width,30));
        textArea.setMaximumSize(new Dimension(Settings.windowSize.width,80));
        textArea.setMinimumSize(new Dimension(Settings.windowSize.width,80));
    }
}
