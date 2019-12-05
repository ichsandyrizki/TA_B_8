package tugaskelompokb8.apap.situ.rest;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PinjamanDetailResponse{

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private PinjamanDetail pinjamanDetail;


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PinjamanDetail getPinjamanDetail() {
        return this.pinjamanDetail;
    }

    public void setPinjamanDetail(PinjamanDetail pinjamanDetail) {
        this.pinjamanDetail = pinjamanDetail;
    }

}