package paf.day22_workshop_self.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import paf.day22_workshop_self.model.RSVP;
import paf.day22_workshop_self.service.RSVPService;

@RestController
@RequestMapping(path="/")
public class RSVPRestController {

    @Autowired
    RSVPService rsvpService;

    @GetMapping(path="/api/rsvp" , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RSVP>> getAllCustomers() {

        List<RSVP> rsvps = new LinkedList<>();

        rsvps = rsvpService.getAllRSVP();

        if (rsvps.isEmpty()){
            return ResponseEntity.noContent().build();
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
        }

        return ResponseEntity.ok().body(rsvps);
        //  return new ResponseEntity<>(rooms, HttpStatus.OK);
        
    }

    @GetMapping(path="/api/rsvp/" , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchByName(@RequestParam("q") String name) {

        List<RSVP> rsvps = new LinkedList<>();

        rsvps = rsvpService.searchByName(name);

        if (rsvps.isEmpty()){
            // return ResponseEntity.noContent().build();
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            String errorMessage = "Guest with name: " + name + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    
        }

        return ResponseEntity.ok().body(rsvps);
        //  return new ResponseEntity<>(rooms, HttpStatus.OK);
        
    }

    @PostMapping("/api/rsvp")
    public ResponseEntity<Boolean> addRSVP(@RequestBody MultiValueMap<String, String> form) throws java.text.ParseException {
        // String idString = form.getFirst("id");
        // Integer id = Integer.parseInt(idString);
        String fullName = form.getFirst("fullName");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        String confirmationDateString = form.getFirst("confirmationDate");
        Date confirmationDate = null;
            if (confirmationDateString != null) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsed = format.parse(confirmationDateString);
                confirmationDate = new java.sql.Date(parsed.getTime());
            } catch (ParseException e) {
                // Handle the parse error
            }
            }

        String comments = form.getFirst("comments");
        
        RSVP rsvp = new RSVP(fullName, email, phone, confirmationDate, comments);

        Boolean created = rsvpService.save(rsvp);

        if(created){
            // return ResponseEntity.status(HttpStatus.CREATED).build();
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }

    @GetMapping(path="/count" , produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> countRSVP() {

        Integer count = rsvpService.countRSVP();

        if (count <= 0){
            // return ResponseEntity.noContent().build();
            // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            String errorMessage = "No RSVP";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    
        }

        return ResponseEntity.ok().body(count);
        //  return new ResponseEntity<>(rooms, HttpStatus.OK);
        
    }


    @PutMapping(path = "/api/rsvp/{email}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateRSVP(@PathVariable("email") String email, @RequestBody RSVP rsvpRequest) {

        RSVP rsvp = rsvpService.findByEmail(email);

        if (rsvp != null) {
            rsvp.setFullName(rsvpRequest.getFullName());
            rsvp.setPhone(rsvpRequest.getPhone());
            rsvp.setConfirmationDate(rsvpRequest.getConfirmationDate());
            rsvp.setComments(rsvpRequest.getComments());

            boolean updated = rsvpService.update(rsvp);

            if (updated) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            String errorMessage = "No RSVP found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

    }

    

    // @PutMapping(path = "/api/rsvp/update", produces= MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<?> updateRSVP(@RequestBody MultiValueMap<String, String> form) throws java.text.ParseException {

    //         String fullName = form.getFirst("fullName");
    //         String email = form.getFirst("email");
    //         String phone = form.getFirst("phone");
    //         String confirmationDateString = form.getFirst("confirmationDate");
    //         Date confirmationDate = null;
    //             if (confirmationDateString != null) {
    //             try {
    //                 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    //                 java.util.Date parsed = format.parse(confirmationDateString);
    //                 confirmationDate = new java.sql.Date(parsed.getTime());
    //             } catch (ParseException e) {
    //                 // Handle the parse error
    //             }
    //             }
    //         String comments = form.getFirst("comments");
            
    //         RSVP updatedRsvp = new RSVP(fullName, email, phone, confirmationDate, comments);
    
    //         Boolean created = rsvpService.update(updatedRsvp);
    
    //         if(created){
    //             // return ResponseEntity.status(HttpStatus.CREATED).build();
    //             return new ResponseEntity<>(created, HttpStatus.CREATED);
    //         } else{
    //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //         }

    // }



    
}
