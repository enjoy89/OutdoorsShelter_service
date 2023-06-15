package impacsys.jeondui.webservice.service.api;

import impacsys.jeondui.webservice.model.entity.Shelter;
import impacsys.jeondui.webservice.model.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {
    private final ShelterRepository shelterRepository;

    @Value("${key}") private static String OPEN_API_SERVICE_KEY;

    private static final String url = "http://apis.data.go.kr/1741000/EmergencyAssemblyArea_Earthquake2/getArea1List";

    @Transactional
    public JSONArray getData() throws IOException, ParseException {

        StringBuilder url_sb = new StringBuilder(url);
        url_sb.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + OPEN_API_SERVICE_KEY);
        url_sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + URLEncoder.encode("1", "UTF-8"));
        url_sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
        url_sb.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url_api = new URL(url_sb.toString());
        HttpURLConnection connection = (HttpURLConnection) url_api.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        System.out.println("Response Code: " + connection.getResponseCode());

        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        String line;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
        } else {
            bufferedReader = new BufferedReader((new InputStreamReader(connection.getInputStream())));
        }

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());

        JSONArray jsonArray = (JSONArray) jsonObject.get("EarthquakeOutdoorsShelter");  // key값에 해당하는 value값 반환
        JSONObject jsonObject_head = (JSONObject) jsonArray.get(0); // head
        JSONObject jsonObject_row = (JSONObject) jsonArray.get(1); // row
        JSONArray jsonArray_row = (JSONArray) jsonObject_row.get("row");

        bufferedReader.close();
        connection.disconnect();
        return jsonArray_row;
    }

    @Transactional
    public void saveData(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject data = (JSONObject) jsonArray.get(i);
            Shelter shelter = Shelter.builder()
                    .area_code(data.get("arcd").toString())
                    .serial_number(data.get("acmdfclty_sn").toString())
                    .city_name(data.get("ctprvn_nm").toString())
                    .city_county_name(data.get("sgg_nm").toString())
                    .facility_name(data.get("vt_acmdfclty_nm").toString())
                    .street_name_address_code(data.get("rdnmadr_cd").toString())
                    .bdong_code(data.get("bdong_cd").toString())
                    .hdong_code(data.get("hdong_cd").toString())
                    .detailed_address(data.get("dtl_adres").toString())
                    .facility_area(data.get("fclty_ar").toString())
                    .longitude_code(data.get("xcord").toString())
                    .latitude_code(data.get("ycord").toString())
                    .build();
            shelterRepository.save(shelter);
        }
    }
}
