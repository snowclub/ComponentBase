package ComponentBase.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by waiti on 5/2/2016.
 */
@CrossOrigin
@Controller
@RequestMapping("/messageBundle")
public class SerializableMessageBundleController {
    @Autowired
    SerializableResourceBundleMessageSource messageBundle;
    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Properties list(@RequestParam String lang) {
        return messageBundle.getAllProperties(new Locale(lang));
    }

}
