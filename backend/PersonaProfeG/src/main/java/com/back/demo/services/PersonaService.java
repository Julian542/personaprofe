package com.back.demo.services;

import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

import com.back.demo.dtos.PersonaDto;
import com.back.demo.entities.Persona;
import com.back.demo.repositories.PersonaRepository;

@Service
public class PersonaService {
	
	public PersonaRepository repository;
	
	public PersonaService(PersonaRepository repository) {
		this.repository = repository;
	}
	
	//metodos
	@Transactional
	public List<PersonaDto> findAll() throws Exception{
		
		List<Persona> entities = repository.findAll();
		List<PersonaDto> dtos = new ArrayList();
		
		try {
			
			for(Persona i : entities) {
				
				PersonaDto unDto = new PersonaDto();
				
				unDto.setId(i.getId());
				unDto.setNombre(i.getNombre());
				unDto.setApellido(i.getApellido());
				unDto.setDni(i.getDni());
				
				dtos.add(unDto);
			}
			 return dtos;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	
	@Transactional
	public PersonaDto findById(int id) throws Exception{
		
		Optional<Persona> entityOptional = repository.findById(id);
		PersonaDto unDto = new PersonaDto();
		
		try {
			Persona entidad = entityOptional.get();
			
			unDto.setId(entidad.getId());
			unDto.setNombre(entidad.getNombre());
			unDto.setApellido(entidad.getApellido());
			unDto.setDni(entidad.getDni());
			
			return unDto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public PersonaDto save(PersonaDto dto) throws Exception{
		
		Persona entity = new Persona();
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDni(dto.getDni());
		
		try {
			
			entity = repository.save(entity);
			dto.setId(entity.getId());
			
			return dto;
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	@Transactional
	public PersonaDto update(int id, PersonaDto dto) throws Exception{
		
		Optional<Persona> entityOptional = repository.findById(id);
		try {
			Persona entidad = entityOptional.get();
			entidad.setId(dto.getId());
			entidad.setNombre(dto.getNombre());
			entidad.setApellido(dto.getApellido());
			entidad.setDni(dto.getDni());
			
			repository.save(entidad);
			dto.setId(entidad.getId());
			
			return dto;
			
		}catch(Exception e) {
			throw new Exception();
		}
	}
	
	public boolean delete(int id) throws Exception{
		try {
			if(repository.existsById(id)) {
				repository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception();
		}
	}
}
