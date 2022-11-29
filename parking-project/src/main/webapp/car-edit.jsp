<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Car edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create car</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit car #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/car">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="vin" value="${dto.vin}" ${empty dto.id ? '' : 'disabled'} > <label for="vin">VIN</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<input type="text" name="ownerId" value="${dto.ownerId}"> <label for="ownerId">Owner ID</label>
				</div>
				<div class="col s6">
					<label for="modelId">Model ID</label> 
					<select name="modelId" class="browser-default" required>
						<option value="">--select car model--</option>
						<c:forEach items="${allModels}" var="model">
							<option value="${model.id}" <c:if test="${model.id eq dto.modelId}">selected="selected"</c:if>>${model.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/car"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
