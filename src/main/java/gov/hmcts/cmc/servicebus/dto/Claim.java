package gov.hmcts.cmc.servicebus.dto;

import java.io.Serializable;

public class Claim implements Serializable {
    private int id;
    private String claim;

    public Claim(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Claim(int id, String claim) {
        this.id = id;
        this.claim = claim;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }
}
