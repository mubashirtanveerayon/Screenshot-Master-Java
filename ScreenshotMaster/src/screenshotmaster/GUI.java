/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screenshotmaster;

import componentresizer.ComponentResizer;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Frame;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author ayon2
 */
public class GUI extends javax.swing.JFrame implements MouseListener, MouseMotionListener {

    public boolean custom;
    public JFileChooser fc;
    public JFrame customFrame;

    int x;
    int y;

    public ComponentResizer resizer;

    public static int total;
    public static int done;

    Loader loader;

    public GUI() {
        initComponents();

        fc = new JFileChooser(".");
        custom = customRadioButton.isSelected();
        ButtonGroup btngrp = new ButtonGroup();
        btngrp.add(fullScreenRadioButton);
        btngrp.add(customRadioButton);

        resizer = new ComponentResizer();

        customFrame = new JFrame();
        customFrame.setUndecorated(true);
        customFrame.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        customFrame.setBounds(300, 150, 300, 300);
        customFrame.setOpacity(0.7f);
        customFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel label = new JLabel();
        label.setBorder(BorderFactory.createLineBorder(Color.red));
        
        customFrame.add(label);
        
        resizer.registerComponent(customFrame);

        customFrame.addMouseListener(this);
        customFrame.addMouseMotionListener(this);

        loader = new Loader();
    }

    public Thread capture(int x, int y, int w, int h, int total, String path) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    for (int i = 1; i <= total&&!loader.stop; i++) {
                        BufferedImage ss = new Robot().createScreenCapture(new Rectangle(x, y, w, h));
                        saveFile(ss, path + "screenshot" + i + ".png");
                        done=i;
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                loader.stop=true;
                setVisible();
            }
        };
        return thread;
    }
    
    public void setVisible(){
        setVisible(true);
        if (customRadioButton.isSelected()) {
            customFrame.setVisible(true);
        }
    }

    public Thread capture(int total, String path) {
        int x = 0;
        int y = 0;
        int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        return capture(x, y, w, h, total, path);
    }

    public void saveFile(BufferedImage bi) {
        fc.setDialogTitle("Save Path");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = fc.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath() + File.separator;
            String name = "screenshot";
            int i = 0;
            while (new File(path + name + i + ".png").exists()) {
                i++;
            }
            try {
                ImageIO.write(bi, "png", new File(path + name + i + ".png"));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void saveFile(BufferedImage bi, String path) {
        try {
            ImageIO.write(bi, "png", new File(path));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fullScreenRadioButton = new javax.swing.JRadioButton();
        customRadioButton = new javax.swing.JRadioButton();
        captureButton = new javax.swing.JButton();
        conCaptureButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Screenshot Master");
        setResizable(false);

        fullScreenRadioButton.setSelected(true);
        fullScreenRadioButton.setText("Full-Screen");
        fullScreenRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullScreenRadioButtonActionPerformed(evt);
            }
        });

        customRadioButton.setText("Custom");
        customRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customRadioButtonActionPerformed(evt);
            }
        });

        captureButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        captureButton.setText("Capture");
        captureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureButtonActionPerformed(evt);
            }
        });

        conCaptureButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        conCaptureButton.setText("Continuous Capture");
        conCaptureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conCaptureButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Screenshot Master");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(captureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fullScreenRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(customRadioButton))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(conCaptureButton)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customRadioButton)
                    .addComponent(fullScreenRadioButton))
                .addGap(18, 18, 18)
                .addComponent(captureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(conCaptureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void captureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureButtonActionPerformed
        customFrame.setVisible(false);
        this.setVisible(false);
        try {
            Thread.sleep(200);
            if (customRadioButton.isSelected()) {
                int lx = customFrame.getLocation().x;
                int ly = customFrame.getLocation().y;
                int w = customFrame.getWidth();
                int h = customFrame.getHeight();
                BufferedImage ss = new Robot().createScreenCapture(new Rectangle(lx, ly, w, h));
                saveFile(ss);
            } else {
                BufferedImage ss = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                saveFile(ss);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (customRadioButton.isSelected()) {
            customFrame.setVisible(true);
        }
        this.setVisible(true);
    }//GEN-LAST:event_captureButtonActionPerformed

    private void customRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customRadioButtonActionPerformed
        customFrame.setVisible(true);
    }//GEN-LAST:event_customRadioButtonActionPerformed

    private void fullScreenRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullScreenRadioButtonActionPerformed
        customFrame.setVisible(false);
    }//GEN-LAST:event_fullScreenRadioButtonActionPerformed

    private void conCaptureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conCaptureButtonActionPerformed
        fc.setDialogTitle("Save Path");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int response = fc.showSaveDialog(null);
        String path = "";
        if (response == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath() + File.separator;
            customFrame.setVisible(false);
            setVisible(false);
            loader.stop=false;
            if (new File(path).list().length == 0) {
                String r = JOptionPane.showInputDialog(null, "Enter the number of total frames :");
                try {
                    total = Integer.parseInt(r);
                    Thread.sleep(200);
                    Thread thread = new Thread(loader);
                    thread.start();
                    if (customRadioButton.isSelected()) {
                        int lx = customFrame.getLocation().x;
                        int ly = customFrame.getLocation().y;
                        int w = customFrame.getWidth();
                        int h = customFrame.getHeight();
                        Thread thread1 = capture(lx,ly,w,h,total,path);
                        thread1.start();
                    } else {
                        Thread thread1 = capture(total,path);
                        thread1.start();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digits only!", "Error", JOptionPane.ERROR_MESSAGE);
                    setVisible();
                } catch (Exception ex) {
                    System.out.println(ex);
                    setVisible();
                }
            } else {
                JOptionPane.showMessageDialog(null, "You need to choose an empty directory.", "Error", JOptionPane.ERROR_MESSAGE);
                setVisible();
            }
        }
    }//GEN-LAST:event_conCaptureButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton captureButton;
    private javax.swing.JButton conCaptureButton;
    private javax.swing.JRadioButton customRadioButton;
    private javax.swing.JRadioButton fullScreenRadioButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!resizer.isResizeCursor) {
            customFrame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
