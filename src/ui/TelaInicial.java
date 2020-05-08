package ui;

import repository.ContaRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {
    private JButton btnListarContas;
    private JButton btnTranferencia;
    private JButton btnCriarContaPoupanca;
    private JPanel panelPrincipal;
    private JButton btnCriaContaCorrente;

    public TelaInicial() {
        setContentPane(panelPrincipal);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListener();

        if (ContaRepository.Companion.lista().size() > 0) {
            btnTranferencia.setEnabled(true);
        } else {
            btnTranferencia.setEnabled(false);
        }
    }

    private void setListener() {
        btnCriarContaPoupanca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CriarConta();
                dispose();
            }
        });

        btnCriaContaCorrente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CriarContaCorrente();
                dispose();
            }
        });

        btnListarContas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarContas();
                dispose();
            }
        });

        btnTranferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transferencia();
                dispose();
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
