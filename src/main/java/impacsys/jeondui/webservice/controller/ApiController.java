package impacsys.jeondui.webservice.controller;

import impacsys.jeondui.webservice.service.api.ApiService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;

    @GetMapping("/api")
    public void call_api() throws IOException, ParseException {
        apiService.saveData(apiService.getData());
    }
}
