package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        alunosDefault();
    }

    private void alunosDefault() {
        AlunoDao alunoDao = new AlunoDao();
        alunoDao.salvar(new Aluno("Flavio", "12356", "flavio@flavio.coom"));
        alunoDao.salvar(new Aluno("Flavio Solci", "12356", "flaviosolci@flavio.coom"));
    }
}
