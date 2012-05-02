<tags:layout title="Edit ${it.id}">
<jsp:attribute name="js">
	<script>
	$('.delete').click(function() {
		$.ajax({
			url: location.href,
			type: 'DELETE',
			success: function() {
				location.href = "/";
			}
		});
		return false;
	});
	</script>
</jsp:attribute>

<jsp:body>
	<h1 class="page-header">Edit entity ${it.id}</h1>
	<form method="post" action="${requestScope['javax.servlet.forward.request_uri']}" class="span5">
        <p>This is what this entity looks like in the datastore:
        <pre>${entity}</pre>

		<label>
			Name
			<input name="name" type="text" value="${it.name}" class="span4">
		</label>
		<label>
			Description
			<textarea name="description" class="span4" rows="4">${it.description}</textarea>
		</label>
		<p><b>Extra</b>: ${it.extra.data1}, ${it.extra.data2}
		<p>
			<a href="/" class="btn">Cancel</button></a>
			<button class="btn btn-danger delete">Delete</button>
			<input class="btn btn-primary" type="submit"> 
		
	</form>
	<aside class="alert alert-block span6">
		<h3>What you should be seeing here:</h3>
		<p><b>JSON storage</b>: The ExtraData attribute is stored as JSON.
		
		<p><b>404 errors</b>: Users.get() is not handling the exception when the key is not found. This is centralized in ExceptionManager.java.
		
		<p><b>Short property names</b>: This entity is using short property names to save space.
		
		<p><b>See the implementation</b>:
		<ul>
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/war/WEB-INF/jsp/users/edit.jsp">edit.jsp</a>
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/model/User.java">User (model class)</a>
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/model/Attrs.java">Attrs (attribute names)</a>
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/action/Users.java">Users.get(), Users.post()</a> 
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/config/MyExceptionMapper.java">MyExceptionMapper</a> 
			<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/service/impl/UsersServiceImpl.java">UsersServiceImpl.get(), UsersServiceImpl.save()</a>
		</ul>
	</aside>
</jsp:body>

</tags:layout>