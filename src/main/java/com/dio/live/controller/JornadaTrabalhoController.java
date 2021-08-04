package com.dio.live.controller;

import com.dio.live.model.JornadaTrabalho;
import com.dio.live.service.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/jornada")
public class JornadaTrabalhoController {

    /*
    *   Jornada Trabalho Controller recebera as requisições do tipo: POST, GET e DELETE
    *   e encaminhara para seus respetivos methods e 'Service'
    * */

    @Autowired
    JornadaService jornadaService;

    @PostMapping
    public JornadaTrabalho createJornada(@RequestBody JornadaTrabalho jornadaTrabalho) {
        return jornadaService.saveJornada(jornadaTrabalho);
    }

    @GetMapping
    public List<JornadaTrabalho> getJornadaList() {
        return jornadaService.findAll();
    }

    /*
    @GetMapping("/{idJornada}")
    public JornadaTrabalho getJornadaByID(@PathVariable("idJornada") Long idJornada) throws Exception {
        return jornadaService.findById(idJornada).orElseThrow(() -> new Exception("Jornada não localizada."));
    }
    */
    //  OU DESTE MODO, para tratar o Status Quo da API
    @GetMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> getJornadaByID(@PathVariable("idJornada") Long idJornada) {
        //  new Exception
        //  OU utilizando
        //  new NoSuchElementException("Not found!")
        return ResponseEntity.ok(jornadaService.getById(idJornada).orElseThrow(() -> new NoSuchElementException("Jornada não localizada.")));
    }

    @PutMapping
    public JornadaTrabalho updateJornada(@RequestBody JornadaTrabalho jornadaTrabalho) {
        return jornadaService.updateJornada(jornadaTrabalho);
    }

    @DeleteMapping("/{idJornada}")
    public ResponseEntity deleteByID(@PathVariable("idJornada") Long idJornada) {
        try {
            jornadaService.deleteJornada(idJornada);
        } catch(Exception ex) {
            return (ResponseEntity) ResponseEntity.notFound();
        }
        return (ResponseEntity) ResponseEntity.ok();
    }
}
