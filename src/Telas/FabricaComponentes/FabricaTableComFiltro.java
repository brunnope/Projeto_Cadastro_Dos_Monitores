package Telas.FabricaComponentes;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import Classes.Aluno;

public class FabricaTableComFiltro extends DefaultTableModel {
	
	private ArrayList<Aluno> dados;
	private ArrayList<Aluno> dadosFiltrados = new ArrayList<>();

    public FabricaTableComFiltro(ArrayList<Aluno> dados) {
        this.dados = dados;
        this.dadosFiltrados = new ArrayList<>(dados);
    }

    public int getRowCount() {
        return dadosFiltrados != null ? dadosFiltrados.size() : 0;
    }

    public int getColumnCount() {
        return 2;
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluno aluno = dadosFiltrados.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aluno.getNome();
            case 1:
                return aluno.getMatricula();
            default:
                return null;
        }
    }

    public void filtrar(String termo) {
        termo = termo.toLowerCase();

        dadosFiltrados.clear();

        for (Aluno aluno : dados) {
            if (aluno.getNome().toLowerCase().contains(termo)) {
                dadosFiltrados.add(aluno);
            }
        }

        fireTableDataChanged();
    }
}