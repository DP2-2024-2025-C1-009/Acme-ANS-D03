<%@page %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="http://acme-framework.org/" %>

<acme:form> 
    <acme:input-moment code="flight-crew-member.activity-log.form.label.registrationMoment" path="registrationMoment" readonly="true"/>

    <acme:input-textbox 
        code="flight-crew-member.activity-log.form.label.incidentType" 
        path="incidentType"
        placeholder="flight-crew-member.activity-log.form.placeholder.incidentType" />

    <acme:input-textarea 
        code="flight-crew-member.activity-log.form.label.description" 
        path="description"
        placeholder="flight-crew-member.activity-log.form.placeholder.description" />

    <acme:input-integer 
        code="flight-crew-member.activity-log.form.label.severityLevel" 
        path="severityLevel"
        placeholder="flight-crew-member.activity-log.form.placeholder.severityLevel" />

    <jstl:choose>
        <jstl:when test="${_command == 'create'}">
            <acme:submit code="flight-crew-member.activity-log.form.button.create" 
                         action="/flight-crew-member/activity-log/create?assignmentId=${assignmentId}" />
        </jstl:when>

		<jstl:when test="${acme:anyOf(_command, 'show|update')}">
            <jstl:if test="${buttonsAvailable}">
                <acme:submit code="flight-crew-member.activity-log.form.button.update" 
                             action="/flight-crew-member/activity-log/update" />
                <acme:submit code="flight-crew-member.activity-log.form.button.delete" 
                             action="/flight-crew-member/activity-log/delete" />
            </jstl:if>
            <jstl:if test="${publishAvailable}">
                <acme:submit code="flight-crew-member.activity-log.form.button.publish" 
                             action="/flight-crew-member/activity-log/publish" />
            </jstl:if>
        </jstl:when>
    </jstl:choose>
</acme:form>
