package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_NOVO_ALUNO_APP_BAR = "Novo Aluno";
    private static final String TITULO_EDITAR_ALUNO_APP_BAR = "Editando Aluno";

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private final AlunoDao dao = new AlunoDao();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializarCampos();

        carregaAluno();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_formulario_aluno_menu_salvar) {
            preencherAluno(campoNome, campoTelefone, campoEmail);
            salvar(aluno);
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaAluno() {
        if (getIntent().hasExtra(ConstanteActivities.CHAVE_ALUNO)) {
            setTitle(TITULO_EDITAR_ALUNO_APP_BAR);
            aluno = getIntent().getParcelableExtra(ConstanteActivities.CHAVE_ALUNO);
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
        } else {
            setTitle(TITULO_NOVO_ALUNO_APP_BAR);
            aluno = new Aluno();
        }
    }


    private void inicializarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }


    private void salvar(Aluno aluno) {
        dao.salvar(aluno);
        finish();
    }

    private void preencherAluno(EditText campoNome, EditText campoTelefone, EditText campoEmail) {
        aluno.setNome(campoNome.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
    }
}
