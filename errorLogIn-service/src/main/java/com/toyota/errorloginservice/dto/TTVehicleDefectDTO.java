package com.toyota.errorloginservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.toyota.errorloginservice.domain.State;
import com.toyota.errorloginservice.domain.TTVehicle;
import com.toyota.errorloginservice.domain.TTVehicleDefectLocation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for tt_vehicle_defect used as input.
 */

public class TTVehicleDefectDTO {
    private String type;
    private String description;
    private State state;
    private LocalDateTime reportTime;

    private String reportedBy;


    private byte[] defectImage;

    private List<TTVehicleDefectLocation> location;

    public TTVehicleDefectDTO() {
    }

    public TTVehicleDefectDTO(String type, String description, State state,
                              LocalDateTime reportTime,
                              byte[] defectImage, List<TTVehicleDefectLocation> location) {
        this.type = type;
        this.description = description;
        this.state = state;
        this.reportTime = reportTime;
        this.defectImage = defectImage;
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = LocalDateTime.now();
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public byte[] getDefectImage() {
        return defectImage;
    }

    public void setDefectImage(byte[] defectImage) {
        this.defectImage = defectImage;
    }

    public List<TTVehicleDefectLocation> getLocation() {
        return location;
    }

    public void setLocation(List<TTVehicleDefectLocation> location) {
        this.location = location;
    }
}

