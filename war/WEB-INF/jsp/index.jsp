<tags:layout title="Hello world!">

<jsp:attribute name="js">
    <script src="/js/jquery.endlessScroll.js"></script>
    <script>
    var 
        
        // the last cursor value returned by the server
        cursor,

        $tbody = $('.userList > tbody');
    var bindScroll = function(e) {
    	e && e.preventDefault(); 
    	$tbody.html('');
	    $(document).endlessScroll({
	        bottomPixels: 100,
	        unbindListeners: true,
	        callback: function(data) {
	            $.ajax({
	                url: '/users',
	                type: 'get',
	                dataType: 'json',
	                data: { 
	                    includeDeleted: $('#includeDeleted:checked').length? true : undefined,
	                    cursor: cursor || undefined
	                },
	                /**
	                    @param {object[]} result.data this page of results
	                    @param {string} result.cursor the cursor to get the next page
	                */
	                success: function(result) {
	                    cursor = result.cursor;
	                    $.each(result.data, function(index, item) {
	                      $tbody.append(
	                        '<tr class="' + (item.deleted? 'deleted' : '') + '">' +
	                          '<td><a href="/users/' + item.id + '">' + item.name + '</a></td>' +
	                          '<td>' + item.deleted + '</td>' +
	                        '</tr>'
	                      );
	                    });
	                    data.callInProgress = false;
	                    data.eof = !result.cursor;
	                }
	            });
	        }
	    });
	};
	bindScroll();
	$('.refresh').click(bindScroll);
    </script>
</jsp:attribute>

<jsp:body>
    <h1 class="page-header">An example of Java development on AppEngine</h1>
    <p>This is a demo application that demostrates a combination of Guice, Jersey and SimpleDS on AppEngine
    
    <form class="well form-inline">
      <a href="/users/reset" class="btn btn-danger" style="float:right">Reset to datastore defaults</a>
      <label class="checkbox">
          <input type="checkbox" id="includeDeleted"> Include deleted users
      </label>
      <button class="btn refresh">Refresh</button>
    </form>
    <div class="row">
	    <section class="span6">
		    <table class="table userList">
		      <thead>
		      <tr>
		        <th>Name
		        <th>Deleted
		      </tr>
		      </thead>
		      <tbody>
		      </tbody>
		    </table>
		</section>
		<aside class="alert alert-block span5">
			<h3>What you should be seeing here:</h3>
			<p><b>CursorList</b>:
			This page is using an example of endless page scroll powered by JSON CursorLists.
			Notice that searching for deleted instances is optional; to avoid creating extra 
			indexes, you can filter results using a Predicate instead of a search clause.
			<p><b>See the implementation</b>:
			<ul>
				<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/war/WEB-INF/jsp/index.jsp">index.jsp</a>
				<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/action/Users.java">Users.list()</a> 
				<li><a href="https://github.com/icoloma/simpleds-kickstart/blob/master/src/main/java/com/acme/service/impl/UsersServiceImpl.java">UsersServiceImpl.find()</a>
			</ul>
		</aside>
	</div>
</jsp:body>

</tags:layout>