package com.learningSpring.rest.webservices.restfulwebservices.pawn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<Pawn> pawns = new ArrayList<>();

    private static int usersCount = 3;
    static {
        pawns.add(new Pawn(1, "Codey", new Date()));
        pawns.add(new Pawn(2, "Natalie", new Date()));
        pawns.add(new Pawn(3, "Victoria", new Date()));
        pawns.add(new Pawn(4, "Valencia", new Date()));
    }
    public List<Pawn> findAll() {
        return pawns;
    }
    public Pawn save(Pawn pawn) {
        if (pawn.getId() == 0) {
            pawn.setId(usersCount++);
        }
        pawns.add(pawn);
        return pawn;
    }
    public Pawn findUser(int id) {
        for(Pawn pawn : pawns) {
            if(pawn.getId() == id) {
                return pawn;
            }
        }
        return null;
    }

    public Pawn deleteById(int id) {
        Iterator<Pawn> iterator = pawns.iterator();
        while(iterator.hasNext()) {
            Pawn pawn = iterator.next();
            if(pawn.getId() == id) {
                iterator.remove();
                return pawn;
            }
        }
        return null;
    }
}
