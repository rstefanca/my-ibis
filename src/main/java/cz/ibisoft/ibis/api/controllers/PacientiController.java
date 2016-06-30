package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.json.*;
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
import java.util.UUID;

/**
 * @author Richard Stefanca
 */

@RestController
public class PacientiController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(path = "/pacienti/{guid}")
    public HttpEntity<Object> nacti(@PathVariable String guid,  @RequestHeader(name = "If-None-Match")Optional<String> version) {
        System.out.println(version);
        Optional<Pacient> maybePacient = pacientService.load(guid);
        return maybePacient
                .map(PacientiController::okWithBody)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private static ResponseEntity<Object> okWithBody(Pacient pacient) {
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noCache())
                //.lastModified(pacient.getLastModified().getTime())
                .eTag(String.valueOf(pacient.getVersion()))
                .header("X-Version", pacient.getVersion().toString())
                .body((Object) createPacientResponse(pacient));
    }

    @RequestMapping(path = "/pacienti", method = RequestMethod.POST)
    public HttpEntity<PacientResponse> zalozit(@RequestBody SimplePacientRequest simplePacientRequest) throws Exception {
        String guid = UUID.randomUUID().toString();
        System.out.println(guid);
        Pacient pacient = mapToPacient(guid, simplePacientRequest);
        pacientService.create(pacient);

        return ResponseEntity
                .created(new URI("/pacienti/" + pacient.getId()))
                //.lastModified(pacient.getCreatedDate().getTime())
                .body(createPacientResponse(pacient));
    }

    @RequestMapping(path = "/pacienti/{guid}", method = RequestMethod.PUT)
    public HttpEntity<Object> upravit(@PathVariable String guid, @RequestHeader(name = "If-Match", required = true) String versionHeader, @RequestBody SimplePacientRequest simplePacientRequest) {
        long version = Long.parseLong(versionHeader);
        Optional<Pacient> savedPacient = pacientService.save(guid, version, simplePacientRequest);
        return savedPacient.map(PacientiController::okWithBody).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/pacienti/{guid}", method = RequestMethod.DELETE)
    public HttpEntity<Void> smazat(@PathVariable String guid) {
        return ResponseEntity.noContent().build();
    }

    private static PacientResponse createPacientResponse(@RequestBody Pacient pacient) {
        cz.ibisoft.ibis.api.json.NastaveniUctu nastaveniUctu = createNastaveniUctuResponse(NastaveniUctu.DEFAULT);
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

    private static Pacient mapToPacient(String id, SimplePacientRequest simplePacientRequest) {
        cz.ibisoft.ibis.api.json.Kontakt kontakt = simplePacientRequest.getKontakt();
        return new Pacient(
                id,
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                new Kontakt(
                        kontakt.getEmail(),
                        kontakt.getMobil()));
    }
}
