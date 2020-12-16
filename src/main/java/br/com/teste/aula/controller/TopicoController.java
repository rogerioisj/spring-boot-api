package br.com.teste.aula.controller;

import br.com.teste.aula.controller.dto.TopicDetailsDTO;
import br.com.teste.aula.controller.dto.TopicoDTO;
import br.com.teste.aula.controller.form.TopicUpdateForm;
import br.com.teste.aula.controller.form.TopicoForm;
import br.com.teste.aula.models.Topico;
import br.com.teste.aula.repository.CursoRepository;
import br.com.teste.aula.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDTO> list(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDTO.converter(topicos);
        } else{
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
            return TopicoDTO.converter(topicos);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDTO> create(@RequestBody @Valid TopicoForm form, UriComponentsBuilder builder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = builder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchOne(@PathVariable Long id){

        Optional<Topico> optional = topicoRepository.findById(id);
        if(optional.isPresent()){
            return ResponseEntity.ok(new TopicDetailsDTO(optional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDTO> update(@PathVariable Long id, @RequestBody @Valid TopicUpdateForm form){

        Optional<Topico> optional = topicoRepository.findById(id);

        if(optional.isPresent()){
            Topico topico = form.update(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDTO(topico));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Topico> optional = topicoRepository.findById(id);

        if(optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Topico excluido com sucesso");
        } else{
            return ResponseEntity.notFound().build();
        }

    }
}
