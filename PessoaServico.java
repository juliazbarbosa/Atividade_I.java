package br.edu.univille.poo.jpa.servico;

import br.edu.univille.poo.jpa.entidade.Pessoa;
import br.edu.univille.poo.jpa.repositorio.PessoaRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;
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

    public void excluir (Pessoa pessoa){
        var opt = pessoaRepository.findById(pessoa.getId());
        if (opt.isEmpty())
            throw new RuntimeException("Pessoa nao encontrada")

    }
    pessoaRepository.delete(pessoa)
}
    public Pessoa incluir(Pessoa pessoa){
        pessoa.setId(0);
        if(Strings.isBlank(pessoa.gtNome())){
            throw new RuntimeException("Nome deve ser informado")
        }
        if(Strings.isBlank(pessoa.getCpf())){
            throw new RuntimeException("Cpf deve ser informado")
        }
        if(Strings.isBlank(pessoa.getNome())){
            throw new RuntimeException("Cpf deve ser informado")
    }
