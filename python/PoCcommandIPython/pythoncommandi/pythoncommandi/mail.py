from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import render
from django.template import loader
import os
import subprocess
import pipes

#Never leave without csrf-token any function, this is a POC!
@csrf_exempt
def index(request):


	if request.method == 'GET':
		
		return render(request,'mail.html')

	elif request.method == 'POST':

		emailTo = request.POST.get('emailTo')
		emailFrom = request.POST.get('emailFrom')
		body = request.POST.get('body')
		submit = request.POST.get('submit')

		if (not(emailTo == "") and not(emailFrom == "") and not(body == "") and (submit == 'Command Injection')):

			payload = "/usr/sbin/sendmail -f" + emailFrom

			#Three ways to invoke commands in Python
			#os.system(payload)
			os.popen(payload)
			#subprocess.call(payload,shell=True)
			

		elif (not(emailTo == "") and not(emailFrom == "") and not(body == "") and (submit == 'Argument Injection')):

			#A classic Argument Injection using "safe" function as subprocess.call with 'shell=False'
			emailFromQuoted = pipes.quote(emailFrom)
			command = "/usr/sbin/sendmail -f" + emailFromQuoted
			payload = command.split()
			subprocess.call(payload,shell=False)


		elif (not(emailTo == "") and not(emailFrom == "") and not(body == "") and (submit == '99% Safe?')):

			#Now that a tokenization of input string is not performed, we are 99% safe.
			#shlex.split() in Python 3.x
			emailFromQuoted = pipes.quote(emailFrom)
			payload = "/usr/sbin/sendmail -f" + emailFromQuoted
			os.system(payload)
			#subprocess.call(["/usr/sbin/sendmail","-f",emailFrom],shell=False)

		return render(request,'mail.html')

	else:
		return HttpResponse("NOTFOUND")





