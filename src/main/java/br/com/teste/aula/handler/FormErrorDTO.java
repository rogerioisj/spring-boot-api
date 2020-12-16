package br.com.teste.aula.handler;

public class FormErrorDTO {

    private String erro;
    private String message;

    public FormErrorDTO(String erro, String message) {
        this.erro = erro;
        this.message = message;
    }

    public String getErro() {
        return erro;
    }

    public String getMessage() {
        return message;
    }
}
