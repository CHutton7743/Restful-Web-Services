package com.learningSpring.rest.webservices.restfulwebservices.pawn;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.learningSpring.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PawnJPAResource {
    @Autowired
    private PawnRepository pawnRepository;

    @GetMapping("/jpa/users")
    public List<Pawn> retrieveAllPawns() {
        return pawnRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Pawn> retrievePawn(@PathVariable int id) throws UserNotFoundException {
        Optional<Pawn> pawn = pawnRepository.findById(id);

        if (!pawn.isPresent()) {
            throw new UserNotFoundException("id =" + id);
        }
        EntityModel<Pawn> model = EntityModel.of(pawn.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllPawns());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deletePawn(@PathVariable int id) throws UserNotFoundException {
        pawnRepository.deleteById(id);
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> postPawn(@Valid @RequestBody Pawn pawn) {
        Pawn savedPawn = pawnRepository.save(pawn);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPawn.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
