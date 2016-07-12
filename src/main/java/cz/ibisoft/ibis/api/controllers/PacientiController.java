package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.domain.Kontakt;
import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.json.NastaveniUctuBuilder;
import cz.ibisoft.ibis.api.json.PacientResponse;
import cz.ibisoft.ibis.api.json.PacientResponseBuilder;
import cz.ibisoft.ibis.api.json.SimplePacientRequest;
import cz.ibisoft.ibis.api.services.PacientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/pacienti")
public class PacientiController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(path = "/{guid}")
    public HttpEntity<Object> loadPacient(@PathVariable String guid, @RequestHeader(name = "If-None-Match") Optional<String> noneMatchHeader) {
        Pacient pacient = pacientService.findById(guid);
        return bodyOrNoContent(pacient, noneMatchHeader);
    }

    /** Pokud je k dispozici header If-None-Match a zaznam nebyl zmenen vraci http kod 304
     *
     * @param pacient nacteny pacient
     * @param noneMatchHeader If-None-Match obsahujici verzi zaznamu
     * @return response
     */
    private static ResponseEntity<Object> bodyOrNoContent(Pacient pacient, Optional<String> noneMatchHeader) {
        return noneMatchHeader
                .map(Long::parseLong)
                .map(version -> pacient.getVersion().equals(version) ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED) : okWithBody(pacient))
                .orElseGet(() -> okWithBody(pacient));
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<PacientResponse> createPacient(@Valid @RequestBody SimplePacientRequest simplePacientRequest) throws Exception {
        Pacient pacient = pacientService.create(
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                simplePacientRequest.getKontakt().getEmail(),
                simplePacientRequest.getKontakt().getMobil()
        );

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pacienti/{guid}")
                .buildAndExpand(pacient.getId())
                .toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .eTag("0")
                .body(createPacientResponse(pacient));
    }

    @RequestMapping(path = "/{guid}", method = RequestMethod.PUT)
    public HttpEntity<Object> updatePacient(@PathVariable String guid, @RequestHeader(name = "If-Match", required = true) String versionHeader, @RequestBody SimplePacientRequest simplePacientRequest) {
        long version = Long.parseLong(versionHeader);
        Pacient pacient = pacientService.update(
                guid,
                version,
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                simplePacientRequest.getKontakt().getEmail(),
                simplePacientRequest.getKontakt().getMobil());
        return okWithBody(pacient);
    }

    @RequestMapping(path = "/{guid}", method = RequestMethod.DELETE)
    public HttpEntity<Void> deletePacient(@PathVariable String guid) {
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Object> okWithBody(Pacient pacient) {
        return ResponseEntity
                .ok()
                //.lastModified(pacient.getLastModified().getTime())
                .eTag(String.valueOf(pacient.getVersion()))
                .body(createPacientResponse(pacient));
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
}
