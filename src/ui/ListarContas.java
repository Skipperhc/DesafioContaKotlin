package ui;

import busines.ContaBusines;
import busines.ListagemBusines;
import entity.Conta;
import entity.ContaCorrente;
import entity.Operacao;
import entity.exceptions.NenhumItemEncontrado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListarContas extends JFrame{
    private JButton btnListarCorrentes;
    private JButton btnListarPoupancas;
    private JButton btnlistarcontas;
    private JButton btnListarOperacoesConta;
    private JTable tableListagem;
    private JButton btnVoltar;
    private JPanel panelPrincipal;
    private JLabel lblFolder;
    private JTextField txtOperacoesContaId;

    private ListagemBusines mlistaconta;
    private ContaBusines mContaBusines;


    public ListarContas() {
        setContentPane(panelPrincipal);
        setSize(1000,500);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mlistaconta = new ListagemBusines();
        mContaBusines = new ContaBusines();
        setListener();
        loadContas(mlistaconta.listarContas());
    }

    private void setListener() {
        btnListarCorrentes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContas(mlistaconta.listarCorrentes());
            }
        });

        btnListarPoupancas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContas(mlistaconta.listarPoupanca());
            }
        });

        btnlistarcontas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadContas(mlistaconta.listarContas());
            }
        });

        btnListarOperacoesConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarOperacoes();
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

    private void listarOperacoes() {
        try {
            Conta conta = mContaBusines.search(txtOperacoesContaId.getText());
            String[] columnNomes = {"Id", "Id conta origem", "Id conta destino", "valor"};
            DefaultTableModel model = new DefaultTableModel(new Object[0][0][0][0], columnNomes);

            Object[] nomeColunas = new Object[4];
            nomeColunas[0] = "          Id";
            nomeColunas[1] = "          Id conta origem";
            nomeColunas[2] = "          Id conta destino";
            nomeColunas[3] = "          valor da operação";
            model.addRow(nomeColunas);

            for(Operacao i : conta.getListaOperacao()) {
                Object[] o = new Object[5];
                o[0] = i.getId();
                o[1] = i.getContaIdOrigem();
                if(i.getContaIdDestino() != 0) {
                    o[2] = i.getContaIdDestino();
                } else {
                    o[2] = "Sem Conta de destino";
                }
                o[3] = i.getTotalTransferencia();
                model.addRow(o);
            }

            tableListagem.clearSelection();
            tableListagem.setModel(model);

            lblFolder.setText(qtdOperacoes(conta.getListaOperacao()));
        } catch (NenhumItemEncontrado excp) {
            JOptionPane.showMessageDialog(new JFrame(), excp.getMessage());
        } catch (NumberFormatException excp) {
            JOptionPane.showMessageDialog(new JFrame(), "Coloca um numera cara");
        }
    }

    private void loadContas(List<Conta> lista) {
        String[] columnNomes = {"Id", "Nome", "Saldo", "Banco", "Limite"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0][0][0][0], columnNomes);

        Object[] nomeColunas = new Object[5];
        nomeColunas[0] = "          Id";
        nomeColunas[1] = "          Nome";
        nomeColunas[2] = "          Saldo";
        nomeColunas[3] = "          Banco";
        nomeColunas[4] = "          Limite";
        model.addRow(nomeColunas);

        for(Conta i : lista) {
            Object[] o = new Object[5];
            o[0] = i.getId();
            o[1] = i.getNomeCliente();
            o[2] = i.getSaldo();
            o[3] = i.getBanco();
            if(i instanceof ContaCorrente) {
                ContaCorrente contaCorrente = (ContaCorrente) i;
                o[4] = contaCorrente.getLimite();
            } else {
                o[4] = "Sem limite";
            }
            model.addRow(o);
        }

        tableListagem.clearSelection();
        tableListagem.setModel(model);

        lblFolder.setText(qtdContas(lista));
    }

    private String qtdContas(List<Conta> lista) {
        if(lista.size() == 0) return "Nenhuma "+ "conta cadastrada";
        if(lista.size() == 1) return "Mostrando a única "+ " conta cadastrada";
        if(lista.size() >= 2) return "Mostrando as " + lista.size() + " contas cadastradas";

        return "Isso nunca vai aparecer mesmo";
    }

    private String qtdOperacoes(List<Operacao> lista) {
        if(lista.size() == 0) return "Nenhuma operação encontrada";
        if(lista.size() == 1) return "Mostrando a única operação da conta";
        if(lista.size() >= 2) return "Mostrando as " + lista.size() + " operações da conta";

        return "Isso nunca vai aparecer mesmo";
    }
}
