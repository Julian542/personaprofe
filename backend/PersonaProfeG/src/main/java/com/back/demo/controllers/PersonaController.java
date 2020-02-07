package com.back.demo.controllers;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.back.demo.dtos.PersonaDto;
import com.back.demo.services.PersonaService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "api/v1/personap")
public class PersonaController {
	
	public PersonaService service;

	public PersonaController(PersonaService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en GetAll\"}");
		}
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en GetOne\"}");
		}
	}
	
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody PersonaDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en POST\"}");
		}
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody PersonaDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en PUT\"}");
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable int id) {
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Registro eliminado\"}");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error ocurrido en DELETE\"}");
		}
	}
}
