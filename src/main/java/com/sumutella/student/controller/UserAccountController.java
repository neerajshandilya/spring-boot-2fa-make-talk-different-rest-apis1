package com.sumutella.student.controller;

import com.sumutella.student.entities.ConfirmationToken;
import com.sumutella.student.entities.User;
import com.sumutella.student.repositories.ConfirmationTokenRepository;
import com.sumutella.student.repositories.UserRepository;
import com.sumutella.student.services.EmailSenderService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sumutella
 * @time 10:49 PM
 * @since 12/7/2019, Sat
 */
@Controller
public class UserAccountController {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailSenderService emailSenderService;

    public UserAccountController(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailSenderService = emailSenderService;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String displayRegistration(Model model, User user)
    {
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String registerUser(Model model, User user)
    {

        User existingUser = userRepository.findByEmailIdIgnoreCase(user.getEmailId());
        if(existingUser != null)
        {
            model.addAttribute("message","This email already exists!");
            return "error";
        }
        else
        {
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmailId());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            model.addAttribute("emailId", user.getEmailId());

            return "successfulRegisteration";
        }

    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIdIgnoreCase(token.getUser().getEmailId());
            user.setEnabled(true);
            userRepository.save(user);
            return "accountVerified";
        }
        else
        {
            model.addAttribute("message","The link is invalid or broken!");
            return "error";
        }
    }
}
