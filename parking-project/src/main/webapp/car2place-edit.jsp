<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Car2Place edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create car to place</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit car to place #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/car2place">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="col s6">
					<label for="placeId">Place</label> 
					<select name="placeId" class="browser-default" required>
						<option value="">--select place--</option>
						<c:forEach items="${allPlaces}" var="place">
							<option value="${place.id}" <c:if test="${place.id eq dto.placeId}">selected="selected"</c:if>>${place.id}</option>
						</c:forEach>
					</select>
                    <label for="carId">Car</label> 
					<select name="carId" class="browser-default" required>
						<option value="">--select car--</option>
						<c:forEach items="${allCars}" var="car">
							<option value="${car.id}" <c:if test="${car.id eq dto.carId}">selected="selected"</c:if>>${car.id}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/car2place"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>

