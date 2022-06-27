package dodorian.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/aop")
    public String dodorian(@RequestParam String name) {
        System.out.println("name ---->"+ name);

        return "name ---->"+ name;
    }
}
