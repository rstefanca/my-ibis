package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.json.NastaveniUctuBuilder;
import cz.ibisoft.ibis.api.json.PacientRequest;
import cz.ibisoft.ibis.api.json.PacientResponse;
import cz.ibisoft.ibis.api.json.PacientResponseBuilder;
import cz.ibisoft.ibis.api.model.Kontakt;
import cz.ibisoft.ibis.api.model.NastaveniUctu;
import cz.ibisoft.ibis.api.model.Pacient;
import cz.ibisoft.ibis.api.services.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * @author Richard Stefanca
 */

@RestController
public class PacientiController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(path = "/pacienti/{guid}")
    public HttpEntity<Object> nacti(@PathVariable String guid) {
        System.out.println(guid);
        Optional<Pacient> maybePacient = pacientService.load(guid);
        return maybePacient
                .map(pacient -> ResponseEntity
                        .ok()
                        .cacheControl(CacheControl.noCache())
                        .lastModified(pacient.getLastModified().getTime())
                        .header("X-Version", pacient.getVersion().toString())
                        .body((Object)createPacientResponse(pacient)))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/pacienti", method = RequestMethod.POST)
    public HttpEntity<PacientResponse> zalozit(@RequestBody PacientRequest pacientRequest) throws Exception {
        Pacient pacient = mapToPacient(pacientRequest);
        pacientService.create(pacient);

        return ResponseEntity
                .created(new URI("/pacienti/" + pacient.getId()))
                //.lastModified(pacient.getCreatedDate().getTime())
                .body(createPacientResponse(pacient));
    }

    @RequestMapping(path = "/pacienti/{guid}", method = RequestMethod.DELETE)
    public HttpEntity<Void> smazat(@PathVariable String guid) {
        return ResponseEntity.noContent().build();
    }

    private static PacientResponse createPacientResponse(@RequestBody Pacient pacient) {
        cz.ibisoft.ibis.api.json.NastaveniUctu nastaveniUctu = createNastaveniUctuResponse(pacient.getNastaveniUctu());
        Kontakt kontakt = pacient.getKontakt();
        return PacientResponseBuilder.aPacientResponse()
                .withCp(pacient.getCp())
                .withJmena(pacient.getJmena())
                .withPrijmeni(pacient.getPrijmeni())
                .withKontakt(new cz.ibisoft.ibis.api.json.Kontakt(kontakt.getEmail(), kontakt.getEmail()))
                .withNastaveniUctu(nastaveniUctu)
                .withOtpKodProZalozeni("otpKod")
                .withStavUctu("aktivni")
                .build();
    }

    private static cz.ibisoft.ibis.api.json.NastaveniUctu createNastaveniUctuResponse(NastaveniUctu nastaveniUctu) {
        return NastaveniUctuBuilder.aNastaveniUctu()
                .withDobaUchovani(nastaveniUctu.getDobaUchovani())
                .withPreferovanaKomunikace(nastaveniUctu.getPreferovanaKomunikace())
                .withPristupNaIdentifikatory(nastaveniUctu.getPristupNaIdentifikatory())
                .withZpusobPristupu(nastaveniUctu.getZpusobPristupu())
                .build();
    }

    private static Pacient mapToPacient(@RequestBody PacientRequest pacientRequest) {
        cz.ibisoft.ibis.api.json.NastaveniUctu nastaveniUctu = pacientRequest.getNastaveniUctu();
        cz.ibisoft.ibis.api.json.Kontakt kontakt = pacientRequest.getKontakt();
        return new Pacient(
                pacientRequest.getCp(),
                pacientRequest.getJmena(),
                pacientRequest.getPrijmeni(),
                new Kontakt(
                        kontakt.getEmail(),
                        kontakt.getMobil()),
                new NastaveniUctu(
                        nastaveniUctu.getPreferovanaKomunikace(),
                        nastaveniUctu.getDobaUchovani(),
                        nastaveniUctu.getPristupNaIdentifikatory(),
                        nastaveniUctu.getZpusobPristupu(),
                        pacientRequest.getHesla().getHeslo1()));
    }
}
