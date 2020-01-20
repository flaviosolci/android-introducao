package br.com.alura.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.R;
import br.com.alura.dao.AlunoDao;
import br.com.alura.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR = "Novo aluno";
    
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private AlunoDao dao = new AlunoDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APP_BAR);

        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        Button botalSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        botalSalvar.setOnClickListener(v -> {
            Aluno aluno = criaAluno(campoNome, campoTelefone, campoEmail);
            salvar(aluno);

        });
    }

    private void salvar(Aluno aluno) {
        dao.salvar(aluno);
        finish();
    }

    private Aluno criaAluno(EditText campoNome, EditText campoTelefone, EditText campoEmail) {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }
}
