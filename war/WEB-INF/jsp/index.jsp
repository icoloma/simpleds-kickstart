<tags:layout title="Hello worlders">

<jsp:attribute name="js">
    <script src="/js/jquery.endlessScroll.js"></script>
    <script>
    var 
        
        // true if there is a fetch in progress
        fetchInProgress,
        
        // the last cursor value returned by the server
        cursor,
        
        firstPage = true,

        $tbody = $('.userList > tbody');
        
    $(document).endlessScroll({
        bottomPixels: 200,
        callInProcess: function() {
            return fetchInProgress;
        },
        callback: function() {
            fetchInProcess = true;
            $.ajax({
                url: '/users',
                type: 'get',
                dataType: 'json',
                data: { 
                    includeDeleted: $('#includeDeleted:checked').length? true : undefined,
                    cursor: cursor
                },
                /**
                    @param {object[]} result.data this page of results
                    @param {string} result.cursor the cursor to get the next page
                */
                success: function(result) {
                    cursor = result.cursor;
                    firstPage = false;
                    $.each(result.data, function(index, item) {
                      $tbody.append(
                        '<tr>' +
                          '<td><a href="/users/edit/' + item.id + '">' + item.name + '</a></td>' +
                          '<td>' + item.deleted + '</td>' +
                        '</tr>'
                      );
                    });
                    fetchInProcess = false;
                }
            });
            return cursor || firstPage;
        }
    });
    </script>
</jsp:attribute>

<jsp:body>
    <h1>List of helloworlders</h1>
    
    <div class="subnav subnav-fixed">
      <ul class="nav nav-pills">
        <li><a href="/users/edit/">Create new</a></li>
      </ul>
    </div>
    <form class="well form-inline">
      <label class="checkbox">
          <input type="checkbox" id="includeDeleted"> Include deleted users
      </label>
      <button class="btn refresh">Refresh</button>
    </form>
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
</jsp:body>

</tags:layout>