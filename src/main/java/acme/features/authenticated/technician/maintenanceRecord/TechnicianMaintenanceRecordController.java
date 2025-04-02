
package acme.features.authenticated.technician.maintenanceRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.maintenance.MaintenanceRecord;
import acme.realms.Technician;

@GuiController
public class TechnicianMaintenanceRecordController extends AbstractGuiController<Technician, MaintenanceRecord> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private TechnicianMaintenanceRecordListService		listService;

	@Autowired
	private TechnicianMaintenanceRecordShowService		showService;

	@Autowired
	private TechnicianMaintenanceRecordCreateService	createService;

	@Autowired
	private TechnicianMaintenanceRecordUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
	}
}
