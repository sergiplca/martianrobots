package com.sergi.martianrobots.repository;

import com.sergi.martianrobots.model.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
}
