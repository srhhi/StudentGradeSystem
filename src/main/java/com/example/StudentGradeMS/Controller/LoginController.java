package com.example.StudentGradeMS.Controller;

import com.example.StudentGradeMS.Model.UserLogin;
import com.example.StudentGradeMS.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {

        // Find user by username
        Optional<UserLogin> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            UserLogin userLogin = userOptional.get();

            // Check if the password matches
            if (userLogin.getPassword().equals(password)) {
                return "redirect:/index"; // Redirect to index page after successful login
            }
        }

        // If no match, show an error message
        model.addAttribute("error", "Invalid username or password");
        return "login"; // Stay on login page if credentials are wrong
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index"; // Show the index page after successful login
    }
}

