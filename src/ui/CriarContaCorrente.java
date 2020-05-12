package ui;

import busines.ContaBusines;
import entity.Conta;
import entity.exceptions.CamposVaziosException;
import entity.exceptions.NumeroNegativoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarContaCorrente extends JFrame{
    private JTextField txtNome;
    private JTextField txtLimite;
    private JTextField txtSaldo;
    private JTextField txtBanco;
    private JButton btnVoltar;
    private JButton btnCriar;
    private JPanel panelPrincipal;
    private JTextField txtIdConta;

    private ContaBusines mContaBusines;

    public CriarContaCorrente() {
        setContentPane(panelPrincipal);
        setSize(600,300);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListener();
        mContaBusines = new ContaBusines();
        txtIdConta.setText("" + (Conta.Companion.getQtdContas() + 1));
    }

    private void setListener() {
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = txtNome.getText();
                    String limite = txtLimite.getText();
                    String saldo = txtSaldo.getText();
                    String banco = txtBanco.getText();

                    mContaBusines.save(nome, saldo, banco, limite);
                    new TelaInicial();
                    dispose();
                } catch (NumberFormatException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), "Insira um valor apropriado");
                } catch (IllegalArgumentException | CamposVaziosException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial();
                dispose();
            }
        });
    }


}
