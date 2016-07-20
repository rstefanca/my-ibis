package cz.ibisoft.ibis.api.controllers.patient;

import cz.ibisoft.ibis.api.domain.PatientCard;
import cz.ibisoft.ibis.api.json.PatientCardResponse;
import cz.ibisoft.ibis.api.services.PatientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Richard Stefanca
 */

@RestController
@RequestMapping("/me/cards")
public class CardController {

    @Autowired
    private PatientCardService patientCardService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<List<PatientCardResponse>> loadCards(Principal principal) {
        String patientId = principal.getName();
        List<PatientCard> cards = patientCardService.findCardsByPatientId(patientId);
        List<PatientCardResponse> jsonCards = cards.stream()
                .map(card -> new PatientCardResponse(card.getCode(), card.getIssuer()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(jsonCards);
    }


}
