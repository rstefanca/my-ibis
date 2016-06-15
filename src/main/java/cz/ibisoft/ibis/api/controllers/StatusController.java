package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.protocol.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Richard Stefanca
 */
@RestController
public class StatusController {

    @RequestMapping("/ping")
    public Status ping() {
        return new Status("OK");
    }
}
