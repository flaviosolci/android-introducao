package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosActivity extends AppCompatActivity {

    private static final String TITLE_APP_BAR = "Lista de alunos";
    private final AlunoDao dao = new AlunoDao();
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE_APP_BAR);

        cofiguraFABNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_lista_alunos_menu_remover) {
            confirmaRemocao(item);

        }
        return super.onContextItemSelected(item);

    }

    private void confirmaRemocao(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        new AlertDialog.Builder(this)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", (dialog, which) -> remover(adapter.getItem(menuInfo.position)))
                .setNegativeButton("Não", null)
                .show();
    }

    private void configuraLista() {
        ListView listDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configurarAdapter(listDeAlunos);
        listDeAlunos.setOnItemClickListener((parent, view, position, id) -> configurarClickItem(parent, position));
        registerForContextMenu(listDeAlunos);
    }

    private void configurarAdapter(ListView listDeAlunos) {
        adapter = new ListaAlunosAdapter(this, dao.todos());
        listDeAlunos.setAdapter(adapter);
    }

    private void configurarClickItem(AdapterView<?> parent, int position) {
        Aluno aluno = (Aluno) parent.getItemAtPosition(position);
        abrirFormularioParaEdicao(aluno);
    }

    private void abrirFormularioParaEdicao(Aluno aluno) {
        Intent formulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        formulario.putExtra(ConstanteActivities.CHAVE_ALUNO, aluno);
        startActivity(formulario);
    }


    private void cofiguraFABNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_list_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(v -> abreFormulario());
    }

    private void abreFormulario() {
        startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }


    private void remover(Aluno selecionado) {
        adapter.remove(selecionado);
        dao.remover(selecionado);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }

    private void atualizaLista() {
        adapter.atualizar(dao.todos());
    }
}
