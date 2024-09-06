package br.edu.univille.poo.jpa.controller;

import br.edu.univille.poo.jpa.entidade.Pessoa;
import br.edu.univille.poo.jpa.servico.PessoaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/pessoa")
public class PessoaRestController {

    @Autowired
    private PessoaServico pessoaServico;

    @GetMapping
    public List<Pessoa> obterTodos(){
        return pessoaServico.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> obterPeloId(@PathVariable Long id){
        var opt = pessoaServico.obterPeloId(id);
        return opt.map(pessoa -> new ResponseEntity<>(pessoa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody Pessoa pessoa){
        try {
            pessoa = pessoaServico.incluir(pessoa);
            return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@RequestBody Pessoa pessoa){
        try {
            pessoa = pessoaServico.atualizar(pessoa);
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> excluir(@RequestBody Pessoa pessoa){
        try{
            pessoaServico.excluir(pessoa);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
