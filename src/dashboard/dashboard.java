package dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.*;

class dashboard {

    private JLabel timeLabel;
    private JLabel dateLabel;
    JTextArea receipTextArea;

    JLabel jlabelname1;
    JLabel jlabelname2;
    JLabel jlabelname3;
    JLabel jlabelname4;
    JLabel jlabelname5;
    JLabel jlabelname6;
    JLabel jlabelname7;
    JLabel jlabelname8;
    JLabel jlabelname9;
    JLabel jlabelname10;
    JLabel jlabelname11;
    JLabel jlabelname12;

    JSpinner jSpinner1, jSpinner2, jSpinner3, jSpinner4, jSpinner5, jSpinner6, jSpinner7, jSpinner8, jSpinner9, jSpinner10, jSpinner11, jSpinner12;

    JCheckBox jcheckbox1;
    JCheckBox jcheckbox2;
    JCheckBox jcheckbox3;
    JCheckBox jcheckbox4;
    JCheckBox jcheckbox5;
    JCheckBox jcheckbox6;
    JCheckBox jcheckbox7;
    JCheckBox jcheckbox8;
    JCheckBox jcheckbox9;
    JCheckBox jcheckbox10;
    JCheckBox jcheckbox11;
    JCheckBox jcheckbox12;

    int counter = 0;

    String itemName;
    double itemPrice;
    double quantity;

    double SubTotal = 0;
    double Tax = 0;
    double Total = 0;

    JTextField jtextfieldtotal, jtextfieldsubtotal, jtextfieldtax;

