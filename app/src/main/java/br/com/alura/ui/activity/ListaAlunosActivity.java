package br.com.alura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.R;
import br.com.alura.dao.AlunoDao;
import br.com.alura.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APP_BAR = "Lista de alunos";
    private AlunoDao dao = new AlunoDao();
    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE_APP_BAR);

        alunosDefault();
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
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            remover(adapter.getItem(menuInfo.position));
        }
        return super.onContextItemSelected(item);

    }


    private void configuraLista() {
        ListView listDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listDeAlunos.setAdapter(adapter);
        listDeAlunos.setOnItemClickListener((parent, view, position, id) -> configurarClickItem(parent, position));
        registerForContextMenu(listDeAlunos);
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

    private void alunosDefault() {
        dao.salvar(new Aluno("Flavio", "12356", "flavio@flavio.coom"));
        dao.salvar(new Aluno("Flavio Solci", "12356", "flaviosolci@flavio.coom"));
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
        adapter.clear();
        adapter.addAll(dao.todos());
    }
}
