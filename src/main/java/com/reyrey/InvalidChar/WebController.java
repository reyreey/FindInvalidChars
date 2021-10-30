package com.reyrey.InvalidChar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class WebController implements WebMvcConfigurer {

    @RequestMapping("/")
    public String showForm() {

        return "form";
    }

    @RequestMapping(
            value = "/",
            params = { "input" },
            method = GET)
    @ResponseBody
    public HashMap<String, String> findInvalidChars(@RequestParam("input") String input) {

        //valid characters: alphabet and digits
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(input);
        StringBuilder invalidChars= new StringBuilder();
        while(matcher.find()) {
            Character currChar=input.charAt(matcher.start());
            invalidChars.append(currChar+" ");
        }
        //JSON output
        HashMap<String, String> output = new HashMap<>();
        System.out.print(input);
        output.put("input", input);
        output.put("invalid char(s)", invalidChars.toString());

        return output;
    }

}