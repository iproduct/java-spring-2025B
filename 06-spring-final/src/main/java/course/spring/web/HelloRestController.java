package course.spring.web;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.util.MimeTypeUtils.*;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping({"name", "name/{name}"})
    public String sayHello(@PathVariable(name = "name", required = false) String name) {
        return String.format("Hello %s, from Spring MVC!", name != null ? name : "Guest");
    }

    @GetMapping("appointments")
    public String getAppointments(
            @RequestParam(value = "from", required = false)String from,
            @RequestParam(value = "to", required = false)String to,
            @RequestParam(value = "doctor", required = false)String doctor,
            @RequestParam(value = "patient", required = false)String patient
            ) {
        var sb = new StringBuilder();
        sb.append("From: ").append(from != null ? from : "not_specified");
        sb.append("<br>To: ").append(to != null ? to : "not_specified");
        sb.append("<br>Doctor: ").append(doctor != null ? doctor : "not_specified");
        sb.append("<br>Patient: ").append(patient != null ? patient : "not_specified");
        return sb.toString();
    }

    @GetMapping(path = "headers", produces = TEXT_HTML_VALUE)
    public String acceptHeader(@RequestHeader("Accept") String acceptHeader) {
        return String.format("<h1>Accept: %s</h1>", acceptHeader);
    }

   @GetMapping(path = "headers", produces = APPLICATION_JSON_VALUE)
    public Map<String, String> allHeaders(@RequestHeader MultiValueMap headers) {
        return headers.toSingleValueMap();
    }


}
