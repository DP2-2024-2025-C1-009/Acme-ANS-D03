<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.leg"
					  path="leg" width="12%"/>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.duty"
					  path="duty" width="10%"/>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.lastUpdate"
					  path="lastUpdate" width="12%"/>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.status"
					  path="status" width="10%"/>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.remarks"
					  path="remarks" width="20%"/>

	<acme:list-column code="flight-crew-member.flight-assignment.list.label.draftMode"
					  path="draftMode" width="8%"/>

	<acme:list-payload path="payload"/>

</acme:list>

<acme:button code="flight-crew-member.flight-assignment.list.button.create"
			 action="/flight-crew-member/flight-assignment/create"/>
