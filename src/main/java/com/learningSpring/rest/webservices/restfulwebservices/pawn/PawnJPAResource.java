package com.learningSpring.rest.webservices.restfulwebservices.pawn;

import com.learningSpring.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PawnJPAResource {
    @Autowired
    private UserDaoService service;
    @Autowired
    private PawnRepository pawnRepository;

    @GetMapping("/jpa/users")
    public List<Pawn> retrieveAllUsers() {
        return pawnRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Pawn> retrieveUser(@PathVariable int id) throws UserNotFoundException {
        Optional<Pawn> pawn = pawnRepository.findById(id);

        if (!pawn.isPresent()) {
            throw new UserNotFoundException("id =" + id);
        }
        EntityModel<Pawn> model = EntityModel.of(pawn.get());
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException {
        Pawn pawn = service.deleteById(id);
        if (pawn == null) {
            throw new UserNotFoundException("id =" + id);
        }
    }
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> postUser(@Valid @RequestBody Pawn pawn) {
        Pawn savedPawn = service.save(pawn);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPawn.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
