<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="pageTitle" value="Model edit" scope="application" />
<t:wrapper>
	<c:choose>
		<c:when test="${empty dto.id}">
			<h1>Create model</h1>
		</c:when>
		<c:otherwise>
			<h1>Edit model #${dto.id}</h1>
		</c:otherwise>
	</c:choose>
	<form class="col s12" method="post" action="/model">
		<div class="row">
			<input type="hidden" name="id" value="${dto.id}" />
			<div class="row">
				<div class="input-field col s12">  
					<input type="text" name="name" value="${dto.name}" ${empty dto.id ? '' : 'disabled'} > <label for="name">Name</label>
				</div>
			</div>
			<div class="row">
				<div class="col s6">
					<label for="brandId">Brand ID</label> 
					<select name="brandId" class="browser-default" required>
						<option value="">--select model brand--</option>
						<c:forEach items="${allBrands}" var="brand">
							<option value="${brand.id}" <c:if test="${brand.id eq dto.brandId}">selected="selected"</c:if>>${brand.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s12 input-field center-align">
				<a class="btn waves-effect waves-light red" href="/model"><i class="material-icons left">list</i>back</a>&nbsp;
				<button class="btn waves-effect waves-light" type="submit">
					<i class="material-icons left">save</i>save
				</button>
			</div>
		</div>
	</form>
</t:wrapper>
