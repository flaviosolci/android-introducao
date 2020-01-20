package br.com.alura.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.R;
import br.com.alura.dao.AlunoDao;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITLE_APP_BAR = "Lista de alunos";
    private AlunoDao dao = new AlunoDao();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITLE_APP_BAR);

        cofiguraFABNovoAluno();

    }

    @Override
    protected void onResume() {
        super.onResume();

        configuraLista();

    }

    private void configuraLista() {
        ListView listDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        listDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }


    private void cofiguraFABNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_list_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(abreFormulario());
    }

    private View.OnClickListener abreFormulario() {
        return v -> startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
    }
}
