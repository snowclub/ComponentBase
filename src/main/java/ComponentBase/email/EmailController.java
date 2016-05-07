package ComponentBase.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * Created by panit on 5/7/2016.
 */
@RestController
public class EmailController {

        @Autowired
        private EmailSender smtpMailSender;

        @RequestMapping("/send-mail")
        public void sendMail() throws MessagingException {

            smtpMailSender.send("panit_j@cmu.ac.th", "Test mail from Spring", "Hello");
        }
}
