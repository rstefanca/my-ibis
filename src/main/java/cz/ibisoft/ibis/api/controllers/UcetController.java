package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.json.NastaveniUctu;
import cz.ibisoft.ibis.api.json.NastaveniUctuBuilder;
import cz.ibisoft.ibis.api.services.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cz.ibisoft.ibis.api.json.NastaveniUctuBuilder.*;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/pacienti/{guid}/nastaveni_uctu")
public class UcetController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@PathVariable String guid, @Valid @RequestBody NastaveniUctu nastaveniUctu) {
        System.out.println(guid);
        System.out.println(nastaveniUctu.getPreferovanaKomunikace());
        System.out.println(nastaveniUctu.getZpusobPristupu());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<NastaveniUctu> load(@PathVariable String guid) {
        cz.ibisoft.ibis.api.domain.NastaveniUctu nastaveniUctu = pacientService.loadPacientNastaveniUctu(guid);
        NastaveniUctu response = aNastaveniUctu()
                .withDobaUchovani(nastaveniUctu.getDobaUchovani())
                .withPristupNaIdentifikatory(nastaveniUctu.getPreferovanaKomunikace())
                .withZpusobPristupu(nastaveniUctu.getZpusobPristupu())
                .withPreferovanaKomunikace(nastaveniUctu.getPreferovanaKomunikace())
                .build();

        return ResponseEntity.ok(response);
    }
}
