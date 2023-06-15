package impacsys.jeondui.webservice.dto;

import impacsys.jeondui.webservice.model.entity.Shelter;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShelterDto {
    private Long id;
    private String area_code;
    private String serial_number;
    private String city_name;
    private String city_county_name;
    private String facility_name;
    private String street_name_address_code;
    private String bdong_code;
    private String hdong_code;
    private String detailed_address;
    private String facility_area;
    private String longitude_code;
    private String latitude_code;

    public Shelter toEntity() {
        return Shelter.builder()
                .id(id)
                .area_code(area_code)
                .serial_number(serial_number)
                .city_name(city_name)
                .city_county_name(city_county_name)
                .facility_name(facility_name)
                .street_name_address_code(street_name_address_code)
                .bdong_code(bdong_code)
                .hdong_code(hdong_code)
                .detailed_address(detailed_address)
                .facility_area(facility_area)
                .longitude_code(longitude_code)
                .latitude_code(latitude_code)
                .build();
    }

    @Builder
    public ShelterDto(Long id, String area_code, String serial_number, String city_name, String city_county_name, String facility_name, String street_name_address_code, String bdong_code, String hdong_code, String detailed_address, String facility_area, String longitude_code, String latitude_code) {
        this.id = id;
        this.area_code = area_code;
        this.serial_number = serial_number;
        this.city_name = city_name;
        this.city_county_name = city_county_name;
        this.facility_name = facility_name;
        this.street_name_address_code = street_name_address_code;
        this.bdong_code = bdong_code;
        this.hdong_code = hdong_code;
        this.detailed_address = detailed_address;
        this.facility_area = facility_area;
        this.longitude_code = longitude_code;
        this.latitude_code = latitude_code;
    }
}