    dashboard() {
        // Main frame
        JFrame frame = new JFrame("Cafe Management App");
        frame.setSize(1270, 690);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanelmain = new JPanel();
        frame.add(jPanelmain);
        jPanelmain.setLayout(null);
        jPanelmain.setBounds(0, 0, 990, 530);
        jPanelmain.setBorder(BorderFactory.createLineBorder(Color.lightGray, 15));
        // Menu Panel

        JPanel jPanelmanuitems = new JPanel();
        jPanelmain.add(jPanelmanuitems);
        jPanelmanuitems.setLayout(null);
        jPanelmanuitems.setBounds(0, 0, 990, 50);
        jPanelmanuitems.setBackground(Color.lightGray);
        jPanelmanuitems.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        Font font = new Font("SanSerif", Font.BOLD, 20);
        JLabel menuitems = new JLabel("MENU ITEMS", SwingConstants.CENTER);
        jPanelmanuitems.add(menuitems);
        menuitems.setFont(font);
        menuitems.setBounds(80, 15, 150, 20);

        // clock and date

        timeLabel = new JLabel("", SwingConstants.CENTER);
        dateLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setBounds(660, 15, 150, 20);
        dateLabel.setBounds(800, 15, 180, 20);

        // Set up the font
        Font font6 = new Font("Arial", Font.BOLD, 24);
        timeLabel.setFont(font6);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add labels to the frame
        jPanelmanuitems.add(timeLabel);
        jPanelmanuitems.add(dateLabel);

        // Set up a timer to update the time every second
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        // Initial update
        updateClock();

        // menu cards

        // values for spinner
        int minValue = 0;
        int maxValue = 10;
        int initialValue = 0;

        // Menu card 1

        JPanel menucard1 = new JPanel();
        jPanelmain.add(menucard1);
        menucard1.setLayout(null);
        menucard1.setBounds(40, 90, 140, 180);
        menucard1.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img1 = ImageIO.read(new File("src/images/tea.jpeg"));
            img1 = img1.getScaledInstance(138, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img1);
            JLabel jlableimage = new JLabel(icon);
            jlableimage.setBounds(1, 1, 138, 80);
            menucard1.add(jlableimage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname1 = new JLabel("Tea", SwingConstants.CENTER);
        menucard1.add(jlabelname1);
        jlabelname1.setFont(font);
        jlabelname1.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep1 = new JLabel("Price:", SwingConstants.CENTER);
        menucard1.add(jlabelpricep1);
        jlabelpricep1.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue1 = new JLabel();
        menucard1.add(jlabelpricevalue1);
        jlabelpricevalue1.setText("80");
        jlabelpricevalue1.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity1 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard1.add(jlabelquantity1);
        jlabelquantity1.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel1 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner1 = new JSpinner(spinnerModel1);
        menucard1.add(jSpinner1);
        jSpinner1.setBorder(null);
        jSpinner1.setBounds(70, 133, 55, 23);

        JLabel jlabelpurchase1 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard1.add(jlabelpurchase1);
        jlabelpurchase1.setBounds(0, 156, 70, 23);

        jcheckbox1 = new JCheckBox();
        menucard1.add(jcheckbox1);
        jcheckbox1.setBounds(70, 156, 68, 22);
        jcheckbox1.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner1.getValue().equals(0) && jcheckbox1.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner1.getValue().equals(0))) {
                    itemName = jlabelname1.getText() + "\t\t";
                    quantity = Double.parseDouble(jSpinner1.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue1.getText());
                    checkboxCounter();
                    jcheckbox1.setEnabled(false);
                    jSpinner1.setEnabled(false);

                }
            }
        });

        // menu card 2

        JPanel menucard2 = new JPanel();
        jPanelmain.add(menucard2);
        menucard2.setLayout(null);
        menucard2.setBounds(193, 90, 140, 180);
        menucard2.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img2 = ImageIO.read(new File("src/images/coffee.jpeg"));
            img2 = img2.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img2);
            JLabel jlableimage2 = new JLabel(icon);
            jlableimage2.setBounds(0, 0, 140, 80);
            menucard2.add(jlableimage2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname2 = new JLabel("Coffee", SwingConstants.CENTER);
        menucard2.add(jlabelname2);
        jlabelname2.setFont(font);
        jlabelname2.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep2 = new JLabel("Price:", SwingConstants.CENTER);
        menucard2.add(jlabelpricep2);
        jlabelpricep2.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue2 = new JLabel();
        menucard2.add(jlabelpricevalue2);
        jlabelpricevalue2.setText("120");
        jlabelpricevalue2.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity2 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard2.add(jlabelquantity2);
        jlabelquantity2.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel2 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner2 = new JSpinner(spinnerModel2);
        menucard2.add(jSpinner2);
        jSpinner2.setBounds(70, 133, 55, 23);
        jSpinner2.setBorder(null);

        JLabel jlabelpurchase2 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard2.add(jlabelpurchase2);
        jlabelpurchase2.setBounds(0, 156, 70, 23);

        jcheckbox2 = new JCheckBox();
        menucard2.add(jcheckbox2);
        jcheckbox2.setBounds(70, 156, 68, 22);

        jcheckbox2.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner2.getValue().equals(0) && jcheckbox2.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner2.getValue().equals(0))) {
                    itemName = jlabelname2.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner2.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue2.getText());
                    checkboxCounter();
                    jcheckbox2.setEnabled(false);
                    jSpinner2.setEnabled(false);
                }
            }
        });

        // menu card 3

        JPanel menucard3 = new JPanel();
        jPanelmain.add(menucard3);
        menucard3.setLayout(null);
        menucard3.setBounds(348, 90, 140, 180);
        menucard3.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img3 = ImageIO.read(new File("src/images/tandoorichai.jpeg"));
            img3 = img3.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img3);
            JLabel jlableimage3 = new JLabel(icon);
            jlableimage3.setBounds(0, 0, 140, 80);
            menucard3.add(jlableimage3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname3 = new JLabel("Tandoori", SwingConstants.CENTER);
        menucard3.add(jlabelname3);
        jlabelname3.setFont(font);
        jlabelname3.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep3 = new JLabel("Price:", SwingConstants.CENTER);
        menucard3.add(jlabelpricep3);
        jlabelpricep3.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue3 = new JLabel();
        menucard3.add(jlabelpricevalue3);
        jlabelpricevalue3.setText("150");
        jlabelpricevalue3.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity3 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard3.add(jlabelquantity3);
        jlabelquantity3.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel3 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner3 = new JSpinner(spinnerModel3);
        menucard3.add(jSpinner3);
        jSpinner3.setBounds(70, 133, 55, 23);
        jSpinner3.setBorder(null);

        JLabel jlabelpurchase3 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard3.add(jlabelpurchase3);
        jlabelpurchase3.setBounds(0, 156, 70, 23);

        jcheckbox3 = new JCheckBox();
        menucard3.add(jcheckbox3);
        jcheckbox3.setBounds(70, 156, 68, 22);

        jcheckbox3.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner3.getValue().equals(0) && jcheckbox3.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner3.getValue().equals(0))) {
                    itemName = jlabelname3.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner3.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue3.getText());
                    checkboxCounter();
                    jcheckbox3.setEnabled(false);
                    jSpinner3.setEnabled(false);
                }
            }
        });

        // menu card 4

        JPanel menucard4 = new JPanel();
        jPanelmain.add(menucard4);
        menucard4.setLayout(null);
        menucard4.setBounds(502, 90, 140, 180);
        menucard4.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img4 = ImageIO.read(new File("src/images/biryani.jpeg"));
            img4 = img4.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img4);
            JLabel jlableimage4 = new JLabel(icon);
            jlableimage4.setBounds(0, 0, 140, 80);
            menucard4.add(jlableimage4);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname4 = new JLabel("Biryani", SwingConstants.CENTER);
        menucard4.add(jlabelname4);
        jlabelname4.setFont(font);
        jlabelname4.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep4 = new JLabel("Price:", SwingConstants.CENTER);
        menucard4.add(jlabelpricep4);
        jlabelpricep4.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue4 = new JLabel();
        menucard4.add(jlabelpricevalue4);
        jlabelpricevalue4.setText("250");
        jlabelpricevalue4.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity4 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard4.add(jlabelquantity4);
        jlabelquantity4.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel4 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner4 = new JSpinner(spinnerModel4);
        menucard4.add(jSpinner4);
        jSpinner4.setBounds(70, 133, 55, 23);
        jSpinner4.setBorder(null);

        JLabel jlabelpurchase4 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard4.add(jlabelpurchase4);
        jlabelpurchase4.setBounds(0, 156, 70, 23);

        jcheckbox4 = new JCheckBox();
        menucard4.add(jcheckbox4);
        jcheckbox4.setBounds(70, 156, 68, 22);

        jcheckbox4.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner4.getValue().equals(0) && jcheckbox4.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner4.getValue().equals(0))) {
                    itemName = jlabelname4.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner4.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue4.getText());
                    checkboxCounter();
                    jcheckbox4.setEnabled(false);
                    jSpinner4.setEnabled(false);
                }
            }
        });

        // menu card 5

        JPanel menucard5 = new JPanel();
        jPanelmain.add(menucard5);
        menucard5.setLayout(null);
        menucard5.setBounds(656, 90, 140, 180);
        menucard5.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img5 = ImageIO.read(new File("src/images/kabab.jpeg"));
            img5 = img5.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img5);
            JLabel jlableimage5 = new JLabel(icon);
            jlableimage5.setBounds(0, 0, 140, 80);
            menucard5.add(jlableimage5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname5 = new JLabel("Kabab", SwingConstants.CENTER);
        menucard5.add(jlabelname5);
        jlabelname5.setFont(font);
        jlabelname5.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep5 = new JLabel("Price:", SwingConstants.CENTER);
        menucard5.add(jlabelpricep5);
        jlabelpricep5.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue5 = new JLabel();
        menucard5.add(jlabelpricevalue5);
        jlabelpricevalue5.setText("90");
        jlabelpricevalue5.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity5 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard5.add(jlabelquantity5);
        jlabelquantity5.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel5 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner5 = new JSpinner(spinnerModel5);
        menucard5.add(jSpinner5);
        jSpinner5.setBounds(70, 133, 55, 23);
        jSpinner5.setBorder(null);

        JLabel jlabelpurchase5 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard5.add(jlabelpurchase5);
        jlabelpurchase5.setBounds(0, 156, 70, 23);

        jcheckbox5 = new JCheckBox();
        menucard5.add(jcheckbox5);
        jcheckbox5.setBounds(70, 156, 68, 22);

        jcheckbox5.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner5.getValue().equals(0) && jcheckbox5.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner5.getValue().equals(0))) {
                    itemName = jlabelname5.getText() + "\t\t";
                    quantity = Double.parseDouble(jSpinner5.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue5.getText());
                    checkboxCounter();
                    jcheckbox5.setEnabled(false);
                    jSpinner5.setEnabled(false);
                }
            }
        });

        // menu card 6

        JPanel menucard6 = new JPanel();
        jPanelmain.add(menucard6);
        menucard6.setLayout(null);
        menucard6.setBounds(809, 90, 140, 180);
        menucard6.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img6 = ImageIO.read(new File("src/images/greentea.jpeg"));
            img6 = img6.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img6);
            JLabel jlableimage6 = new JLabel(icon);
            jlableimage6.setBounds(0, 0, 140, 80);
            menucard6.add(jlableimage6);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname6 = new JLabel("Green Tea", SwingConstants.CENTER);
        menucard6.add(jlabelname6);
        jlabelname6.setFont(font);
        jlabelname6.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep6 = new JLabel("Price:", SwingConstants.CENTER);
        menucard6.add(jlabelpricep6);
        jlabelpricep6.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue6 = new JLabel();
        menucard6.add(jlabelpricevalue6);
        jlabelpricevalue6.setText("50");
        jlabelpricevalue6.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity6 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard6.add(jlabelquantity6);
        jlabelquantity6.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel6 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner6 = new JSpinner(spinnerModel6);
        menucard6.add(jSpinner6);
        jSpinner6.setBounds(70, 133, 55, 23);
        jSpinner6.setBorder(null);

        JLabel jlabelpurchase6 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard6.add(jlabelpurchase6);
        jlabelpurchase6.setBounds(0, 156, 70, 23);

        jcheckbox6 = new JCheckBox();
        menucard6.add(jcheckbox6);
        jcheckbox6.setBounds(70, 156, 68, 22);

        jcheckbox6.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner6.getValue().equals(0) && jcheckbox6.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner6.getValue().equals(0))) {
                    itemName = jlabelname6.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner6.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue6.getText());
                    checkboxCounter();
                    jcheckbox6.setEnabled(false);
                    jSpinner6.setEnabled(false);
                }
            }
        });

        // Menu card 7

        JPanel menucard7 = new JPanel();
        jPanelmain.add(menucard7);
        menucard7.setLayout(null);
        menucard7.setBounds(40, 300, 140, 180);
        menucard7.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img7 = ImageIO.read(new File("src/images/chikenburger.jpeg"));
            img7 = img7.getScaledInstance(138, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img7);
            JLabel jlableimage = new JLabel(icon);
            jlableimage.setBounds(1, 1, 138, 80);
            menucard7.add(jlableimage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Font font2 = new Font("SanSerif", Font.BOLD, 15);
        JLabel jlabelname7 = new JLabel("Chicken Burger", SwingConstants.CENTER);
        menucard7.add(jlabelname7);
        jlabelname7.setFont(font2);
        jlabelname7.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep7 = new JLabel("Price:", SwingConstants.CENTER);
        menucard7.add(jlabelpricep7);
        jlabelpricep7.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue7 = new JLabel();
        menucard7.add(jlabelpricevalue7);
        jlabelpricevalue7.setText("300");
        jlabelpricevalue7.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity7 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard7.add(jlabelquantity7);
        jlabelquantity7.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel7 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner7 = new JSpinner(spinnerModel7);
        menucard7.add(jSpinner7);
        jSpinner7.setBounds(70, 133, 55, 23);
        jSpinner7.setBorder(null);

        JLabel jlabelpurchase7 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard7.add(jlabelpurchase7);
        jlabelpurchase7.setBounds(0, 156, 70, 23);

        jcheckbox7 = new JCheckBox();
        menucard7.add(jcheckbox7);
        jcheckbox7.setBounds(70, 156, 68, 22);

        jcheckbox7.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner7.getValue().equals(0) && jcheckbox7.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner7.getValue().equals(0))) {
                    itemName = jlabelname7.getText();
                    quantity = Double.parseDouble(jSpinner7.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue7.getText());
                    checkboxCounter();
                    jcheckbox7.setEnabled(false);
                    jSpinner7.setEnabled(false);
                }
            }
        });

        // menu card 8

        JPanel menucard8 = new JPanel();
        jPanelmain.add(menucard8);
        menucard8.setLayout(null);
        menucard8.setBounds(193, 300, 140, 180);
        menucard8.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img8 = ImageIO.read(new File("src/images/chikenpizza.jpeg"));
            img8 = img8.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img8);
            JLabel jlableimage8 = new JLabel(icon);
            jlableimage8.setBounds(0, 0, 140, 80);
            menucard8.add(jlableimage8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname8 = new JLabel("Pizza\t", SwingConstants.CENTER);
        menucard8.add(jlabelname8);
        jlabelname8.setFont(font);
        jlabelname8.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep8 = new JLabel("Price:", SwingConstants.CENTER);
        menucard8.add(jlabelpricep8);
        jlabelpricep8.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue8 = new JLabel();
        menucard8.add(jlabelpricevalue8);
        jlabelpricevalue8.setText("1200");
        jlabelpricevalue8.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity8 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard8.add(jlabelquantity8);
        jlabelquantity8.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel8 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner8 = new JSpinner(spinnerModel8);
        menucard8.add(jSpinner8);
        jSpinner8.setBounds(70, 133, 55, 23);
        jSpinner8.setBorder(null);

        JLabel jlabelpurchase8 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard8.add(jlabelpurchase8);
        jlabelpurchase8.setBounds(0, 156, 70, 23);

        jcheckbox8 = new JCheckBox();
        menucard8.add(jcheckbox8);
        jcheckbox8.setBounds(70, 156, 68, 22);

        jcheckbox8.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner8.getValue().equals(0) && jcheckbox8.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner8.getValue().equals(0))) {
                    itemName = jlabelname8.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner8.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue8.getText());
                    checkboxCounter();
                    jcheckbox8.setEnabled(false);
                    jSpinner8.setEnabled(false);
                }
            }
        });

        // menu card 9

        JPanel menucard9 = new JPanel();
        jPanelmain.add(menucard9);
        menucard9.setLayout(null);
        menucard9.setBounds(348, 300, 140, 180);
        menucard9.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img9 = ImageIO.read(new File("src/images/rollpratha.jpeg"));
            img9 = img9.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img9);
            JLabel jlableimage9 = new JLabel(icon);
            jlableimage9.setBounds(0, 0, 140, 80);
            menucard9.add(jlableimage9);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname9 = new JLabel("Roll Paratha", SwingConstants.CENTER);
        menucard9.add(jlabelname9);
        jlabelname9.setFont(font);
        jlabelname9.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep9 = new JLabel("Price:", SwingConstants.CENTER);
        menucard9.add(jlabelpricep9);
        jlabelpricep9.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue9 = new JLabel();
        menucard9.add(jlabelpricevalue9);
        jlabelpricevalue9.setText("150");
        jlabelpricevalue9.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity9 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard9.add(jlabelquantity9);
        jlabelquantity9.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel9 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner9 = new JSpinner(spinnerModel9);
        menucard9.add(jSpinner9);
        jSpinner9.setBounds(70, 133, 55, 23);
        jSpinner9.setBorder(null);

        JLabel jlabelpurchase9 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard9.add(jlabelpurchase9);
        jlabelpurchase9.setBounds(0, 156, 70, 23);

        jcheckbox9 = new JCheckBox();
        menucard9.add(jcheckbox9);
        jcheckbox9.setBounds(70, 156, 68, 22);

        jcheckbox9.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner9.getValue().equals(0) && jcheckbox9.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner9.getValue().equals(0))) {
                    itemName = jlabelname9.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner9.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue9.getText());
                    checkboxCounter();
                    jcheckbox9.setEnabled(false);
                    jSpinner9.setEnabled(false);
                }
            }
        });

        // menu card 10

        JPanel menucard10 = new JPanel();
        jPanelmain.add(menucard10);
        menucard10.setLayout(null);
        menucard10.setBounds(502, 300, 140, 180);
        menucard10.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img10 = ImageIO.read(new File("src/images/seekhkabab.jpeg"));
            img10 = img10.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img10);
            JLabel jlableimage10 = new JLabel(icon);
            jlableimage10.setBounds(0, 0, 140, 80);
            menucard10.add(jlableimage10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname10 = new JLabel("Seekh Kabab", SwingConstants.CENTER);
        menucard10.add(jlabelname10);
        jlabelname10.setFont(font);
        jlabelname10.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep10 = new JLabel("Price:", SwingConstants.CENTER);
        menucard10.add(jlabelpricep4);
        jlabelpricep10.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue10 = new JLabel();
        menucard10.add(jlabelpricevalue10);
        jlabelpricevalue10.setText("100");
        jlabelpricevalue10.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity10 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard10.add(jlabelquantity10);
        jlabelquantity10.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel10 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner10 = new JSpinner(spinnerModel10);
        menucard10.add(jSpinner10);
        jSpinner10.setBounds(70, 133, 55, 23);
        jSpinner10.setBorder(null);

        JLabel jlabelpurchase10 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard10.add(jlabelpurchase10);
        jlabelpurchase10.setBounds(0, 156, 70, 23);

        jcheckbox10 = new JCheckBox();
        menucard10.add(jcheckbox10);
        jcheckbox10.setBounds(70, 156, 68, 22);

        jcheckbox10.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (jSpinner10.getValue().equals(0) && jcheckbox10.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }

                if (!(jSpinner10.getValue().equals(0))) {
                    itemName = jlabelname10.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner10.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue10.getText());
                    checkboxCounter();
                    jcheckbox10.setEnabled(false);
                    jSpinner10.setEnabled(false);
                }
            }
        });

        // menu card 11

        JPanel menucard11 = new JPanel();
        jPanelmain.add(menucard11);
        menucard11.setLayout(null);
        menucard11.setBounds(656, 300, 140, 180);
        menucard11.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img11 = ImageIO.read(new File("src/images/zingerburger.jpeg"));
            img11 = img11.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img11);
            JLabel jlableimage11 = new JLabel(icon);
            jlableimage11.setBounds(0, 0, 140, 80);
            menucard11.add(jlableimage11);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname11 = new JLabel("Zinger Burger", SwingConstants.CENTER);
        menucard11.add(jlabelname11);
        jlabelname11.setFont(font);
        jlabelname11.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep11 = new JLabel("Price:", SwingConstants.CENTER);
        menucard11.add(jlabelpricep11);
        jlabelpricep11.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue11 = new JLabel();
        menucard11.add(jlabelpricevalue11);
        jlabelpricevalue11.setText("350");
        jlabelpricevalue11.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity11 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard11.add(jlabelquantity11);
        jlabelquantity11.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel11 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner11 = new JSpinner(spinnerModel11);
        menucard11.add(jSpinner11);
        jSpinner11.setBounds(70, 133, 55, 23);
        jSpinner11.setBorder(null);

        JLabel jlabelpurchase11 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard11.add(jlabelpurchase11);
        jlabelpurchase11.setBounds(0, 156, 70, 23);

        jcheckbox11 = new JCheckBox();
        menucard11.add(jcheckbox11);
        jcheckbox11.setBounds(70, 156, 68, 22);

        jcheckbox11.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner11.getValue().equals(0) && jcheckbox11.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner11.getValue().equals(0))) {
                    itemName = jlabelname11.getText() + "\t";
                    quantity = Double.parseDouble(jSpinner11.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue11.getText());
                    checkboxCounter();
                    jcheckbox11.setEnabled(false);
                    jSpinner11.setEnabled(false);
                }
            }
        });

        // menu card 12

        JPanel menucard12 = new JPanel();
        jPanelmain.add(menucard12);
        menucard12.setLayout(null);
        menucard12.setBounds(809, 300, 140, 180);
        menucard12.setBorder(BorderFactory.createLineBorder(Color.lightGray, 3));

        try {
            Image img12 = ImageIO.read(new File("src/images/coke.png"));
            img12 = img12.getScaledInstance(140, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img12);
            JLabel jlableimage12 = new JLabel(icon);
            jlableimage12.setBounds(0, 0, 140, 80);
            menucard12.add(jlableimage12);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jlabelname12 = new JLabel("Coke", SwingConstants.CENTER);
        menucard12.add(jlabelname12);
        jlabelname12.setFont(font);
        jlabelname12.setBounds(0, 80, 140, 30);

        JLabel jlabelpricep12 = new JLabel("Price:", SwingConstants.CENTER);
        menucard12.add(jlabelpricep12);
        jlabelpricep12.setBounds(0, 110, 70, 23);

        JLabel jlabelpricevalue12 = new JLabel();
        menucard12.add(jlabelpricevalue12);
        jlabelpricevalue12.setText("100");
        jlabelpricevalue12.setBounds(70, 110, 140, 23);

        JLabel jlabelquantity12 = new JLabel("Quantity:", SwingConstants.CENTER);
        menucard12.add(jlabelquantity12);
        jlabelquantity12.setBounds(0, 133, 70, 23);

        SpinnerModel spinnerModel12 = new SpinnerNumberModel(initialValue, minValue, maxValue, 1);
        jSpinner12 = new JSpinner(spinnerModel12);
        menucard12.add(jSpinner12);
        jSpinner12.setBounds(70, 133, 55, 23);
        jSpinner12.setBorder(null);

        JLabel jlabelpurchase12 = new JLabel("Purchase:", SwingConstants.CENTER);
        menucard12.add(jlabelpurchase12);
        jlabelpurchase12.setBounds(0, 156, 70, 23);

        jcheckbox12 = new JCheckBox();
        menucard12.add(jcheckbox12);
        jcheckbox12.setBounds(70, 156, 68, 22);

        jcheckbox12.addItemListener((ItemListener) new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (jSpinner12.getValue().equals(0) && jcheckbox12.isSelected()) {
                    JOptionPane.showMessageDialog(frame, "Increase the quantity");
                }
                if (!(jSpinner12.getValue().equals(0))) {
                    itemName = jlabelname12.getText() + "\t\t";
                    quantity = Double.parseDouble(jSpinner12.getValue().toString());
                    itemPrice = quantity * Double.parseDouble(jlabelpricevalue12.getText());
                    checkboxCounter();
                    jcheckbox12.setEnabled(false);
                    jSpinner12.setEnabled(false);
                }
            }
        });

        // buttons pannel

        JPanel jPanelbuttons = new JPanel();
        frame.add(jPanelbuttons);
        jPanelbuttons.setBounds(0, 520, 990, 85);
        jPanelbuttons.setBorder(BorderFactory.createLineBorder(Color.lightGray, 15));
        jPanelbuttons.setLayout(null);

        JButton totalButton = new JButton("Total");
        jPanelbuttons.add(totalButton);
        totalButton.setFont(font2);
        totalButton.setBackground(new Color(154, 222, 123));
        totalButton.setBounds(40, 26, 95, 33);
        totalButton.setBorder(null);
        totalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (counter == 0) {
                    JOptionPane.showMessageDialog(frame, "Purchase products first!", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {

                    totaling();
                    totalButton.setEnabled(false);
                }
            }
        });

        JButton receiptButton = new JButton("Receipt");
        jPanelbuttons.add(receiptButton);
        receiptButton.setFont(font2);
        receiptButton.setBackground(new Color(95, 189, 255));
        receiptButton.setBounds(145, 26, 100, 33);
        receiptButton.setBorder(null);

        // reset button

        JButton resetButton = new JButton("Reset");
        jPanelbuttons.add(resetButton);
        resetButton.setFont(font2);
        resetButton.setBackground(new Color(255, 196, 54));
        resetButton.setBounds(256, 26, 90, 33);

        // exit button

        JButton exitButton = new JButton("Exit");
        jPanelbuttons.add(exitButton);
        exitButton.setFont(font2);
        exitButton.setBackground(Color.RED);
        exitButton.setBounds(358, 26, 90, 33);
        exitButton.setBorder(null);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // receipt panel

        JPanel jPanelreceipt = new JPanel();
        frame.add(jPanelreceipt);
        jPanelreceipt.setLayout(null);
        jPanelreceipt.setBounds(985, 0, 294, 605);
        jPanelreceipt.setBackground(Color.lightGray);
        jPanelreceipt.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        Font font3 = new Font("SanSerif", Font.BOLD, 16);

        Font font7 = new Font("Consolas", Font.PLAIN, 11);
        receipTextArea = new JTextArea();
        receipTextArea.setFont(font7);
        jPanelreceipt.add(receipTextArea);
        receipTextArea.setBounds(5, 1, 286, 450);
        receipTextArea.setEditable(false);

        JLabel jlabelsubtotal = new JLabel("SubTotal");
        jPanelreceipt.add(jlabelsubtotal);
        jlabelsubtotal.setBounds(30, 465, 90, 40);
        jlabelsubtotal.setFont(font3);

        jtextfieldsubtotal = new JTextField();
        jPanelreceipt.add(jtextfieldsubtotal);
        jtextfieldsubtotal.setText("0.0");
        jtextfieldsubtotal.setFont(font);
        jtextfieldsubtotal.setBorder(null);
        jtextfieldsubtotal.setBounds(120, 470, 160, 30);
        jtextfieldsubtotal.setEditable(false);

        JLabel jlabeltax = new JLabel("Tax");
        jPanelreceipt.add(jlabeltax);
        jlabeltax.setBounds(30, 504, 90, 40);
        jlabeltax.setFont(font3);

        jtextfieldtax = new JTextField();
        jPanelreceipt.add(jtextfieldtax);
        jtextfieldtax.setText("0.0");
        jtextfieldtax.setFont(font);
        jtextfieldtax.setBorder(null);
        jtextfieldtax.setBounds(120, 510, 160, 30);
        jtextfieldtax.setEditable(false);

        JLabel jlabeltotal = new JLabel("Total");
        jPanelreceipt.add(jlabeltotal);
        jlabeltotal.setBounds(30, 543, 90, 40);
        jlabeltotal.setFont(font3);

        jtextfieldtotal = new JTextField();
        jPanelreceipt.add(jtextfieldtotal);
        jtextfieldtotal.setText("0.0");
        jtextfieldtotal.setFont(font);
        jtextfieldtotal.setBounds(120, 550, 160, 30);
        jtextfieldtotal.setBorder(null);
        jtextfieldtotal.setEditable(false);

        // reset button action

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jSpinner1.setValue(0);
                jSpinner2.setValue(0);
                jSpinner3.setValue(0);
                jSpinner4.setValue(0);
                jSpinner5.setValue(0);
                jSpinner6.setValue(0);
                jSpinner7.setValue(0);
                jSpinner8.setValue(0);
                jSpinner9.setValue(0);
                jSpinner10.setValue(0);
                jSpinner11.setValue(0);
                jSpinner12.setValue(0);

                jcheckbox1.setSelected(false);
                jcheckbox2.setSelected(false);
                jcheckbox3.setSelected(false);
                jcheckbox4.setSelected(false);
                jcheckbox5.setSelected(false);
                jcheckbox6.setSelected(false);
                jcheckbox7.setSelected(false);
                jcheckbox8.setSelected(false);
                jcheckbox9.setSelected(false);
                jcheckbox10.setSelected(false);
                jcheckbox11.setSelected(false);
                jcheckbox12.setSelected(false);

                jcheckbox1.setEnabled(true);
                jcheckbox2.setEnabled(true);
                jcheckbox3.setEnabled(true);
                jcheckbox4.setEnabled(true);
                jcheckbox5.setEnabled(true);
                jcheckbox6.setEnabled(true);
                jcheckbox7.setEnabled(true);
                jcheckbox8.setEnabled(true);
                jcheckbox9.setEnabled(true);
                jcheckbox10.setEnabled(true);
                jcheckbox11.setEnabled(true);
                jcheckbox12.setEnabled(true);

                jSpinner1.setEnabled(true);
                jSpinner2.setEnabled(true);
                jSpinner3.setEnabled(true);
                jSpinner4.setEnabled(true);
                jSpinner5.setEnabled(true);
                jSpinner6.setEnabled(true);
                jSpinner7.setEnabled(true);
                jSpinner8.setEnabled(true);
                jSpinner9.setEnabled(true);
                jSpinner10.setEnabled(true);
                jSpinner11.setEnabled(true);
                jSpinner12.setEnabled(true);

                totalButton.setEnabled(true);

                counter = 0;

                receipTextArea.setText("");
                jtextfieldsubtotal.setText("0.0");
                jtextfieldtax.setText("0.0");
                jtextfieldtotal.setText("0.0");

                Tax = 0;
                SubTotal = 0;
                Total = 0;
            }
        });

        // footer

        JPanel jPanelfooter = new JPanel();
        frame.add(jPanelfooter);
        jPanelfooter.setBounds(0, 616, 1300, 30);
        jPanelfooter.setBackground(Color.DARK_GRAY);

        JLabel jlabelfooter = new JLabel("Cafe Management Application by Abdul Basit, Abdullah Riaz and Muhammad Raza");
        jPanelfooter.add(jlabelfooter);
        jlabelfooter.setForeground(Color.white);

    }

    // receipt text design

    void checkboxCounter() {
        if (jcheckbox1.isSelected() || jcheckbox2.isSelected() || jcheckbox3.isSelected() || jcheckbox4.isSelected()
                || jcheckbox5.isSelected() || jcheckbox6.isSelected() || jcheckbox7.isSelected()
                || jcheckbox8.isSelected() || jcheckbox9.isSelected() || jcheckbox10.isSelected()
                || jcheckbox11.isSelected() || jcheckbox12.isSelected()) {
            counter++;
            if (counter == 1) {

                barCafelabel(counter);
            } else {
                productdetail(counter);
            }
        }
    }

    void productdetail(int counter) {
        receipTextArea.setText(
                receipTextArea.getText() + "\n" + counter + " " + itemName + "\t" + quantity + "\t" + itemPrice);

        // calculating subtotal
        
        SubTotal += itemPrice;
        String subtotalString = String.valueOf(SubTotal);
        jtextfieldsubtotal.setText(subtotalString);
        double subt = Double.parseDouble(jtextfieldsubtotal.getText());
        
        // calculating tax
        
        Tax = subt * 18/100;
        String taxString = String.valueOf(Tax);
        jtextfieldtax.setText(taxString);
        double tax = Double.parseDouble(jtextfieldtax.getText());

        // calculating Total

        Total = tax + subt;
        String totalString = String.valueOf(Total);
        jtextfieldtotal.setText(totalString);

    }

    void barCafelabel(int counter) {

        // random id generator

        double ran = Math.ceil(Math.random() * 1000000);
        int random = (int) ran;

        // calculating subtotal
        
        SubTotal += itemPrice;
        String subtotalString = String.valueOf(SubTotal);
        jtextfieldsubtotal.setText(subtotalString);
        double subt = Double.parseDouble(jtextfieldsubtotal.getText());
        

        // calculating tax

        Tax = subt * 0.18;
        String taxString = String.valueOf(Tax);
        jtextfieldtax.setText(taxString);
        double tax = Double.parseDouble(jtextfieldtax.getText());

        // calculating Total

        Total = tax + subt;
        String totalString = String.valueOf(Total);
        jtextfieldtotal.setText(totalString);

        receipTextArea.setText("\n*******************BAR Cafe*******************\n" +
                " Time: " + timeLabel.getText() + "\t  Date: " + dateLabel.getText() + "\n Purchase id: " + random
                + "\n**********************************************\n Item name:\t\tqty\tPrice\n" + counter + " "
                + itemName + "\t" + quantity + "\t" + itemPrice);

    }

    // total button function

    void totaling() {

        receipTextArea.setText(
                receipTextArea.getText() + "\n\n**********************************************\n Subtotal\t\t\t"
                        + jtextfieldsubtotal.getText() + "\n Tax\t\t\t\t" + jtextfieldtax.getText() + "\n Total\t\t\t\t"
                        + jtextfieldtotal.getText() + "\n\n******************THANK YOU*******************");
        jcheckbox1.setEnabled(false);
        jcheckbox2.setEnabled(false);
        jcheckbox3.setEnabled(false);
        jcheckbox4.setEnabled(false);
        jcheckbox5.setEnabled(false);
        jcheckbox6.setEnabled(false);
        jcheckbox7.setEnabled(false);
        jcheckbox8.setEnabled(false);
        jcheckbox9.setEnabled(false);
        jcheckbox10.setEnabled(false);
        jcheckbox11.setEnabled(false);
        jcheckbox12.setEnabled(false);

        jSpinner1.setEnabled(false);
        jSpinner2.setEnabled(false);
        jSpinner3.setEnabled(false);
        jSpinner4.setEnabled(false);
        jSpinner5.setEnabled(false);
        jSpinner6.setEnabled(false);
        jSpinner7.setEnabled(false);
        jSpinner8.setEnabled(false);
        jSpinner9.setEnabled(false);
        jSpinner10.setEnabled(false);
        jSpinner11.setEnabled(false);
        jSpinner12.setEnabled(false);
    }

    private void updateClock() {
        // Get the current time and date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

        // Update the labels
        timeLabel.setText(now.format(timeFormatter));
        dateLabel.setText(now.format(dateFormatter));
    }
}

