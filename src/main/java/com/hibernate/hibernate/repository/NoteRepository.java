package com.hibernate.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hibernate.hibernate.models.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	
}
