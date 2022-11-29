<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="pageTitle" value="Cars list" scope="application"/>
<t:wrapper>
		<h1>Edit Item</h1>

		<div class="row">
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">account_circle</i>
						<input id="first_name" type="text" class="validate"> <label for="first_name">First Name</label>
					</div>
					<div class="input-field col s6">
						<input id="last_name" type="text" class="validate"> <label for="last_name">Last Name</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">directions_car</i>
						<input id="car_model" type="text" class="validate"> <label for="car_model">Car Model</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">local_parking</i>
						<input id="place" type="number" class="validate"> <label for="place">Place</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">call_end</i>
						<input id="phone" type="text" class="validate"> <label for="phone">Phone Number</label>
					</div>
				</div>
				<div class="row">
					<div class="input-field col s6">
						<i class="material-icons prefix">email</i>
						<input id="email" type="email" class="validate"> <label for="email">Email</label>
					</div>
				</div>
				
				
			</form>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="list.jsp"><i class="material-icons left">list</i>к списку</a> <a class="btn waves-effect waves-light green"
					href="#"><i class="material-icons left">save</i>Сохранить</a>
			</div>
		</div>
	
</t:wrapper>>