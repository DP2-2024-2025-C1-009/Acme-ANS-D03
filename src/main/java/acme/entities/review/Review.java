
package acme.entities.review;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.mappings.Automapped;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidNumber;
import acme.client.components.validation.ValidString;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	@ValidString(min = 1, max = 50, message = "{acme.validation.review.alias-length}")
	@Automapped
	private String				alias;

	@Mandatory
	@Temporal(TemporalType.TIMESTAMP)
	@ValidMoment(past = true, message = "{acme.validation.review.moment-past}")
	private Date				moment;

	@Mandatory
	@ValidString(min = 1, max = 50, message = "{acme.validation.review.subject-length}")
	@Automapped
	private String				subject;

	@Mandatory
	@ValidString(min = 1, max = 255, message = "{acme.validation.review.text-length}")
	@Automapped
	private String				text;

	@Optional
	@ValidNumber(min = 0, max = 10, fraction = 2, message = "{acme.validation.review.score-range}")
	@Automapped
	private Double				score;

	@Optional
	@Automapped
	@Valid
	private Boolean				isRecommended;

}
