package cz.ibisoft.ibis.api.controllers.patient;

import cz.ibisoft.ibis.api.domain.AccountSetting;
import cz.ibisoft.ibis.api.domain.Contact;
import cz.ibisoft.ibis.api.domain.Patient;
import cz.ibisoft.ibis.api.json.*;
import cz.ibisoft.ibis.api.services.PatientService;
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

import static cz.ibisoft.ibis.api.json.NastaveniUctuBuilder.aNastaveniUctu;
import static cz.ibisoft.ibis.api.json.PacientResponse.PacientResponseBuilder.aPacientResponse;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/me")
public class AccountController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Object> loadPacient(Principal principal, @Nullable @RequestHeader(name = "If-None-Match", required = false) String noneMatchHeader) {
        String patientId = principal.getName();
        Patient patient = patientService.findById(patientId);
        return bodyOrNoContent(patient, noneMatchHeader);
    }

    /** Pokud je k dispozici header If-None-Match a zaznam nebyl zmenen vraci http kod 304
     *
     * @param patient nacteny patient
     * @param noneMatchHeader If-None-Match obsahujici verzi zaznamu
     * @return response
     */
    private static ResponseEntity<Object> bodyOrNoContent(Patient patient, String noneMatchHeader) {
        if (noneMatchHeader != null) {
            Long version = Long.parseLong(noneMatchHeader);
            return patient.getVersion().equals(version) ?
                    new ResponseEntity<>(HttpStatus.NOT_MODIFIED) : okWithBody(patient);
        }

        return okWithBody(patient);
    }

    @RequestMapping(path = "/me/register", method = RequestMethod.POST)
    public HttpEntity<PacientResponse> register(@Valid @RequestBody SimplePacientRequest simplePacientRequest) throws Exception {
        Patient patient = patientService.create(
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                simplePacientRequest.getKontakt().getEmail(),
                simplePacientRequest.getKontakt().getMobil(),
                simplePacientRequest.getHeslo()
        );

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/me")
                .buildAndExpand()
                .toUri();

        return ResponseEntity
                .created(uriOfNewResource)
                .eTag("0")
                .body(createPacientResponse(patient));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public HttpEntity<Object> updatePacient(Principal principal, @RequestHeader(name = "If-Match", required = true) String versionHeader, @RequestBody SimplePacientRequest simplePacientRequest) {
        String patientId = principal.getName();
        long version = Long.parseLong(versionHeader);
        Patient patient = patientService.update(
                patientId,
                version,
                simplePacientRequest.getCp(),
                simplePacientRequest.getJmena(),
                simplePacientRequest.getPrijmeni(),
                simplePacientRequest.getKontakt().getEmail(),
                simplePacientRequest.getKontakt().getMobil());

        return okWithBody(patient);
    }

    @RequestMapping(path = "/account_settings", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String guid, @Valid @RequestBody NastaveniUctuRequest nastaveniUctu) {
        patientService.updateAccountSettings(
                guid,
                nastaveniUctu.getPreferredCommunication(),
                nastaveniUctu.getDobaUchovani(),
                nastaveniUctu.getPristupNaIdentifikatory(),
                nastaveniUctu.getAccessType()
        );
    }

    @RequestMapping(path = "/account_settings", method = RequestMethod.GET)
    public ResponseEntity<NastaveniUctuResponse> load(Principal principal) {
        String patientId = principal.getName();
        AccountSetting accountSetting = patientService.loadAccountSettings(patientId);
        NastaveniUctuResponse response = aNastaveniUctu()
                .withDobaUchovani(accountSetting.getDobaUchovani())
                .withPristupNaIdentifikatory(accountSetting.getPristupNaIdentifikatory().toString())
                .withZpusobPristupu(accountSetting.getAccessType().toString())
                .withPreferovanaKomunikace(accountSetting.getPreferredCommunication().toString())
                .build();

        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public HttpEntity<Void> deletePacient() {
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Object> okWithBody(Patient patient) {
        return ResponseEntity
                .ok()
                //.lastModified(patient.getLastModified().getTime())
                .eTag(String.valueOf(patient.getVersion()))
                .body(createPacientResponse(patient));
    }


    private static PacientResponse createPacientResponse(@RequestBody Patient patient) {
        NastaveniUctuResponse nastaveniUctuResponse = createNastaveniUctuResponse(patient.getAccountSetting());
        Contact contact = patient.getContact();
        return aPacientResponse()
                .withId(patient.getId())
                .withCp(patient.getInsuranceNumber())
                .withJmena(patient.getFirstName())
                .withPrijmeni(patient.getLastName())
                .withKontakt(new cz.ibisoft.ibis.api.json.Kontakt(contact.getEmail(), contact.getEmail()))
                .withNastaveniUctu(nastaveniUctuResponse)
                .withOtpKodProZalozeni("otpKod")
                .withStavUctu(patient.isBlocked() ? "zablokovany" : "aktivni")
                .build();
    }

    private static NastaveniUctuResponse createNastaveniUctuResponse(AccountSetting accountSetting) {
        return NastaveniUctuBuilder.aNastaveniUctu()
                .withDobaUchovani(accountSetting.getDobaUchovani())
                .withPreferovanaKomunikace(accountSetting.getPreferredCommunication().toString())
                .withPristupNaIdentifikatory(accountSetting.getPristupNaIdentifikatory().toString())
                .withZpusobPristupu(accountSetting.getAccessType().toString())
                .build();
    }
}
