package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.json.NastaveniUctuRequest;
import cz.ibisoft.ibis.api.json.NastaveniUctuResponse;
import cz.ibisoft.ibis.api.services.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static cz.ibisoft.ibis.api.json.NastaveniUctuBuilder.aNastaveniUctu;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/pacienti/{guid}/nastaveni_uctu")
public class UcetController {

    @Autowired
    private PacientService pacientService;


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String guid, @Valid @RequestBody NastaveniUctuRequest nastaveniUctu) {
        pacientService.updateNastaveniUctu(
                guid,
                nastaveniUctu.getPreferovanaKomunikace(),
                nastaveniUctu.getDobaUchovani(),
                nastaveniUctu.getPristupNaIdentifikatory(),
                nastaveniUctu.getZpusobPristupu()
                );
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<NastaveniUctuResponse> load(@PathVariable String guid) {
        NastaveniUctu nastaveniUctu = pacientService.loadPacientNastaveniUctu(guid);
        NastaveniUctuResponse response = aNastaveniUctu()
                .withDobaUchovani(nastaveniUctu.getDobaUchovani())
                .withPristupNaIdentifikatory(nastaveniUctu.getPristupNaIdentifikatory().toString())
                .withZpusobPristupu(nastaveniUctu.getZpusobPristupu().toString())
                .withPreferovanaKomunikace(nastaveniUctu.getPreferovanaKomunikace().toString())
                .build();

        return ResponseEntity.ok(response);
    }
}
