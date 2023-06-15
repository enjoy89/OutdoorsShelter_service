package impacsys.jeondui.webservice.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shelter")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * 지역코드
     */
    private String area_code;

    /**
     * 시설일련번호
     */
    private String serial_number;

    /**
     * 시도명
     */
    private String city_name;

    /**
     * 시군구명
     */
    private String city_county_name;

    /**
     * 시설명
     */
    private String facility_name;

    /**
     * 도로명주소코드
     */
    private String street_name_address_code;

    /**
     * 법정동코드
     */
    private String bdong_code;

    /**
     * 행정동코드
     */
    private String hdong_code;

    /**
     * 상세주소
     */
    private String detailed_address;

    /**
     * 시설면적
     */
    private String facility_area;

    /**
     * 경도
     */
    private String longitude_code;

    /**
     * 위도
     */
    private String latitude_code;

    @Builder
    public Shelter(Long id, String area_code, String serial_number, String city_name, String city_county_name, String facility_name, String street_name_address_code, String bdong_code, String hdong_code, String detailed_address, String facility_area, String longitude_code, String latitude_code) {
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
