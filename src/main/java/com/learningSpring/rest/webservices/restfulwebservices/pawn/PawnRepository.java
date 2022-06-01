package com.learningSpring.rest.webservices.restfulwebservices.pawn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PawnRepository extends JpaRepository<Pawn, Integer> {

}
