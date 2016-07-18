package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.json.NastaveniUctuResponse;
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
    public void update(@PathVariable String guid, @Valid @RequestBody NastaveniUctuResponse nastaveniUctuResponse) {
        System.out.println(guid);
        System.out.println(nastaveniUctuResponse.getPreferovanaKomunikace());
        System.out.println(nastaveniUctuResponse.getZpusobPristupu());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<NastaveniUctuResponse> load(@PathVariable String guid) {
        NastaveniUctu nastaveniUctu = pacientService.loadPacientNastaveniUctu(guid);
        NastaveniUctuResponse response = aNastaveniUctu()
                .withDobaUchovani(nastaveniUctu.getDobaUchovani())
                .withPristupNaIdentifikatory(nastaveniUctu.getPreferovanaKomunikace())
                .withZpusobPristupu(nastaveniUctu.getZpusobPristupu())
                .withPreferovanaKomunikace(nastaveniUctu.getPreferovanaKomunikace())
                .build();

        return ResponseEntity.ok(response);
    }
}
