
package acme.features.authenticated.technician.maintenanceRecord;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.aircraft.Aircraft;
import acme.entities.maintenance.MaintenanceRecord;
import acme.entities.maintenance.Status;
import acme.realms.Technician;

@GuiService
public class TechnicianMaintenanceRecordCreateService extends AbstractGuiService<Technician, MaintenanceRecord> {

	@Autowired
	private TechnicianMaintenanceRecordRepository repository;


	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		MaintenanceRecord maintenanceRecord;
		Technician technician = (Technician) super.getRequest().getPrincipal().getActiveRealm();

		maintenanceRecord = new MaintenanceRecord();
		maintenanceRecord.setDraftMode(true);
		maintenanceRecord.setTechnician(technician);

		super.getBuffer().addData(maintenanceRecord);
	}

	@Override
	public void bind(final MaintenanceRecord maintenanceRecord) {
		Aircraft aircraft;
		Date currentMoment;

		aircraft = super.getRequest().getData("aircraft", Aircraft.class);
		currentMoment = MomentHelper.getCurrentMoment();

		super.bindObject(maintenanceRecord, "ticker", "nextInspectionDueDate", "estimatedCost", "notes");
		maintenanceRecord.setMoment(currentMoment);
		maintenanceRecord.setStatus(Status.PENDING);
		maintenanceRecord.setAircraft(aircraft);
	}

	@Override
	public void validate(final MaintenanceRecord maintenanceRecord) {

	}

	@Override
	public void perform(final MaintenanceRecord maintenanceRecord) {
		this.repository.save(maintenanceRecord);
	}

	@Override
	public void unbind(final MaintenanceRecord maintenanceRecord) {
		Dataset dataset;
		SelectChoices statusChoices;
		SelectChoices aircraftChoices;
		Collection<Aircraft> aircrafts;

		statusChoices = SelectChoices.from(Status.class, maintenanceRecord.getStatus());
		aircrafts = this.repository.findAllAircrafts();
		aircraftChoices = SelectChoices.from(aircrafts, "numberRegistration", maintenanceRecord.getAircraft());

		dataset = super.unbindObject(maintenanceRecord, "ticker", "moment", "nextInspectionDueDate", "estimatedCost", "notes", "draftMode");
		dataset.put("status", statusChoices.getSelected().getKey());
		dataset.put("statuses", statusChoices);
		dataset.put("aircraft", aircraftChoices.getSelected().getKey());
		dataset.put("aircrafts", aircraftChoices);

		super.getResponse().addData(dataset);
	}
}
