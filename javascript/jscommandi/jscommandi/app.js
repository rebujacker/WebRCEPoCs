var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var path = require("path");
var exec = require('child_process').exec;
var spawn = require('child_process').spawn;
var execFile = require('child_process').execFile;

app.use(bodyParser.json()); 
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(__dirname + '/public'));

app.get('/', function (req, res) {
  res.sendFile(path.join(__dirname+'/views/mail.html'));
});

app.post('/', function(req, res) {
    var emailTo = req.body.emailTo;
    var emailFrom = req.body.emailFrom;
    var body = req.body.body;
    var submit = req.body.submit;

    if(submit == 'Command Injection'){

    	var payload = "/usr/sbin/sendmail -f" + emailFrom

    	//We have other functions inside 'child_process' itself...,other libraries like 'shelljs' ,etc
		exec(payload, function(error, stdout, stderr) {});



    }else if(submit == 'Argument Injection'){

    	//Tokenization of user input happens and Argument Injection could happen
    	var tokenizedPayload = emailFrom.split(" ");
    	var tokenizedPayload2 = tokenizedPayload.splice(0,0,"-f");

    	var child = spawn('/usr/sbin/sendmail', tokenizedPayload);
    }else{


    	var child = spawn('/usr/sbin/sendmail', ['-f', emailFrom]);

    }




    res.sendFile(path.join(__dirname+'/views/mail.html'));
});


app.listen(3000, function () {
  console.log('Example app listening on port 3000!');
});


