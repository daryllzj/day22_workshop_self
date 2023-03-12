package paf.day22_workshop_self.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paf.day22_workshop_self.model.RSVP;
import paf.day22_workshop_self.repo.RSVPRepo;

@Service
public class RSVPService {

    @Autowired
    RSVPRepo rsvpRepo;

    public List<RSVP> getAllRSVP(){

        return rsvpRepo.getAllRSVP();
        
    }

    public List<RSVP> searchByName(String name){

        return rsvpRepo.searchByName(name);
    }


    public Boolean save(RSVP rsvp){

        return rsvpRepo.save(rsvp);
    }

    public Integer countRSVP() {

        return rsvpRepo.countRSVP();
        
    }

    public Boolean update(RSVP rsvp){

        return rsvpRepo.update(rsvp);
    }

    public RSVP findByEmail (String email){

        return rsvpRepo.findByEmail(email);
    }
    
}
