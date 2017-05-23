
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/PHPCommandIPoC/static/bootstrap.min.css">
<link rel="shortcut icon" href="/PHPCommandIPoC/static/favi.ico"/>


<style type="text/css">
	
body{
    background: url("/PHPCommandIPoC/static/string.jpg")no-repeat center center fixed; 
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover
    
}
.vertical-offset-100{
    padding-top:100px;
}
</style>

<title>PHPCommandI</title>


<div class="container">
    <div class="row vertical-offset-100">
    	<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" role="form" method="post" action="commandI.php">
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




<?php



if (isset($_POST['emailFrom']) and isset($_POST['emailTo']) and isset($_POST['body']) and !strcmp($_POST['submit'],'Command Injection')){

	$emailTo = $_POST['emailTo'];

	$emailFrom = $_POST['emailFrom'];

	$body = $_POST['body'];




//Command injection vulnerability, pick up your method!
//CommandI are possible when we pass a user input String without any kind of control to a bash/sh/cmd. These softwares detect some symbols as ";|&." in a special way, so we can trick it to run more commands as the one is supposed to be executed.

//Payload1 used here!

//Popen call pipe to the target process, is totally Command Injectable
//popen("/usr/sbin/sendmail -f".$emailFrom,"r");

//Same as Popen!
//shell_exec("/usr/sbin/sendmail -f".$emailFrom);

//Same as Popen!
//passthru("/usr/sbin/sendmail -f".$emailFrom);

//Same as Popen!
exec("/usr/sbin/sendmail -f".$emailFrom);

//Same as Popen!
//system("/usr/sbin/sendmail -f".$emailFrom);

//Same as popen! Litle more conf params, but can happen.
//$descriptorspec = array(
//   0 => array("pipe", "r"),  // stdin is a pipe that the child will read from
//   1 => array("pipe", "w"),  // stdout is a pipe that the child will write to
//   2 => array("file", "/tmp/error-output.txt", "a") // stderr is a file to write to
//);
//$cwd = '/tmp';
//$env = array('some_option' => 'aeiou');
//proc_open("/usr/sbin/sendmail -f".$emailFrom, $descriptorspec, $pipes, $cwd, $env);

//Same as popen. Funny backticks lol
//`/usr/sbin/sendmail -f{$emailto}`;

//Actually only working in CGI and PHP-CLI directly
//pcntl_exec("/usr/sbin/sendmail -f".$emailFrom);

}elseif (isset($_POST['emailFrom']) and isset($_POST['emailTo']) and isset($_POST['body']) and !strcmp($_POST['submit'],'Argument Injection')) {
	
	$emailTo = $_POST['emailTo'];

	$emailFrom = escapeshellcmd($_POST['emailFrom']);

	$body = $_POST['body'];




//Argument Injection: Ability to add extra arguments to called binary.The escapeshellcmd() will avoid us to break out of the string intepreted by bash/sh/cmd, but we will be able to inject extra parameters to the called process! We can play with this using "commanddebugger" so we discover how we want our arguments to be passed to the called binary.Changing "commanddebugger" with target binary will help us to discover the right exploit.
//Note1: Argument Injection could be difficult to exploit, since it depends more on target binary. Particularly,when escapeshellcmd() is used,we cannot use ' or " since they are totally escaped. In these last ocasions we need to play we target binary properties.

//Payload2 used here!

popen("/usr/sbin/sendmail -f".$emailFrom,"r");

//We can try using the rest of dangerous functions here!
}else{


	$emailTo = $_POST['emailTo'];

	$emailFrom = escapeshellarg($_POST['emailFrom']);

	$body = $_POST['body'];




//In theory safe. escapeshellarg() will set the string as inside quotes without the possiblity to break out of it.
//We can change our sendmail binary with "commanddebugger" to see that this time we are not able to break out of the -f passed argument!
popen("/usr/sbin/sendmail -f".$emailFrom,"r");


//We can try using the rest of dangerous functions here!

}


?>
