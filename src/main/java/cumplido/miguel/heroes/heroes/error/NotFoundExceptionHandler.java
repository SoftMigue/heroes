package cumplido.miguel.heroes.heroes.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NotFoundExceptionHandler extends Exception {

    private static final Logger log = LoggerFactory.getLogger(NotFoundExceptionHandler.class);

    public NotFoundExceptionHandler(String message) {
        super(message);
        log.error(message);
    }

}
