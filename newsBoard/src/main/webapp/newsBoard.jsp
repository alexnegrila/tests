<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-ui.min.css" />"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$( "#dialog-message" ).hide();

		$('#ibutton').click(function(e) {
			e.preventDefault();

			var ajaxdata = '';
			$("input:checkbox[name='likedNews']").each(function() {
				if ($(this).prop("checked")) {
					ajaxdata += $(this).val() + ',';
				}
			});
			var value = 'likedNews=' + ajaxdata;

			$.ajax({
				url : "/newsBoard/registerVotes",
				//type: "post",
				data : value,
				cache : false,
				success : function(data) {
					$( "#dialog-message" ).dialog({
					      modal: true,
					      
					    });
					/* alert('Votes registered'); */
				}
			});
		});
	});
</script>
<html>
<body>
	<div id="dialog-message" title="Votes registered">
	  <p>
	    <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
	    
	  </p>
	  
	</div>
	<h2>${feed.description}</h2>
	<form action="/newsBoard/registerVotes" method="POST" target="_blank">
		<ul>
			<c:forEach items="${feed.messages}" var="message">
				<li><input type="checkbox" name="likedNews"
					value="${message.guid}" /> <a href="${message.link}"
					target="_blank"> <img alt="" src="${message.thumbnail}">
						${message.title}
				</a> <span style="color: grey; font-size: 9px;">${message.pubDate}</span>
					<p>${message.description}</p></li>
			</c:forEach>
		</ul>
		<input type="button" id="ibutton" value="Vote" />
	</form>
</body>
</html>
