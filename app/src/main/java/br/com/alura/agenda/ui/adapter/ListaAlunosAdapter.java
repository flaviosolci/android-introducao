package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Context context;

    public ListaAlunosAdapter(@NonNull Context context, @NonNull List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criarView(parent);

        vincular(viewCriada, position);
        return viewCriada;
    }

    private void vincular(View view, int position) {
        Aluno aluno = getItem(position);
        TextView textViewNome = view.findViewById(R.id.item_aluno_nome);
        textViewNome.setText(aluno.getNome());
        TextView textViewTelefone = view.findViewById(R.id.item_aluno_telefone);
        textViewTelefone.setText(aluno.getTelefone());
    }

    private View criarView(ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, parent, false);
    }

    private void clear() {
        this.alunos.clear();
    }

    private void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno aluno) {
        this.alunos.remove(aluno);
        notifyDataSetChanged();
    }

    public void atualizar(List<Aluno> alunos) {
        this.clear();
        this.addAll(alunos);
        notifyDataSetChanged();
    }
}
