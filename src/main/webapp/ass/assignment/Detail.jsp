<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Assignment Detail</title>
            <link rel="stylesheet" type="text/css" href="/merits/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Assignment Detail</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Title:"/>
                <h:outputText value="#{assignment.assignment.title}" title="Title" />
                <h:outputText value="Details:"/>
                <h:outputText value="#{assignment.assignment.details}" title="Details" />
                <h:outputText value="IssueDate:"/>
                <h:outputText value="#{assignment.assignment.issueDate}" title="IssueDate" />
                <h:outputText value="DueDate:"/>
                <h:outputText value="#{assignment.assignment.dueDate}" title="DueDate" />
                <h:outputText value="Course:"/>
                <h:panelGroup>
                    <h:outputText value="#{assignment.assignment.course}"/>
                    <h:panelGroup rendered="#{assignment.assignment.course != null}">
                        <h:outputText value=" ("/>
                        <h:commandLink value="Show" action="#{course.detailSetup}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment.course][course.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="assignment"/>
                            <f:param name="jsfcrud.relatedControllerType" value="za.co.merits.view.ass.AssignmentController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{course.editSetup}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment.course][course.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="assignment"/>
                            <f:param name="jsfcrud.relatedControllerType" value="za.co.merits.view.ass.AssignmentController"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{course.destroy}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.currentCourse" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment.course][course.converter].jsfcrud_invoke}"/>
                            <f:param name="jsfcrud.relatedController" value="assignment"/>
                            <f:param name="jsfcrud.relatedControllerType" value="za.co.merits.view.ass.AssignmentController"/>
                        </h:commandLink>
                        <h:outputText value=" )"/>
                    </h:panelGroup>
                </h:panelGroup>
                <h:outputText value="PossibleMark:"/>
                <h:outputText value="#{assignment.assignment.possibleMark}" title="PossibleMark" />
                <h:outputText value="Open:"/>
                <h:outputText value="#{assignment.assignment.open}" title="Open" />
                <h:outputText value="DisplayText:"/>
                <h:outputText value="#{assignment.assignment.displayText}" title="DisplayText" />
                <h:outputText value="Id:"/>
                <h:outputText value="#{assignment.assignment.id}" title="Id" />
                <h:outputText value="CreatedOn:"/>
                <h:outputText value="#{assignment.assignment.createdOn}" title="CreatedOn" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:outputText>
                <h:outputText value="ModifiedOn:"/>
                <h:outputText value="#{assignment.assignment.modifiedOn}" title="ModifiedOn" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:outputText>
                <h:outputText value="DisplayText:"/>
                <h:outputText value="#{assignment.assignment.displayText}" title="DisplayText" />


            </h:panelGrid>
            <br />
            <h:commandLink action="#{assignment.remove}" value="Destroy">
                <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{assignment.editSetup}" value="Edit">
                <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}" />
            </h:commandLink>
            <br />
            <h:commandLink action="#{assignment.createSetup}" value="New Assignment" />
            <br />
            <h:commandLink action="#{assignment.listSetup}" value="Show All Assignment Items"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
