package ui;

import busines.ContaBusines;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import entity.Conta;
import entity.exceptions.CamposVaziosException;
import entity.exceptions.NumeroNegativoException;
import repository.ContaRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CriarConta extends JFrame{
    private JTextField nometxt;
    private JTextField saldotxt;
    private JTextField bancotxt;
    private JButton btnCancelar;
    private JButton btnCriarConta;
    private JPanel panelPrincipal;
    private JTextField txtIdConta;

    private ContaBusines mContaBusines;

    public CriarConta() {
        setContentPane(panelPrincipal);
        setSize(500,250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContaBusines = new ContaBusines();
        setListener();
        txtIdConta.setText("" + (Conta.Companion.getQtdContas() + 1));
    }

    private void setListener() {
        btnCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nometxt.getText();
                    String saldo = saldotxt.getText();
                    String banco = bancotxt.getText();

                    mContaBusines.save(nome, saldo, banco);
                    new TelaInicial();
                    dispose();
                } catch (NumberFormatException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), "Insira um valor apropriado");
                } catch (CamposVaziosException | IllegalArgumentException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial();
                dispose();
            }
        });
    }
}
