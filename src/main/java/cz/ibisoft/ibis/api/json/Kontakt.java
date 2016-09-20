package cz.ibisoft.ibis.api.json;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author Richard Stefanca
 */
@Value
@AllArgsConstructor
public class Kontakt {
	private final String email;
	private final String mobil;
}
