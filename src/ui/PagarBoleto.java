package ui;

import busines.ContaBusines;
import busines.TransferenciaBusines;
import entity.Conta;
import entity.ContaCorrente;
import entity.exceptions.NenhumItemEncontrado;
import entity.exceptions.NumeroNegativoException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PagarBoleto extends JFrame {
    private JTextField txtIdConta;
    private JTextField txtNomeConta;
    private JLabel txtIdEncontrado;
    private JLabel txtNomeEncontrado;
    private JLabel txtSaldoEncontrado;
    private JLabel txtLimiteEncontrado;
    private JPanel panelPrincipal;
    private JLabel txtBancoEncontrado;
    private JTextField txtValorBoleto;
    private JButton btnPagar;
    private JButton btnBuscar;
    private JButton btnCancelar;

    private Conta conta;
    private final ContaBusines mContaBusines;
    private TransferenciaBusines mTransferenciaBusines;

    public PagarBoleto() {
        setContentPane(panelPrincipal);
        setSize(500, 300);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListener();
        mContaBusines = new ContaBusines();
        mTransferenciaBusines = new TransferenciaBusines();
        btnPagar.setEnabled(false);
    }

    private void setListener() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conta = mContaBusines.search(txtIdConta.getText());
                    txtNomeConta.setText(conta.getNomeCliente());
                    txtIdEncontrado.setText(conta.getId() + "");
                    txtNomeEncontrado.setText(conta.getNomeCliente());
                    txtSaldoEncontrado.setText(conta.getSaldo() + "");
                    txtBancoEncontrado.setText(conta.getBanco());
                    if (conta instanceof ContaCorrente) {
                        ContaCorrente contaCorrente = (ContaCorrente) conta;
                        txtLimiteEncontrado.setText(contaCorrente.getLimite() + "");
                    } else {
                        txtLimiteEncontrado.setText("Sem limite");
                    }
                    btnPagar.setEnabled(true);
                } catch (NumberFormatException | NenhumItemEncontrado | NullPointerException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mTransferenciaBusines.pagar(txtValorBoleto.getText(), conta);
                    txtNomeConta.setText(conta.getNomeCliente());
                    txtIdEncontrado.setText(conta.getId() + "");
                    txtNomeEncontrado.setText(conta.getNomeCliente());
                    txtSaldoEncontrado.setText(conta.getSaldo() + "");
                    txtBancoEncontrado.setText(conta.getBanco());
                    JOptionPane.showMessageDialog(new JFrame(), "Pagamento efetuado com sucesso");
                    new Transferencia();
                    dispose();
                } catch (IllegalArgumentException | NumeroNegativoException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transferencia();
                dispose();
            }
        });
    }
}
