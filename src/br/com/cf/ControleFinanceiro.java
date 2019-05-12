package br.com.cf;

import br.com.cf.gui.TelaInicialGui;

public class ControleFinanceiro {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaInicialGui().setVisible(true));
    }
}
