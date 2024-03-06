package network.doctors.SanagaHealthNetwork.controllers;


import net.bytebuddy.utility.RandomString;
import network.doctors.SanagaHealthNetwork.entity.User;
import network.doctors.SanagaHealthNetwork.exceptions.ApplicationException;
import network.doctors.SanagaHealthNetwork.model.ResetPassword;
import network.doctors.SanagaHealthNetwork.service.PasswordResetService;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class PasswordResetController  {

//    @Autowired
    private final JavaMailSender mailSender;
//
//    public PasswordResetController(JavaMailSender javaMailSender) {
//        this.mailSender = javaMailSender;
//    }




    private final PasswordResetService passwordResetService;


    public PasswordResetController(JavaMailSender mailSender, PasswordResetService passwordResetService) {
        this.mailSender = mailSender;
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/forgot-password")
    public String resetForm(Model model) {
        model.addAttribute("resetPassword", new ResetPassword());
        return "forgot_password_form";
    }

    //sends password reset email
    @PostMapping("/forgot-password")
    public String processForgotForm(HttpServletRequest request, Model model, @ModelAttribute("resetPassword") ResetPassword resetPassword)
    throws ApplicationException {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try{
            passwordResetService.updatePasswordToken(token, email);
            String resetPasswordLink =  "http://localhost:4040/reset-password?token=" + token;
            sendPasswordResetEmail(email, resetPasswordLink);
            model.addAttribute("resetMessage", "We have sent a reset password link to your email.");

        } catch (ApplicationException e) {
            model.addAttribute("error", e.getLocalizedMessage());
        }catch (UnsupportedEncodingException  | MessagingException e) {
            model.addAttribute("error","Error occurred while sending email");
        }
        return "forgot_password_form";
    }

    //when user clicks link in email to reset password, this controller shows password reset form
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@Param(value = "token")String token, Model model) {
        User user = passwordResetService.getPasswordResetToken(token);
        model.addAttribute("token", token);
//        if(!user.getPasswordResetToken().equals(token)) {
//            model.addAttribute("message", "Invalid password reset token");
//            return "message";
//        }
        return "reset_password_form";
    }

    @GetMapping("reset-form")
    public String showResetForm() {
        return  "reset_password_form";
    }

    @PostMapping("/reset-password")
    public String processResetForm(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User user = passwordResetService.getPasswordResetToken(token);
        model.addAttribute("Header", "Reset your password below");
        if(!user.getPasswordResetToken().equals(token)){
            model.addAttribute("message", "Invalid token");
            return "message";
        } else {
            passwordResetService.updateUserPassword(user, password);
            model.addAttribute("message","You have successfully reset your password");
        }
        return "message";
    }

    public void sendPasswordResetEmail( String userEmail, String url) throws MessagingException,
            UnsupportedEncodingException {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        mimeMessageHelper.setFrom("contact@sanaga.com", "SanagaHealth");
        mimeMessageHelper.setTo(userEmail);

        String emailSubject = "Password Reset Instructions";
        String emailBody = "<p> Hello, " + " </p>" +
                "<p> You requested to reset your password</p>"
                + "<p>Click the link below to reset your password</p>"
                + "<p><a href=\"" + url + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you did not request.</p>"
                ;
        mimeMessageHelper.setSubject(emailSubject);
        mimeMessageHelper.setText(emailBody, true);
        mailSender.send(message);

    }


}
