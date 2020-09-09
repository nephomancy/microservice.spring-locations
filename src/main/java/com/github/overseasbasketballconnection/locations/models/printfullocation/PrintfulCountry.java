package com.github.overseasbasketballconnection.locations.models.printfullocation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.overseasbasketballconnection.locations.utils.Jsonifier;
import com.github.overseasbasketballconnection.locations.utils.services.EntityInterface;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leonhunter
 * @created 03/27/2020 - 6:46 PM
 */
@Entity
public class PrintfulCountry implements EntityInterface<Long> {
    @ManyToOne
    private PrintfulCountryResult result;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String code;

    @OneToMany
    private List<PrintfulState> states;

    public PrintfulCountry() {
    }

    public PrintfulCountry(PrintfulCountryResult result, Long id, String name, String code, List<PrintfulState> states) {
        this.result = result;
        this.id = id;
        this.name = name;
        this.code = code;
        this.states = states;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PrintfulCountryResult getResult() {
        return result;
    }

    public void setResult(PrintfulCountryResult result) {
        this.result = result;
    }

    public List<PrintfulState> getStates() {
        return states == null ? new ArrayList<>() : states;
    }

    public void setStates(List<PrintfulState> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return Jsonifier.jsonify(this);
    }
}
