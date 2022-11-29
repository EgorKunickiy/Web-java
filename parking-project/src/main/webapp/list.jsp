<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:set var="pageTitle" value="Cars list" scope="application"/>
<t:wrapper>
		<h1>Items list</h1>
		
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Сar model</th>
					<th>Parking place</th>
					<th>actions</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Alvin</td>
					<td>BMW</td>
					<td>12</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
				<tr>
					<td>Alan</td>
					<td>Honda</td>
					<td>11</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
				<tr>
					<td>Jonathan</td>
					<td>Toyota</td>
					<td>13</td>
					<td><a class="btn-small btn-floating waves-effect waves-light blue" title="редактировать" href="edit.jsp"><i class="material-icons">edit</i></a></td>
				</tr>
			</tbody>
		</table>

	<div class="row">
			<div class="col s12">
				<div class="center-align">
					<a class="btn-floating btn-large waves-effect waves-light" href="edit.jsp"><i class="material-icons">add</i></a>
				</div>
			</div>
		</div>

	</t:wrapper>