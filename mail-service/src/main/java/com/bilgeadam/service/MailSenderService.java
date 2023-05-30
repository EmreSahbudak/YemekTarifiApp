package com.bilgeadam.service;

import com.bilgeadam.dto.response.ForgotPasswordMailResponseDto;
import com.bilgeadam.rabbitmq.model.RegisterMailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendMail(RegisterMailModel registerMailModel){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(registerMailModel.getEmail());
        mailMessage.setSubject("AKTIVASYON KODU");

        mailMessage.setText(
                registerMailModel.getUsername() + " başarıyla kayıt oldunuz.\n" +
                        "Aktivasyon Kodu: " + registerMailModel.getActivationCode()
        );
        javaMailSender.send(mailMessage);
    }
    public Boolean sendMailForgotPassword(ForgotPasswordMailResponseDto dto){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("${spring.mail.username}");
            mailMessage.setTo(dto.getEmail());
            mailMessage.setSubject("Yeni Şifre");
            mailMessage.setText("Yeni Şifreniz:" + dto.getPassword() +
                    "\n Giriş yaptıktan sonra şifrenizi değiştiriniz");

            javaMailSender.send(mailMessage);
        }catch(Exception e){
            e.getMessage();
        }
        return true;
    }

}
