<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Assignment</title>
            <link rel="stylesheet" type="text/css" href="/merits/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Editing Assignment</h1>
        <h:form>
            <h:panelGrid columns="2">
                <h:outputText value="Title:"/>
                <h:inputText id="title" value="#{assignment.assignment.title}" title="Title" />
                <h:outputText value="Details:"/>
                <h:inputText id="details" value="#{assignment.assignment.details}" title="Details" />
                <h:outputText value="IssueDate:"/>
                <h:inputText id="issueDate" value="#{assignment.assignment.issueDate}" title="IssueDate" />
                <h:outputText value="DueDate:"/>
                <h:inputText id="dueDate" value="#{assignment.assignment.dueDate}" title="DueDate" />
                <h:outputText value="Course:"/>
                <h:selectOneMenu id="course" value="#{assignment.assignment.course}" title="Course" >
                    <f:selectItems value="#{course.courseItemsAvailableSelectOne}"/>
                </h:selectOneMenu>
                <h:outputText value="PossibleMark:"/>
                <h:inputText id="possibleMark" value="#{assignment.assignment.possibleMark}" title="PossibleMark" />
                <h:outputText value="Open:"/>
                <h:inputText id="open" value="#{assignment.assignment.open}" title="Open" />
                <h:outputText value="DisplayText:"/>
                <h:outputText value="#{assignment.assignment.displayText}" title="DisplayText" />
                <h:outputText value="Id:"/>
                <h:outputText value="#{assignment.assignment.id}" title="Id" />
                <h:outputText value="CreatedOn (MM/dd/yyyy HH:mm:ss):"/>
                <h:inputText id="createdOn" value="#{assignment.assignment.createdOn}" title="CreatedOn" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                <h:outputText value="ModifiedOn (MM/dd/yyyy HH:mm:ss):"/>
                <h:inputText id="modifiedOn" value="#{assignment.assignment.modifiedOn}" title="ModifiedOn" >
                    <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                </h:inputText>
                <h:outputText value="DisplayText:"/>
                <h:outputText value="#{assignment.assignment.displayText}" title="DisplayText" />

            </h:panelGrid>
            <br />
            <h:commandLink action="#{assignment.edit}" value="Save">
                <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <br />
            <h:commandLink action="#{assignment.detailSetup}" value="Show" immediate="true">
                <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][assignment.assignment][assignment.converter].jsfcrud_invoke}"/>
            </h:commandLink>
            <br />
            <h:commandLink action="#{assignment.listSetup}" value="Show All Assignment Items" immediate="true"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />

        </h:form>
        </body>
    </html>
</f:view>
