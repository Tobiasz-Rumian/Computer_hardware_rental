package view;

import data.Product;
import data.User;
import enums.Role;
import rental.Rental;
import rental.Run;
import session.CurrentSession;
import view.panels.ButtonTabComponent;
import view.panels.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class View extends JFrame {

    private final JTabbedPane pane = new JTabbedPane();
    private Map<JButton, Role> menuButtons = new HashMap<>();
    private JButton addProduct = new JButton("Dodaj produkt");
    private JButton showAvailableProducts = new JButton("Wyświetl produkty dostępne do wypożyczenia");
    private JButton addClient = new JButton("Dodaj klienta");
    private JButton showClients = new JButton("Wyświetl listę klinektów");
    private JButton rentProduct = new JButton("Wypożycz produkt");
    private JButton showTransactions = new JButton("Wyświetl historię tranzakcji");
    private int activeTabsCounter = 0;

    {
        menuButtons.put(addProduct, Role.ADMIN);
        menuButtons.put(showAvailableProducts, Role.USER);
        menuButtons.put(addClient, Role.ADMIN);
        menuButtons.put(showClients, Role.SELLER);
        menuButtons.put(rentProduct, Role.SELLER);
        menuButtons.put(showTransactions, Role.SELLER);
    }

    public View(String title, Rental rental, Run run) {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(pane);

        this.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    run.saveRentalToFile();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
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
        });

        loggin(rental);
        addProduct.addActionListener(e -> {
            //Create and populate the panel.
            JPanel panel = new JPanel(new SpringLayout());
            JLabel nameJLabel = new JLabel("Nazwa produktu", JLabel.TRAILING);
            JLabel priceJLabel = new JLabel("Cena", JLabel.TRAILING);
            JTextField nameTextField = new JTextField(10);
            JTextField priceTextField = new JTextField(10);
            JButton buttonAddUser = new JButton("Dodaj");
            nameTextField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buttonAddUser.doClick();
                    }
                }
            });
            priceTextField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buttonAddUser.doClick();
                    }
                }
            });
            panel.add(nameJLabel);
            nameJLabel.setLabelFor(nameTextField);
            panel.add(nameTextField);
            panel.add(priceJLabel);
            priceJLabel.setLabelFor(priceTextField);
            panel.add(priceTextField);
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    2, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            component.add(buttonAddUser);
            pane.add("Dodaj produkt", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;
            buttonAddUser.addActionListener(b -> {
                try {
                    rental.addProduct(nameTextField.getText(), BigDecimal.valueOf(Double.parseDouble(priceTextField.getText())));
                    pane.remove(activeTabsCounter - 1);
                    activeTabsCounter--;
                } catch (IllegalArgumentException i) {
                    JOptionPane.showMessageDialog(this, i.getMessage(), "Błąd!", JOptionPane.ERROR_MESSAGE);
                }
            });
        });

        showAvailableProducts.addActionListener(e -> {
            long counter = rental.getProducts().values().stream().filter(Product::isAvailable).count();
            //Create and populate the panel.
            JPanel panel = new JPanel(new SpringLayout());
            JLabel a = new JLabel("Nazwa produktu", JLabel.TRAILING);
            panel.add(a);
            JLabel b = new JLabel("Cena", JLabel.TRAILING);
            panel.add(b);
            rental.getProducts().forEach((S, P) -> {
                if (P.isAvailable()) {
                    JLabel c = new JLabel(S, JLabel.TRAILING);
                    panel.add(c);
                    JLabel d = new JLabel(P.getPrice().toString(), JLabel.TRAILING);
                    panel.add(d);
                }

            });
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    (int) counter + 1, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            pane.add("Produkty dostępne do wypożyczenia", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;
        });

        addClient.addActionListener(e -> {
            //Create and populate the panel.
            JPanel panel = new JPanel(new SpringLayout());
            JLabel loginJLabel = new JLabel("Login", JLabel.TRAILING);
            JLabel roleJLabel = new JLabel("Rola", JLabel.TRAILING);
            JLabel passwordJLabel = new JLabel("Hasło", JLabel.TRAILING);
            JTextField loginTextField = new JTextField(10);
            JComboBox<Role> roleJComboBox = new JComboBox<>();
            roleJComboBox.addItem(Role.ADMIN);
            roleJComboBox.addItem(Role.SELLER);
            roleJComboBox.addItem(Role.USER);
            JPasswordField passwordField = new JPasswordField();
            JButton buttonAddUser = new JButton("Dodaj");
            panel.add(loginJLabel);
            loginJLabel.setLabelFor(loginTextField);
            panel.add(loginTextField);
            panel.add(roleJLabel);
            roleJLabel.setLabelFor(roleJComboBox);
            panel.add(roleJComboBox);
            panel.add(passwordJLabel);
            passwordJLabel.setLabelFor(passwordField);
            panel.add(passwordField);
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    3, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            component.add(buttonAddUser);
            pane.add("Dodaj użytkownika", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;
            loginTextField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buttonAddUser.doClick();
                    }
                }
            });
            passwordField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buttonAddUser.doClick();
                    }
                }
            });
            roleJComboBox.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        buttonAddUser.doClick();
                    }
                }
            });
            buttonAddUser.addActionListener(b -> {
                try {
                    rental.addUser(loginTextField.getText(), (Role) roleJComboBox.getSelectedItem(), passwordField.getText());
                    pane.remove(activeTabsCounter - 1);
                    activeTabsCounter--;
                } catch (IllegalArgumentException i) {
                    JOptionPane.showMessageDialog(this, i.getMessage(), "Błąd!", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
        showClients.addActionListener(e -> {
            //Create and populate the panel.
            JPanel panel = new JPanel(new SpringLayout());
            JLabel a = new JLabel("Nazwa użytkownika", JLabel.TRAILING);
            panel.add(a);
            a = new JLabel("Rola użytkownika", JLabel.TRAILING);
            panel.add(a);
            rental.getUsers().forEach((S, C) -> {
                JLabel b = new JLabel(S, JLabel.TRAILING);
                panel.add(b);
                b = new JLabel(C.getRole().toString(), JLabel.TRAILING);
                panel.add(b);

            });
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    rental.getUsers().size() + 1, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            pane.add("Użytkownicy", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;
        });
        rentProduct.addActionListener(e -> {
            if (rental.getProducts().size() == 0) {
                JOptionPane.showMessageDialog(this, "Brak produktów do wypożyczenia", "Błąd!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JPanel panel = new JPanel(new SpringLayout());
            JLabel userJLabel = new JLabel("Użytkownik", JLabel.TRAILING);
            JLabel productJLabel = new JLabel("Produkt", JLabel.TRAILING);
            JLabel daysJLabel = new JLabel("Na ile dni", JLabel.TRAILING);
            JComboBox<User> userJComboBox = new JComboBox<>();
            rental.getUsers().forEach((S, U) -> userJComboBox.addItem(U));
            JComboBox<Product> productJComboBox = new JComboBox<>();
            rental.getProducts().forEach((S, P) -> productJComboBox.addItem(P));
            JTextField daysTextField = new JTextField();
            JButton rent = new JButton("Wypożycz");

            panel.add(userJLabel);
            userJLabel.setLabelFor(userJComboBox);
            panel.add(userJComboBox);
            panel.add(productJLabel);
            productJLabel.setLabelFor(productJComboBox);
            panel.add(productJComboBox);
            panel.add(daysJLabel);
            daysJLabel.setLabelFor(daysTextField);
            panel.add(daysTextField);
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    3, 2, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            component.add(rent);
            pane.add("Wypożycz produkt", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;

            userJComboBox.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        rent.doClick();
                    }
                }
            });
            productJComboBox.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        rent.doClick();
                    }
                }
            });
            daysTextField.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                }

                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        rent.doClick();
                    }
                }
            });

            rent.addActionListener(b -> {
                try {
                    rental.rent((User) userJComboBox.getSelectedItem(), (Product) productJComboBox.getSelectedItem(), Integer.parseInt(daysTextField.getText()));
                    pane.remove(activeTabsCounter - 1);
                    activeTabsCounter--;
                } catch (IllegalArgumentException i) {
                    JOptionPane.showMessageDialog(this, i.getMessage(), "Błąd!", JOptionPane.ERROR_MESSAGE);
                }
            });
        });
        showTransactions.addActionListener(e -> {
            //Create and populate the panel.
            JPanel panel = new JPanel(new SpringLayout());
            JLabel a = new JLabel("Użytkownik", JLabel.TRAILING);
            panel.add(a);
            JLabel b = new JLabel("Produkt", JLabel.TRAILING);
            panel.add(b);
            JLabel c = new JLabel("Data wypożyczenia", JLabel.TRAILING);
            panel.add(c);
            JLabel d = new JLabel("Data zwrotu", JLabel.TRAILING);
            panel.add(d);
            rental.getHistory().forEach(T -> {
                JLabel x = new JLabel(T.getUser().toString(), JLabel.TRAILING);
                panel.add(x);
                JLabel f = new JLabel(T.getProduct().toString(), JLabel.TRAILING);
                panel.add(f);
                JLabel g = new JLabel(T.dateOfRentalToString(), JLabel.TRAILING);
                panel.add(g);
                JLabel h = new JLabel(T.dateOfReturnToString(), JLabel.TRAILING);
                panel.add(h);
            });
            //Lay out the panel.
            SpringUtilities.makeCompactGrid(panel,
                    rental.getHistory().size() + 1, 4, //rows, cols
                    6, 6,        //initX, initY
                    6, 6);       //xPad, yPad

            //Create and set up the window.
            panel.setOpaque(true);  //content panes must be opaque
            JComponent component = new JPanel(false);
            component.add(panel);
            pane.add("Historia tranzakcji", component);
            initTabComponent(activeTabsCounter);
            pane.setSelectedIndex(activeTabsCounter);
            activeTabsCounter++;
        });
    }

    public void decActiveTabsCounter() {
        activeTabsCounter--;
    }


    private void initTabComponent(int i) {
        pane.setTabComponentAt(i,
                new ButtonTabComponent(pane, this));
    }

    private void loggin(Rental rental) {
        JButton button = new JButton("Zaloguj");
        JPanel p = new JPanel(new SpringLayout());
        JLabel loginJLabel = new JLabel("Login", JLabel.TRAILING);
        JLabel passwordJLabel = new JLabel("Hasło", JLabel.TRAILING);
        p.add(loginJLabel);
        JTextField loginTextField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        loginJLabel.setLabelFor(loginTextField);
        p.add(loginTextField);
        p.add(passwordJLabel);
        passwordJLabel.setLabelFor(passwordField);
        p.add(passwordField);
        loginTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });
        passwordField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });
        SpringUtilities.makeCompactGrid(p,
                2, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        //Create and set up the window.
        p.setOpaque(true);  //content panes must be opaque
        JComponent panelLogging = new JPanel(false);
        panelLogging.add(p);
        panelLogging.add(button);
        pane.add("Logowanie", panelLogging);
        initTabComponent(activeTabsCounter);
        activeTabsCounter++;
        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        setVisible(true);

        button.addActionListener(e -> {
            if (rental.checkCredentials(loginTextField.getText(), passwordField.getText())) {
                CurrentSession.getInstance().setLoggedUser(rental.getUser(loginTextField.getText()));
                pane.remove(activeTabsCounter - 1);
                activeTabsCounter--;
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                switch (CurrentSession.getInstance().getLoggedUserRole()) {
                    case USER:
                        menuButtons.forEach((B, R) -> {
                            if (R == Role.USER) panel.add(B);
                        });
                        break;
                    case SELLER:
                        menuButtons.forEach((B, R) -> {
                            if (R != Role.ADMIN) panel.add(B);
                        });
                        break;
                    case ADMIN:
                        menuButtons.forEach((B, R) -> panel.add(B));
                }
                pane.add("Główne menu", panel);
                initTabComponent(activeTabsCounter);
                activeTabsCounter++;
            } else {
                loginTextField.setText("");
                passwordField.setText("");
                JOptionPane.showMessageDialog(this, "Błędny login lub hasło!", "Błąd!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

