package recipes.controllers;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/shutdown")
public class shutdownController implements ApplicationContextAware {

    private ApplicationContext context;

    @PostMapping
    public ResponseEntity<?> shutdown(){
        Thread process = new Thread(()->((ConfigurableApplicationContext) context).close());
        process.start();
        return ResponseEntity.ok(null);
    }


    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.context = ctx;
    }

}
