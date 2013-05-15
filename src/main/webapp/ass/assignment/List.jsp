<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Assignment Items</title>
            <link rel="stylesheet" type="text/css" href="/merits/faces/jsfcrud.css" />
        </head>
        <body>
        <h:panelGroup id="messagePanel" layout="block">
            <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
        </h:panelGroup>
        <h1>Listing Assignment Items</h1>
        <h:form styleClass="jsfcrud_list_form">
            <h:outputText escape="false" value="(No Assignment Items Found)<br />" rendered="#{assignment.pagingInfo.itemCount == 0}" />
            <h:panelGroup rendered="#{assignment.pagingInfo.itemCount > 0}">
                <h:outputText value="Item #{assignment.pagingInfo.firstItem + 1}..#{assignment.pagingInfo.lastItem} of #{assignment.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{assignment.prev}" value="Previous #{assignment.pagingInfo.batchSize}" rendered="#{assignment.pagingInfo.firstItem >= assignment.pagingInfo.batchSize}"/>&nbsp;
                <h:commandLink action="#{assignment.next}" value="Next #{assignment.pagingInfo.batchSize}" rendered="#{assignment.pagingInfo.lastItem + assignment.pagingInfo.batchSize <= assignment.pagingInfo.itemCount}"/>&nbsp;
                <h:commandLink action="#{assignment.next}" value="Remaining #{assignment.pagingInfo.itemCount - assignment.pagingInfo.lastItem}"
                               rendered="#{assignment.pagingInfo.lastItem < assignment.pagingInfo.itemCount && assignment.pagingInfo.lastItem + assignment.pagingInfo.batchSize > assignment.pagingInfo.itemCount}"/>
                <h:dataTable value="#{assignment.assignmentItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Title"/>
                        </f:facet>
                        <h:outputText value="#{item.title}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Details"/>
                        </f:facet>
                        <h:outputText value="#{item.details}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="IssueDate"/>
                        </f:facet>
                        <h:outputText value="#{item.issueDate}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DueDate"/>
                        </f:facet>
                        <h:outputText value="#{item.dueDate}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Course"/>
                        </f:facet>
                        <h:outputText value="#{item.course}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="PossibleMark"/>
                        </f:facet>
                        <h:outputText value="#{item.possibleMark}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Open"/>
                        </f:facet>
                        <h:outputText value="#{item.open}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DisplayText"/>
                        </f:facet>
                        <h:outputText value="#{item.displayText}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Id"/>
                        </f:facet>
                        <h:outputText value="#{item.id}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="CreatedOn"/>
                        </f:facet>
                        <h:outputText value="#{item.createdOn}">
                            <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="ModifiedOn"/>
                        </f:facet>
                        <h:outputText value="#{item.modifiedOn}">
                            <f:convertDateTime pattern="MM/dd/yyyy HH:mm:ss" />
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="DisplayText"/>
                        </f:facet>
                        <h:outputText value="#{item.displayText}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText escape="false" value="&nbsp;"/>
                        </f:facet>
                        <h:commandLink value="Show" action="#{assignment.detailSetup}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][assignment.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Edit" action="#{assignment.editSetup}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][assignment.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                        <h:outputText value=" "/>
                        <h:commandLink value="Destroy" action="#{assignment.remove}">
                            <f:param name="jsfcrud.currentAssignment" value="#{jsfcrud_class['za.co.merits.view.ass.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][assignment.converter].jsfcrud_invoke}"/>
                        </h:commandLink>
                    </h:column>

                </h:dataTable>
            </h:panelGroup>
            <br />
            <h:commandLink action="#{assignment.createSetup}" value="New Assignment"/>
            <br />
            <br />
            <h:commandLink value="Index" action="welcome" immediate="true" />


        </h:form>
        </body>
    </html>
</f:view>
