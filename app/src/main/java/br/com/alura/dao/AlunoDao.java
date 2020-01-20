package br.com.alura.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.alura.model.Aluno;

public class AlunoDao {
    private static final List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        if (aluno.temIdValido()) {
            editar(aluno);
        } else {
            inserir(aluno);
        }

    }

    private void inserir(Aluno aluno) {
        aluno.setId(UUID.randomUUID().toString());
        alunos.add(aluno);
    }

    private void editar(Aluno aluno) {
        int indexOf = alunos.indexOf(aluno);
        if (indexOf != -1) {
            alunos.set(indexOf, aluno);
        }
    }


    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public void remover(Aluno aluno) {
        alunos.remove(aluno);
    }
}
