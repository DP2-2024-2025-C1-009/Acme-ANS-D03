
package acme.features.flightCrewMember.activityLog;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.activityLog.ActivityLog;
import acme.realms.flightCrewMembers.FlightCrewMember;

@GuiService
public class ActivityLogUpdateService extends AbstractGuiService<FlightCrewMember, ActivityLog> {

	@Autowired
	private ActivityLogRepository activityLogRepository;


	@Override
	public void authorise() {
		int id = super.getRequest().getData("id", int.class);
		ActivityLog log = this.activityLogRepository.findActivityLogById(id);

		boolean correctCrew = log != null && log.getActivityLogAssignment().getCrewMember().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		boolean legInPast = log != null && MomentHelper.isPast(log.getActivityLogAssignment().getLeg().getScheduledArrival());
		boolean draftMode = log != null && log.getDraftMode();

		boolean authorised = correctCrew && legInPast && draftMode;
		super.getResponse().setAuthorised(authorised);
	}

	@Override
	public void load() {
		int id = super.getRequest().getData("id", int.class);
		ActivityLog log = this.activityLogRepository.findActivityLogById(id);
		super.getBuffer().addData(log);
	}

	@Override
	public void bind(final ActivityLog log) {
		super.bindObject(log, "incidentType", "description", "severityLevel");
	}

	@Override
	public void validate(final ActivityLog log) {
		;
	}

	@Override
	public void perform(final ActivityLog log) {
		this.activityLogRepository.save(log);
	}

	@Override
	public void unbind(final ActivityLog log) {
		Dataset data;

		var assignment = log.getActivityLogAssignment();

		boolean correctUser = assignment.getCrewMember().getId() == super.getRequest().getPrincipal().getActiveRealm().getId();
		boolean buttonsAvailable = log.getDraftMode() && correctUser;
		boolean publishAvailable = !assignment.getDraftMode() && log.getDraftMode() && correctUser && MomentHelper.isPast(assignment.getLeg().getScheduledArrival());

		data = super.unbindObject(log, "registrationMoment", "incidentType", "description", "severityLevel", "draftMode");

		data.put("id", log.getId());
		data.put("assignmentId", assignment.getId());
		data.put("buttonsAvailable", buttonsAvailable);
		data.put("publishAvailable", publishAvailable);
		data.put("registrationMoment", log.getRegistrationMoment());
		data.put("draftMode", log.getDraftMode());

		super.getResponse().addData(data);
	}

}
