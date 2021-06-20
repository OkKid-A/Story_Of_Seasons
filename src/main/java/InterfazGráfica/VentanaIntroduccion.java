package InterfazGráfica;

import Usuario.Usuario;

import javax.swing.*;
import java.awt.event.*;

public class VentanaIntroduccion extends JFrame {
    private JTextField NametextField;
    private JTextField NicknametextField;
    private JPanel IntroduccionPanel;
    private JButton ContinuarButton;
    private JLabel NameLabel;
    private JLabel NicknameLabel;
    private JPanel ButtonPanel;
    private JPanel TopPanel;
    private Usuario usuario;

    public VentanaIntroduccion(Usuario usuario) {
        this.usuario = usuario;
        fixComponents();
        setContinuarButton();
    }

    private void fixComponents() {
        JOptionPane.showMessageDialog(IntroduccionPanel, "Antes de empezar, por favor introduce tus datos.");
        this.setContentPane(IntroduccionPanel);
        this.setTitle(VentanaMenuPrincipal.TITLE);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void setContinuarButton() {
        ContinuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMenuPrincipal inicio = new VentanaMenuPrincipal(usuario);
                dispose();
                usuario.setData(NametextField.getText(),NicknametextField.getText());
            }
        });
    }
}
