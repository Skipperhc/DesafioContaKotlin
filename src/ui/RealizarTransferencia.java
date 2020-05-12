package ui;

import busines.ContaBusines;
import busines.TransferenciaBusines;
import entity.Conta;
import entity.Operacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RealizarTransferencia extends JFrame{
    private JTextField txtIdOrigem;
    private JTextField txtNomeOrigem;
    private JTextField txtBancoOrigem;
    private JTextField txtSaldoOrigem;
    private JButton btnDepositar;
    private JButton btnVoltar;
    private JButton btnBuscarOrigem;
    private JButton btnBuscarDestino;
    private JPanel panelPrincipal;
    private JTextField txtIdDestino;
    private JTextField txtNomeDestino;
    private JTextField txtBancoDestino;
    private JTextField txtValorDeposito;
    private JLabel txtIdOperacao;

    private final ContaBusines mContaBusines;
    private TransferenciaBusines mTransferenciaBusines;
    private Conta origem;
    private Conta destino;

    public RealizarTransferencia() {
        setContentPane(panelPrincipal);
        setSize(600,300);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListener();
        mContaBusines = new ContaBusines();
        txtIdOperacao.setText("" + (Operacao.Companion.getNumeroOperacao() + 1));
        btnBuscarDestino.setEnabled(false);
        btnDepositar.setEnabled(false);
    }

    private void setListener() {
        btnBuscarOrigem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    origem = mContaBusines.search(txtIdOrigem.getText());
                    txtNomeOrigem.setText(origem.getNomeCliente());
                    txtBancoOrigem.setText(origem.getBanco());
                    txtSaldoOrigem.setText(origem.getSaldo()+"");
                    btnBuscarDestino.setEnabled(true);
                    btnBuscarOrigem.setEnabled(false);
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnBuscarDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    destino = mContaBusines.search(txtIdDestino.getText());
                    if(origem.getId() != destino.getId()) {
                        txtBancoDestino.setText(destino.getBanco());
                        txtNomeDestino.setText(destino.getNomeCliente());
                        btnDepositar.setEnabled(true);
                        btnBuscarDestino.setEnabled(false);
                    } else {
                        throw new Exception("Conta de origem igual a de destino");
                    }
                } catch (Exception excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mTransferenciaBusines.pagar(txtValorDeposito.getText(), origem, destino);
                    JOptionPane.showMessageDialog(new JFrame(), "Pagamento efetuado com sucesso");
                    new Transferencia();
                    dispose();
                } catch (IllegalArgumentException excp) {
                    JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transferencia();
                dispose();
            }
        });
    }
}
