package br.com.rocketseat.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraRota")
public class Controller {
    /*
    * Métodos de acesso do HTTP:
    *
    * GET - Buscar uma informação
    * POST - Adicionar um dado/informação
    * PUT - Alterar dados/informações
    * DELETE - Remover um dado
    * PATCH - Alterar somente uma parte da informação/dado
    *
    */

    @GetMapping("/")
    public String primeiraMensagem() {
        return "Hello World!";
    }
}
