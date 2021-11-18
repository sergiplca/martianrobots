package com.sergi.martianrobots.repository;

import com.sergi.martianrobots.model.Scent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ScentRepository extends JpaRepository<Scent, Long> {

    boolean existsScent(@Param("x_coord") int xCoord,
                    @Param("y_coord") int yCoord,
                    @Param("orientation") String orientation);
}
