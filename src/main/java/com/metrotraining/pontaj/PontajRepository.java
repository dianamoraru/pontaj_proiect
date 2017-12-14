package com.metrotraining.pontaj;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PontajRepository extends JpaRepository<Pontaj, Long> {

	public ArrayList<Pontaj> findAll();

}
