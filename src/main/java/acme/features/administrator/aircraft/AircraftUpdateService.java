
package acme.features.administrator.aircraft;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.principals.Administrator;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.aircraft.Aircraft;

// Error desconocido al actualizar
@GuiService
public class AircraftUpdateService extends AbstractGuiService<Administrator, Aircraft> {

	@Autowired
	private AircraftRepository aircraftRepository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		int id = super.getResponse().getData("id", int.class);
		Aircraft aircraft = this.aircraftRepository.findAircraftById(id);
		super.getBuffer().addData(aircraft);
	}

	@Override
	public void bind(final Aircraft aircraft) {
		super.bindObject(aircraft, "model", "numberRegistration", "numberPassengers", "loadWeight", "isActive", "optionalDetails");
	}

	@Override
	public void validate(final Aircraft aircraft) {
		boolean confirmation;
		confirmation = super.getRequest().getData("confirmation", boolean.class);
		super.state(confirmation, "confirmation", "acme.validation.confirmation.message");
	}

	@Override
	public void perform(final Aircraft aircraft) {
		this.aircraftRepository.save(aircraft);
	}

	@Override
	public void unbind(final Aircraft aircraft) {
		Dataset data = super.unbindObject(aircraft, "model", "numberRegistration", "numberPassengers", "loadWeight", "IsActive", "optionalDetails");
		data.put("confirmation", false);
		data.put("readonly", false);

		super.getResponse().addData(data);
	}

}
