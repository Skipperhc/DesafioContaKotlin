package ui;

import busines.ListagemBusines;
import entity.Conta;
import entity.ContaCorrente;
import repository.ContaRepository;

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
    private JButton btnListarContasOperacoes;
    private JTable tableListagem;
    private JButton btnVoltar;
    private JPanel panelPrincipal;
    private JLabel lblFolder;

    private ListagemBusines mlistaconta;

    public ListarContas() {
        setContentPane(panelPrincipal);
        setSize(1000,500);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mlistaconta = new ListagemBusines();
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

        btnListarContasOperacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        if(lista.size() == 0) return "Nenhuma conta cadastrada";
        if(lista.size() == 1) return "Mostrando a única conta cadastrada";
        if(lista.size() >= 2) return "Mostrando as " + lista.size() + " contas cadastradas";

        return "Isso nunca vai aparecer mesmo";
    }
}
