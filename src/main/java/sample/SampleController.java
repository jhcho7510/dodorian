package sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class SampleController {

    @GetMapping("aop")
    public void dodorian(@RequestParam String name) {
        System.out.println("name ---->"+ name);
    }
}
