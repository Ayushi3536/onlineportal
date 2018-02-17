<div class = "container">

<div class = "row">
<div class = "col-md-3"></div>
<%@include file ="./shared/sidebar.jsp" %>
<div class = "col-md-9">
<!-- Added breadcrumb component -->
<div class = "row">
<div class = "col-lg-12">
<c:if test="${LoadAllProducts==true  }">
<ol class = "breadcrumb">
<li><a href="${contextRoot }/home">Home</a>
<li class ="active">All products</li>
</ol>
</c:if>
<c:if test="${LoadclickProducts==true  }">
<ol class = "breadcrumb">
<li><a href="${contextRoot }/home">Home</a></li>
<li class ="active">Category</li>
<li class= "active">${category.name }</li>
</ol>
</c:if>

</div>
</div>
</div>
</div>
</div>