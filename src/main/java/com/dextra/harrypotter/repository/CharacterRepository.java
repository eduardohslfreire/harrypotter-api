package com.dextra.harrypotter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dextra.harrypotter.domain.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

	List<Character> findAllByHouse(String Id);
}
