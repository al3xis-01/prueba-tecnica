package com.servifacil.backendapp.library.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SystemController {


    @GetMapping
    public ResponseEntity<IndexResponse> index(){
        IndexResponse entity = new  IndexResponse("Welcome Servifacil");

        return ResponseEntity.ok(entity);
    }
    
    public class IndexResponse {
        public String message;
        public Long time;
        public IndexResponse(String message){
            this.message = message;
            this.time = System.nanoTime();
        }
    }
}
