package com.toyota.errorloginservice.dto;

import java.util.List;

/**
 * Class for storing image and location dto  together
 */
public class ImageDTO {
    private byte[] image;
    private List<TTVehicleDefectLocationDTO> locationDTO;

    public ImageDTO() {
    }

    public ImageDTO(byte[] image, List<TTVehicleDefectLocationDTO> locationDTO) {
        this.image = image;
        this.locationDTO = locationDTO;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<TTVehicleDefectLocationDTO> getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(List<TTVehicleDefectLocationDTO> locationDTO) {
        this.locationDTO = locationDTO;
    }
}
