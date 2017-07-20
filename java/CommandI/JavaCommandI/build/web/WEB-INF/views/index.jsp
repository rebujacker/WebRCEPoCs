<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<c:url value="/resources/bootstrap.min.css"/>" />
<link rel="shortcut icon" href="<c:url value="/resources/favi.ico"/>"/>


<style type="text/css">
	
body{
    background: url(<c:url value="/resources/java.jpg"/>)no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover
    
}
.vertical-offset-100{
    padding-top:100px;
}
</style>


<title>JavaCommandI&ArgumentI</title>


<div class="container">
    <div class="row vertical-offset-100">
    	<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" role="form" method="post" action="/JavaCommandI/mail">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">EmailTo</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="emailTo" name="emailTo" placeholder="example@domain.com" value="">
				</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">EmailFrom</label>
					<div class="col-sm-10">
					<input type="text" class="form-control" id="emailFrom" name="emailFrom" placeholder="example@domain.com" value="">
					</div>
				</div>
				<div class="form-group">
					<label for="message" class="col-sm-2 control-label">Message</label>
					<div class="col-sm-10">
					<textarea class="form-control" rows="4" name="body"></textarea>
					</div>
				</div>
				<div class="form-group">
				<div class="col-sm-10 col-sm-offset-2">
					<input id="submit" name="submit" type="submit" value="Command Injection" class="btn btn-primary">
					<input id="submit" name="submit" type="submit" value="Argument Injection" class="btn btn-primary">
					<input id="submit" name="submit" type="submit" value="99% Safe?" class="btn btn-primary">
				</div>
				</div>

		</form>
		</div>
	</div>
</div>