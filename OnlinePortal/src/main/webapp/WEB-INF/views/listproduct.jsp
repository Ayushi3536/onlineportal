<div class = "container">

<div class = "row">
<div class="col-md-3">

			<%@include file="./shared/sidebar.jsp"%>

</div>
<div class = "col-md-9">
<!-- Added breadcrumb component -->
<div class = "row">
<div class = "col-lg-12">
</br>
<c:if test="${LoadAllProducts==true  }">
<script>
window.categoryId ='';
</script>
<ol class = "breadcrumb">
<li><a href="${contextRoot}/home">Home/</a>
<li class ="active">All products/</li>
</ol>
</c:if>
<c:if test="${LoadclickProducts==true  }">
<script>
window.categoryId ='${category.id}';
</script>
<ol class = "breadcrumb">
<li><a href="${contextRoot }/home">Home/</a></li>
<li class ="active">Category/</li>
<li class= "active">${category.name}/</li>
</ol>
</c:if>
<table id="productListTable" class="table table-striped table-bordered">

<thead>
<tr>
<th></th>
<th>name</th>
<th>brand</th>
<th>price</th>
<th>Qty. available</th>
<th></th>
</tr>
</thead>
<tfoot>
<tr>
<th></th>
<th>name</th>
<th>brand</th>
<th>price</th>
<th>Qty. available</th>
<th></th>
</tr>
</tfoot>
</table>
</div>
</div>
</div>
</div>
</div>