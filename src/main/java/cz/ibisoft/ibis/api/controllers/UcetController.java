package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.json.NastaveniUctu;
import org.springframework.web.bind.annotation.*;

/**
 * @author Richard Stefanca
 */

@RestController
public class UcetController {

    @RequestMapping(value = "/pacienti/{guid}/nastaveni_uctu", method = RequestMethod.PUT)
    public void update(@PathVariable String guid, @RequestBody NastaveniUctu nastaveniUctu) {
        System.out.println(guid);
        System.out.println(nastaveniUctu.getPreferovanaKomunikace());
        System.out.println(nastaveniUctu.getZpusobPristupu());
    }
}
