package com.protosstechnology.train.bootexamine.controller;

import com.protosstechnology.train.bootexamine.model.Document;
import com.protosstechnology.train.bootexamine.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by nitchpon.t on 31/8/2563.
 */
@Slf4j
@RestController
@RequestMapping("/document")
public class DocumentController {

    private DocumentRepository repository;

    @Autowired
    public void setRepository(DocumentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable("id") String id) {
        log.info("GET /document/{} -> DocumentController.getDocument(id=\"{}\")", id, id);
        Long longId = Long.getLong(id);

        Optional<Document> queryResult = repository.findById(longId);
        if(!queryResult.isPresent()) {
            log.warn("DocumentController.getDocument(id) Document not found for id={}", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(queryResult.get());
    }

    @PostMapping
    public ResponseEntity<Document> postDocument(@RequestBody Document document) {
        log.info("Post /document -> DocumentController.postDocument(document)");
        log.info("DocumentController.postDocument(document) document.id={}", document.getId());
        log.info("DocumentController.postDocument(document) document.documentNumber={}", document.getDocumentNumber());
        log.info("DocumentController.postDocument(document) document.description={}", document.getDescription());

        Document savedDocument = repository.save(document);
        return ResponseEntity.ok(savedDocument);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> putDocument(@PathVariable String id, @RequestBody Document document) {
        log.info("Put /document/{} -> DocumentController.puttDocument(id, document)", id);
        log.info("DocumentController.putDocument(id, document) id={}", id);
        log.info("DocumentController.putDocument(id, document) document.id={}", document.getId());
        log.info("DocumentController.putDocument(id, document) document.documentNumber={}", document.getDocumentNumber());
        log.info("DocumentController.putDocument(id, document) document.description={}", document.getDescription());

        Long longId = Long.getLong(id);
        if(document.getId() != null && longId != document.getId()) {
            log.error("DocumentController.putDocument(id, document) id in PathVariable({}) and in RequestBody({}) are different.", id, document.getId().toString());
            return ResponseEntity.badRequest().build();
        }

        Document savedDocument = repository.save(document);
        return ResponseEntity.ok(savedDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") String id) {
        log.info("DELETE /document/{} -> DocumentController.deleteDocument(id=\"{}\")", id, id);
        Long longId = Long.getLong(id);
        repository.deleteById(longId);
        return ResponseEntity.ok("");
    }

}
