package cz.ibisoft.ibis.api.controllers;

import cz.ibisoft.ibis.api.domain.Kontakt;
import cz.ibisoft.ibis.api.domain.NastaveniUctu;
import cz.ibisoft.ibis.api.domain.Pacient;
import cz.ibisoft.ibis.api.json.NastaveniUctuBuilder;
import cz.ibisoft.ibis.api.json.NastaveniUctuResponse;
import cz.ibisoft.ibis.api.json.PacientResponse;
import cz.ibisoft.ibis.api.json.SimplePacientRequest;
import cz.ibisoft.ibis.api.services.PacientService;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;

import static cz.ibisoft.ibis.api.json.PacientResponse.PacientResponseBuilder.aPacientResponse;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/pacienti")
public class PacientiController {

    @Autowired
    private PacientService pacientService;

    @RequestMapping(path = "/{guid}")
    public HttpEntity<Object> loadPacient(Principal principal, @PathVariable String guid, @Nullable @RequestHeader(name = "If-None-Match", required = false) String noneMatchHeader) {
        System.out.println(principal.getName());
        Pacient pacient = pacientService.findById(guid);
        return bodyOrNoContent(pacient, noneMatchHeader);
    }

    /** Pokud je k dispozici header If-None-Match a zaznam nebyl zmenen vraci http kod 304
     *
     * @param pacient nacteny pacient
     * @param noneMatchHeader If-None-Match obsahujici verzi zaznamu
     * @return response
     */
    private static ResponseEntity<Object> bodyOrNoContent(Pacient pacient, String noneMatchHeader) {
        if (noneMatchHeader != null) {
            Long version = Long.parseLong(noneMatchHeader);
            return pacient.getVersion().equals(version) ?
                    new ResponseEntity<>(HttpStatus.NOT_MODIFIED) : okWithBody(pacient);
        }

        return okWithBody(pacient);
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<PacientResponse> createPacient(@Valid @RequestBody SimplePacientRequest simplePacientRequest) throws Exception {
        Pacient pacient = pacientService.create(
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                simplePacientRequest.getKontakt().getEmail(),
                simplePacientRequest.getKontakt().getMobil(),
                simplePacientRequest.getHeslo()
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
        NastaveniUctuResponse nastaveniUctuResponse = createNastaveniUctuResponse(pacient.getNastaveniUctu());
        Kontakt kontakt = pacient.getKontakt();
        return aPacientResponse()
                .withId(pacient.getId())
                .withCp(pacient.getCp())
                .withJmena(pacient.getJmena())
                .withPrijmeni(pacient.getPrijmeni())
                .withKontakt(new cz.ibisoft.ibis.api.json.Kontakt(kontakt.getEmail(), kontakt.getEmail()))
                .withNastaveniUctu(nastaveniUctuResponse)
                .withOtpKodProZalozeni("otpKod")
                .withStavUctu(pacient.isZablokovany() ? "zablokovany" : "aktivni")
                .build();
    }

    private static NastaveniUctuResponse createNastaveniUctuResponse(NastaveniUctu nastaveniUctu) {
        return NastaveniUctuBuilder.aNastaveniUctu()
                .withDobaUchovani(nastaveniUctu.getDobaUchovani())
                .withPreferovanaKomunikace(nastaveniUctu.getPreferovanaKomunikace().toString())
                .withPristupNaIdentifikatory(nastaveniUctu.getPristupNaIdentifikatory().toString())
                .withZpusobPristupu(nastaveniUctu.getZpusobPristupu().toString())
                .build();
    }
}
