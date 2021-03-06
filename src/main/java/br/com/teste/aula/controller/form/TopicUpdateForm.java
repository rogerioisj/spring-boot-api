package br.com.teste.aula.controller.form;

import br.com.teste.aula.models.Topico;
import br.com.teste.aula.repository.TopicoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicUpdateForm {
    @NotNull
    @NotEmpty
    private String titulo;

    @NotNull
    @NotEmpty
    private String mensagem;

    public TopicUpdateForm( @NotNull @NotEmpty String titulo, @NotNull @NotEmpty String mensagem ) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo( ) {
        return titulo;
    }

    public void setTitulo( String titulo ) {
        this.titulo = titulo;
    }

    public String getMensagem( ) {
        return mensagem;
    }

    public void setMensagem( String mensagem ) {
        this.mensagem = mensagem;
    }

    public Topico update( Long id, TopicoRepository topicoRepository ) {
        Topico topico = topicoRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
    }
}
