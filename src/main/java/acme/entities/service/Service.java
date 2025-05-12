
package acme.entities.service;

import javax.persistence.Column;
import javax.persistence.Entity;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Money;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoney;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidPromotionCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidPromotionCode
public class Service extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidString(min = 1, max = 50, message = "{acme.validation.service.name-length}")
	@Automapped
	private String				name;

	@Mandatory
	@ValidUrl(remote = false, message = "{acme.validation.service.photo-link-valid}")
	@Automapped
	private String				photoLink;

	@Mandatory
	@ValidNumber(min = 1, max = 100, integer = 3, fraction = 2)
	@Automapped
	private double				averageDwellTime;

	@Optional
	@Column(unique = true)
	private String				promotionCode;

	@Optional
	@ValidMoney
	@Automapped
	private Money				discountedMoney;

}
