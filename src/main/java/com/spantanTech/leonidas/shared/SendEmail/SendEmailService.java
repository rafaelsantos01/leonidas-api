package com.spantanTech.leonidas.shared.SendEmail;

import com.spantanTech.leonidas.modules.templatesEmail.ENUM.TEMPLATETYPE;
import com.spantanTech.leonidas.modules.templatesEmail.entities.Templates;
import com.spantanTech.leonidas.modules.templatesEmail.repository.TemplatesRepository;
import com.spantanTech.leonidas.shared.SendEmail.dto.SendEmailServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {


    @Autowired
    private  JavaMailSender javaMailSender;

    @Autowired
    private TemplatesRepository templatesRepository;


    public  void send(SendEmailServiceDTO data) throws MessagingException {


        Templates templates = templatesRepository.findByTemplateTypeAndStatus(data.getTemplateType(), true);

        if(templates == null){
            throw new Error("templateNotFound");
        }

        String templateSend = "";
        if(data.getTemplateType() == TEMPLATETYPE.NEWUSER.getKey()){
            templateSend =  newUserTemplate(templates.getTemplate(),data.getUserName(), data.getLink());

        }else{
            templateSend = templates.getTemplate();
        }

        String titleReplace = templates.getTitle().replace("{name}",data.getUserName());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(data.getUserMail());
        helper.setSubject(titleReplace);
        helper.setText(templateSend, true);

        javaMailSender.send(message);
    }


    public String newUserTemplate(String html, String nome, String url) {
        html = html.replace("{name}", nome);
        html = html.replace("{url}", url);
        return html;
    }

}
