<%@ page contentType="text/html; charset=UTF-8" %>

<title>Novo professor</title>

<ul class="breadcrumb breadcrumb-wrapper">
       <li><a href="${pageContext.request.contextPath}/professor">Professor</a> <span class="divider">/</span></li>
   	<li class="active">Novo</li>
</ul>

<fieldset>
	<legend>Nova professor</legend>
	<%@include file="form.jsp"%>
</fieldset>	
