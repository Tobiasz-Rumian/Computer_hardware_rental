package view;

import rental.Rental;
import rental.Run;
import settings.Settings;
import view.panels.PanelWelcome;
import view.panels.TabPanel;

import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Tobiasz Rumian on 25.04.2017.
 */
public class View extends JFrame implements WindowListener {
    private Run run;
    private Rental rental;
    private TabPanel tabPanel = new TabPanel();

    public View(Run run){
        super("Wypożyczalnia");
        setSize(800, 800);
        setContentPane(tabPanel);
        this.addWindowListener(this);
        this.addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                Settings.windowSize=e.getComponent().getSize();
            }

            public void componentMoved(ComponentEvent e) {

            }

            public void componentShown(ComponentEvent e) {

            }

            public void componentHidden(ComponentEvent e) {

            }
        });
        this.run=run;
        this.rental=run.getRental();
        setVisible(true);
    }

    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent e) {
        try {
            run.saveRentalToFile();
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        dispose();
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {

    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }
}
