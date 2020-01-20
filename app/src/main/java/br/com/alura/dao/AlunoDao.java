package br.com.alura.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.model.Aluno;

public class AlunoDao {
    private static final List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> todos() {
        return Collections.unmodifiableList(alunos);
    }
}