// login page frame

class loginpage {
    JFrame loginJFrame;
    Font font2 = new Font("Sanserif", Font.PLAIN, 15);
    Font font4 = new Font("Sanserif", Font.PLAIN, 10);

    loginpage() {

        // Login Frame

        loginJFrame = new JFrame();
        loginJFrame.setSize(400, 500);
        loginJFrame.setVisible(true);
        loginJFrame.setLayout(null);
        loginJFrame.setResizable(false);
        loginJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // user icon JLabel

        CirclePanel circlePanel = new CirclePanel();
        loginJFrame.add(circlePanel);
        Dimension size = circlePanel.getPreferredSize();
        circlePanel.setBounds(156, 50, size.width, size.height);

        // Login to account
        JPanel logintoaccountpanel = new JPanel();
        loginJFrame.add(logintoaccountpanel);
        logintoaccountpanel.setBounds(90, 140, 200, 30);

        // label login to your account

        JLabel logintoaccount = new JLabel("Login To Your Account");
        logintoaccount.setBounds(120, 90, 100, 30);
        logintoaccountpanel.add(logintoaccount);
        logintoaccount.setForeground(new Color(48, 129, 208));
        logintoaccount.setFont(font2);

        // user login panel

        JPanel userlogin = new JPanel();
        loginJFrame.add(userlogin);
        userlogin.setLayout(null);
        userlogin.setBounds(56, 180, 270, 200);

        // Username label

        JLabel usernamJLabel = new JLabel("Username");
        userlogin.add(usernamJLabel);
        usernamJLabel.setFont(font4);
        usernamJLabel.setForeground(new Color(160, 160, 160));
        usernamJLabel.setBounds(59, 14, 70, 20);

        // user name textfield

        JTextField usernamTextField = new JTextField();
        userlogin.add(usernamTextField);
        usernamTextField.setBorder(null);
        usernamTextField.setBounds(59, 34, 155, 25);

        // Password label

        JLabel passwordJLabel = new JLabel("Password");
        userlogin.add(passwordJLabel);
        passwordJLabel.setFont(font4);
        passwordJLabel.setForeground(new Color(160, 160, 160));
        passwordJLabel.setBounds(59, 70, 70, 20);

        // Password textfield

        JPasswordField passwordTextField = new JPasswordField();
        userlogin.add(passwordTextField);
        passwordTextField.setBorder(null);
        passwordTextField.setBounds(59, 90, 155, 25);

        // login button

        JButton loginButton = new JButton("Login");
        userlogin.add(loginButton);
        loginButton.setBorder(null);
        loginButton.setFont(font2);
        loginButton.setBackground(new Color(48, 129, 208));
        loginButton.setForeground(Color.white);
        loginButton.setBounds(95, 150, 70, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernamTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());
                if (username.equals("basit") && password.equals("basit")
                        || username.equals("raza") && password.equals("raza")
                        || username.equals("abdullah") && password.equals("abdullah")) {
                    new dashboard();
                    loginJFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(loginJFrame, "Invalid username or password", "Warning",
                            JOptionPane.WARNING_MESSAGE);
                    usernamTextField.setText("");
                    passwordTextField.setText("");

                }
            }
        });

    }

    static class CirclePanel extends JPanel {
        private Image userIcon;

        // user login image icon

        CirclePanel() {
            setPreferredSize(new Dimension(70, 70));
            try {
                userIcon = ImageIO.read(new File("src/images/usericon.png"));
            } catch (IOException e) {
                e.printStackTrace();
                userIcon = createCircleImage(Color.lightGray);
            }
        }

        private Image createCircleImage(Color color) {
            BufferedImage image = new BufferedImage(90, 90, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            g2d.setColor(color);
            g2d.fillOval(0, 0, 90, 90);
            g2d.dispose();
            return image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the image at the center of the panel
            int diameter = Math.min(getWidth(), getHeight());
            int x = (getWidth() - diameter) / 2;
            int y = (getHeight() - diameter) / 2;
            g.setClip(new Ellipse2D.Float(x, y, diameter, diameter));
            g.drawImage(userIcon, x, y, diameter, diameter, this);
        }
    }

}

class mainapp {
    public static void main(String[] args) {
        new loginpage();
    }

}