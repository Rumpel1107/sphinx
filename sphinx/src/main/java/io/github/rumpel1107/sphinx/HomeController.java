package io.github.rumpel1107.sphinx;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{

    @GetMapping("/")
    public String home(Model model)
    {
        
        // Get the current date and time and format it
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy, h:mm:ss a"));
        
        // Add the formatted date-time string to the model
        model.addAttribute("serverTime", currentDateTime);
        
        return "index";
    }
}