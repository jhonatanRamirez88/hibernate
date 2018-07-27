package com.hibernate.hibernate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.hibernate.exception.ResourceNotFoundException;
import com.hibernate.hibernate.models.Note;
import com.hibernate.hibernate.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping("/notes")
	public List<Note> getAllNotes(){
		return noteRepository.findAll();
	}
	
	@PostMapping("/save")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteRepository.save(note);
	}
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable("id") Long id) {
		return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}
	@PutMapping("/notes/{id}")
	public Note update(@PathVariable("id") Long id, @Valid @RequestBody Note noteDetails) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		note.setTitle(noteDetails.getTitle());
		note.setContaints(noteDetails.getContaints());
		return noteRepository.save(note);
	}
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable("id") Long id){
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		noteRepository.delete(note);
		return ResponseEntity.ok().build();
	}
}
