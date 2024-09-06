package br.edu.univille.poo.jpa.repositorio;

import br.edu.univille.poo.jpa.entidade.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findAllByNomeContaining(String nome);

    List<Pessoa> findAllByCpf(String cpf);

}
