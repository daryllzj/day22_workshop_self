package paf.day22_workshop_self.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import paf.day22_workshop_self.model.RSVP;

@Repository
public class RSVPRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String GET_ALL_RSVP = "select * from rsvp";

    private final String SEARCH_BY_NAME = "SELECT * FROM rsvp WHERE full_name LIKE ?";

    private final String CREATE_RSVP = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values ( ?, ?, ?,?,?) ";

    private final String COUNT_RSVP = "select count(*) from rsvp ";

    private final String UPDATE_RSVP = "update rsvp set full_name = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";

    private final String FIND_RSVP_BY_EMAIL = "select * from rsvp where email = ?";

    public RSVP findByEmail (String email){

        return jdbcTemplate.queryForObject(FIND_RSVP_BY_EMAIL, BeanPropertyRowMapper.newInstance(RSVP.class), email);
    }

    
    public List<RSVP> getAllRSVP(){

        return jdbcTemplate.query(GET_ALL_RSVP, BeanPropertyRowMapper.newInstance(RSVP.class));

    }

    public List<RSVP> searchByName(String name){

        RowMapper<RSVP> rowMapper = new BeanPropertyRowMapper<>(RSVP.class);
        List<RSVP> rsvps = jdbcTemplate.query(SEARCH_BY_NAME, rowMapper, "%" + name + "%");
        return rsvps;

    }

    public Boolean save(RSVP rsvp){
        Integer result = jdbcTemplate.update(CREATE_RSVP, rsvp.getFullName(), rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments());

        return result > 0 ? true: false;
    }

    public Integer countRSVP() {

        return jdbcTemplate.queryForObject(COUNT_RSVP,Integer.class);
        
    }

    public Boolean update(RSVP rsvp){
        Integer result = jdbcTemplate.update(UPDATE_RSVP, rsvp.getFullName(), rsvp.getPhone(), rsvp.getConfirmationDate(), rsvp.getComments(), rsvp.getEmail());

        return result > 0 ? true: false;
    }


    
}
