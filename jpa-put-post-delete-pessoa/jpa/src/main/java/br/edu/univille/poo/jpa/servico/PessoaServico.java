package br.edu.univille.poo.jpa.servico;

import br.edu.univille.poo.jpa.entidade.Pessoa;
import br.edu.univille.poo.jpa.repositorio.PessoaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServico {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> obterTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> obterPeloId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa incluir(Pessoa pessoa) {
        pessoa.setId(0);
        if(Strings.isBlank(pessoa.getNome())){
            throw new RuntimeException("Nome não informado.");
        }
        if(Strings.isBlank(pessoa.getCpf())){
            throw new RuntimeException("CPF não informado.");
        }
        if(!pessoaRepository.findAllByCpf(pessoa.getCpf()).isEmpty()){
            throw new RuntimeException("CPF já está cadastrado.");
        }
        if(!pessoaRepository.findAllByNomeContaining(pessoa.getNome()).isEmpty()){
            throw new RuntimeException("Nome já está cadastrado.");
        }
        pessoa = pessoaRepository.save(pessoa);
        return pessoa;
    }

    public Pessoa atualizar(Pessoa pessoa) {
        Pessoa antigo = pessoaRepository.findById(pessoa.getId()).orElse(null);
        if(antigo == null){
            throw new RuntimeException("Pessoa não foi encontrada.");
        }
        antigo.setNome(pessoa.getNome());
        antigo.setCpf(pessoa.getCpf());
        antigo.setEmail(pessoa.getEmail());
        if(Strings.isBlank(pessoa.getNome())){
            throw new RuntimeException("Nome não informado.");
        }
        if(Strings.isBlank(pessoa.getCpf())){
            throw new RuntimeException("CPF não informado.");
        }
        for(var p : pessoaRepository.findAllByCpf(pessoa.getCpf())){
            if(antigo.getId() != p.getId()){
                throw new RuntimeException("CPF já está cadastrado.");
            }
        }
        for(var p : pessoaRepository.findAllByNomeContaining(pessoa.getNome())){
            if(antigo.getId() != p.getId()){
                throw new RuntimeException("Nome já está cadastrado.");
            }
        }
        return pessoaRepository.save(antigo);
    }

    public void excluir(Pessoa pessoa) {
        var antigo = pessoaRepository.findById(pessoa.getId()).orElse(null);
        if(antigo == null){
            throw new RuntimeException("Pessoa não encontrada.");
        }
        pessoaRepository.delete(antigo);
    }
}
